
public class extractionAnalysis_Methods {

	/**
	 * 
	 * @param Sentence_1
	 * @param Sentence_2
	 * @return 0 if no match, 1 if partial match, 2 if exact match
	 */
	public static int relationComparison(Sentence Sentence_1, Sentence Sentence_2)
	{
		int Exact_Match = 0;
		String Relation_1 = Sentence_1.getRelation();
		String Relation_2 = Sentence_2.getRelation();

		if(Relation_1.equals(Relation_2)){
			Exact_Match = 2;
		}
		else if(Relation_1.contains(Relation_2) && Relation_2!=null){
			Exact_Match = 1;
		}
		else if(Relation_2.contains(Relation_1) && Relation_1!=null){
			Exact_Match = 1;
		}
		else{
			Exact_Match = 0;
		}
		
		return Exact_Match;
	}
}
