/*
1. Abstract Class
Calculate Cumulative Grade Point Average (CGPA) using Abstract Class

An abstract class is a class that is declared by the abstract keyword, and it may or may not include abstract methods. 
Abstract classes cannot be instantiated but can be subclassed.

There are three classes: Student, Aided, and SelfFinance.

Task:

Make the Student class abstract.
Declare one abstract method in the Student class – String result(String MarksOfStudent).(This method should be declared as public)
Override the abstract method(result()), and implement it in the Aided and SelfFinance classes by extending the abstract Student class.
Calculate the CGPA of the student, and return the CGPA in string format (up to two decimal places) for 
both Aided and SelfFinance classes.


Students are categorized as aided and self. Input (MarksOfStudent) differs based on the type of student.

Aided Student:

There are three sets of marks given for aided students, Subject marks, NCC marks, and Sports marks. 
The three sets are separated by “|”.

Individual Subject marks are separated by comma (“,”). Each mark is comprised of the subject marks and credit points, 
which are separated by space (“ ”).

In NCC marks, there are three sets of strings. The first one indicates if a student is involved in NCC 
or not (if 0, you need not consider the subject marks and credit points). 
The second one is subject marks, and the third one is credit points.

For Sports marks, follow the same procedure as NCC marks.


Self-Finance  Student:

Everything remains the same as in the case of aided students, except that NCC marks are not awarded.



Calculating CGPA:

formula for cgpa =   

In the numerator, the credit point is entered. The grade point should be calculated from the Subject, NCC, or Sports marks.

In the denominator, the credit point refers to the total of the credit points of all subjects.             



Calculating Grade Point:         

Range of Marks

Grade Point

75-100

9.0-10.0

60-74

8.0-8.9

50-59

7.0-7.9

40-49

6.0-6.9

Below 40

0



Calculate the corresponding grade point for the given mark, proportional to the given range. 
For example, the corresponding grade point for “80” is “9.2” and "53" is "7.3".
(take grade point upto 1 decimal place for calculation)

Perform all calculations in double type. After calculating the CGPA, 
return the CGPA in string format (up to two decimal places) in the result function for Aided and SelfFinance  class. 



Note: 100 is the maximum mark, and 5 is the maximum credit point.



Aided Student:

Sample Input:

100 5,100 5,100 5|1,100,5|0,100,5

Sample Output:

10.00

Explanation:

CGPA=  




STEP 1: Checking the availability of NCC and Sports mark, NCC mark was awarded due to 1 in the first string(1,100,5), 
sports mark was not awarded due to 0 in the first string(0,100,5)



STEP 2: = 5+5+5+5 = 20 (the total maximum credit points for four subjects including NCC )

Note: Grade point should be in one decimal place

STEP 3: Calculating the proportional grade point for marks. Student scored 100 in all subjects. 
Hence the corresponding grade point of 100 is 10.0

STEP 4: = ((10*5)+(10*5)+(10*5)+(10*5)) = 200    

STEP 5:  = 200/20 = 10.00 (upto two decimal places, use string format function)



Self-Finance  Student:

Sample Input:

100 5,100 5,53 5,76 3|0,100,5

Sample Output:

8.18

Explanation:

CGPA=  




STEP 1: Checking the availability of sports mark, sports mark was not awarded due to 0 in the first string(0,100,5)



STEP 2:  = 5+5+5+5 = 20 (the total maximum credit points for four subjects )

Note: Grade point should be in one decimal place

STEP 3: Calculating the proportional grade point for marks. 
The corresponding grade point of 100 is 10.0, the corresponding grade point of 53 is 7.3, 
where there are 9 numbers in 50-59(both inclusive) and 7.0-7.9(both inclusive), 9 /9=1, 
hence 0.1 increase of grade point(7.0-7.9) is same as the increase of 1 mark(50-59) 
and the corresponding grade point of 76 is 9.0, where there are 26 numbers in 75-100(both inclusive) 
and 11 numbers in  9.0-10.0(both inclusive), 26/11=2.36(approximately), 
hence 0.1 increase of grade point(9.0-10.0) is same as the increase of 2.36 mark(75-100).

STEP 4: = ((10*5)+(10*5)+(7.3*5)+(9.0*3)) = 163.5    

STEP 5: = 163.5/20 = 8.18 (upto two decimal places, use string format function)
 */
package com.example.demo.service;

public abstract class Student {



	public abstract String result(String marksOfStudent);

	public Double gradePoint(Integer marks) {
		Double grade=0.0;
		if(marks>=75 && marks<=100) {
			grade= 9.0+(marks-75)/25.0;
		}else if(marks>=60 && marks<=74) {
			grade=8.0 +(marks-60)/15.56;
		}else if(marks>=50 && marks<=59) {
			grade=7.0+(marks-50)/10.0;
		}else if(marks>=40 && marks<=49) {
			grade=6.0+(marks-40)/10.0;
		}
		return Double.parseDouble(String.format("%.1f", grade));
	}



}