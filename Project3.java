// Laura Bedoyan
// Project 3
// 12/13/2022
// COMP 610

//Project 3 creates a brute force algo to determine optimum soln to load balancing problem. It
//goes through each of the different accumulated combinations of each load item and compares it to the ideal
//load split whiich is the totalload/2. We will pick the best combinations for each round and keep track in an array.
//We will pick the max value in that array because it would have been the best out of each round.
//*************************************************************************
//import necessary libraries
import java.util.*;
import java.io.*;
import java.lang.Math;
import java.util.Arrays;


//class project3
public class Project3 {
//create a static global class constant variable N which will hold the value N of input array.
   static int N = 0;
    
    //Main method:
   public static void main(String[] args) {
   //Get the data from input file
      int[] theData = getInput(); 
      //find the half of the total
      int half = findHalf(theData);
      //print out opt
      findOpt(theData, half);
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
     
//calculate half
     public static int findHalf(int[]theData)
     
     {
      int half = 0;
      for (int x = 0; x < N; x++) 
      {
            half = half +theData[x];
      }
      
      half = half/2;
         
      return half;
      }
    //finnd opt takes the data and half as input
      public static void findOpt(int[]theData, int half)
      
      {
      //create new array that holds the best for each round
         int[] allVal = new int[N];
         //use three different pointers
         int pMax = 0;
         int cMax = 0;
         int rMax = 0;
         //loop through each value to start
         for (int x = 0; x < theData.length; x++) 
         {
         //initialize pointer variables. pmax will keep track of the sum and cmax and rmax will keep track of the difference
         //from half.
               pMax = pMax + theData[x];
               cMax = half - pMax;
               //take abs value
               cMax = Math.abs(cMax);
               //send allVal = pmax
               allVal[x] = pMax;
           
              for (int y = 0; y < theData.length; y++) 
               {     
               //try all different combos
                   if(x!=y)
                   {
                   //accumulate pmax
                     pMax = pMax + theData[y];
                     //ssubtract from half
                     rMax = half - pMax;
                     //take abs value
                     rMax = Math.abs(rMax);
                     //if new score is less than old score then change allval to the new pmax
                     if (rMax<cMax)
                     {
                         allVal[x] = pMax;  
                     
                     }
                     //otherwise dont change. And update cMax to rmax
                     cMax =rMax;
                                          
                   } 
                              
               }
            //reset
               pMax = 0;
               cMax = 0;
               rMax = 0;
              
         }
         //find the max value of allVal array
         int maxValue = allVal[0];
         for (int i = 1; i < allVal.length; i++) 
         {
            if (allVal[i] > maxValue) 
            {
               maxValue = allVal[i];
            }
        }
     //print out the max value which is the worst that the brute force could do
     //to make the load closest to half 
    System.out.println(maxValue);
    
     
      }
      
      
    
  }