/*
   You will recreate a couple logic games shown in the Algorithms movie and you will ask which one the user would like to play. 
   Upon completion of the game, display who won and ask if they would like to play again and which version of the game. 
   You will need to use one class and a method dedicated to the game(s).

Chili Pepper:

   You will create a "bucket" containing 13 "chocolates" and a "chili pepper." 
   The user can select 1-3 chocolates out of the bucket, the computer will do the same until someone is left with the chili pepper. 
   Will it matter who goes first? Do you need to change the starting number of chocolates if the user goes first?

Nim:

   You will also create 5 "lanes" of "pieces" and each lane has that number of pieces on it; 
   i.e. lane 5 = 5 pieces, lane 4 = 4 pieces, etc.  
   The user and the computer can take any number of pieces from one lane. 
   The object is to not take the last piece. Will it matter who goes first?

Make sure you have instructions on how to play your game upon request.


*/
package games;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;


public class LogicGameGo{
	
	//create a scanner
	static Scanner scan = new Scanner(System.in);
	
	//used to keep track of chocolates
	static int choco = 13;
	
	//used to display nim game
	static String[][] myDisplay = {{"    ", "* ", "\n"},{"   ", "* ","* ","\n"},{"  ", "* ","* ","* ", "\n"},{" ", "* ", "* ", "* ", "* ", "\n"},{"", "* ","* ","* ","* ","* "}};
	
	//keep track of nim tokens
	static ArrayList <Integer> tokens = new ArrayList<Integer> (5);
	
	//keep track of how many tokens are on the table
	static int totalTokens = 15;
	
	public static void main(String str[]) throws IOException	{
		
		//continue prompting until user types y or n
		boolean responded = false;
		do {
		
			//give instructions
			System.out.println("Would you like to play a game? (y/n)");
			char ans = scan.next().charAt(0);
		
			//if y, start game
			if(ans == 'y') {
				responded = true;
				chooseGame();
			}
		
			//if n, end
			else if(ans == 'n') {
				System.out.print("Goodbye.");
				responded = true;
			}
		
			//remind user
			else {
				System.out.println("Enter a single character.");
			}
		}
		while(!responded);
		
	}  
	
	//select chili or nim game
	public static void chooseGame() throws InputMismatchException {
		
		//keep looping until 1 or 2 is entered
		boolean started = false;
		while(!started){
			try {
				//present options
				System.out.println("Press 1 to play Chili Pepper. Press 2 to play Nim. ");
			
				//removing this line results in an infinite loop 
				scan.nextLine();
				
				//store input
				int b = scan.nextInt();
				
				//make sure 1 or 2
				if(b == 1) {
					started = true;
					chili();
				}
				if(b == 2) {
					started = true;
					nim();
				}
				else {
					
					//remind user
					System.out.println("Enter 1 or 2. ");
				}
				
			}	
			//catch if not integer
			catch(InputMismatchException e) {
				System.out.println("Enter an integer. ");
			}	
		
		}
	}
	
	//chili game starting method
	public static void chili() throws InputMismatchException{
		
		//loop until 1 or 2 is entered
		boolean chiliTime = false;
		do {
			System.out.println("Enter 1 for the rules. Enter 2 to begin Chili.");
			try {
				
				//scan input
				scan.nextLine();
				int gameStart = scan.nextInt();
				if(gameStart == 1) {
					chiliTime = true;
					
					//call rules void function
					chiliRules();
					
				}
				if(gameStart == 2) {
					chiliTime = true;
					
					//game visual display 
					displayChili();
				}
			}
			
			//if not the right input, remind
			catch(InputMismatchException f) {
				System.out.println("Enter an integer.");
			}
		}
		while(!chiliTime);
				
		//keep looping until game is over
		boolean gameOver = false;
		
		//first move
		System.out.println("I will go first. I take one chocolate.");
		
		//call takeChoco 
		takeChoco(1);
		
		//display new board
		displayChili();
		
		//keep track of how many chocolates are taken
		int taken = 0;
		while(!gameOver) {
			System.out.println("How many chocolates would you like to take? (Enter 1-3)");
			boolean takes = false;
			
			//while the user has not taken anything
			while(!takes) {
				try {
					scan.nextLine();
					taken = scan.nextInt();
					
					//make sure they take the right amount
					if(taken > 0 && taken < 4) {
						takes = true;	
						takeChoco(taken);	
						displayChili();
					}
					else {
						System.out.println("You can take between 1 and 3 chocolates.");
					}
				}
				
				//if they don't put an integer
				catch(InputMismatchException g) {	
					System.out.println("Enter an integer.");
				}
			}
			
			//take the appropriate amount
			int myTurn = (4 - taken);
			System.out.print("My turn. I take ");
			System.out.println(myTurn);
			
			//call takeChoco and displayChili function
			takeChoco(myTurn);
			displayChili();
			if(choco == 0) {
				gameOver = true;
			}
		}
		System.out.println("Hooray, I won.");
		playAgain();
	}
	
