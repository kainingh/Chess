
import java.util.ArrayList;



public class Pawn extends Piece{

	static public ArrayList<Move> getMoves(char[][] config, int i,int j)
	{
		ArrayList<Move> result = new ArrayList<Move>();
		char ch = config[i][j],tempch;
		int ori;
		int value;
		
		//transform, not finished
		if((Character.isLowerCase(ch) && i == 7)||(Character.isUpperCase(ch) && i == 2)){ 
			return result;
		}
		
		ori=Character.isLowerCase(ch)?1:-1;

		// move ahead 
		tempch=config[i+ori][j];
		if(tempch=='0'){  
			value = getPieceValue(tempch);
			result.add(new Move(i,j,i+ori,j,value));
		}
		
		// black first move
		if(Character.isLowerCase(ch) && i == 2){ 
			tempch=config[i+2][j];
			if(tempch=='0'){ 
				value = getPieceValue(tempch);
				result.add(new Move(i,j,i+2,j,value));
			}
		}
		// white first move
		if(Character.isUpperCase(ch) && i == 7){ 
			tempch=config[i-2][j];
			if(tempch=='0'){  
				value = getPieceValue(tempch);
				result.add(new Move(i,j,i-2,j,value));
			}
		}
		
		// take opposite piece
		if(j>1){
			tempch=config[i+ori][j-1];
			if(!isSameSide(ch,tempch)&&tempch!='0'){ // opposite piece 
				value = getPieceValue(tempch);
				result.add(new Move(i,j,i+ori,j-1,value));
			}
		}
		if(j<8){
			tempch=config[i+ori][j+1];
			if(!isSameSide(ch,tempch)&&tempch!='0'){ // opposite piece 
				value = getPieceValue(tempch);
				result.add(new Move(i,j,i+ori,j+1,value));
			}
		}
		return result;
	}
}

