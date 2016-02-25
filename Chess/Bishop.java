

import java.util.*;


public class Bishop extends Piece {
	static public ArrayList<Move> getMoves(char[][] config, int i,int j)
	{
		ArrayList<Move> result = new ArrayList<Move>();
		int tempi,tempj,value;
		char self= config[i][j];
		char des;
		//left-up;
		tempi=i;
		tempj=j;
		while ((tempi>1)&&(tempj>1))
		{
			tempi-=1;
			tempj-=1;
			des=config[tempi][tempj];
			if  (isSameSide(self,des)==false)
			{
				value = getPieceValue(config[tempi][j]);
				result.add(new Move(i,j,tempi,tempj,value));
				if(value != 0)
					break;
			}
			else
			{
				break;
			}
		}
		//left-down
		tempi=i;
		tempj=j;
		while ((tempi<8)&&(tempj>1))
		{
			tempi+=1;
			tempj-=1;
			des=config[tempi][tempj];
			if  (isSameSide(self,des)==false)
			{
				value = getPieceValue(config[tempi][j]);
				result.add(new Move(i,j,tempi,tempj,value));
				if(value != 0)
					break;
			}
			else
			{
				break;
			}
		}
		//right-up
		tempi=i;
		tempj=j;
		while ((tempi>1)&&(tempj<8))
		{
			tempi-=1;
			tempj+=1;
			des=config[tempi][tempj];
			if  (isSameSide(self,des)==false)
			{
				value = getPieceValue(config[tempi][j]);
				result.add(new Move(i,j,tempi,tempj,value));
				if(value != 0)
					break;
			}
			else
			{
				break;
			}
		}
		//right-down
		tempi=i;
		tempj=j;
		while ((tempi<8)&&(tempj<8))
		{
			tempi+=1;
			tempj+=1;
			des=config[tempi][tempj];
			if  (isSameSide(self,des)==false)
			{
				value = getPieceValue(config[tempi][j]);
				result.add(new Move(i,j,tempi,tempj,value));
				if(value != 0)
					break;
			}
			else
			{
				break;
			}
		}
		
		return result;
	}
	
}
