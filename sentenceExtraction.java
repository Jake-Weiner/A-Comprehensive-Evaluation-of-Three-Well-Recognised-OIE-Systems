import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class sentenceExtraction {

	public static ArrayList<Sentence> reverb(String reverbTextToAnalyse) throws IOException 
	{
		ArrayList<Sentence> reverbSentences = new ArrayList<Sentence>();
		/**
		 * column positions in ReVerB output
		 */
		int sentenceNumber_index = 1;
		int arg1_index = 2;
		int relation_index = 3;
		int arg2_index = 4;
		
		/**
		 * read in file and extract neccessary data
		 */
		try {
			FileReader myFileReader = new FileReader(reverbTextToAnalyse);
			BufferedReader myBufferedReader1 = new BufferedReader(myFileReader);
			
			// Temporary string to hold sentence information
			String line;
			
			while ((line = myBufferedReader1.readLine())!=null){		
			Sentence temp = new Sentence();
			ArrayList<String> entities = new ArrayList<String>();
			String[] columns = line.split("\t");
			
			temp.setSentenceNumber(columns[sentenceNumber_index]);
			entities.add(columns[arg1_index]);
			entities.add(columns[arg2_index]);
			temp.setEntities(entities);
			temp.setRelation(columns[relation_index]);
			reverbSentences.add(temp);
			}
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return reverbSentences;
	}
	
	public static ArrayList<Sentence> Stanford_Or_Reverb(String reverbTextToAnalyse, Boolean isStanford) throws IOException 
	{
		ArrayList<Sentence> Sentences = new ArrayList<Sentence>();
		
		/**
		 * column positions in ReVerB output
		 */
		int sentenceNumber_Reverb_index = 12;
		int sentenceNumber_Stanford_index = 1;
		int arg1_index = 2;
		int relation_index = 3;
		int arg2_index = 4;
		int Stanford_Correction_Factor = 0;	
		
		if(isStanford){
		Stanford_Correction_Factor = 1;
		}
		
		Pattern sentenceNumber_ReVerb = Pattern.compile("\\S+\\s");
		/**
		 * read in file and extract neccessary data
		 */
		try {
			FileReader myFileReader = new FileReader(reverbTextToAnalyse);
			BufferedReader myBufferedReader1 = new BufferedReader(myFileReader);
			
			// Temporary string to hold sentence information
			String line;
			
			while ((line = myBufferedReader1.readLine())!=null){		
			Sentence temp = new Sentence();
			
			ArrayList<String> entities = new ArrayList<String>();
			String[] columns = line.split("\t");
			Matcher sentenceNumber_Matcher = sentenceNumber_ReVerb.matcher(columns[sentenceNumber_Reverb_index]);
			if (sentenceNumber_Matcher.find()){
				temp.setSentenceNumber(sentenceNumber_Matcher.group(0).trim());
			}
			if(isStanford){
			int sentenceNumber = Integer.valueOf(columns[sentenceNumber_Stanford_index]) + Stanford_Correction_Factor;
			temp.setSentenceNumber(String.valueOf(sentenceNumber)); // Stanford output starts sentences at sentence 0
			}
			entities.add(columns[arg1_index]);
			entities.add(columns[arg2_index]);
			temp.setEntities(entities);
			temp.setRelation(columns[relation_index]);
			Sentences.add(temp);
			}
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Sentences;
	}
	
	public static ArrayList<Sentence> NYT_Annotated_Nary(String NYGroundTruthTextToAnalyse) throws IOException 
	{
		ArrayList<Sentence> NYT_Annotated_Nary_Sentences = new ArrayList<Sentence>();
		
		String relationPattern = "[\\{]{3}.+[\\}]{3}";
		String entityPattern = "\\[{3}[^\\]]*\\]{3}";
		Pattern relation = Pattern.compile(relationPattern); 
		Pattern entity = Pattern.compile(entityPattern); 
		int counter =0;
		int sentenceNumber = 1;
		/**
		 * read in file and extract neccessary data
		 */
		try {
			FileReader myFileReader = new FileReader(NYGroundTruthTextToAnalyse);
			BufferedReader myBufferedReader1 = new BufferedReader(myFileReader);
			
			// Temporary string to hold sentence information
			String line;
			while ((line = myBufferedReader1.readLine())!=null){	
			Sentence sentenceRead = new Sentence();	
			sentenceRead.setSentenceNumber(Integer.toString(sentenceNumber));
			ArrayList<String> entities = new ArrayList<String>();
			Matcher relationMatcher = relation.matcher(line);
			Matcher entityMatcher = entity.matcher(line);
			//System.out.println("Sentence Number: " + sentenceNumber);
			// find all relations in the text
			while (relationMatcher.find()) {
				String relationFound = relationMatcher.group(0).replaceAll("\\{|\\}", "").toLowerCase();
				sentenceRead.setRelation(relationFound);
		      //  System.out.println("Found relation: " + relationFound);
		      }
			
			// find all entitites in the text
			while (entityMatcher.find()) {
				if(!entityMatcher.group(0).isEmpty()){
				 String entityFound = entityMatcher.group(0).replaceAll("\\[{3}(DATE|PER|LOC|TIME|MISC|ORG|NONE)|\\]{4}", "").toLowerCase();	
				 entities.add(entityFound);
		        // System.out.println("Found entity: " + entityFound);
				}
				sentenceRead.setEntities(entities);
		      }			
			NYT_Annotated_Nary_Sentences.add(sentenceRead);
			sentenceNumber++;
			}
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return NYT_Annotated_Nary_Sentences;
	}
	
	public static ArrayList<Sentence> Annotated_Binary(String Annotated_Binary_File) throws IOException
	{
		ArrayList<Sentence> Annotated_Binary_Sentences = new ArrayList<Sentence>();
		
		/**
		 * column positions in Annotated file 
		 */
		
		int sentenceNumber = 0;
		int arg1_index = 0;
		int relation_index = 1;
		int arg2_index = 2;
		/**
		 * read in file and extract neccessary data
		 */
		try {
			FileReader myFileReader = new FileReader(Annotated_Binary_File);
			BufferedReader myBufferedReader1 = new BufferedReader(myFileReader);
			// Temporary string to hold sentence information
			String line;
				
			while ((line = myBufferedReader1.readLine())!=null){
				if(sentenceNumber>0){
					Sentence temp = new Sentence();
					ArrayList<String> entities = new ArrayList<String>();
					String[] columns = line.split("\t");	
					if (!columns[relation_index].equals("---")){
						temp.setRelation_Exists(true);
					}
					temp.setSentenceNumber(String.valueOf(sentenceNumber));
					entities.add(columns[arg1_index]);
					entities.add(columns[arg2_index]);
					temp.setEntities(entities);
					temp.setRelation(columns[relation_index]);
					Annotated_Binary_Sentences.add(temp);	
				}
				sentenceNumber++;
			}
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Annotated_Binary_Sentences;
	}

	
	
}
