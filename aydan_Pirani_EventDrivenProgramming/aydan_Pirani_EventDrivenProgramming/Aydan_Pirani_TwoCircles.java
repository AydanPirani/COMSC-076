/*
 * Name: Aydan Pirani
 * Assignment: #3 (Event-Driven Programming), Part 3
 * Assignment Desc: Make a program that places 2 draggable circles and a line between them
 */


package aydan_Pirani_EventDrivenProgramming;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Aydan_Pirani_TwoCircles extends Application {

	//Uses the Pythagorean theorem to calculate distance between the 2 centers
	public double pythagoreanTheorem(Circle circle1, Circle circle2) {
		double changeInX = Math.abs(circle1.getCenterX()-circle2.getCenterX());
		double changeInY = Math.abs(circle1.getCenterY()-circle2.getCenterY());
		return (Math.floor((Math.pow(Math.pow(changeInX, 2) + Math.pow(changeInY, 2), 0.5)*100))/100);
	}

	//Updates the endpoints of the lines given the centers of the circles
	public void updateLine(Circle circle1, Circle circle2, Line line) {
		line.setStartX(circle1.getCenterX()); line.setEndX(circle2.getCenterX());
		line.setStartY(circle1.getCenterY()); line.setEndY(circle2.getCenterY());
	}

	//Updates the text's position by placing it at the midpoint of the 2 circles
	public void centerText(Circle circle1, Circle circle2, Text text) {
		text.setX((circle1.getCenterX() + circle2.getCenterX())/2);
		text.setY((circle1.getCenterY() + circle2.getCenterY())/2);
	}

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		// Create a pane and set its properties
		Pane pane = new Pane();
		Circle circle1 = new Circle(40,40,10);
		Circle circle2 = new Circle(120,150,10);
		Text text = new Text(Double.toString(pythagoreanTheorem(circle1, circle2)));
		centerText(circle1, circle2, text);
		Line line = new Line(circle1.getCenterX(), circle1.getCenterY(), circle2.getCenterX(), circle2.getCenterY());
		pane.getChildren().addAll(circle1, circle2, text, line);

		circle1.setOnMouseDragged(e -> {       
			circle1.setCenterX(e.getX()); circle1.setCenterY(e.getY());
			text.setText(Double.toString(pythagoreanTheorem(circle1, circle2)));
			updateLine(circle1, circle2, line);
			centerText(circle1, circle2, text);
		});
		circle2.setOnMouseDragged(e -> {       
			circle2.setCenterX(e.getX()); circle2.setCenterY(e.getY());
			text.setText(Double.toString(pythagoreanTheorem(circle1, circle2)));
			updateLine(circle1, circle2, line);
			centerText(circle1, circle2, text);
		});


		// Create a scene and place it in the stage
		Scene scene = new Scene(pane, 400, 400);
		primaryStage.setTitle("Two Circles(Aydan Pirani):"); // Set the stage title
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

