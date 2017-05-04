
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ControlerKvizGUI {

	private BaseManager baseManager = new BaseManager();
	private QuestionData tekucePitanje = null;
	private Integer points = 0;

	@FXML
	private Button b1;

	@FXML
	private Button b2;

	@FXML
	private Button b3;

	@FXML
	private Button b4;

	@FXML
	private Label label1;

	@FXML
	private Label label2;

	@FXML
	private Label label3;

	@FXML
	public void initialize() {
		tekucePitanje = baseManager.getNextQuestionData();

		label1.setText(tekucePitanje.getQestion());
		b1.setText(tekucePitanje.getPossibleAnswers().get(0));
		b2.setText(tekucePitanje.getPossibleAnswers().get(1));
		b3.setText(tekucePitanje.getPossibleAnswers().get(2));
		b4.setText(tekucePitanje.getPossibleAnswers().get(3));
		label3.setText(points.toString());
	}

	public void anyButtonClick(ActionEvent e) {
		baseManager.poveziMe();

		// Proveri tacnost odgovora
		Button anyButton = (Button) e.getSource();
		String userAnsw = anyButton.getText();
		isCorrect(userAnsw);

		baseManager.increaseQIndex();

		setNextQuest();

		baseManager.close();

	}

	private void setNextQuest() {
		tekucePitanje = baseManager.getNextQuestionData();

		// Proveri da li je sledece pitanje setovano, ako nije, znaci da smo
		// stigli do kraja kviza
		if (tekucePitanje.getQestion() == null) {
			label1.setText("Kraj");
			return;
		}

		try {
			label1.setText(tekucePitanje.getQestion());
			b1.setText(tekucePitanje.getPossibleAnswers().get(0));
			b2.setText(tekucePitanje.getPossibleAnswers().get(1));
			b3.setText(tekucePitanje.getPossibleAnswers().get(2));
			b4.setText(tekucePitanje.getPossibleAnswers().get(3));
		} catch (IndexOutOfBoundsException ex) {
			ex.printStackTrace();
		}

	}

	private boolean isCorrect(String userAnswer) {
		if (userAnswer.equals(tekucePitanje.getSolution())) {

			label3.setText(Integer.toString(++points));
			return true;
		}

		return false;
	}

}
