import java.util.ArrayList;
import java.util.Collections;

public class Sentence {

	private String sentenceNumber;
	private String relation;
	private ArrayList<String> entities;
	private Boolean checkFlag;
	private Boolean correct;
	private Boolean Relation_Exists;


	/**
	 * 
	 * @param lineNumber being read
	 * @param relation found in sentence being read
	 * @param entities found in sentence being read
	 */
	
	public Sentence(String sentenceNumber, String relation, ArrayList<String> entities)
	{
		this.sentenceNumber = sentenceNumber;
		if (!relation.equals(null)){
			this.relation = relation;
		}
		Collections.copy(this.entities, entities);
	}
	
	public Sentence()
	{
		checkFlag = false;
		correct = false;
		Relation_Exists = false;
	}

	/**
	 * 
	 * Getters and Setters
	 */
	

	public String getRelation() {
		return relation;
	}

	public String getSentenceNumber() {
		return sentenceNumber;
	}

	public void setSentenceNumber(String sentenceNumber) {
		this.sentenceNumber = sentenceNumber;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public ArrayList<String> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<String> entities) {
		this.entities = (ArrayList<String>) entities.clone();
	}

	public Boolean getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(Boolean checkFlag) {
		this.checkFlag = checkFlag;
	}

	public Boolean getCorrect() {
		return correct;
	}

	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}

	public Boolean getRelation_Exists() {
		return Relation_Exists;
	}

	public void setRelation_Exists(Boolean relation_Exists) {
		Relation_Exists = relation_Exists;
	}


}
