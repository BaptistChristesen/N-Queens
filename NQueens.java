/**
 * This class represents an object that solves the N Queens Problem
 * 
 * @author (your name) 
 * @version (the date)
 */

import info.gridworld.grid.Grid;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

public class NQueens
{
    private NQueensWorld world;
    private Grid<Actor> board;

    /**
     * Constructs an object that solves the N Queens Problem.
     * @param n the number of queens to be placed on an n by n board
     */
    public NQueens(int n){
        board = new BoundedGrid<Actor>(n, n);
        world = new NQueensWorld(board);
        world.show();
    }

    /**
     * Kicks off the recursion to solve the N Queens Problem.
     */
    public void solve(){
        placeQueen(0);
        
    }
    
    /**
     * Attempts to place the qth queen on the board.
     * Precondition: 0 <= q < numQueens()
     * @param q row index of next queen to place
     * @returns true if non-attacking queens were placed in all rows;
     *          false otherwise.
     *
     * This queen needs to be placed in row q.  The only question is
     * which column she will be in.  placeQueen needs to try each column
     * in turn.  Whenever it finds a column that could work, it puts the
     * queen there and then recurses to place the rest of the queens.
     */
    private boolean placeQueen(int q){
        //Location loc = new Location(0, 0);
        //addQueen(loc);
        //removeQueen(loc);
        //return true;
        if(q<board.getNumCols()){
            for(int i = 0; i < board.getNumRows(); i++){
                Location loc = new Location(q, i);
                if(locationIsOK(loc)){
                    addQueen(loc);
                    try {Thread.sleep(1000);} 
                    catch (Exception e) {}
                    if(placeQueen(q+1)){
                        return true;
                    }
                    else{
                        removeQueen(loc);
                    }
                }
            }
            return false;
        }
        return true;
    }

    /**
     * Determines whether a queen can be placed at the specified
     * location.
     * @param loc the location to test
     * @returns true if this location is not attached by any queen
     *               above it (with a smaller row).  ie there is no queen
     *               in the same column or either diagonal;
     *          false otherwise.
     *
     * locationIsOK verifies that another queen can't attack this
     * location.  Note that when this method is called, only queens
     * in previous rows have been placed.
     */
    private boolean locationIsOK(Location loc){
        int row = loc.getRow();
        int col = loc.getCol();
        int r, c;

        // check all locations directly above loc (write your code below)
        for(r = 0;r < board.getNumRows(); r++)
            if(board.get(new Location(r, col)) != null)
                return false;
        
        // check all locations up and to the right loc (this code works properly)
        for(r = row - 1, c = col + 1; r >= 0 && c < board.getNumCols(); r--, c++)
            if(board.get(new Location(r, c)) != null)
                return false;

        // check all locations up and to the left of loc (write your code below,
        // similar to the above example)
        for(r = row - 1, c = col - 1; r >= 0 && c >= 0; r--, c--)
            if(board.get(new Location(r, c)) != null)
                return false;
        // passed all the tests, so return true
        return true;
    }

    /**
     * Adds a queen at the specified location and re-shows the world.
     * Precondition: loc is valid location in grid
     * @param loc the location where the queen should be placed
     */
    private void addQueen(Location loc){        
        new Queen().putSelfInGrid(board, loc);
        world.show();
    }

    /**
     * Removes a queen at the specified location and re-shows the world.
     * Precondition: loc is valid location in grid
     * @param loc the location where the queen should be removed
     */
    private void removeQueen(Location loc){
        board.get(loc).removeSelfFromGrid();
        world.show();
    }
    
    
}
