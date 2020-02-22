/*
 * Name: Aydan Pirani
 * Assignment: #2 (JavaFX)
 * Assignment Desc: Make a more detailed version of a clock, including marks at every minute
 */
package aydan_Pirani_JavaFX;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import java.util.Calendar; 
import java.util.GregorianCalendar;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

class ClockPane extends Pane {
	private int hour;
	private int minute;
	private int second;

	/** Construct a default clock with the current time*/
	public ClockPane() {
		setCurrentTime();
	}

	/** Construct a clock with specified hour, minute, and second */
	public ClockPane(int hour, int minute, int second) {
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}

	/** Return hour */
	public int getHour() {
		return hour;
	}

	/** Set a new hour */
	public void setHour(int hour) {
		this.hour = hour;
		paintClock();
	}

	/** Return minute */
	public int getMinute() {
		return minute;
	}

	/** Set a new minute */
	public void setMinute(int minute) {
		this.minute = minute;
		paintClock();
	}

	/** Return second */
	public int getSecond() {
		return second;
	}

	/** Set a new second */
	public void setSecond(int second) {
		this.second = second;
		paintClock();
	}

	/* Set the current time for the clock */
	public void setCurrentTime() {
		// Construct a calendar for the current date and time
		Calendar calendar = new GregorianCalendar();

		// Set current hour, minute and second
		this.hour = calendar.get(Calendar.HOUR_OF_DAY);
		this.minute = calendar.get(Calendar.MINUTE);
		this.second = calendar.get(Calendar.SECOND);

		paintClock(); // Repaint the clock
	}

	/** Paint the clock */
	private void paintClock() {
		// Initialize clock parameters
		double clockRadius = 
				Math.min(getWidth(), getHeight()) * 0.8 * 0.5;
		double centerX = getWidth() / 2;
		double centerY = getHeight() / 2;

		// Draw circle
		Circle circle = new Circle(centerX, centerY, clockRadius);
		circle.setFill(Color.WHITE);
		circle.setStroke(Color.BLACK);

		// Draw second hand
		double sLength = clockRadius * 0.8;
		double secondX = centerX + sLength * Math.sin(second * (2 * Math.PI / 60));
		double secondY = centerY - sLength * Math.cos(second * (2 * Math.PI / 60));
		Line sLine = new Line(centerX, centerY, secondX, secondY);
		sLine.setStroke(Color.RED);

		// Draw minute hand
		double mLength = clockRadius * 0.65;
		double xMinute = centerX + mLength * Math.sin(minute * (2 * Math.PI / 60));
		double minuteY = centerY - mLength * Math.cos(minute * (2 * Math.PI / 60));
		Line mLine = new Line(centerX, centerY, xMinute, minuteY);
		mLine.setStroke(Color.BLUE);

		// Draw hour hand
		double hLength = clockRadius * 0.5;
		double hourX = centerX + hLength *  Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
		double hourY = centerY - hLength *  Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
		Line hLine = new Line(centerX, centerY, hourX, hourY);
		hLine.setStroke(Color.GREEN);

		getChildren().clear();  
		getChildren().addAll(circle, sLine, mLine, hLine);

		//Create marks on the border of the clock and create numbers for hours
		for (int degree = 360; degree >= 0; degree = degree - 6) { //360 total degrees / 60 mins = 6 degrees
			double pointX1 = centerX + clockRadius * Math.cos(((degree)/360.0) * 2 * Math.PI);
			double pointX2 = centerX + (clockRadius - 5) * Math.cos(((degree)/360.0) * 2 * Math.PI);
			double pointX3 = centerX - 5.5 + (clockRadius - 19) * Math.cos(((degree)/360.0) * 2 * Math.PI);
			double pointY1 = centerY + clockRadius * Math.sin((degree/360.0) * 2 * Math.PI);
			double pointY2 = centerY + (clockRadius - 5) * Math.sin(((degree)/360.0) * 2 * Math.PI);
			double pointY3 = centerY + 4.5 + (clockRadius - 19) * Math.sin(((degree)/360.0) * 2 * Math.PI);

			if (degree % 30 == 0) {
				pointX2 = centerX + (clockRadius - 10) * Math.cos(((degree)/360.0) * 2 * Math.PI);
				pointY2 = centerY + (clockRadius - 10) * Math.sin(((degree)/360.0) * 2 * Math.PI);
				int hour = ((degree + 90)%360)/30;
				if (hour == 0) {
					hour = 12;
				}
				Text temporaryText = new Text(pointX3, pointY3, Integer.toString(hour));
				getChildren().add(temporaryText);
			}
			Line smallLine = new Line (pointX2, pointY2, pointX1, pointY1);
			getChildren().add(smallLine);
		}
	}
	@Override
	public void setWidth(double width) {
		super.setWidth(width);
		paintClock();
	}

	@Override
	public void setHeight(double height) {
		super.setHeight(height);
		paintClock();
	}
}

public class Aydan_Pirani_ClockPane extends Application {
	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		// Create a clock and a label
		ClockPane clock = new ClockPane();
		String timeString = clock.getHour() + ":" + clock.getMinute() 
		+ ":" + clock.getSecond();
		Label lblCurrentTime = new Label(timeString);

		// Place clock and label in border pane
		BorderPane pane = new BorderPane();
		pane.setCenter(clock);
		pane.setBottom(lblCurrentTime);
		BorderPane.setAlignment(lblCurrentTime, Pos.TOP_CENTER);

		// Create a scene and place it in the stage
		Scene scene = new Scene(pane, 250, 250);
		primaryStage.setTitle("DisplayClock"); // Set the stage title
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