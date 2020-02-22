/*
 * Name: Aydan Pirani
 * Assignment: #2 (JavaFX)
 * Assignment Desc: Make a tic-tac-toe program in which all of the blocks are randomly filled in
 */

package aydan_Pirani_JavaFX;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class Aydan_Pirani_TicTacToe extends Application  {
	public void start(Stage primaryStage) {
		// Create a pane and set its properties
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		pane.setHgap(5.5);
		pane.setVgap(5.5);

		Image xImage = new Image("https://ak1.picdn.net/shutterstock/videos/31104631/thumb/1.jpg");
		Image oImage = new Image("https://ak7.picdn.net/shutterstock/videos/31104637/thumb/1.jpg");
		Image blankSpace = new Image("https://static-cdn.jtvnw.net/jtv_user_pictures/e91a3dcf-c15a-441a-b369-996922364cdc-profile_image-300x300.png");
		ImageView printImage;

		// Place nodes in the pane
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 3; column++) {
				int randomTest = (int) (Math.random()*100);
				if (randomTest % 3 == 0) {
					printImage = new ImageView(xImage);	
				} else if (randomTest % 3 == 1) {
					printImage = new ImageView(oImage);
				} else {
					printImage = new ImageView(blankSpace);
				}
				
				printImage.setFitHeight(100); printImage.setFitWidth(100);
				pane.add(printImage, column, row);
			}
		}

		// Create a scene and place it in the stage
		Scene scene = new Scene(pane);
		primaryStage.setTitle("ShowGridPane"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}

	/**
	 * The main method is only needed for the IDE with limited
	 * JavaFX support. Not needed for running from the command line.
	 */
	public static void main(String[] args) {
		launch(args);
	}
} 