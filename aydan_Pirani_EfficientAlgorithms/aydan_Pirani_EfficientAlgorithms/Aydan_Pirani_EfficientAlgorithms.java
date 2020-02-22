/*
 * Name: Aydan Pirani
 * Assignment: Efficient Algorithms
 * Assignment Desc: Make a program that can sort 100 data points.
 */
package aydan_Pirani_EfficientAlgorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

class PointComparator implements Comparator<Point2D> {

	@Override
	public int compare(Point2D p1, Point2D p2) { //Compares points based on their x-coordinatess
		if (p1.getX() > p2.getX()) {
			return 1;
		} else if (p1.getX() == p2.getX()) {
			if (p1.getY() > p2.getY()) {
				return 1;
			} else {
				return -1;
			}
		} else {
			return -1;
		}
	}

}

class Point2D extends PointComparator{
	double x; //x-coordinate
	double y; //y-coordinate

	public Point2D() { //Creates a point with random x and y coordinates
		do {
			this.x = 10 * Math.random();
			this.y = 10 * Math.random();
		} while (!(x < 100 && y < 100)); //Ensures that the coordinates are both less than 100
	}

	public Point2D(double x, double y) { //Creates a point given certain coordinates
		this.x = x;
		this.y = y;	
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

}

class Pair {
	Point2D p1;
	Point2D p2;

	public Pair() { //Creates a pair of two random points
		this.p1 = new Point2D();
		this.p2 = new Point2D();
	}

	public Pair(Point2D p1, Point2D p2) { //Creates a pair given 2 points
		this.p1 = p1;
		this.p2 = p2;
	}

	public Point2D getP1() {
		return p1;
	}

	public void setP1(Point2D p1) {
		this.p1 = p1;
	}

	public Point2D getP2() {
		return p2;
	}

	public void setP2(Point2D p2) {
		this.p2 = p2;
	}

	public double getDistance() { //Returns the distance between the two points
		double changeInX = p2.getX() - p1.getX();
		double changeInY = p2.getY() - p1.getY();
		return (Math.sqrt(changeInX * changeInX + changeInY * changeInY));
	}

}

public class Aydan_Pirani_EfficientAlgorithms{
	static double smallestDistance = 20;
	static double smallestL = 20;
	static double smallestR = 20;
	static double midpoint;

	public static void main(String[] args) {
		Point2D[] array = new Point2D[100];
		for (int i = 0; i < 100; i++) {
			array[i] = new Point2D();
		}
		Pair closestPoints = getClosestPair(array);

		System.out.println("Closest pair: (" + closestPoints.p1.getX() + ", " + closestPoints.p1.getX()
		+ ") and (" + closestPoints.p2.getX() + ", " + closestPoints.p2.getY() + ")");
		System.out.println("Distance between closest points: " + distance(closestPoints.p1, closestPoints.p2));
	}

	// Return the distance of the closest pair of points
	public static Pair getClosestPair(double[] [] points) {
		for (int i = 0; i < points.length; i++) { //Converts doubles to Point2D format
			try { //Accounts for multiple lengths in the second array, need 2 points
				Point2D[] revisedPoints = new Point2D [points.length];
				return getClosestPair(revisedPoints); //Uses the closestPair method for Point2D
			} catch (Exception e) {
				System.out.println("The input array was not formatted correctly: there were "
						+ "multiple coordinates assigned to each point. ");
			}
		}
		return null;
	}

	// Return the distance of the closest pair of points
	public static Pair getClosestPair(Point2D[] points) {
		Point2D[] pointsOrderedOnX = points;
		Point2D[] pointsOrderedOnY = new Point2D[points.length];

		Arrays.sort(pointsOrderedOnX, new PointComparator());

		//Sorts y-coordinates by flipping coordinates, sorting for x, then flipping again
		for (int i = 0; i < points.length; i++) { //Switches x and y coordinates
			pointsOrderedOnY[i] = new Point2D(points[i].getY(), points[i].getX());
		}

		Arrays.sort(pointsOrderedOnY, new PointComparator());

		for (int i = 0; i < points.length; i++) { //Flips the points back after sorting
			double newX = pointsOrderedOnY[i].getY();
			double newY = pointsOrderedOnY[i].getX();
			pointsOrderedOnY[i].setX(newX); pointsOrderedOnY[i].setY(newY);
		}

		return distance (pointsOrderedOnX, 0, points.length-1, pointsOrderedOnY);
	}

