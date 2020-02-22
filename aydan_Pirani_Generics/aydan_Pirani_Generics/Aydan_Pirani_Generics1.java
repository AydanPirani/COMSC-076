/*
 * Name: Aydan Pirani
 * Assignment: Generics
 * Assignment Description: Make a program that can sort various arrayLists
 */
package aydan_Pirani_Generics;

import java.util.ArrayList;

public class Aydan_Pirani_Generics1 {

	public static void main(String[] args) {
		ArrayList<Integer> integerTest = new ArrayList<>(); //Creates and sets int List
		integerTest.add(2); integerTest.add(4);	integerTest.add(3);
		
		ArrayList<Double> doubleTest = new ArrayList<>(); //Creates and sets double list
		doubleTest.add(3.4); doubleTest.add(1.2); doubleTest.add(-12.3);
		
		ArrayList<String> stringTest = new ArrayList<>(); //Creates and sets string list
		stringTest.add("Bob"); stringTest.add("Alice"); stringTest.add("Ted"); stringTest.add("Carol");
		
		sort(integerTest); System.out.println(integerTest);
		sort(doubleTest); System.out.println(doubleTest);
		sort(stringTest); System.out.println(stringTest);
	}

	public static <E extends Comparable <E>> void sort(ArrayList <E> list) {
		sort (list, 0); //Calls on the helper method
	}
	
	public static <E extends Comparable <E>> void sort(ArrayList <E> list, int recursionCount) {
		E currentTerm = list.get(recursionCount); //Sets the minimum term to start

		for (int i = recursionCount; i<list.size(); i++) { //Puts minimum value at recursionCount
			if ((currentTerm.compareTo(list.get(i))>0)){
				E temporaryTerm = list.get(i);
				list.set(i, currentTerm); list.set(recursionCount, temporaryTerm);
				currentTerm = temporaryTerm;
			}
		}

		if (recursionCount < list.size()-1) {
			sort(list, recursionCount+1); //Calls same method
		}
	}
}

