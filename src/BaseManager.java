
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaseManager {

	private Connection myConnection = null;
	private java.sql.PreparedStatement myState = null;
	private ResultSet myResult = null;

	private final String url = "jdbc:mysql://localhost:3306/kviz?autoReconnect=true&useSSL=false";
	private final String userName = "root";
	private final String password = "ivansvinja";

	private int currentQIndex = 1;

	public BaseManager() {
		poveziMe();
	}

	public int getQIndex() {
		return currentQIndex;
	}

	public void increaseQIndex() {
		this.currentQIndex++;
	}

	public void poveziMe() {
		try {
			myConnection = DriverManager.getConnection(url, userName, password);
			myState = myConnection.prepareStatement(" SELECT * FROM quesposanswsol WHERE id = ?");

		} catch (Exception ex) {
			System.out.println("Problem with establishing a connection with the database");
			ex.printStackTrace();
		}

	}

	public QuestionData getNextQuestionData() {

		QuestionData questionData = new QuestionData();

		try {
			myState.setInt(1, currentQIndex);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			myResult = myState.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Get the data from the database for the current question
		// and fill in my questionData object
		try {
			while (myResult.next()) {
				// Setting the question
				questionData.setQestion(myResult.getString(2));

				// Setting possible answers
				List<String> possibleAnswers = new ArrayList<String>();
				possibleAnswers.add(myResult.getString(3));
				possibleAnswers.add(myResult.getString(4));
				possibleAnswers.add(myResult.getString(5));
				possibleAnswers.add(myResult.getString(6));
				questionData.setPossibleAnswers(possibleAnswers);

				// Setting the correct answer (solution)
				questionData.setSolution(myResult.getString(7));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		// Reset the pointer TODO
		try {
			myResult.beforeFirst();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return questionData;

	}

	public void close() {
		try {
			myConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			myResult.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
