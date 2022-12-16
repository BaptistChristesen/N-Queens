import java.awt.Color;

import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

public class Queen extends Rock
{
    private static int numQueensInGrid = 0;

    public String toString()
    {
        return "Queen";
    }

    @Override
    public void putSelfInGrid(Grid<Actor> gr, Location loc)
    {
        super.putSelfInGrid(gr, loc);
        numQueensInGrid++;
    }

    @Override
    public void removeSelfFromGrid()
    {
        super.removeSelfFromGrid();
         numQueensInGrid--;
       }

    public static int getNumQueensInGrid()
    {
        return numQueensInGrid;
    }
}
