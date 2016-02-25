

import java.util.*;




public class Queen extends Piece{
		
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
		/*
		for(int p=1;p<9;p++)
		{
			for(int q=1;q<9;q++)
				System.out.print(config[p][q]);
			System.out.println();
		}*/
		
		
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
		// north-west direction
		tempi=i;
		tempj=j;
		while((--tempi) >= 1&&(--tempj)>= 1){
			if(isSameSide(ch,config[tempi][tempj]))
				break;
			value = getPieceValue(config[tempi][tempj]);
			result.add(new Move(i,j,tempi,tempj,value));
			if(value != 0)
				break;
		}
		// south-east direction
		tempi=i;
		tempj=j;
		while((++tempi) <= 8 && (++tempj) <= 8){
			if(isSameSide(ch,config[tempi][tempj]))
				break;
			value = getPieceValue(config[tempi][tempj]);
			result.add(new Move(i,j,tempi,tempj,value));
			if(value != 0)
				break;
		}
		// south-west direction
		tempi=i;
		tempj=j;
		while((++tempi) <= 8&&(--tempj)>= 1){
			if(isSameSide(ch,config[tempi][tempj]))
				break;
			value = getPieceValue(config[tempi][tempj]);
			result.add(new Move(i,j,tempi,tempj,value));
			if(value != 0)
				break;
		}
		// north-east direction
		tempi=i;
		tempj=j;
		while((--tempi) >= 1&&(++tempj)<= 8){
			if(isSameSide(ch,config[tempi][tempj]))
				break;
			value = getPieceValue(config[tempi][tempj]);
			result.add(new Move(i,j,tempi,tempj,value));
			if(value != 0)
				break;
		}
		return result;
	}
	

}
