/*
 1. Generic Class
Getting Student Information using Generic Class       

A generic type is a class or interface that is parameterized over types.
In this challenge, you have to create two generic classes for an Array List.
There are three classes, StudentClass, StudentList, and ScoreList.


In StudentClass, there is a getQuery() function that contains the input data.
In getQuery (String studentData, String query), studentData is a dataset that differs based on the query type.
Query is the conditional string to perform an operation. 

There are five types of queries:         

Query Type 1:

Find student names beginning with a particular letter.  

Query Type 2:

Find students who have a specific blood group that is given in the input.

Query Type 3:

Find the number of students who scored higher than the specified threshold score.         

Query Type 4:

Find the average of student marks.

Query Type 5:

Find the average of student CGPA values. 

Note: In studentData, student data is separated by space (“ ”). In a query string, query data is separated by comma (“,”). The first character refers to the query type, and the second character refers to the conditional string (based on the query type).

For Query Type 1: 

Sample input:

studentData = Raja Ravi Vinay Rahul Arun 

query = 1,r

Sample output:

Raja

Ravi

Rahul 

Note: The output for query type 1 should end with a new line character.

For Query Type 2:

Sample input:

studentData = Raja Ravi Vinay Rahul Arun

query = 2,B- AB- B+ O+ B+,B+

Sample output :

Vinay

Arun

Note: The output for query type 2 should end with a new line character.

Explanation: In the studentData, "Raja Ravi Vinay Rahul Arun" are student names. In the query, "2" is query type and "B- AB- B+ O+ B+" are the respective blood groups of student names and B+ is the blood group we have to find. Hence Vinay and Arun are the students who have "B+" blood group.

For Query Type 3: 

Sample input:

studentData = 96 78 93 45 90 42 69 

query = 3,90

Sample output:

3 students scored 90 above

For Query Type 4: 

Sample input:

studentData = 96 78 93 45 90 42 69 

query = 4

Sample output:

73.29

For Query Type 5:

Sample input:

studentData = 5.6 6.7 8.9 5.8 

query = 5

Sample output:

6.75

In the StudentList class, you have to implement the functionalities for the first three types of queries using an Array List. 
Type should be generic. Create 6 methods.

In the ScoreList Class, you have to implement the functionalities of the next two types of queries using an Array List.
Type should be generic. It must extend from the Numbers class, and restrict the type (int, float, double, and long).
Create 4 methods.

The first three methods are common for both classes:  

   1. void addElement(Type) – Must reflect the add() function of the Array List.

   2. void removeElement(Type) - Must reflect the remove() function of the Array List.

   3. Type getElement(int) – Must reflect the get() function of the Array List. 

In StudentList,

  4. beginsWith(String) – Must return the names of students starting with the specified letter, for the names in the list.

  5. bloodGroupOf(String[],String) – Must return the names of students with the specified blood group, for the names in the list.

  6. int thresholdScore(int) – Must return the count of the number of students whose score is higher than the specified threshold score, for the scores in the list.

In ScoreList,

 4. double averageValues() – Must return the average of the set of values in the list.

Note: The methods mentioned above should be defined as public methods.
 */
package com.example.demo.service;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Ram
 */
public class StudentClassTest {
    
    StudentClass stud;
    
    public StudentClassTest() {
    }
    
    @BeforeEach
    public void setUp() {
        stud = new StudentClass();
    }
    
    
    @Test
    public void testGeneric1() {
        Method[] methods = StudentList.class.getMethods();
        int count=0;
        for (Method method : methods) {
            if(method.getName().equals("addElement"))
                count++;
            else if(method.getName().equals("removeElement"))
                count++;
            else if(method.getName().equals("getElement"))
                count++;
            else if(method.getName().equals("beginsWith"))
                count++;
            else if(method.getName().equals("bloodGroupOf"))
                count++;
            else if(method.getName().equals("thresholdScore"))
                count++;
                
        }
       if(count==6)
           assertEquals(0,0);
       else
           assertEquals(0,1);
       
    }
    
