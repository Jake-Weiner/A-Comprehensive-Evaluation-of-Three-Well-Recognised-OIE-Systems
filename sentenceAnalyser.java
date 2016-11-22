import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class sentenceAnalyser {

	private static ArrayList<Sentence> NYT_Annotated_Sentences_Nary;
	
	private static ArrayList<Sentence> NYT_Annotated_Sentences_Binary;
	private static ArrayList<Sentence> NYT_Reverb_Sentences_Binary;
	private static ArrayList<Sentence> NYT_Stanford_Sentences_Binary;
	
	private static ArrayList<Sentence> Penn_Annotated_Sentences_Binary;
	private static ArrayList<Sentence> Penn_Stanford_Sentences_Binary;
	private static ArrayList<Sentence> Penn_Reverb_Sentences_Binary;
	private static ArrayList<Sentence> Penn_Original_Sentences_Binary;
	
	private static ArrayList<Sentence> Web_Annotated_Sentences_Binary;
	private static ArrayList<Sentence> Web_Stanford_Sentences_Binary;
	private static ArrayList<Sentence> Web_Reverb_Sentences_Binary;
	
	
	public static Boolean lineNumberCheck()
	{
		Boolean checkStatus = false;
		return checkStatus;
	}

	/**
	 * 
	 * @param file - Require original groundtruth file
	 * @throws IOException
	 */
	public static void create_NYT_Reverb_Sentences_Binary(String file) throws IOException
	{
		NYT_Reverb_Sentences_Binary = (ArrayList<Sentence>) sentenceExtraction.reverb(file).clone();
	}
	
	public static void create_NYT_Annotated_Sentences_Nary(String file) throws IOException
	{
		NYT_Annotated_Sentences_Nary = (ArrayList<Sentence>) sentenceExtraction.NYT_Annotated_Nary(file).clone();
	}
	
	public static void create_NYT_Annotated_Sentences_Binary(String file) throws IOException
	{
		NYT_Annotated_Sentences_Binary = (ArrayList<Sentence>) sentenceExtraction.Annotated_Binary(file).clone();
	}
	
	public static void create_NYT_Stanford_Sentences_Binary(String file) throws IOException
	{
		NYT_Stanford_Sentences_Binary = (ArrayList<Sentence>) sentenceExtraction.Stanford_Or_Reverb(file, true).clone();
	}
	
	public static void create_Penn_Annotated_Sentences_Binary(String file) throws IOException
	{
		Penn_Annotated_Sentences_Binary = (ArrayList<Sentence>) sentenceExtraction.Annotated_Binary(file).clone();
	}
	
	public static void create_Penn_Original_Sentences_Binary(String file) throws IOException
	{
		Penn_Original_Sentences_Binary = (ArrayList<Sentence>) sentenceExtraction.Annotated_Binary(file).clone();
	}
	
	public static void create_Penn_Stanford_Sentences_Binary(String file) throws IOException
	{
		Penn_Stanford_Sentences_Binary = (ArrayList<Sentence>) sentenceExtraction.Stanford_Or_Reverb(file, true).clone();
	}
	
	public static void create_Penn_Reverb_Sentences_Binary(String file) throws IOException
	{
		Penn_Reverb_Sentences_Binary = (ArrayList<Sentence>) sentenceExtraction.Stanford_Or_Reverb(file, true).clone();
	}
	
	public static void create_Web_Reverb_Sentences_Binary(String file) throws IOException
	{
		Web_Reverb_Sentences_Binary = (ArrayList<Sentence>) sentenceExtraction.Stanford_Or_Reverb(file, false).clone();
	}
	
	public static void create_Web_Stanford_Sentences_Binary(String file) throws IOException
	{
		Web_Stanford_Sentences_Binary = (ArrayList<Sentence>) sentenceExtraction.Stanford_Or_Reverb(file, true).clone();
	}
	
	public static void create_Web_Annotated_Sentences_Binary(String file) throws IOException
	{
		Web_Annotated_Sentences_Binary = (ArrayList<Sentence>) sentenceExtraction.Annotated_Binary(file).clone();
	}
	
	public static void stanfordAnalysis_Binary(ArrayList<Sentence> Stanford_Sentences, ArrayList<Sentence> Annotated_Sentences)
	{
		ArrayList<Sentence> Stanford_Sentences_Checked = new ArrayList<Sentence>();
		int Stanford_Sentence_Counter = 0;
		int Annotated_Sentence_Counter = 0;
		int Stanford_CurrentSentenceNumber = 0;
		int Annotated_CurrentSentenceNumber = 0;
		Sentence Stanford_CurrentSentence;
		Sentence Annotated_CurrentSentence;
		
		while((Stanford_Sentence_Counter<Stanford_Sentences.size()) && (Annotated_Sentence_Counter<Annotated_Sentences.size())){
			Stanford_CurrentSentence = Stanford_Sentences.get(Stanford_Sentence_Counter);
			Annotated_CurrentSentence = Annotated_Sentences.get(Annotated_Sentence_Counter);
			
			if(Annotated_CurrentSentence.getRelation_Exists()){
				Stanford_CurrentSentence.setRelation_Exists(true);
			}
			Stanford_CurrentSentenceNumber = Integer.valueOf(Stanford_CurrentSentence.getSentenceNumber());
			Annotated_CurrentSentenceNumber = Integer.valueOf(Annotated_CurrentSentence.getSentenceNumber());
			
			
			// check sentence number match
			if (Stanford_CurrentSentenceNumber == Annotated_CurrentSentenceNumber){
				if(extractionAnalysis_Methods.relationComparison(Stanford_CurrentSentence,Annotated_CurrentSentence) == 0){
					Stanford_Sentence_Counter++;
				}
				else{
					if(extractionAnalysis_Methods.relationComparison(Stanford_CurrentSentence,Annotated_CurrentSentence) == 1){
						Stanford_CurrentSentence.setCheckFlag(true);
						Stanford_Sentence_Counter++;
					}
					if(extractionAnalysis_Methods.relationComparison(Stanford_CurrentSentence,Annotated_CurrentSentence) == 2){
						Stanford_CurrentSentence.setCorrect(true);
						Stanford_Sentence_Counter++;
					}	
				}
			}
			else if(Stanford_CurrentSentenceNumber < Annotated_CurrentSentenceNumber ){
				Stanford_Sentence_Counter++;
			}
			else{
				Annotated_Sentence_Counter++;
			}
			Stanford_Sentences_Checked.add(Stanford_CurrentSentence);
		}
		printSentences.print_Sentences_Matched(Stanford_Sentences_Checked);
	}
	
	public static void main (String args[]) throws IOException 
	{
		//BINARY
		
		/*
		 *  File names of the Raw Annotations - Entity1, Relation, Entity2, Relational phrase, Annotated Sentence
		 */
		
		String Penn_Annotated_Binary_File_Raw = "/Users/weiner103/Documents/Thesis/Datasets/datasets/experiments/binary/manual/Penn/penn-ground-truth.txt";
		String Web_Annotated_Binary_File_Raw = "/Users/weiner103/Documents/Thesis/Datasets/datasets/experiments/binary/manual/Web/web-ground-truth.txt";
		String NYT_Annotated_Binary_File_Raw = "/Users/weiner103/Documents/Thesis/Datasets/datasets/experiments/binary/manual/NyTimes/nytimes-ground-truth.txt";
		
		
		/*
		 *  File names of the Raw System Extractions - For ReVerb and Stanford this is in ReVerb style, for OpenIE it is different
		 */
		
		String Penn_Stanford_Binary_Reverb_File = "/Users/weiner103/Documents/Thesis/Results/OIE_Systems_Output_Raw/Penn_Stanford_Binary_Reverb.txt";
		String Web_Stanford_Binary_File = "/Users/weiner103/Documents/Thesis/Results/OIE_Systems_Output_Raw/Web_Stanford_Binary.txt";
		String Penn_Reverb_Binary_File = "/Users/weiner103/Documents/Thesis/Results/OIE_Systems_Output_Raw/Penn_Reverb_Binary.txt";
		String Web_Reverb_Binary_File = "/Users/weiner103/Documents/Thesis/Results/OIE_Systems_Output_Raw/Web_Reverb_Binary.txt";
		
		/*
		 * Sentence Creation
		 */
		
		 create_Penn_Annotated_Sentences_Binary(Penn_Annotated_Binary_File_Raw);
		 create_Penn_Stanford_Sentences_Binary(Penn_Stanford_Binary_Reverb_File);
		//create_Penn_Reverb_Sentences_Binary(Penn_Reverb_Binary_File);
		
		//create_Web_Annotated_Sentences_Binary(Web_Annotated_Binary_File_Raw);
		//create_Web_Reverb_Sentences_Binary(Web_Reverb_Binary_File);
		
		 /*
		  * Sentence Analysis
		  */
		 
		 stanfordAnalysis_Binary(Penn_Stanford_Sentences_Binary,Penn_Annotated_Sentences_Binary);
		
		/*
		 * Print Sentences
		 */
		 
		 printSentences.print_Sentences_General(Penn_Stanford_Sentences_Binary, false);
		 printSentences.print_Sentences_General(Penn_Annotated_Sentences_Binary, false);
		 
		 //printSentences.print_Sentences_Annotated_Relations(Penn_Annotated_Sentences_Binary);
	
		
		// Create different size input files
		String file_source = "/Users/weiner103/Documents/Thesis/Time_Complexity_Analysis/Dataset_Original/Time_Complexity_Dataset.txt";
		String file_dest_50 = "/Users/weiner103/Documents/Thesis/Time_Complexity_Analysis/Dataset_DifferentSizes/Dataset_50";
		String file_dest_100 = "/Users/weiner103/Documents/Thesis/Time_Complexity_Analysis/Dataset_DifferentSizes/Dataset_100";
		String file_dest_150 = "/Users/weiner103/Documents/Thesis/Time_Complexity_Analysis/Dataset_DifferentSizes/Dataset_150";
		String file_dest_200 = "/Users/weiner103/Documents/Thesis/Time_Complexity_Analysis/Dataset_DifferentSizes/Dataset_200";
		String file_dest_250 = "/Users/weiner103/Documents/Thesis/Time_Complexity_Analysis/Dataset_DifferentSizes/Dataset_250";
		String file_dest_300 = "/Users/weiner103/Documents/Thesis/Time_Complexity_Analysis/Dataset_DifferentSizes/Dataset_300";
		String file_dest_350 = "/Users/weiner103/Documents/Thesis/Time_Complexity_Analysis/Dataset_DifferentSizes/Dataset_350";

		String file_dest_500 = "/Users/weiner103/Documents/Thesis/Time_Complexity_Analysis/Dataset_DifferentSizes/Dataset_500";
		String file_dest_600 = "/Users/weiner103/Documents/Thesis/Time_Complexity_Analysis/Dataset_DifferentSizes/Dataset_600";
		String file_dest_700 = "/Users/weiner103/Documents/Thesis/Time_Complexity_Analysis/Dataset_DifferentSizes/Dataset_700";

		//printSentences.printOriginal("/Users/weiner103/Documents/Thesis/Datasets/datasets/experiments/binary/manual/Penn/penn_ground_truth_original_sentences.txt");
		
		
		
		/*timeComplexity.fileCreation(file_source, file_dest_50, 50);
		timeComplexity.fileCreation(file_source, file_dest_100, 100);
		timeComplexity.fileCreation(file_source, file_dest_150, 150);
		timeComplexity.fileCreation(file_source, file_dest_200, 200);
		timeComplexity.fileCreation(file_source, file_dest_250, 250);
		timeComplexity.fileCreation(file_source, file_dest_300, 300);
		timeComplexity.fileCreation(file_source, file_dest_350, 350);
		
		timeComplexity.fileCreation(file_source, file_dest_500, 500);
		timeComplexity.fileCreation(file_source, file_dest_600, 600);
		timeComplexity.fileCreation(file_source, file_dest_700, 700);
		*/

	}
}
