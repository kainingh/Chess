

import java.util.*;


public class Knight extends Piece{
	static public ArrayList<Move> getMoves(char[][] config, int i,int j)
	{
		ArrayList<Move> result = new ArrayList<Move>();
		char ch = config[i][j];
		int tempi, tempj, value;
		
		// north-west direction, vertical jump
		tempi = i-1;
		tempj = j-2;
		if (tempi>=1 && tempj>=1){
			if (!isSameSide(ch,config[tempi][tempj])) {
			value = getPieceValue(config[tempi][tempj]);
			result.add(new Move(i,j,tempi,tempj,value));
			}
		}
		
		// north-west direction, horizontal jump
		tempi = i-2;
		tempj = j-1;
		if (tempi>=1 && tempj>=1){
			if (!isSameSide(ch,config[tempi][tempj])) {
			value = getPieceValue(config[tempi][tempj]);
			result.add(new Move(i,j,tempi,tempj,value));
			}
		}
		
		// north-east direction, vertical jump
		tempi = i+1;
		tempj = j-2;
		if (tempi<=8 && tempj>=1){
			if (!isSameSide(ch,config[tempi][tempj])) {
			value = getPieceValue(config[tempi][tempj]);
			result.add(new Move(i,j,tempi,tempj,value));	
			}
		}
		
		// north-east direction, horizontal jump
		tempi = i+2;
		tempj = j-1;
		if (tempi<=8 && tempj>=1){
			if (!isSameSide(ch,config[tempi][tempj])) {
			value = getPieceValue(config[tempi][tempj]);
			result.add(new Move(i,j,tempi,tempj,value));
			}
		}
		
		// south-west direction, vertical jump
		tempi = i-1;
		tempj = j+2;
		if (tempi>=1 && tempj<=8){
			if (!isSameSide(ch,config[tempi][tempj])) {
			value = getPieceValue(config[tempi][tempj]);
			result.add(new Move(i,j,tempi,tempj,value));	
			}
		}
		
		// south-west direction, horizontal jump
		tempi = i-2;
		tempj = j+1;
		if (tempi>=1 && tempj<=8){
			if (!isSameSide(ch,config[tempi][tempj])) {
			value = getPieceValue(config[tempi][tempj]);
			result.add(new Move(i,j,tempi,tempj,value));	
			}
		}
		
		// south-east direction, vertical jump
		tempi = i+1;
		tempj = j+2;
		if (tempi<=8 && tempj<=8){
			if (!isSameSide(ch,config[tempi][tempj])) {
			value = getPieceValue(config[tempi][tempj]);
			result.add(new Move(i,j,tempi,tempj,value));	
			}
		}
		
		// south-east direction, horizontal jump
		tempi = i+2;
		tempj = j+1;
		if (tempi<=8 && tempj<=8){
			if (!isSameSide(ch,config[tempi][tempj])) {
			value = getPieceValue(config[tempi][tempj]);
			result.add(new Move(i,j,tempi,tempj,value));	
			}
		}
				
		return result;
	}
}
