/*
 * 1. Working with Multithreading
You will be given an array of size 300. 
The name of the array is threadArray, and the index of the array should be referenced by using a variable name i.

(Note: The variables threadArray, and i (variable name to access the index of the array) 
are declared in the predefined class solution).

You have to insert elements into the array using three threads by creating three classes, 
namely Task1, Task2, and Task3, respectively. The values to be inserted into the array are 0,1,2,3,.....299. 

You should override the run method in the threads. Three integers i, j, 
and k will be given, representing the number of elements each thread should append inside the given array.
Thread one should append 0 to i-1 inside the array, thread two should append i to i+j-1 inside the array, 
and the third thread should append i+j to 299 inside the array.
Threads one and two must run simultaneously, 
and the values of the threads one and two must be inserted inside the indices of the array from 0 to i+j-1 randomly. 
The third thread should start only after the first two threads have been executed completely.

Input Specifications:
The first line is an integer representing the number of elements to be inserted inside the array by thread one.
The second line is an integer representing the number of elements to be inserted inside the array by thread two.
The third line is an integer representing the number of elements to be inserted inside the array by thread three.

Output Specifications:
The output should be true if the processes are executed in the correct order. Otherwise, it should be false.

Sample Input:
80
130
90

Sample Output:
true (if both the threads one and two have run simultaneously and the third thread has run after them), 
or false (if threads one and two have not run simultaneously and thread three has started before threads one 
and two have got completed).
 */

package com.example.demo.service;
import java.util.Scanner;

class Task1 extends Thread {
	public static int a;
	public static int beg;



	public void run() {
		for (int i = 0; i < a; i++) {
			MultiThreadSolution.threadArray[i] = i;
		}
	}
}



class Task2 extends Thread {
	public static int a;
	public static int beg;



	public void run() {
		int val=0;
		for (int i = beg; i < (a+beg-1); i++) {
			MultiThreadSolution.threadArray[i]=i;
			MultiThreadSolution.threadArray[val++] = MultiThreadSolution.threadArray[i];
		}
	}
}



class Task3 extends Thread {
	public static int a;
	public static int beg;



	public void run() {
		for (int i =beg; i < 300; i++) {
			MultiThreadSolution.threadArray[i] = i;
		}
	}
}

public class MultiThreadSolution {
		public static final int[] threadArray = new int[300];
		public static volatile String i = 0+"";
		public boolean test() throws InterruptedException
		{
			Task1 task1 = new Task1();
			Task2 task2 = new Task2();
			Task3 task3 = new Task3();
			Thread task2Thread = new Thread(task2);
			Thread task3Thread = new Thread(task3);
			task1.start();
			task2Thread.start();
			task1.join();
			task2Thread.join();
			task3Thread.start();
			int first = Task1.a+Task2.a;
			int containsSecondThread = Task1.a;
			String oneAndTwo = "";
			String sizeOfTask1 = "";
			for(int i=0;i<first;i++)
			{
				oneAndTwo += threadArray[i]+" ";
			}
			for(int i=0;i<containsSecondThread;i++)
			{
				sizeOfTask1 += threadArray[i]+" ";
			}
			int begOfTask3 = Task3.beg;
			String checkingString = "";
			for(int i=begOfTask3;i<threadArray.length;i++)
			{
				checkingString += i + " ";
			}
			String task3String = "";
			for(int j = begOfTask3;j<threadArray.length;j++)
			{
				task3String += threadArray[j]+" ";
			}
			if((!oneAndTwo.contains(begOfTask3+"") && sizeOfTask1.contains(Task2.beg+"")) && task3String.equals(checkingString))
			{
				return true;
			}
			return false;
		}
		public static void main(String[] args) throws InterruptedException 
		{
			Scanner sc= new Scanner(System.in);
			MultiThreadSolution solution = new MultiThreadSolution();
			int one = sc.nextInt();
			Task1.a = one;
			Task1.beg = 0;
			int two = sc.nextInt();
			Task2.a = two;
			Task2.beg = one;
			int three = sc.nextInt();
			Task3.a = three;
			Task3.beg = one+two;
			System.out.print(solution.test());
		}
}