	//explain game
	public static void chiliRules() {
		System.out.print("There are 13 chocolates and 1 chili in a jar. You and I will take turns "
				+ "selecting 1-4 chilis at a time, until one person is left with the chili at the "
				+ "end. \n");
	}
	
	//show amount of chocolates and pepper
	public static void displayChili() {
		String display = "";
		for(int i = 0; i < choco; i++) {
			display += "🍫 ";
		}
		display += "🌶️";
		System.out.println(display);
	}
	
	//take away chocolates
	public static void takeChoco(int pluck) {
		for(int i = 0; i < pluck; i++) {
			choco--;
		}
	}
	
	//starting method for nim
	public static void nim() throws InputMismatchException{
	
		boolean nimTime = false;
		while(!nimTime) {
		System.out.println("Enter 1 for the rules. Enter 2 to begin Nim.");
		try {
	
			//scan input
			scan.nextLine();
			int gameStart2 = scan.nextInt();
			if(gameStart2 == 1) {
				nimTime = true;
				
				//call rules void function
				nimRules();
				
			}
			if(gameStart2 == 2) {
				nimTime = true;
			}
		}
		
		//if not the right input, remind
		catch(InputMismatchException f) {
			System.out.println("Enter an integer.");
		}
		}	
		
		//populate tokens with initial values per row
		tokens.add(1);
		tokens.add(2);
		tokens.add(3);
		tokens.add(4);
		tokens.add(5);
		
		//show board
		displayNim();
		
		//first move, remove the top piece on the top row
		System.out.println("\nI will go first.");
		removePiece(0, 1, tokens);
		displayNim();
		
		//take turns until victory
		boolean victory = false;
		while(!victory) {
			
			//keep looping until valid move
			boolean validMove = false;
			while(!validMove) {
				try {
					
					//prompt to enter row 
					System.out.println("\nWhich row would you like to take from?");
					scan.nextLine();
					int takeRow = scan.nextInt() - 1;
					
					//make sure it is between 1 and 5
					if(takeRow > 4 || takeRow < 0) {
						System.out.println("That is not an option. Enter a number from 1 - 5.");
					}
					
					//if the row is empty
					else if(tokens.get(takeRow) == 0) {
						System.out.println("There are no items in that row. Select another number");
					}
					
					//if available row
					else{
						boolean correctAmount = false;
						while(!correctAmount) {
							try {
								
								//prompt to enter how many to take
								System.out.println("How many items would you like to remove?");
								scan.nextLine();
								int takeCount = scan.nextInt();
								
								//if more than available, ask again
								if(takeCount > tokens.get(takeRow)) {
									System.out.println("That is more than the available tokens.");
								}
								else {
									
									//call removePiece method, pass which row and how many to take
									removePiece(takeRow, takeCount, tokens);
									
									//display board
									displayNim();
									
									//break out of loop
									correctAmount = true;
									validMove = true;
								}
							}
							
							//if an invalid data type is entered for amount taken
							catch(InputMismatchException f) {
								System.out.print("Enter a single integer for how many you want to remove.");
							}
					}
					}
				}
				
				//if an invalid data type is entered for row to take from
				catch(InputMismatchException f) {
					System.out.println("Enter a single integer");
				}
			}
			
			
			//computer's turn
			System.out.println("\nMy turn!");
			
			//call myMove method
			//returns an array containing {row to take from, how many to to take}
			int[] myMove = nextMove();
			removePiece(myMove[0], myMove[1], tokens);
			
			//tell user move
			System.out.print("I take ");
			System.out.print(myMove[1]);
			System.out.print(" from row ");
			System.out.print(myMove[0] + 1);
			System.out.print(".\n");
			
			//display new board
			displayNim();
			
			//if there is only one left, declare victory
			if(totalTokens == 1) {
				victory = true;
				break;
			}
		}
		
		//declare victory
		System.out.println("\nHooray, I win!");
			
		//ask to play again
		playAgain();
	}
	
	//print the rules
	public static void nimRules() {
		System.out.println("There are 5 rows of pieces. The first has 1, the second has 2,\n"
				+ "so on so forth. You may take any amount of pieces from any row, but\n"
				+ "you may not select multiple pieces from different rows. The objective is to\n"
				+ "NOT take the last piece.");
	}
	
	//display the board
	//display using the ArrayList declared earlier
	public static void displayNim() {
		
		//loop through five times
		for(int i = 0; i < 5; i++) {
			
			//go through myDisplay array containing representation of board
			for(int j = 0; j < myDisplay[i].length; j++) {
			System.out.print(myDisplay[i][j]);
			}
		}
		
	}
	
	//remove pieces from nim game
	
	//remove display at index
	//int i represents row, int j represents how many to remove
	
	//int i is the row, int j is the count
	public static void removePiece(int i, int j, ArrayList<Integer> tkn) {
		
		//reset totalTokens to reflect new amount
		totalTokens = totalTokens - j;
		int removed = 0;
		int k = 1;
		while(removed != j) {
			if(myDisplay[i][k] == "* ") {
				myDisplay[i][k] = "  ";
				removed++;
			}
			k++;
		}
		
		//reset token array to remove j tokens from row i
		tkn.set(i, tkn.get(i) - j);
	}
	
