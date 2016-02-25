
import java.util.*;



public class Rook extends Piece{
	/**
	 * Attention Please!!!!!!!!!!!!!!!
	 * I have added several help functions in class Piece to help you
	 * Their meaning and usage is self-evident in Piece.java and the below function
	 * @param config
	 * @param i
	 * @param j
	 * @return
	 */
	static public ArrayList<Move> getMoves(char[][] config, int i,int j)
	{
		ArrayList<Move> result = new ArrayList<Move>();
		char ch = config[i][j];
		int tempi,tempj,value;
		// north direction
		tempi=i;
		while((--tempi)>=1){
			if(isSameSide(ch,config[tempi][j]))
				break;
			value = getPieceValue(config[tempi][j]);
			result.add(new Move(i,j,tempi,j,value));
			if(value != 0)
				break;
		}
		//south direction
		tempi=i;
		while((++tempi)<=8){
			if(isSameSide(ch,config[tempi][j]))
				break;
			value = getPieceValue(config[tempi][j]);
			result.add(new Move(i,j,tempi,j,value));
			if(value != 0)
				break;
		}
		//west direction
		tempj=j;
		while((--tempj)>=1){
			if(isSameSide(ch,config[i][tempj]))
				break;
			value = getPieceValue(config[i][tempj]);
			result.add(new Move(i,j,i,tempj,value));
			if(value != 0)
				break;
		}
		// east direction
		tempj=j;
		while((++tempj)<=8){
			if(isSameSide(ch,config[i][tempj]))
				break;
			value = getPieceValue(config[i][tempj]);
			result.add(new Move(i,j,i,tempj,value));
			if(value != 0)
				break;
		}

		return result;
	}
	

}