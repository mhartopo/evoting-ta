package app;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
	private Stage primaryStage;
	private static Stage myStage;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
        initWindow();
	}
	
	public void initWindow() {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/MainWindow.fxml"));
		try {
			AnchorPane pane = (AnchorPane)loader.load();
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("E-Voting Demo");
			myStage = primaryStage;
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static Stage getMyStage() {
		return myStage;
	}

	public static void setMyStage(Stage myStage) {
		Main.myStage = myStage;
	}
}
