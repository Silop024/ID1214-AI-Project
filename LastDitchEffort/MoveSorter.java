package connect4;

public class MoveSorter 
{
	private int size;
	//private int[] entries;
	
	Entries[] entries = new Entries[7];
	
	
	private class Entries
	{
		int move;
		int score;
	}
	
	public MoveSorter()
	{
		for(int i = 0; i < 7; i++)
		{
			this.entries[i] = new Entries();
		}
		for(int i = 0; i < 7; i++)
			
		{
			this.entries[i].move = 0;
			this.entries[i].score = 0;
		}
		this.size = 0;
	}
	
	public void add(int move, int score)
	{
		int position = size++;
		
		for(; position != 0 && entries[position - 1].score > score; --position) 
			entries[position] = entries[position - 1];
		entries[position].move = move;
		entries[position].score = score;
	}
	
	public int getNext()
	{
		if(size != 0)
			return entries[--size].move;
		else
			return 0;
	}
	
	public int getSize()
	{
		return size;
	}
}
