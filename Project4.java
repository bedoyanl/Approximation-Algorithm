// Laura Bedoyan
// Project 4
// 12/13/2022
// COMP 610

//Project 4 creates a greedy algorithm that loads items if one load is less than the other.
//There are two ways to implement this algorithm: 1. Sort the loads largest to smallest. 2. Dont
//sort largest to smallest. You can see that when you sort the loads largest to smallest you get 
//an answer that is more close to the optimum value (Project three).
//*************************************************************************
//import necessary libraries
import java.util.*;
import java.io.*;
import java.lang.Math;
import java.util.Arrays;
import java.util.Collections;


//class project4
public class Project4 {
//create a static global class constant variable N which will hold the value N of input array.
   static int N = 0;
    
    //Main method:
   public static void main(String[] args) {
   //Get the data from input file
      int[] theData = getInput();
    //Get the data from input file
      int[] theDat2 = getInput();
      //Sort the second copy of the input dataset
      theDat2 = sortOpt(theDat2);
      //find the best way to load the items given that one item is placed on load with smallest weight
      //sorted
      int a = findOpt(theDat2);   
      //not sorted   
      int b = findOpt(theData);
      //print
      System.out.printf("%d %d", b, a);
    }

   // Reading from input.txt and filling theData-- adapted from Professor Noga's Project 0 file. returns the 
   //data in an array theData
   private static int[] getInput() {
      Scanner sc = null;
      try {
         // Note the filename is input.txt without any mention of the path
         sc = new Scanner(new File("input.txt"));
      } 
      //if file is not found
      catch (FileNotFoundException e) 
      {
         System.out.println("Did you forget the input file?");
         System.exit(1);
      }
      //initialize size to be the first integer from input file
      int pos = 0, size = sc.nextInt(), counter=0;
      N =size;
     //create the new 2d array but with size four to put the areas in that column
      int[] theData = new int[size];
      
      //add in the data
      while (sc.hasNextInt()) 
      {
         for (int row = 0; row < size; row++) 
         { 
            theData[row] = sc.nextInt();  
            
            counter ++;
         } 
      }
    
     return theData;
     }
  
    //Sum the values of a load. When we do this, we know what the sum is and therefore 
    //know which laodd to place the new item to. 
    
    public static int sumArray(List<Integer> array)
    {
     
      int sum = 0;
         for (int x = 0; x < array.size(); x++) 
      { 
            sum = array.get(x)+sum;
           
      }
      
      return sum;
    }
    
    //Sort the array largest to smallest. Using the Array.sort method puts items smallest to largest
    //so we must reverse the order into a new array newi and then return it as the new sortedd array.
      public static int [] sortOpt(int[]theData)
      
      {
            int a = N;
            Arrays.sort(theData);
             int[] newi = new int[a];
            
             for (int i = 0; i < N; i++) 
             {
                  newi[a - 1] = theData[i];
                  a = a - 1;
               }
            

            return newi;
      }
      
      //input is the input array. return best sum (max of the two) after all loads all placed.
      public static int findOpt(int[]theData)
      
      {
         
       //create new array that holds the best for each round
       //load one
         List<Integer> oneVal = new ArrayList<Integer>();
       //load two
         List<Integer> twoVal = new ArrayList<Integer>();
         //variable to keep track of one load's sum
         int ov = 0;
         //variable to keep track of other load's sum
         int tv = 0;
         
         int pmax = 0;
         
         //add first item to load one
          oneVal.add(theData[0]);
          
          //start on the next load until end
            for (int x = 1; x < theData.length; x++) 
            {
            //accumulate and add to left load
               ov = sumArray(oneVal);
            //accumulate and add to right load
               tv = sumArray(twoVal);
            //if left load less than right load then add x (new ) it
               if (ov<tv)
               {
                  oneVal.add(theData[x]);

               }
               //else add it to the right load
             
               else 
               {
                     twoVal.add(theData[x]);
               
               }
               //reset the sum variables
               ov = 0;
               tv = 0;
         
                     }
            //final variables to compare to each other
            int so = sumArray(oneVal);
            int st = sumArray(twoVal);
            //return the max
            if(so<st){return st;}
            else {return so;}
        
         }
   
  }