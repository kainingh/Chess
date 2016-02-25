

import java.io.*;
public class ReadBoard {
	
	public static char[][] getBoard(String filename) throws IOException{
		
		char[][]board=new char[9][9];
		String FileNameIn=filename;
		FileReader fileRead=new FileReader(FileNameIn);
		BufferedReader reader =new BufferedReader(fileRead);
		int i = 1;
		String line=null;
		board[0][0]='0';
		while((line=reader.readLine())!=null)
		{
			int j=1;
///			System.out.println("*"+line+"*");
			for(int k=0;k <line.length();k++)
			{
				if (Character.isLetter(line.charAt(k)) || line.charAt(k)=='0')
				{
					board[i][j]=line.charAt(k);
					
				//	System.out.print(line.charAt(k));
					j+=1;
				}
			}
			//System.out.println();
			i+=1;
		}
		reader.close();
		
		return board;
	}
}