    @Test
    public void testGeneric2() {
        Method[] methods = ScoreList.class.getMethods();
        int count=0;
        for (Method method : methods) {
            if(method.getName().equals("addElement"))
                count++;
            else if(method.getName().equals("removeElement"))
                count++;
            else if(method.getName().equals("getElement"))
                count++;
            else if(method.getName().equals("averageValues"))
                count++;
            
                
        }
       if(count==4)
           assertEquals(0,0);
       else
           assertEquals(0,1);
       
    }
    
    
    public boolean testGeneric3(){
        Field[] fields1 = StudentList.class.getDeclaredFields(); 
        Field[] fields2 = ScoreList.class.getDeclaredFields(); 
        int flag=0;
        for(Field field : fields1){
            if(field.getType().toString().equals("class java.util.ArrayList")){
                flag++;
            }
        }
        for(Field field : fields2){
            if(field.getType().toString().equals("class java.util.ArrayList")){
                flag++;
            }
        }
        if(flag>=2)
            return true;
        else
            return false;
    }
    
    
    public boolean testGeneric4(){
        TypeVariable<Class<StudentList>>[] parameter1=StudentList.class.getTypeParameters();
        TypeVariable<Class<ScoreList>>[] parameter2=ScoreList.class.getTypeParameters();
        if(parameter1.length==1 && parameter2.length==1)
            return true;
        else
            return false;
    }

            
    @Test
    public void testGetQuery11() {
        if(testGeneric3() && testGeneric4() ){
        String expected = "Ram\nRahul\n";
        String actual = stud.getQuery("Ram Vijay Vinay Rahul Arun", "1,r");
        assertEquals(expected,actual);
        }
        else{
            assertEquals(0,1);
        }
            
    }
    
    @Test
    public void testGetQuery12() {
        if(testGeneric3() && testGeneric4() ){
        String expected = "Vijay\nVinay\n";
        String actual = stud.getQuery("Ram Vijay Vinay Rahul Arun", "1,v");
        assertEquals(expected,actual);
        }
        else{
            assertEquals(0,1);
        }
            
    }
    
    @Test
    public void testGetQuery21() {
        if(testGeneric3() && testGeneric4()){
        String expected = "Ram\nVinay\nArun\n";
        String actual = stud.getQuery("Ram Vijay Vinay Rahul Arun", "2,B- AB- B- O+ B-,B-");
        assertEquals(expected,actual);
        }
        else{
            assertEquals(0,1);
        }
    }
    
    @Test
    public void testGetQuery22() {
        if(testGeneric3() && testGeneric4()){
        String expected = "Vijay\nVinay\n";
        String actual = stud.getQuery("Ram Vijay Vinay Rahul Arun", "2,B- AB- AB- O+ B-,AB-");
        assertEquals(expected,actual);
        }
        else{
            assertEquals(0,1);
        }
    }
    
    @Test
    public void testGetQuery31() {
        if(testGeneric3() && testGeneric4()){
        String expected = "3 students scored 70 above";
        String actual = stud.getQuery("90 78 69 45 90 42 69", "3,70");
        assertEquals(expected,actual);
        }
        else{
            assertEquals(0,1);
        }
    }
    
    @Test
    public void testGetQuery32() {
        if(testGeneric3() && testGeneric4()){
        String expected = "2 students scored 90 above";
        String actual = stud.getQuery("90 78 69 45 90", "3,90");
        assertEquals(expected,actual);
        }
        else{
            assertEquals(0,1);
        }
    }
    
    @Test
    public void testGetQuery41() {
        if(testGeneric3() && testGeneric4()){
        String expected = "69.00";
        String actual = stud.getQuery("90 78 69 45 90 42 69", "4");
        assertEquals(expected,actual);
        }
        else{
            assertEquals(0,1);
        }
        
    }
    @Test
    public void testGetQuery42() {
        if(testGeneric3() && testGeneric4()){
        String expected = "73.29";
        String actual = stud.getQuery("96 78 93 45 90 42 69", "4");
        assertEquals(expected,actual);
        }
        else{
            assertEquals(0,1);
        }
        
    }
    
    @Test
    public void testGetQuery51() {
        if(testGeneric3() && testGeneric4()){
        String expected = "6.23";
        String actual = stud.getQuery("6.9 7.3 4.5", "5");
        assertEquals(expected,actual);
        }
        else{
            assertEquals(0,1);
        }
    }
    @Test
    public void testGetQuery52() {
        if(testGeneric3() && testGeneric4()){
        String expected = "6.32";
        String actual = stud.getQuery("6.4 7.9 7.4 3.4 6.5", "5");
        assertEquals(expected,actual);
        }
        else{
            assertEquals(0,1);
        }
    }
    
}
