import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.regex.*;

public class Parser {
	 
	 public static void groundTruthParse(String GroundTruth_Raw_File, String GroundTruth_Original_Sentences) throws IOException
	 {
		 int sentenceCounter =0;
         try {
        	FileReader myFileReader = new FileReader(GroundTruth_Raw_File);
 			BufferedReader myBufferedReader1 = new BufferedReader(myFileReader);
 			//String key information 
 			String line;
 			PrintStream out = new PrintStream(new FileOutputStream(GroundTruth_Original_Sentences));
			System.setOut(out);
 			while ((line = myBufferedReader1.readLine())!=null){		
	 			if(sentenceCounter++>0){
		 			String[] columns = line.split("\t");
		 			String sentence = columns[4];
		 			sentence = regexReplace(sentence);
		 			sentence = regexReplace2(sentence);
		 			//sentence = regexReplace2(sentence);
					//System.out.print(String.valueOf((sentenceCounter-1)) +" " + sentence + "\n" );
		 			System.out.print(sentence +"\n");
	 			}
			}
         // Cycle through the positive matches and print them to screen
         // Make sure string isn't empty and trim off any whitespace
         }
         catch (FileNotFoundException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		 }
	 }
	
		     public static String regexReplace(String str2Replace){
		
		         // REGEX that matches 1 or more white space
		
		         Pattern replace = Pattern.compile("[\\{{3}|\\}{3}|\\[{3}|\\]{3}]([A-Z]{3,4})?");

		         // trim the string t prepare it for a replace
		
		         Matcher regexMatcher = replace.matcher(str2Replace.trim());
    // replaceAll replaces all white space with commas

		         
		         return regexMatcher.replaceAll("");
		         //System.out.println(regexMatcher.replaceAll(", "));
		
		     }
		     
		     public static String regexReplace2(String str2Replace){
		 		
		         // REGEX that matches 1 or more white space
		
		
		         Pattern replace2 = Pattern.compile("--->|<---");
		         // trim the string t prepare it for a replace
		
		         Matcher regexMatcher = replace2.matcher(str2Replace.trim());
    // replaceAll replaces all white space with commas
		         
		         return regexMatcher.replaceAll("");
		         //System.out.println(regexMatcher.replaceAll(", "));
		
		     }
		     
		 	public static void main (String args[]) throws IOException 
		 	{
		 		String Penn_GroundTruth_Raw_File = "/Users/weiner103/Documents/Thesis/Datasets/datasets/experiments/binary/manual/Penn/penn-ground-truth.txt";
		 		String Penn_GroundTruth_Original_Sentences = "/Users/weiner103/Documents/Thesis/Datasets/datasets/experiments/binary/manual/Penn/penn_ground_truth_original_sentences.txt";
		 		
		 		String Web_GroundTruth_Raw_File = "/Users/weiner103/Documents/Thesis/Datasets/datasets/experiments/binary/manual/Web/web-ground-truth.txt";
		 		String Web_GroundTruth_Original_Sentences = "/Users/weiner103/Documents/Thesis/Datasets/datasets/experiments/binary/manual/Web/web_ground_truth_original_sentences.txt";
		 		
				String NYT_GroundTruth_Raw_File = "/Users/weiner103/Documents/Thesis/Datasets/datasets/experiments/binary/manual/NyTimes/nytimes-ground-truth.txt";
				String NYT_GroundTruth_Original_Sentences = "/Users/weiner103/Documents/Thesis/Datasets/datasets/experiments/binary/manual/NyTimes/nytimes_ground_truth_original_sentences.txt";
		 		
				groundTruthParse(Penn_GroundTruth_Raw_File,Penn_GroundTruth_Original_Sentences);
		 	}

}
