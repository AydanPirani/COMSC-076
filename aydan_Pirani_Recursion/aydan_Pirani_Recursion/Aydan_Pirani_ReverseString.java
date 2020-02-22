/*
 * Name: Aydan Pirani
 * Assignment: #4 (Recursion)
 * Assignment Desc: Make a program that uses recursion to flip a string
 */

package aydan_Pirani_Recursion;

import java.util.Scanner;

public class Aydan_Pirani_ReverseString {
	static String test = "";
	
	public static void reverseDisplay(String value) {
		reverseDisplay(value, value.length());
	}
	
	//Reverses the display 
	public static void reverseDisplay(String value, int high) {
		int counter = high-1;
		test = test + Character.toString(value.charAt(counter));
		if (counter > 0) {
			reverseDisplay(value, high-1);
		} else {
			System.out.println(test);
		}
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the string to reverse: ");
		String userInput = input.nextLine();
		reverseDisplay(userInput);
		input.close();
	}
}