	//calculate new move
	public static int[] nextMove() {
		int[] move = {-1, -1};
				
		int[][] components = {{0,0},{0,0},{0,0},{0,0},{0,0}};
		ArrayList<Integer> unmatched = new ArrayList<Integer>();
		ArrayList<Integer> index = new ArrayList<Integer>();
		ArrayList<Integer> filled = new ArrayList<Integer>();
		
		int emptyRows = 0;
		int k = 0;
		//populate components with the 1, 2, 4 "factors" of each row
		for(int i : tokens) {
			switch(i) {
			case 0:
				emptyRows++;
				break;
			case 1:
				components[k][0] = 1;
				filled.add(k);
				break;
			case 2:
				components[k][0] = 2;
				filled.add(k);
				break;
			case 3:
				components[k][0] = 1;
				components[k][1] = 2;
				filled.add(k);
				break;
			case 4: 
				components[k][0] = 4;
				filled.add(k);
				break;
			case 5:
				components[k][0] = 4;
				components[k][1] = 1;
				filled.add(k);
				break;
			}
			k++;
		}
		
		/*
		for(int i = 0; i < 5; i++) {
			System.out.print(components[i][0]);
			System.out.print(" ");
			System.out.println(components[i][1]);
			System.out.println("------");
		}
		*/
		
		//loop through components and find unmatched values
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 2; j++) {
				
				int takeAway = unmatched.indexOf(components[i][j]);
				
				//if the component does not have a pair, add to unmatched and index
				if(takeAway == -1 && components[i][j] != 0) {
					unmatched.add(components[i][j]);
					index.add(i);
				}
				//if the component does pair up, remove from unmatched and index
				if(takeAway != -1 && components[i][j] != 0) {
					unmatched.remove(takeAway);
					index.remove(takeAway);
				}
		
		//now, unmatched and index should hold info for values preventing nim sum 0
		
		}			
		}
		
		//System.out.println(unmatched);
		//System.out.println(index);
		
		//special case 1: one row is left
		//remove count - 1
		if(emptyRows == 4) {
			move[0] = index.get(0);
			move[1] = unmatched.get(0) - 1;
			return move;
		}
		
		
		//special case 2, the unmatched pairs are on the same row
		//remove the whole row
		if(index.size() == 2 && index.get(0) == index.get(1)) {
			move[0] = index.get(0);
			move[1] = tokens.get(index.get(0));
			return move;
			
		}
		
		//special case 3, 3 are unmatched (1, 2, 4)
		//remove 1 from the row with 4
		if(unmatched.size() == 3) {
			move[0] = index.indexOf(4);
			move[1] = 1;
			return move;
		}
		//special case 4, 3 rows left and 2 are identical
		//if the tokens at rows a and b are the same, remove everything from row c
				if(emptyRows == 2) {
					
					if(tokens.get(filled.get(0)) == tokens.get(filled.get(1))) {
						move[0] = filled.get(2);
						move[1] = tokens.get(filled.get(2));
						return move;
					}
					if(tokens.get(filled.get(0)) == tokens.get(filled.get(2))) {
						move[0] = filled.get(1);
						move[1] = tokens.get(filled.get(1));
						return move;
					}
					if(tokens.get(filled.get(1)) == tokens.get(filled.get(2))) {
						move[0] = filled.get(0);
						move[1] = tokens.get(filled.get(0));
						return move;
					}
				}
		
		//normal logic A, if two unmatched- 
		if(unmatched.size() == 2) {
			if(unmatched.get(0) > unmatched.get(1)) {
				//remove from the row with the larger unmatched value
				move[0] = index.get(0);
				//remove larger unmatched value minus the smaller unmatched value 
				move[1] = unmatched.get(0) - unmatched.get(1);
				return move;
			}
			if(unmatched.get(0) < unmatched.get(1)) {
				//remove from the row with the larger unmatched value
				move[0] = index.get(1);
				//remove larger unmatched value minus the smaller unmatched value 
				move[1] = unmatched.get(1) - unmatched.get(0);
				return move;
			}
		}
		
		
		
		//normal logic B, if one unmatched-
		if(unmatched.size() == 1) {
			move[0] = index.get(0);
			move[1] = unmatched.get(0);
			return move;
		}
		
		//in case something is wrong, for debugging
		return move;
	
	}
	
	public static void playAgain() {
		System.out.println("Would you like to play again? (y/n)");
		boolean restart = false;
		while(!restart) {
		try {
			//try checking for y or n
			scan.nextLine();
			char again = scan.next().charAt(0);
			if(again == 'y') {
				restart = true;
				//call chooseGame function again
				chooseGame();
			}
			else if(again == 'n') {
				restart = true;
			}
			else {
				System.out.println("Enter y or n");
			}
		}
		//if they don't enter a character
		catch(InputMismatchException e) {
			System.out.println("Enter a single character");
		}
		}
	}
}
		


	









