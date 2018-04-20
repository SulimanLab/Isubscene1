
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sun.applet.Main;

public class jfcxlUI extends Application {
	private Stage primaryStage;
	private Pane mainLayout;

	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		primaryStage.setTitle("iSubscene");
		showMainView();
	}
	
	

	public void showMainView() throws IOException {
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "ImageRotator");
		FXMLLoader l = new FXMLLoader();
		l.setLocation(Main.class.getResource("/MainView.fxml"));
		mainLayout = l.load();
		Scene scene = new Scene(mainLayout);
		scene.getStylesheets().add("/Styles.css");
		primaryStage.setScene(scene);
		primaryStage.show();
		

	}

	public static void Display() {
		System.out.println("yahooooo");
	}

	public static void main(String[] args) {
		launch(args);
	}
}
