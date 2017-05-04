
import java.util.ArrayList;
import java.util.List;

public class QuestionData {
	private String qestion;
	String solution;
	List<String> possibleAnswers = new ArrayList<String>();

	public String getQestion() {
		return qestion;
	}

	public void setQestion(String qestion) {
		this.qestion = qestion;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public List<String> getPossibleAnswers() {
		return possibleAnswers;
	}

	public void setPossibleAnswers(List<String> possibleAnswers) {
		this.possibleAnswers = possibleAnswers;
	}

	public void createQestion(String str1, String str2, List<String> list) {

		this.qestion = str1;
		this.solution = str2;

		for (String x : list) {
			possibleAnswers.add(x);

		}

	}
}