	//Return the distance of the closest pair of points in pointsOrderedOnX[low, high]
	//Recursive method, does not change pointsOrderedOnX or pointsOrderedOnY later on
	public static Pair distance(Point2D[] pointsOrderedOnX, int low, int high, Point2D[] pointsOrderedOnY) {
		Pair output = new Pair();
		if (high - low == 2) { //Base case #1
			output = new Pair (pointsOrderedOnX[low], pointsOrderedOnX[low+1]);
			if (output.getDistance() < smallestDistance) {
				smallestDistance = output.getDistance();
			}
			return output;
		} else if (high - low == 3) { //Base case #2
			Pair pAB = new Pair (pointsOrderedOnX[low+0], pointsOrderedOnX[low+1]);
			Pair pAC = new Pair (pointsOrderedOnX[low+0], pointsOrderedOnX[low+2]);
			Pair pBC = new Pair (pointsOrderedOnX[low+1], pointsOrderedOnX[low+2]);

			//Checks for largest pair in the base case, and returns it
			if (pAB.getDistance() > pAC.getDistance() & pAB.getDistance() > pBC.getDistance()) {
				if (pAB.getDistance() < smallestDistance) {
					smallestDistance = pAB.getDistance();
				}
				return pAB;
			} else if (pBC.getDistance() > pAC.getDistance() & pBC.getDistance() > pAB.getDistance()){
				if (pBC.getDistance() < smallestDistance) {
					smallestDistance = pBC.getDistance();
				}
				return pBC;
			} else if (pAC.getDistance() > pAB.getDistance() & pAC.getDistance() > pBC.getDistance()){
				if (pAC.getDistance() < smallestDistance) {
					smallestDistance = pAC.getDistance();
				}
				return pAC;
			}
		} else { //Recursive call
			Pair rSide = distance (pointsOrderedOnX, 0, high/2, pointsOrderedOnY);
			smallestR = smallestDistance;
			smallestDistance = 20; //Resets smallestDistance
			Pair lSide = distance (pointsOrderedOnX, (low+high)/2, high, pointsOrderedOnY);
			smallestL = smallestDistance;

			double deviationDistance = Math.min(smallestR, smallestL);
			if (deviationDistance == smallestR) {
				output = rSide;
			} else {
				output = lSide;
			}
			midpoint = (pointsOrderedOnX[(high-low)/2].getX() + pointsOrderedOnX[(high-low)/2 +1].getX())/2;
			ArrayList<Point2D> temporaryArray = new ArrayList<>();
			for (int i = 0; i < high-low; i++) {
				if (pointsOrderedOnY[i].getX() < midpoint + deviationDistance && pointsOrderedOnY[i].getX() > midpoint - deviationDistance) {
					temporaryArray.add(pointsOrderedOnY[i]);
				}
			}
			Point2D[] pointsInStrip = new Point2D[temporaryArray.size()];
			for (int i=0; i < temporaryArray.size(); i++) {
				pointsInStrip[i] = temporaryArray.get(i);
			}

			int r = 0;
			for (Point2D p : pointsInStrip) {
				while (r < pointsInStrip.length && pointsInStrip[r].getY() <= p.getY() - deviationDistance) {
					r++;
				}	
				while (r < pointsInStrip.length && Math.abs(pointsInStrip[r].getY()-p.getY()) <= deviationDistance) {
					if (distance (pointsInStrip[r], p) > deviationDistance) {
						deviationDistance = distance (pointsInStrip[r], p);
						output = new Pair(pointsInStrip[r], p);
					}
					r++;
				}
			}
		}
		return output;
	}

	//Compute the distance between two points p1 and p2
	public static double distance (Point2D p1, Point2D p2) {
		double changeInX = p2.getX() - p1.getX();
		double changeInY = p2.getY() - p1.getY();
		return (Math.sqrt(changeInX * changeInX + changeInY * changeInY));
	}

	// Compute the distance between points (x1, y1) and (x2, y2)
	public static double distance(double x1, double y1, double x2, double y2) {
		double changeInX = x2 - x1;
		double changeInY = x2 - x1;
		return (Math.sqrt(changeInX * changeInX + changeInY * changeInY));

	}
}
