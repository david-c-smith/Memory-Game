import java.util.*;
import java.io.*;

/**
 * David Smith: dsmith52@students.towson.edu
 * COSC 237 - Homework 3
 * Overview: The basic premise of the game is to select a card and then find a matching 
 * card.  The goal of the game is to complete the game with the fewest amount of moves.
 * The program will also ask the player to enter their name to be displayed and then output
 * on a leaderboard file in program's the working directory.
 * Input: player name, option number, row, column 
 * Output:  Playing boards
 * Variables: int x, char[][]array1, char[][]values, char[] fourByThree, char[] fourByFour, char[] fiveByFour, char[] sixBySix,
 * char[] sevenBySix, int r, int c, int q, int row, int col, int whichRow1, int whichRow2, int whichCol1, int whichCol2, int length,
 * int width, int total, String line, File log, char a, char b, int numMoves, String prompt 
 * Plan
 * 1. Create playing boards 
 * 2. Game rules
 * 3. Create leaderboard
 * 4. Input validation methods (getInt, getRow, getCol)
 * 
 * @version 1.0 due 2/23/15
 */

public class Memory { 
  public static void main (String[] args) throws InterruptedException {
    Scanner input = new Scanner(System.in); 
    
    /*Menu method is called and asks user to input which playing board they would like to use.
     * Each of the five cases account for a different sized playing board. 
     */
    int x = 1;
    while (x != 0){
      menu();
      String prompt = "Please enter your option #: ";
      x = getInt(input, prompt);
      switch(x) {
        case 0:
          System.out.println("Goodbye!");
          break;
        case 1:
          //4x3 playing board
          char[] fourByThree = {'!','#','!','@','&','#','%','@','+','%','&','+'};
          char[][]array1 = new char[4][3];
          char[][]values = new char[4][3];
          fillArray(array1);
          System.out.println(); 
          fill(values, fourByThree);
          play(values, array1, input); 
          break;
        case 2:
          //4x4 playing board
          char[] fourByFour = {'&','!','@','&','#','+','%','!','%','+','@','=','#','*','*','='};
          array1 = new char[4][4];
          values = new char[4][4];
          fillArray(array1);
          System.out.println(); 
          fill(values, fourByFour);
          play(values, array1, input); 
          break;
        case 3:
          //5x4 playing board
          char[] fiveByFour = {'!','@','!','%','&','+','*','=','@','&','=','-','%','$','1','*','$','+','-','1'};
          array1 = new char[5][4];
          values = new char[5][4];
          fillArray(array1);
          System.out.println(); 
          fill(values, fiveByFour);
          play(values, array1, input); 
          break;
        case 4:
          //6x6 playing board
          char[] sixBySix = {'!','@','#','&','*','$','+','%','-','=','1','&','!','2','#','*','$','1','%','-','=','+','@','3','2','4','5','5','3','6','7','4','6','8','7','8'};
          array1 = new char[6][6];
          values = new char[6][6];
          fillArray(array1);
          System.out.println(); 
          fill(values, sixBySix);
          play(values, array1, input); 
          break;
        case 5:
          //7x6 playing board
          char[] sevenBySix = {'!','@','#','$','%','@','$','&','*','%','+','!','#','=','-','&','=','1','*','-','+','1','4','2','4','3','5','7','6','2','7','6','3','8','9','A','5','9','8','B','A','B'};
          array1 = new char[7][6];
          values = new char[7][6];
          fillArray(array1);
          System.out.println(); 
          fill(values, sevenBySix);
          play(values, array1, input); 
          break;
      }   
    }  
  }
  
  //Prints array.
  public static void printArray(char[][] array1) {
    for (int r = 0; r < array1.length; r++){ 
      for (int c = 0; c < array1[r].length; c++) 
        System.out.print(array1[r][c] + "\t"); 
      System.out.println(); 
    } 
  }
  
  //Fills array of X's for initial playing board.
  public static void fillArray(char[][] array1) {
    for (int r = 0; r < array1.length; r++) {
      for(int c = 0; c < array1[r].length; c++) {
        array1[r][c] = 'X';
      }
    }
  }
  
  //Fills playing board.
  public static void fill(char[][] values, char[] array1) {
    int count = 0;
    for(int r = 0; r < values.length; r++) {
      for(int c = 0; c < values[r].length; c++) {
        values[r][c] = array1[count];
        count++;
      }
    }
  } 
  
  //Menu allows user to choose from 5 different playing board sizes or exit the game.
  public static void menu() {
    System.out.println("How many cards would you like to play with?");
    System.out.println("____________________________________________");
    System.out.println();
    System.out.println("1) 12 cards (4x3 playing board)");
    System.out.println("2) 16 cards (4x4 playing board)");
    System.out.println("3) 20 cards (5x4 playing board)");
    System.out.println("4) 36 cards (6x6 playing board)");
    System.out.println("5) 42 cards (7x6 playing board)");
    System.out.println("0) EXIT");
    System.out.println();
  }  
  
  //Validates menu option input.
  public static int getInt(Scanner input, String prompt) {
    System.out.print(prompt);
    while(!input.hasNextInt()) {
      input.next();
      System.err.println("Not an integer, try again:");
      System.out.println(prompt);
    }
    return input.nextInt();
  }
  
