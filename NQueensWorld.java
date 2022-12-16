import info.gridworld.grid.Grid;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;

import java.util.concurrent.Semaphore;

public class NQueensWorld extends ActorWorld
{
    private static final String DEFAULT_MESSAGE = "";

    private Semaphore lock;
 
    /**
     * Constructs an N Queen world with a default grid.
     */
    public NQueensWorld()
    {
        System.setProperty("info.gridworld.gui.selection", "hide");
        lock = new Semaphore(0);
    }

    /**
     * Constructs an N Queen world with a given grid.
     * @param grid the grid for this world.
     */
    public NQueensWorld(Grid<Actor> grid)
    {
        super(grid);
        System.setProperty("info.gridworld.gui.selection", "hide");
        lock = new Semaphore(0);
    }

    public void show()
    {
        if (getMessage() == null)
            setMessage(DEFAULT_MESSAGE);
        super.show();

        try
        {
            lock.acquire();
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(
                "Had catastrophic InterruptedException");
        }
    }

    public void step()
    {
        int gridQueens = getGrid().getOccupiedLocations().size();
        int queenQueens = Queen.getNumQueensInGrid();

        if (gridQueens == queenQueens)
        {
            lock.release();
            return;
        }

        String msg;
        if (gridQueens < queenQueens)
            msg = "At least one queen thinks it's in the grid, " +
                "but it's not.";
        else // (gridQueens > queenQueens)
            msg = "At least one queen thinks it's not in the grid, " +
                "but it is.";
        msg += " (Queens in grid: " + gridQueens + ", " +
            "Queens that think they are in the grid: " + queenQueens + ")";
        throw new IllegalStateException(msg);
    }
}