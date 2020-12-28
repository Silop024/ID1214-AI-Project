
public class TestClient 
{
	public static void main(String[] args)
	{	
		int grid[][] = {
						{1, 0, 0, 0, 0, 0},
						{1, 2, 0, 0, 0, 0},
						{0, 0, 0, 0, 0, 0},
						{1, 2, 1, 2, 0, 0},
						{2, 1, 2, 1, 0, 0},
						{0, 0, 0, 0, 0, 0},
						{0, 0, 0, 0, 0, 0}
					   };
		int columnMoves[] = {1, 2, 0, 4, 4, 0, 0};
		int moves = 11;
		
		Position p = new Position(grid, columnMoves, moves);
		
		Solver s = new Solver(p);
		
		int score = s.negamax(p, -21, 21);

		System.out.println("Score of " + score);
		
		
	}
}
