/**
 *
 * Name: Kaining Huang  Andrew: kainingh
 * File: Board.java
 * Description: This is the mainBody of the 
 * chess program, I assign each piece with a value
 * By using game tree and alpha-beta pruning to 
 * predict a better movie
 *
 */
import java.io.*;
import java.util.*;




public class Board extends Node{
	
	/*config is a 9-by-9 matrix that stores the arrangement of each piece
	  index starts from 1 to 9
	  lower case represents white side, upper case represents black side
	*/
	public char[][] config;
	
	public int ply = 0; 
	
	
	/**
	 * Board should call ReadBoard class to read a configuration file
	 * ReadBoard provides static method that requires filename
	 * Example: char[][] config = ReadBoard.readFile(filename); 
	 * config is a 9-by-9 matrix that stores the arrangement of each piece 
	 * @param fileName
	 * @throws IOException 
	 */
    public Board(String fileName) throws IOException{
		config = ReadBoard.getBoard(fileName);
	};
	
	/*
	 * get rootNode
	 * @return
	 */
	public Node getRootNode() {
		return rootNode;
	}

	/**
	 * apply move :change the original character to '0' and the destination character to
	 * the original character
	 * @param move
	 * @return destination character for use of unmaking move
	 */
	public char applyMove(Move move) {
		ply = ply + 1;
		char ch = config[move.original_i][move.original_j]; //which piece calls getMoves();
		//after move
		config[move.original_i][move.original_j] = '0';
		char destination = config[move.destination_i][move.destination_j];
		config[move.destination_i][move.destination_j] = ch;
		//return destination character for use of unmaking move;
		return destination;
	}
	
	/**
	 * unmake the previous move
	 * @param move
	 * @param destination
	 */
	private void unMakeMove(Move move, char destination) {
		ply = ply - 1;
		config[move.original_i][move.original_j] = config[move.destination_i][move.destination_j];
		config[move.destination_i][move.destination_j] = destination;
	}
	
	/**
	 * calculate the total value of the current chess position, initial value equals to 0;
	 * @return
	 */
	private int configValue() {
		int sumValue = 0;
		int pieceValue = 0;
		for(int i = 1; i != 9; i++) {
			for(int j = 1; j != 9; j++) {
	  			if(config[i][j] != 0){
	  				char ch = config[i][j];
	  				pieceValue = Piece.getPieceValue(ch);
	  				if(Character.isLowerCase(ch))
	  					pieceValue = - pieceValue;
	  			}
	  			sumValue += pieceValue;
			}
		}	
		return sumValue;
	}
	/**
	 * save the config to the file configureBoard.txt
	 * @return
	 */
	
	private static void ConfigureSave(char[][] config1) throws IOException{
		File boardConfig = new File("ConfigureBoard.txt");
		if(boardConfig.exists()){

			boolean a = boardConfig.delete();
			if(a)
			System.out.print("delete Successful\r\n");
		}
		FileWriter os = new FileWriter(boardConfig,true);
		for(int i = 1; i != config1.length; i++){
			for(int j = 1; j != config1[i].length; j++){
				os.write(config1[i][j]+" ");
				}
			os.write("\r\n");
		}
		os.close();
	}
	/**
	 * traverse all the board and get all the legal moves for each piece
	 * return an ArrayList of all the moves
	 * @return 
	 */
	private ArrayList<Move> getAllMoves() {
		ArrayList<Move> moves = new ArrayList<Move>();
		for(int i = 1; i != 9; i++){
			for(int j = 1; j != 9; j++){
				ArrayList<Move> temp = new ArrayList<Move>();
			
	  			if(config[i][j] != 0){
	  				char ch = config[i][j];	
	  				if(ply % 2 == 0){
					if(ch >= 'a' && ch <= 'z')
						continue;
					
				}else{
					if(ch >= 'A' && ch <='Z' )
						continue;
				}
	  				
	  				ch = Character.toUpperCase(ch);
	  				switch (ch) {
	  				case 'P': temp = Pawn.getMoves(config,i,j); break;
	  				case 'K': temp = King.getMoves(config,i,j); break;
	  				case 'N': temp = Knight.getMoves(config, i, j); break;
	  				case 'Q': temp = Queen.getMoves(config, i, j); break;
	  				case 'R': temp = Rook.getMoves(config, i, j); break;
	  				case 'B': temp = Bishop.getMoves(config, i, j); break;
	  				default: break;
	  				}
	  			}
	  			if(temp != null)
	  				moves.addAll(temp);
			}
		}
		return moves;
	}
	
