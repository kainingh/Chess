

import java.util.ArrayList;

class Node{
	   
	protected Move move;
	protected int depth;
	protected ArrayList<Node> child;
	protected int value;
	protected int order;
	
	public Node () {
		child = new ArrayList<Node>();
		move = null;
		depth = 0;
		value = 0;	
		order = 0;
	}
	
	public Node(Move move){
		child = new ArrayList<Node>();
		this.move = move;
		depth = 0;
		value = 0;
		order = 0;
	}	
		
}
