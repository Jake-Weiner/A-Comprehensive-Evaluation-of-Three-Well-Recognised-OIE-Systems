import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public class printSentences {

	public static void print_Sentences_Matched(ArrayList<Sentence> Sentences)
	{
		
		System.out.println("Matched Sentences are");
		System.out.println("Sentence No. \t\t Relation \t\t Entities \t Correct \t Checkflag");

		/* conditions for printing out a sentence
		- partial relation, total relation, extraction exists within original annotations
		*/
		for (Sentence tempSentences : Sentences) {
			if((tempSentences.getCheckFlag() || tempSentences.getCorrect()) && tempSentences.getRelation_Exists()){
				System.out.print(tempSentences.getSentenceNumber()+ "\t\t");
				System.out.print(tempSentences.getRelation() + "\t\t");
				try {
					for (String tempEntities  : tempSentences.getEntities()){
						System.out.print(tempEntities + ", ");
						}
				} catch (Exception e) {
					System.out.print("no entities");
				}
				System.out.print("\t" + tempSentences.getCorrect());
				System.out.print("\t" + tempSentences.getCheckFlag());
				System.out.println();
			}
		}
	}
	
	public static void print_Sentences_General(ArrayList<Sentence> Sentences,boolean Limit_Sentence) throws FileNotFoundException
	{
		
		int Sentence_Limit = 100;
		int Sentence_Counter = 0;
		//PrintStream out = new PrintStream(new FileOutputStream("/Users/weiner103/Documents/Thesis/Results/OIE_Systems_Output_Raw/Penn_Reverb_Manual_Binary"));
		//System.setOut(out);
		System.out.println("Sentence No. \t\t Relation \t\t Entities \t Correct \t Checkflag");
		if (Limit_Sentence){
			for (Sentence tempSentences : Sentences) {
				System.out.print(tempSentences.getSentenceNumber()+ "\t\t");
				System.out.print(tempSentences.getRelation() + "\t\t");
				try {
					for (String tempEntities  : tempSentences.getEntities()){
						System.out.print(tempEntities + ", ");
						}
				} catch (Exception e) {
					System.out.print("no entities");
				}
				System.out.print("\t" + tempSentences.getCorrect());
				System.out.print("\t" + tempSentences.getCheckFlag());
				System.out.println();
				Sentence_Counter++;
				if(Sentence_Counter>Sentence_Limit){	
					break;
				}
			}
		}
		else{
			for (Sentence tempSentences : Sentences) {
				System.out.print(tempSentences.getSentenceNumber()+ "\t\t");
				System.out.print(tempSentences.getRelation() + "\t\t");
				try {
					for (String tempEntities  : tempSentences.getEntities()){
						System.out.print(tempEntities + ", ");
						}
				} catch (Exception e) {
					System.out.print("no entities");
				}
				System.out.print("\t" + tempSentences.getCorrect());
				System.out.print("\t" + tempSentences.getCheckFlag());
				System.out.println();
			}
		}
	}
	
	public static void print_Sentences_Annotated_Relations(ArrayList<Sentence> Sentences) throws FileNotFoundException
	{
		int No_Of_Relations = 0;
		System.out.println("Sentence No. \t\t Relation \t\t Entities \t Correct \t Checkflag");
		for (Sentence tempSentences : Sentences) {
			if(!tempSentences.getRelation().equals("---")){
				System.out.print(tempSentences.getSentenceNumber()+ "\t\t");
				System.out.print(tempSentences.getRelation() + "\t\t");
				try {
					for (String tempEntities  : tempSentences.getEntities()){
						System.out.print(tempEntities + ", ");
						}
				} catch (Exception e) {
					System.out.print("no entities");
				}
				System.out.print("\t" + tempSentences.getCorrect());
				System.out.print("\t" + tempSentences.getCheckFlag());
				System.out.println();
				No_Of_Relations++;
			}
		}
		System.out.println("Number of Relations is " + No_Of_Relations);	
	}
// TODO Print original text to make sure there are 100 sentences
	public static void printOriginal(String file) throws IOException{
		
		int sentenceNumber = 0;	
		PrintStream out = new PrintStream(new FileOutputStream("/Users/weiner103/Documents/Thesis/Datasets/datasets/experiments/binary/manual/Penn_GroundTruth_Sentences"));
		System.setOut(out);
		try {
			FileReader myFileReader = new FileReader(file);
			BufferedReader myBufferedReader1 = new BufferedReader(myFileReader);
			// Temporary string to hold sentence information
			String line;
			while ((line = myBufferedReader1.readLine())!=null){
				System.out.println(line + "\t" + sentenceNumber);
				sentenceNumber++;
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