	/**
	 * insert all the legal moves as child nodes for the current position
	 * @param current
	 * @throws IOException 
	 */
    private void insertLeaf(Node current) throws IOException{
  	  
  	  ArrayList<Move> allMoves = new ArrayList<Move>();
  	  allMoves = getAllMoves();
    	Iterator<Move> iter = allMoves.iterator();
  	  while(iter.hasNext()){
  		  Node childNode = new Node(iter.next());
  		  char ch = config[childNode.move.original_i][childNode.move.original_j];
  		  int moveValue = 0;
  		  if(Character.isLowerCase(ch)) {
  			  moveValue = - childNode.move.move_value;
  		  }
  		  else {
  			 moveValue = childNode.move.move_value;
  		  }
  		  childNode.depth = current.depth + 1 ;
  		  childNode.value = current.value + moveValue;
  		  current.child.add(childNode);
  	  }
    }
  
    
    /**
     * build the chess tree recursively
     * @param current : current tree node, initial node is the root
     * @param maxDepth : maximum following steps considerd before make a move
     * @throws IOException 
     */
    public void treeBuild(Node current, int maxDepth) throws IOException {
      if(current.depth == maxDepth) {
    	  return;    	  
      }
  	  insertLeaf(current);
  	  Iterator<Node> iter = current.child.iterator();
  	  while(iter.hasNext()){
  		  Node childNode = iter.next();
  		  char ch = applyMove(childNode.move);
  	      treeBuild(childNode, maxDepth);
  	      unMakeMove(childNode.move, ch);
  	  }
    }  
    public int count = 0;
    
    public void DFSearch(Node current) throws IOException{          
    	Iterator<Node> iter = current.child.iterator();
    	while(iter.hasNext()){
    		Node current1 = iter.next();
    		FileWriter filewriter = new FileWriter("DFS.txt",true);
    		filewriter.write(current1.depth+" ");
    		filewriter.write(current1.move.original_i+" ");
    		filewriter.write(current1.move.original_j+" ");
    		filewriter.write(current1.move.destination_i+" ");
    		filewriter.write(current1.move.destination_j+" ");
    		filewriter.write(current1.value+" "+count+"\r\n");
    		filewriter.close();
    		count++;
    		DFSearch(current1);  
    	}
    	return;
    }
    
    public void BFSeasrch(Node current) throws IOException{
    	ArrayList<Node> save = new ArrayList<Node>();
    	int depth = 0;
    	save.add(current);
    	count = 0;
    	int width = save.size();
    	while(width != 0){
    		current = save.remove(0);
    		width--;
    		FileWriter filewriter = new FileWriter("BFS.txt",true);
    		filewriter.write(current.depth+" ");
    		filewriter.write(current.move.original_i+" ");
    		filewriter.write(current.move.original_j+" ");
    		filewriter.write(current.move.destination_i+" ");
    		filewriter.write(current.move.destination_j+" ");
    		filewriter.write(current.value+" "+count+"\r\n");
    		filewriter.close();
    		count++;
    		save.addAll(current.child);
    		if(width == 0){
    			width = save.size();
    			depth++;
    		}
    		
    	}
    }
    
    public void ClearFiles(){
    	File file  = new File("BFS.txt");
    	if(file.exists()){
    		System.out.println("there exists BFS");
    	boolean a = file.delete();
    	if(a)
    		System.out.println("delete success");
    	else
    		System.out.println("failed");
    	}
    	File file1  = new File("DFS.txt");
    	if(file1.exists()){
    		System.out.println("there exists DFS");
    	boolean b = file1.delete();
    	if(b)
    		System.out.println("delete success");
    	else
    		System.out.println("failed");
    	}
    	File file3  = new File("Alpha-Beta.txt");
    	if(file3.exists()){
    		System.out.println("there exists Alpha-Beta");
    	boolean c = file3.delete();
    	if(c)
    		System.out.println("delete success");
    	else
    		System.out.println("failed");
    	}
    	
    }
    private Move save = new Move(0,0,0,0,0);
	public Node alphabeta(Node current, int depth, int alpha, int beta, boolean maximizingPlayer) throws IOException {
    	if (depth == 0 || current.child.isEmpty()){
    			return current ;
    	}   	
    	if(maximizingPlayer){
    	int	v =  - Integer.MAX_VALUE;
    		Iterator<Node> iter = current.child.iterator();
    		while(iter.hasNext()){	
    			
    			current = iter.next();
    			FileWriter filewriter = new FileWriter("Alpha-Beta.txt",true);
        		filewriter.write(current.depth+" ");
        		filewriter.write(current.move.original_i+" ");
        		filewriter.write(current.move.original_j+" ");
        		filewriter.write(current.move.destination_i+" ");
        		filewriter.write(current.move.destination_j+" ");
        		filewriter.write(current.value+" "+count+"\r\n");    		
        		filewriter.close();
        		count++;
     			v = Math.max(v , alphabeta(current, depth - 1, alpha, beta, false).value );
                current.value = v;  
     			if(v > alpha && current.depth == 1 ){
                save = current.move;
             
     			 }
     		
    			alpha = Math.max(alpha, current.value);
    			if(beta <= alpha)	break;
    		}
    		
    		return current;
    	}
    	else{
    		int v = Integer.MAX_VALUE;
    		Iterator<Node> iter = current.child.iterator();
    		while(iter.hasNext()){

    		
    			current = iter.next();
    			FileWriter filewriter = new FileWriter("Alpha-Beta.txt",true);
        		filewriter.write(current.depth+" ");
        		filewriter.write(current.move.original_i+" ");
        		filewriter.write(current.move.original_j+" ");
        		filewriter.write(current.move.destination_i+" ");
        		filewriter.write(current.move.destination_j+" ");
        		filewriter.write(current.value+" "+count+"\r\n");       		
        		filewriter.close();
        		count++;
    			v = Math.min(v, alphabeta(current, depth - 1, alpha, beta, true).value);
    	        current.value = v;
                if(v < beta && current.depth == 1){
    				   save = current.move;
    				
    			}
                
    			beta = Math.min(beta, current.value);
    			if(beta <= alpha)
    				break;
    		}
    		return current;
    	}
    }
	
	
 

