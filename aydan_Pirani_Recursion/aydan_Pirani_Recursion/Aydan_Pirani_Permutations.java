/*
 * Name: Aydan Pirani
 * Assignment: #4 (Recursion), Part 2
 * Assignment Desc: Make a program that finds all possible permutations of a string
 */


package aydan_Pirani_Recursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Aydan_Pirani_Permutations {
	static ArrayList<String> arrayList = new ArrayList<String>();

	//Utility method to remove any duplicates from an ArrayList
	public static void removeDuplicates(ArrayList<String> arrayList) {
		Collections.sort(arrayList); //Sorts ArrayList
		for (int n = 0; n < arrayList.size()-1; n++) { //Checks for consecutive indexes
			if (arrayList.get(n).contentEquals(arrayList.get(n+1))) {
				arrayList.remove(n);
				n=0;
			}
		}
		printList(arrayList);
	}
	
	//Prints every item in a list
	public static void printList(ArrayList<String> arrayList) {
		for (int n = 0; n < arrayList.size()-1; n++) {
			System.out.println(arrayList.get(n));
		}
	}

	public static void displayPermutation(String s) {
		displayPermutation("", s);
	}

	//Helper method
	public static void displayPermutation(String s1, String s2) {
		int length = s2.length();
		if (s2.length() == 2) { //Base case
			arrayList.add(s1 + s2.substring(length-2, length-1) + s2.substring(length-1, length));
			arrayList.add(s1 + s2.substring(length-1, length) + s2.substring(length-2, length-1));
		} else {
			int n = 0; 
			String var1;
			String var2;
			while (n < length) {
				var1 = s1 + Character.toString(s2.charAt(n));
				var2 = s2.substring(0, n) + s2.substring(n+1);
				displayPermutation(var1, var2);
				n++;
			}
		}
	}

	public static void main (String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the string to find permutations of: ");
		String userInput = input.nextLine();
		displayPermutation(userInput); //Creates an ArrayList
		removeDuplicates(arrayList); //Removes  duplicates from ArrayList
		input.close();
	}
}
