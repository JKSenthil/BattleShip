import java.util.*;

public class BattleShip {
	public static void main(String[] args){
		//init keyboard input stream
		Scanner input = new Scanner(System.in);
		
		//init misc variables
		String userInput = "";
		char inputLetter = 'z';
		int inputNumber = 0;
		int rowNum = 999, columnNum = 999, check = 999;
		
		//init more stuff
		String[] shipNames = {"destroyer", "submarine", "cruiser", "battleship", "carrier"};
		String[] shipSizes = {"2", "3", "3", "4", "5"};
		
		//init all the arrays
		boolean[][] playerGrid = new boolean[10][10];
		boolean[][] playerHitGrid = new boolean[10][10];
		boolean[][] computerGrid = new boolean[10][10];
		boolean[][] computerHitGrid = new boolean[10][10];
		
		//arrays for algorithm efficiency
		char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
		int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		
		//greets user
		System.out.println("Welcome to the BattleShip program!");
		
		//sets all the arrays to false
		for(int x = 0; x<10; x++){
			for(int y = 0; y<10; y++){
				playerGrid[x][y] = false;
				playerHitGrid[x][y] = false;
				computerGrid[x][y] = false;
				computerHitGrid[x][y] = false;
			}
		}
		
		//----------------------code for player to set up ship location--------------------------//
		//displays the board
		System.out.println("");
		System.out.print("   ");
		for(int i = 0; i<10; i++){
			System.out.print(numbers[i] + "   ");
		}
		System.out.println("");
		for(int i = 0; i<10; i++){
			System.out.print(letters[i]);
			System.out.print("  ");
			for(int x = 0; x<10; x++){
				System.out.print("0" + "   ");
			}
			System.out.println("");
		}
		
		//user input
		for(int a = 0; a<5; a++){
			System.out.println("\nWhat direction would you like the " + shipNames[a] + " to go? (" + shipSizes[a] + " spaces)");
			System.out.println("1) Horizontal");
			System.out.println("2) Vertical");
			userInput = input.nextLine();
			if(userInput.equalsIgnoreCase("1")){
				System.out.println("What square would you like to place the top left corner of the ship on? (enter letter then number, no space)");
				userInput = input.nextLine();
				inputLetter = userInput.charAt(0);
				inputNumber = Character.getNumericValue(userInput.charAt(1));
				for(int i = 0; i<10; i++){
					if(inputLetter == letters[i]){
						rowNum = i;
						break;
					}
				}
				for(int i = 0; i<10; i++){
					if(inputNumber == numbers[i]){
						columnNum = i;
						break;
					}
				}
				if(a==0){
					playerGrid[rowNum][columnNum] = true;
					playerGrid[rowNum][columnNum+1] = true;
				}else if(a==1){
					playerGrid[rowNum][columnNum] = true;
					playerGrid[rowNum][columnNum+1] = true;
					playerGrid[rowNum][columnNum+2] = true;
				}else if(a==2){
					playerGrid[rowNum][columnNum] = true;
					playerGrid[rowNum][columnNum+1] = true;
					playerGrid[rowNum][columnNum+2] = true;
				}else if(a==3){
					playerGrid[rowNum][columnNum] = true;
					playerGrid[rowNum][columnNum+1] = true;
					playerGrid[rowNum][columnNum+2] = true;
					playerGrid[rowNum][columnNum+3] = true;
				}else if(a==4){
					playerGrid[rowNum][columnNum] = true;
					playerGrid[rowNum][columnNum+1] = true;
					playerGrid[rowNum][columnNum+2] = true;
					playerGrid[rowNum][columnNum+3] = true;
					playerGrid[rowNum][columnNum+4] = true;
				}

			}else if(userInput.equalsIgnoreCase("2")){
				System.out.println("What square would you like to place the top of the ship on? (enter letter then number, no space)");
				userInput = input.nextLine();
				inputLetter = userInput.charAt(0);
				inputNumber = Character.getNumericValue(userInput.charAt(1));
				for(int i = 0; i<10; i++){
					if(inputLetter == letters[i]){
						rowNum = i;
						break;
					}
				}
				for(int i = 0; i<10; i++){
					if(inputNumber == numbers[i]){
						columnNum = i;
						break;
					}
				}
				if(a==0){
					playerGrid[rowNum][columnNum] = true;
					playerGrid[rowNum+1][columnNum] = true;
				}else if(a==1){
					playerGrid[rowNum][columnNum] = true;
					playerGrid[rowNum+1][columnNum] = true;
					playerGrid[rowNum+2][columnNum] = true;
				}else if(a==2){
					playerGrid[rowNum][columnNum] = true;
					playerGrid[rowNum+1][columnNum] = true;
					playerGrid[rowNum+2][columnNum] = true;
				}else if(a==3){
					playerGrid[rowNum][columnNum] = true;
					playerGrid[rowNum+1][columnNum] = true;
					playerGrid[rowNum+2][columnNum] = true;
					playerGrid[rowNum+3][columnNum] = true;
				}else if(a==4){
					playerGrid[rowNum][columnNum] = true;
					playerGrid[rowNum+1][columnNum] = true;
					playerGrid[rowNum+2][columnNum] = true;
					playerGrid[rowNum+3][columnNum] = true;
					playerGrid[rowNum+4][columnNum] = true;
				}
			}else{
				System.out.println("Please enter a valid number");
			}
			System.out.println("");
			System.out.print("   ");
			for(int i = 0; i<10; i++){
				System.out.print(numbers[i] + "   ");
			}
			System.out.println("");
			for(int i = 0; i<10; i++){
				System.out.print(letters[i]);
				System.out.print("  ");
				for(int x = 0; x<10; x++){
					if(playerGrid[i][x]){
						System.out.print("S" + "   ");
					}else{
						System.out.print("0" + "   ");
					}
				}
				System.out.println("");
			}
		}
	}
}