	public int alphabeta(int depth, int alpha, int beta, boolean maximizingPlayer) throws IOException {
		
		if(depth == 0)
			return configValue();
		
		ArrayList<Move> allMoves = getAllMoves();
		if(allMoves == null)
			return configValue();
		
		if(maximizingPlayer) {
			int	v = -Integer.MAX_VALUE;
			Iterator<Move> it = allMoves.iterator();
    		while(it.hasNext()){
    			Move currentMove = it.next();
    			count++;
    			char c =applyMove(currentMove);	
    		    v = Math.max(v, alphabeta(depth - 1, alpha, beta,false) );
    		    unMakeMove(currentMove, c);
    		    if(v > alpha && depth == DEPTH )
    		    save = currentMove;
    		    alpha = Math.max(alpha,v);
    			if(beta <= alpha) break; //beta cut-off
    		}
    		return v;
		}
		
		else {
			int v = Integer.MAX_VALUE;
			Iterator<Move> it = allMoves.iterator();
    	     while(it.hasNext()){
    			count++;
    			char c =applyMove(currentMove);
    			  v = Math.min(v, alphabeta(depth - 1, alpha, beta, true) );
    		    unMakeMove(currentMove, c);
    		    if(v < beta && depth == DEPTH )
    		    save = currentMove;
    			beta = Math.min(beta,v);
    			if(beta <= alpha) break; //alpha cut-off
    		}
    		return v;
			
		}
	}

	Move one = new Move(4,4,4,4,4);
	private Node rootNode = new Node(one);
	public static int DEPTH = 2;
	public static void main(String[] args) throws IOException {
		int fulldepth = 8;
		Openning(fulldepth);
		System.out.println("open end");
		Middle(fulldepth);
		System.out.println("middle end");
		End(fulldepth);
	}