  /*getRow method validates input for column entry.  Any invalid input outside of the bounds
   *of the playing board will result in the program reprompting the user for their input.  
   */
  public static int getRow(Scanner input, char[][] array1) {
    int q = 1;
    String prompt = "Please select a row";
    int row = getInt(input, prompt);
    if(row > array1.length || row <= 0) {
      System.err.println("Please enter a row within the bounds of the playing board");
      q = 0;
    }
    while(q == 0) {
      row = getInt(input, prompt);
      if(row > array1.length || row <= 0) {
        System.err.println("Please enter a row within the bounds of the playing board");
      }
      else {
        q = 1;
      }
    }
    return row;
  }
  
  /*getCol method validates input for column entry.  Any invalid input outside of the bounds
   *of the playing board will result in the program reprompting the user for their input.  
   */
  public static int getCol(Scanner input, char[][] array1) {
    String prompt = "Please select a column";
    int q = 1;
    int col = getInt(input, prompt);
    if(col > array1[0].length || col <= 0) {
      System.err.println("Please enter a column within the bounds of the playing board");
      q = 0;
    }
    while(q == 0) {
      col = getInt(input, prompt);
      if(col > array1[0].length || col <= 0) {
        System.err.println("Please enter a column within the bounds of the playing board");
      }
      else {
        q = 1;
      }
    }
    return col;
  }
  
  //If card1 = card2 you have found a match; otherwise you do not have a match.
  public static boolean equals(char a, char b) {
    return a == b;
  }
  
  /*If there are no remaining X's on the playing board, the player has won the game, otherwise, the player must continue
   *matching cards.
   */
  public static boolean win(char[][] array1) {
    int count = 0;
    for(int r = 0; r < array1.length; r++) {
      for(int c = 0; c < array1[r].length; c++) {
        if(array1[r][c] == 'X') {
          count++;
        }
      }
    }
    if(count == 0) {
      return true;
    }
    return false;
  }
  
  
  /*The play method accounts for the gameplay of "Memory".  Within the method,  
   *the rows and columns entered by the user will be checked for a match.
   *
   * 
   */
  public static void play(char[][] values, char[][] array1, Scanner input) {
    printArray(array1);
    int length = array1.length;
    int width = array1[0].length;
    int total = length * width;
    int numMoves = 0;
    //When array1 is completely filled with the matching pairs, the player has won. 
    while(win(array1) == false) {
      int whichRow1 = getRow(input, array1);
      int whichCol1 = getCol(input, array1); 
      array1[whichRow1 - 1][whichCol1 - 1] = values[whichRow1 - 1][whichCol1 - 1];
      printArray(array1);
      int whichRow2 = getRow(input, array1);
      int whichCol2 = getCol(input, array1);
      //Handles the error of a player submitting a previously entered row/column. 
      while(whichRow1 == whichRow2 && whichCol1 == whichCol2){
        System.err.println("Please do not enter the same row and column as before.");
        whichRow2 = getRow(input,array1);
        whichCol2 = getCol(input,array1);
      }
      array1[whichRow2 - 1][whichCol2 - 1] = values[whichRow2 - 1][whichCol2 - 1];
      printArray(array1);
      char a = array1[whichRow1 - 1][whichCol1 - 1];
      char b = array1[whichRow2 - 1][whichCol2 - 1];
      //If char a (first card picked) is not equal to b (second card picked), return "no match found".
      if(equals(a,b) == false) {
        System.out.println();
        System.err.println("No match...waiting 3 seconds then clearing screen: 3,2,1...");
        try {
          //Stops output for three seconds (clears the screen).
          Thread.sleep(3000); 
        } catch(InterruptedException ex) {
          Thread.currentThread().interrupt();
        }
        array1[whichRow1 - 1][whichCol1 - 1] = 'X';
        array1[whichRow2 - 1][whichCol2 - 1] = 'X';
        System.out.println();
        printArray(array1);
      }
      else {
        System.out.println();
        System.out.println("You found a match!");
      }
      numMoves++;
    }
    System.out.println("Congratulations! Total moves to complete: " +numMoves);
    System.out.println("Please enter your name to display on the leaderboard");
    String playerName = input.next();
    leaderboardWriter(numMoves, playerName, array1);
    leaderboardReader(numMoves, playerName, array1);
    System.out.println();
    System.out.println("Returning to main menu in 3,2,1...");
  }
  
  /*Creates new file named leaderboard.txt in working directory if the file does not already exist.
   *If the file does exist already, the player's name, size of the board used, and number of moves will
   *be written to the leaderboard.txt file without overwriting the previous recorded data.  
   */
  public static void leaderboardWriter(int numMoves, String playerName, char[][] array1) {
    File log = new File("leaderboard.txt");
    try {
      if(!log.exists()){
        System.out.println("New file created in working directory.");
        log.createNewFile();
      }  
      int boardSize = array1.length * array1[0].length; 
      FileWriter fileWriter = new FileWriter(log, true);
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
      bufferedWriter.write("| PlayerName: " +playerName + " | " + "BoardSize: " + boardSize + " | " + "NumberofMoves: " +numMoves + " | ");
      bufferedWriter.write("\n");
      bufferedWriter.close();
      System.out.println("Done.");
    } catch(IOException e) {
      System.err.println("Error. Could not record data.");
    }
  }
  
  //Reads from leaderboard.txt file created in the leaderboardWriter method and outputs leaderboard on screen.
  public static void leaderboardReader(int numMoves, String playerName, char[][] array1) {
    try {
      BufferedReader in = new BufferedReader(new FileReader("leaderboard.txt"));
      String line;
      //While there is nothing on the next line in the leaderboard.txt file, display score. 
      while((line = in.readLine()) != null) {
        System.out.println(line);
      }
      in.close();
    } catch(IOException e) {
      System.err.println("Error. Could not read data from file.");
    }
  }
}
