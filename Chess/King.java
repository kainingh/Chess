

import java.util.*;


public class King extends Piece{
	static public ArrayList<Move> getMoves(char[][] config, int i,int j)
	{
		// eight possible positions for the King
		int [][] deltaij ={{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}}; 
		ArrayList<Move> result = new ArrayList<Move>();
		char ch = config[i][j];
		int tempi,tempj,value;
		 //evaluate the value of the eight positions
		for (int k=0; k <= 7; k++){ 
			tempi=i+deltaij[k][0];
			tempj=j+deltaij[k][1];
			if (tempi >= 1 && tempi <= 8 &&	tempj >= 1 && tempj <= 8 )
					if(isSameSide(ch,config[tempi][tempj]))
						continue;   
					else{
						value = getPieceValue(config[tempi][tempj]);
			            result.add(new Move(i,j,tempi,tempj,value));
					}	
			else
				continue;
		}
		return result;
	}
}