	private String changeConfigToString()
	{
		String result="";
		for(int i=1;i<9;i++)
		{
			for(int j=1;j<9;j++)
			{
				result+=Character.toString(config[i][j]);
			}
			result+="\r\n";
		}
		return result;
	}
	public static void End(int fulldepth) throws IOException
	{
		
		
		Random rand=new Random();
		for(int pp=0;pp<3;pp++)
		{
			FileWriter filewriter = new FileWriter("result.txt",true);
			FileWriter fileconfig = new FileWriter("config.txt",true);
			Board chessBoard = new Board("EndConfig.txt");
			int numbertoBan=pp*2;
			int numberBaned=0;
			for(int j=1;j<9;j++)
				for(int i=1;i<9;i++)
				{
					if(numberBaned<numbertoBan&&chessBoard.config[i][j]!='n' && chessBoard.config[i][j]!='N'&&rand.nextDouble()<0.5)
					{
						chessBoard.config[i][j]='0';
						numberBaned++;
					}
				}
			filewriter.write("End\r\n");
			filewriter.write("          DFS         BFS         ABP\r\n");
			fileconfig.write(chessBoard.changeConfigToString());
			fileconfig.write("-----------------------------------\r\n");
			for(int depth=2;depth<=fulldepth;depth+=2)
			{
				
				chessBoard.DEPTH = depth;
				
				
				

				filewriter.write("Plies "+depth);
				System.out.println("DFS started");
				chessBoard.count = 0;
				if(depth<=4)
				{
					chessBoard.treeBuild(chessBoard.getRootNode(),depth);
					chessBoard.DFSearch(chessBoard.getRootNode());
				}
				filewriter.write("   "+chessBoard.count+"     ");
				System.out.println("DFS finished");
				
				System.out.println("BFS started");
				chessBoard.count = 0;
//				chessBoard.BFSeasrch(chessBoard.getRootNode());
				filewriter.write("   "+chessBoard.count+"     ");
				System.out.println("BFS finished");
				
				System.out.println("ABP started");
				chessBoard.count = 0;
				chessBoard.alphabeta(depth,- Integer.MAX_VALUE, Integer.MAX_VALUE,true);
				filewriter.write("   "+chessBoard.count+"     \r\n");
				System.out.println("ABP finished");
			}
			filewriter.close();
			fileconfig.close();
		}
		
	}
	
	
	public static void Middle(int fulldepth) throws IOException
	{
		
		
		Random rand=new Random();
		for(int pp=0;pp<3;pp++)
		{
			FileWriter filewriter = new FileWriter("result.txt",true);
			FileWriter fileconfig = new FileWriter("config.txt",true);
			Board chessBoard = new Board("MiddleConfig.txt");
			int numbertoBan=2*pp;
			int numberBaned=0;
			for(int j=1;j<9;j++)
				for(int i=1;i<9;i++)
				{
					if(numberBaned<numbertoBan&&chessBoard.config[i][j]!='n' && chessBoard.config[i][j]!='N'&&rand.nextDouble()<0.4)
					{
						chessBoard.config[i][j]='0';
						numberBaned++;
					}
				}
			fileconfig.write(chessBoard.changeConfigToString());
			fileconfig.write("-----------------------------------\r\n");
			filewriter.write("Middle\r\n");
			filewriter.write("          DFS         BFS         ABP\r\n");
			
			for(int depth=2;depth<=fulldepth;depth+=2)
			{
				
				chessBoard.DEPTH = depth;
				
				
				
				
				
				filewriter.write("Plies "+depth);
				System.out.println("DFS started");
				chessBoard.count = 0;
				if(depth<=4)
				{
					chessBoard.treeBuild(chessBoard.getRootNode(),depth);
					chessBoard.DFSearch(chessBoard.getRootNode());
				}
				filewriter.write("   "+chessBoard.count+"     ");
				System.out.println("DFS finished");
				
				System.out.println("BFS started");
				chessBoard.count = 0;
				filewriter.write("   "+chessBoard.count+"     ");
				System.out.println("BFS finished");
				
				System.out.println("ABP started");
				chessBoard.count = 0;
				chessBoard.alphabeta(depth,- Integer.MAX_VALUE, Integer.MAX_VALUE,true);
				filewriter.write("   "+chessBoard.count+"     \r\n");
				System.out.println("ABP finished");
			   
			}
			filewriter.close();
			fileconfig.close();
		}
		
	}
	
	public static void Openning(int fulldepth) throws IOException
	{
		Random rand=new Random();
		for(int pp=0;pp<3;pp++)
		{
			FileWriter filewriter = new FileWriter("result.txt",true);
			FileWriter fileconfig = new FileWriter("config.txt",true);
			Board chessBoard = new Board("OpenningConfig.txt");
			int numbertoBan=2*pp;
			int numberBaned=0;
			for(int j=1;j<9;j++)
				for(int i=1;i<9;i++)
				{
					if(numberBaned<numbertoBan&&chessBoard.config[i][j]!='n' && chessBoard.config[i][j]!='N'&&rand.nextDouble()<0.2)
					{
						chessBoard.config[i][j]='0';
						numberBaned++;
					}
				}
			fileconfig.write(chessBoard.changeConfigToString());
			fileconfig.write("-----------------------------------\r\n");
			filewriter.write("Openning\r\n");
			filewriter.write("          DFS         BFS         ABP\r\n");
			for(int depth=2;depth<=fulldepth;depth+=2)
			{
				chessBoard.DEPTH = depth;
				
				filewriter.write("Plies "+depth);
				System.out.println("DFS started");
				chessBoard.count = 0;
				if(depth<=4)
				{
					chessBoard.treeBuild(chessBoard.getRootNode(),depth);
					chessBoard.DFSearch(chessBoard.getRootNode());
				}
				filewriter.write("   "+chessBoard.count+"     ");
				System.out.println("DFS finished");
				
				System.out.println("BFS started");
				chessBoard.count = 0;
				filewriter.write("   "+chessBoard.count+"     ");
				System.out.println("BFS finished");
				
				System.out.println("ABP started");
				chessBoard.count = 0;
				chessBoard.alphabeta(depth,- Integer.MAX_VALUE, Integer.MAX_VALUE,true);
				filewriter.write("   "+chessBoard.count+"     \r\n");
				System.out.println("ABP finished");
			}
			filewriter.close();
			fileconfig.close();
		}
		
	}
	
}
	