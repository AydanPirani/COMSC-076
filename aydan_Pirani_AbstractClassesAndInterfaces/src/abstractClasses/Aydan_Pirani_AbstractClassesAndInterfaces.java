/* Name: Aydan Pirani
 * Assignment: Abstract Classes and Interfaces (#1)
 * 
 */

package abstractClasses;

import java.util.Date;
import java.util.Scanner;

abstract class GeometricObject {
	private String color; //Color of the object
	private boolean filled; //Filled status of the object
	private Date date; //Date the object was created

	//No-argument constructor, no variables
	public GeometricObject() {
		color = "white";
		filled = false;
		date = new java.util.Date();		
	}

	//Constructor w/ arguments, variables provided
	public GeometricObject(String color, boolean filled) {
		this.color = color;
		this.filled = filled;
	}

	//Returns the color of the geometric object
	public String getColor() {
		return color;
	}

	//Changes the color of the geometric object
	public void setColor(String color) {
		this.color = color;
	}

	//Returns the boolean value of filled
	public boolean isFilled() {
		return filled;
	}

	//Changes the boolean value of filled
	public void setFilled (boolean filled) {
		this.filled = filled;
	}

	//Returns the date on which a geometric object was created
	public Date getDateCreated(){
		return date;
	}

	//Returns the results of the program in a string
	public String toString() {
		return ("Color: " + color + "; Filled: " + filled);
	}

	//Define abstract methods for later (area and perimeter)
	public abstract double getPerimeter();
	public abstract double getArea();
}

class Triangle extends GeometricObject {
	private double[] sideLengths = {0.0, 0.0, 0.0}; //New double array of side lengths

	public Triangle(String color, boolean filled, double[] sideLengths) {
		super(color, filled); //Creates a geometric object
		System.arraycopy(sideLengths, 0, this.sideLengths, 0, 3); //Sets side lengths (see lines 72-80)
	}

	//Returns the side lengths of the triangle as an array
	public double[] getSideLengths() {
		return sideLengths;
	}

	//Sets the side lengths by using a arraycopy to copy an input array into the object array
	public void setSideLengths(double[] sideLengths) {
		System.arraycopy(sideLengths, 0, this.sideLengths, 0, 3);
	}

	//Gets the perimeter
	public double getPerimeter() {
		return (sideLengths[0] + sideLengths[1] + sideLengths[2]);
	}

	//Uses Heron's formula to calculate the total area of the triangle
	public double getArea() {
		double s = getPerimeter()/2;
		return (Math.pow((s * (s-sideLengths[0]) * (s-sideLengths[1]) * (s-sideLengths[2])), 0.5));
	}

	@Override //Adds on to the string produced by the GeometricObject class
	public String toString() {
		return (super.toString() + "; Perimeter: " + getPerimeter() + "; Area: " + getArea());
	}
}

public class Aydan_Pirani_AbstractClassesAndInterfaces {

	public static void main(String args[]) {
		double[] sideLengths = {0.0, 0.0, 0.0}; //Initializes an array for the side lengths
		String color; //Initializes string for color
		boolean filled = false; //Initializes boolean for filled
		GeometricObject triangle; //Initializes the object
		Scanner input = new Scanner(System.in);

		try {
			System.out.print("Enter three side lengths of the triangle: "); //Asks for side lengths
			for (int n = 0; n < 3; n++) {
				double temporaryVar = input.nextDouble(); //Sets side n, up to 3 (3 side lengths)
				if (temporaryVar < 0) {
					System.out.println("Side lengths cannot be 0 or negative. Please try again.");//Error handling
					System.exit(0);
				} else {
					sideLengths[n] = temporaryVar; //Sets the input to the side lengths array
				}
			}
		} catch(Exception inputMismatch) { //Error handling, unrealistic side lengths entered
			System.out.println("The side lengths are not numbers. Please try again.");
			System.exit(0);
		}
		if (checkValidity(sideLengths)) { //Checks if  the side lengths of a triangle are valid
			System.out.print("Enter color of the triangle: "); //Asks for color
			input.nextLine(); //Skips the current line of input
			color = input.nextLine(); //Sets color to the next line

			try {
				System.out.print("Enter whether or not the triangle is filled: "); //Asks for filled
				filled = input.nextBoolean();
			} catch(Exception inputMismatch) { //Error handling, improper "boolean" input
				System.out.println("The input is not a boolean. Please try again.");
				System.exit(0);
			}


			triangle = new Triangle(color, filled, sideLengths); //Creates a new triangle
			System.out.println(triangle.toString());//Prints out the final method
		} else {
			System.out.println("The side lengths entered do not compose a real triangle. Please "
					+ "try again."); //Error message
			System.exit(0);
		}
		input.close();
	}

	//Checks if the triangle is a valid triangle by comparing one side length to the sum of the others
	public static boolean checkValidity(double[] sideLengths) {
		if (sideLengths[0] < (sideLengths[1] + sideLengths[2])) {
			if (sideLengths[1] < (sideLengths[0] + sideLengths[2])) {
				if (sideLengths[2] < sideLengths[0] + sideLengths[1]) {
					return true;
				}
			}
		}
		return false;
	}
}