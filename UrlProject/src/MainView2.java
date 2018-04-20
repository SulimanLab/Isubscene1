import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class MainView2 {

	private int NumberOfSearchs = 0;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button searchButt;

	@FXML
	private Label label;

	@FXML
	private TextField txtField;

	@FXML
	private Pane ancPane2;


	@FXML
	private ListView<String> listView = new ListView<String>();

	@FXML
	private Button searchButt1;

	@FXML
	void onSubmitClick(ActionEvent event) {

		listView.getItems().clear();

		if (NumberOfSearchs != 0) {
			MangeTheFile.setSubsLinks();
			MangeTheFile.setSubs(1000);
			MangeTheFile.subsSize = 0;
			NumberOfSearchs = 0;
		}

		NumberOfSearchs++;
		String args[] = { txtField.getText() };
		System.out.println(args[0]);
		if (txtField.getText().equals("")) {
			txtField.requestFocus();
			txtField.setAccessibleHelp("fasdf");
		} else {
			try {

				Url.main(args);

			} catch (IOException e1) {
				e1.printStackTrace();

			} catch (URISyntaxException e1) {
				e1.printStackTrace();

			}
			Url u = new Url();
			String s[] = u.getSubs();
			// progressBar.progressProperty().bind(MangeTheFile.ti);
			int i = 0;
			while (s[i] != null) {
				if (s[0] == null) {
					listView.getItems().add("Couldn't find subtitles try changing the title");
				}
				listView.getItems().add(s[i++]);
			}

			System.out.println(txtField.getText());

			// String xx = listView.getItems().get(0);
		}

	}

	@FXML
	void onSubmitClick2(ActionEvent event) {
		if (!(listView.getSelectionModel().getSelectedIndex() == -1))
			try {

				Url.mtf.changeDowloadLink(listView.getSelectionModel().getSelectedIndex(), Url.getFp(), Url.getDd());

				Url.mtf.openFileWithVlc(MangeTheFile.getGlopalDis());

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

	}

	@FXML
	void textFieldPressed(KeyEvent event) {

		if (event.getCode() == KeyCode.ENTER)
			onSubmitClick(null);
	}

	@FXML
	void initialize() {

		assert ancPane2 != null : "fx:id=\"ancPane2\" was not injected: check your FXML file 'MainView.fxml'.";
		assert listView != null : "fx:id=\"listView\" was not injected: check your FXML file 'MainView.fxml'.";
		assert searchButt != null : "fx:id=\"searchButt\" was not injected: check your FXML file 'MainView.fxml'.";
		assert label != null : "fx:id=\"label\" was not injected: check your FXML file 'MainView.fxml'.";
		assert txtField != null : "fx:id=\"txtField\" was not injected: check your FXML file 'MainView.fxml'.";
		assert searchButt1 != null : "fx:id=\"searchButt1\" was not injected: check your FXML file 'MainView.fxml'.";

		try {
			String name = MangeTheFile.getTheMovieName();
			txtField.setText(name);
			if (name == null)
				txtField.setText("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
