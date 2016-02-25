
import java.util.*;
public abstract class Piece {
	final public static int PAWN = 100;
	final public static int ROOK = 500;
	final public static int KNIGHT = 350;
	final public static int BISHOP = 350;
	final public static int QUEEN = 900;
	final public static int KING = Integer.MAX_VALUE/2;
	
	
	public static ArrayList<Move> getMoves(char[][] config, int i,int j){return null;}
	
	/**
	 * this function decides whether a and b are same side
	 * if both a and b are lower case return true
	 * if both a and b are upper case return true
	 * otherwise, return false
	 */
	static public boolean isSameSide(char a, char b)
	{
//		System.out.println(a+" "+b);
		if( a>='a' && a<='z' && b>='a' && b<='z')
			return true;
		if( a>='A' && a<='Z' && b>='A' && b<='Z')
			return true;
//		System.out.println("***********");
		return false;
		
	}
	

	// attention please, knight is n
	static public int getPieceValue(char ch)
	{
		int result = -1;
		ch = Character.toLowerCase(ch);
		switch (ch){
		case 'p':
			result = PAWN;
			break;
		case 'r':
			result = ROOK;
			break;
		case 'n':
			result = KNIGHT;
			break;
		case 'b':
			result = BISHOP;
			break;
		case 'q':
			result = QUEEN;
			break;
		case 'k':
			result = KING;
			break;
		case '0':
			result = 0;
			break;
		default:
				assert(1 == 0);
				break;
		}
		return result;
	}
}
