public class NQueensRunner
{
    public static void main(String[] args)
    {
        int n = (int)(Math.random() * 4 + 6);
        NQueens queens = new NQueens(n);
        queens.solve();
    }
}
