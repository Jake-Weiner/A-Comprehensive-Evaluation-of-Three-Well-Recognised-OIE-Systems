import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class timeComplexity {

	public static void fileCreation (String file_source, String file_dest, int no_of_sentences) throws IOException
	{
		try {
			FileReader myFileReader = new FileReader(file_source);
			BufferedReader myBufferedReader1 = new BufferedReader(myFileReader);
			String line;
			PrintStream out = new PrintStream(new FileOutputStream(file_dest));
			System.setOut(out);
			int sentence_counter = 0;
			while (((line = myBufferedReader1.readLine())!=null) && sentence_counter<no_of_sentences){
				System.out.println(line);
				sentence_counter++;
			}			
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
