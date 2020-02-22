/*
 * Name: Aydan Pirani
 * Assignment: #3 (Event-Driven Programming), Part 1
 * Assignment Desc: Make a program that uses JavaFX to identify the future value of a loan
 */

package aydan_Pirani_EventDrivenProgramming;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Aydan_Pirani_InterestCalculator extends Application {
	TextField principalTextField = new TextField();
	TextField rateTextField = new TextField();
	TextField yearsTextField = new TextField();
	TextField timesPerYearTextField = new TextField();
	Text text = new Text();
	GridPane pane = new GridPane();

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		// Create a pane and set its properties
		pane.setPadding(new Insets(11, 12, 13, 14));
		pane.setHgap(5);
		pane.setVgap(5);

		Button button = new Button("OK");
		button.setPrefSize(150, 20);
	
		// Place nodes in the pane    
		pane.add(new Label("Initial Amount: "), 0, 0);
		pane.add(principalTextField, 1, 0);
		pane.add(new Label("Interest Rate (Percent Form): "), 0, 1);
		pane.add(rateTextField, 1, 1);
		pane.add(new Label ("Years:"), 0, 3);
		pane.add(yearsTextField, 1, 3);
		pane.add(new Label("Times per Year:"), 0, 4);
		pane.add(timesPerYearTextField, 1, 4);
		pane.add(button, 1, 6);
		pane.add(text, 1, 5);


		button.setOnAction(e -> calculateInterest());
		Scene scene = new Scene(pane, 400, 250);
		primaryStage.setTitle("Interest Calculator (Aydan Pirani):"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage


	}


	public static void main(String[] args) {
		launch(args);
	}

	//Method to calculate the interest
	private void calculateInterest() {
		text.setText("");
		try {
			double principal = Double.parseDouble(principalTextField.getText())*1.0;
			double rateOfInterest = Double.parseDouble(rateTextField.getText())/100.0;
			int years = Integer.parseInt(yearsTextField.getText());
			int timesPerYear = Integer.parseInt(timesPerYearTextField.getText());
			double amount = principal * Math.pow((1.0+(rateOfInterest/(1.0*timesPerYear))),years*timesPerYear);
			text.setText("$" + Double.toString(Math.floor(amount*100)/100));
		} catch (Exception e) {
			text.setText("There was an error in the processing.");
		}
	}
}
