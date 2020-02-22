/*
 * Name: Aydan Pirani
 * Assignment: Lists, Stacks, and Queues
 * Assignment Desc: Make a program that creates and sorts through 100 points
 */
package aydan_Pirani_ListsStacksQueues;

import java.util.Arrays;
import java.util.Comparator;

class CompareY implements Comparator<Point> { //Creates a class that compares

	@Override
	public int compare(Point p1, Point p2) { //Default compare method
		if (p1.getyPoint()>p2.getyPoint()) {
			return 1;
		} else if (p1.getyPoint() < p2.getyPoint()) {
			return -1;
		} else {
			if (p1.getxPoint() > p2.yPoint) {
				return 1;
			}	else {
				return -1;
			} 
		}
	}
}

class PointComparator implements Comparator<Point> { //Creates a comparator

	@Override
	public int compare(Point p1, Point p2) { //Compares the two points
		if (p1.getxPoint() > p2.getxPoint()) {
			return 1; //Returns 1 if p1 is "greater"
		} else if (p1.getxPoint() < p2.getxPoint()) {
			return -1; //Returns -1 if p2 is "greater"
		} else {
			if (p1.getyPoint() > p2.getyPoint()) {
				return 1;
			} else {
				return -1;
			}
		}
	}

}

class Point extends PointComparator { //Class to define the object Point
	double xPoint; //Initial vars
	double yPoint;

	Point(){ //No-arg constructor
		this.xPoint = 100 * Math.random();
		this.yPoint = 100 * Math.random();
	}

	public Point(double xPoint, double yPoint) { //Constructor with args
		this.xPoint = xPoint;
		this.yPoint = yPoint;
	}

	public double getxPoint() { //Gets the X-coordinate
		return xPoint;
	}

	public void setxPoint(double xPoint) { //Sets the X-coordinate
		this.xPoint = xPoint;
	}

	public double getyPoint() { //Gets the Y-coordinate
		return yPoint;
	}

	public void setyPoint(double yPoint) { //Sets the Y-coordinate
		this.yPoint = yPoint;
	}

}

public class Aydan_Pirani_ListsStacksQueues{

	public static void main(String[] args) {
		Point[] listX = new Point[100]; //Initializes a new 2D array of Double
		Point[] listY = new Point[100]; //Initializes another 2D array for Y-sort

		for (int i = 0; i < 100; i++) {
			Point temporaryPoint = new Point();
			listX[i] = temporaryPoint;
			Point secondTemporaryPoint = new Point(temporaryPoint.getyPoint(), temporaryPoint.getxPoint());
			listY[i] = secondTemporaryPoint;
		} //Creates and places the coordinates inside the array

		Arrays.sort(listX, new PointComparator());
		Arrays.sort(listY, new PointComparator());
		

		System.out.println("When sorting by X-coordinate: ");
		for (int i = 0; i < 100; i ++) { //Prints the points in coordinate form
			System.out.println("(" + listX[i].getxPoint() + ", " + listX[i].getyPoint() + ")");
		}

		System.out.println();
		System.out.println();

		System.out.println("When sorting by Y-coordinate: ");
		for (int i = 0; i < 100; i ++) { //Prints the points in coordinate form
			System.out.println("(" + listY[i].getyPoint() + ", " + listY[i].getxPoint() + ")");
		}

	}

}