import java.util.*;

public class BattleShip {
    public static void main(String[] args){
        //init keyboard input stream
        Scanner input = new Scanner(System.in);

        //init misc variables
        String userInput = "";
        char inputLetter = 'z';
        int inputNumber = 0;
        int inputNumber2 = 0;
        int rowNum = 999, columnNum = 999;

        //player v AI
        int playerHits = 0;
        int computerHits = 0;

        //init more stuff
        String[] shipNames = {"destroyer", "submarine", "cruiser", "battleship", "carrier"};
        String[] shipSizes = {"2", "3", "3", "4", "5"};

        //init all the arrays
        boolean[][] playerGrid = new boolean[10][10];
        boolean[][] playerHitGrid = new boolean[10][10];
        boolean[][] computerGrid = new boolean[10][10];
        boolean[][] computerHitGrid = new boolean[10][10];

        //variable set up for intelligent computer AI
        boolean isHit = false;
        boolean right = false, left = false, up = false, down = false;
        boolean firstStep = false, secondStep = false;
        int baseRow = 0, baseColumn = 0;
        int count = 1;
        int counter = 2;

        //sets up a boolean to control repeat loops
        boolean loop = true;
        boolean letterLegit = false;
        boolean changeArrays = true;

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
                System.out.print(" " + "   ");
            }
            System.out.println("");
        }

        for(int a = 0; a<5; a++){
            System.out.println("\nWhat direction would you like the " + shipNames[a] + " to go? (" + shipSizes[a] + " spaces)");
            System.out.println("1) Horizontal");
            System.out.println("2) Vertical");
            userInput = input.nextLine();
            int shipSize = Integer.parseInt(shipSizes[a]);
            if(userInput.equalsIgnoreCase("1")){
                do{
                    System.out.println("What square would you like to place the top left corner of the ship on? (enter letter (capatilized) then number, no spaces)");
                    userInput = input.nextLine();
                    if(userInput.length() <=1 || userInput.length() > 3){
                        System.out.println("Invalid input. Please try again: ");
                    }else{
                        inputLetter = userInput.charAt(0);
                        inputNumber = Character.getNumericValue(userInput.charAt(1));
                        //checks if letter is legit
                        for(int i = 0; i<10; i++){
                            if(inputLetter == letters[i]){
                                letterLegit = true;
                                break;
                            }
                        }
                        if(inputNumber == 1){
                            if(userInput.length() == 3){
                                inputNumber2 = Character.getNumericValue(userInput.charAt(2));
                                inputNumber = (inputNumber*10) + inputNumber2;
                            }
                        }
                        if(inputNumber > (10 - (shipSize-1)) || !letterLegit || inputNumber<=0){
                            System.out.println("Invalid input. Please enter again:");
                        }else{
                            loop = false;
                        }
                    }
                }while(loop);
                loop = true;
                letterLegit = false;
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
                    if(playerGrid[rowNum][columnNum] || playerGrid[rowNum][columnNum+1]){
                        System.out.println("The coordinate is already taken by another ship! Please try again: ");
                        a-=1;
                        changeArrays = false;
                    }
                }else if(a==1){
                    if(playerGrid[rowNum][columnNum] || playerGrid[rowNum][columnNum+1] || playerGrid[rowNum][columnNum+2]){
                        System.out.println("The coordinate is already taken by another ship! Please try again: ");
                        a-=1;
                        changeArrays = false;
                    }
                }else if(a==2){
                    if(playerGrid[rowNum][columnNum] || playerGrid[rowNum][columnNum+1] || playerGrid[rowNum][columnNum+2]){
                        System.out.println("The coordinate is already taken by another ship! Please try again: ");
                        a-=1;
                        changeArrays = false;
                    }
                }else if(a==3){
                    if(playerGrid[rowNum][columnNum] || playerGrid[rowNum][columnNum+1] || playerGrid[rowNum][columnNum+2] || playerGrid[rowNum][columnNum+3]){
                        System.out.println("The coordinate is already taken by another ship! Please try again: ");
                        a-=1;
                        changeArrays = false;
                    }
                }else if(a==4){
                    if(playerGrid[rowNum][columnNum] || playerGrid[rowNum][columnNum+1] || playerGrid[rowNum][columnNum+2] || playerGrid[rowNum][columnNum+3] || playerGrid[rowNum][columnNum+4]){
                        System.out.println("The coordinate is already taken by another ship! Please try again: ");
                        a-=1;
                        changeArrays = false;
                    }
                }
                if(changeArrays){
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
                }
                changeArrays = true;
            }else if(userInput.equalsIgnoreCase("2")){
                int alphaNum = 999;
                do{
                    System.out.println("What square would you like to place the top of the ship on? (enter letter (capatilized) then number, no spaces)");
                    userInput = input.nextLine();
                    if(userInput.length() <=1 || userInput.length() > 3){
                        System.out.println("Invalid input. Please try again: ");
                    }else{
                        inputLetter = userInput.charAt(0);
                        inputNumber = Character.getNumericValue(userInput.charAt(1));
                        //checks if letter is legit
                        for(int i = 0; i<10; i++){
                            if(inputLetter == letters[i]){
                                letterLegit = true;
                                alphaNum = i+1;
                                break;
                            }
                        }
                        if(inputNumber == 1){
                            if(userInput.length() == 3){
                                inputNumber2 = Character.getNumericValue(userInput.charAt(2));
                                inputNumber = (inputNumber*10) + inputNumber2;
                            }
                        }
                        if(alphaNum > (10-(shipSize-1)) || !letterLegit || inputNumber<=0){
                            System.out.println("Invalid input. Please enter again:");
                        }else{
                            loop = false;
                        }
                    }
                }while(loop);
                loop = true;
                letterLegit = false;
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
                    if(playerGrid[rowNum][columnNum] || playerGrid[rowNum+1][columnNum]){
                        System.out.println("The coordinate is already taken by another ship! Please try again: ");
                        a-=1;
                        changeArrays = false;
                    }
                }else if(a==1){
                    if(playerGrid[rowNum][columnNum] || playerGrid[rowNum+1][columnNum] || playerGrid[rowNum+2][columnNum]){
                        System.out.println("The coordinate is already taken by another ship! Please try again: ");
                        a-=1;
                        changeArrays = false;
                    }
                }else if(a==2){
                    if(playerGrid[rowNum][columnNum] || playerGrid[rowNum+1][columnNum] || playerGrid[rowNum+2][columnNum]){
                        System.out.println("The coordinate is already taken by another ship! Please try again: ");
                        a-=1;
                        changeArrays = false;
                    }
                }else if(a==3){
                    if(playerGrid[rowNum][columnNum] || playerGrid[rowNum+1][columnNum] || playerGrid[rowNum+2][columnNum] || playerGrid[rowNum+3][columnNum]){
                        System.out.println("The coordinate is already taken by another ship! Please try again: ");
                        a-=1;
                        changeArrays = false;
                    }
                }else if(a==4){
                    if(playerGrid[rowNum][columnNum] || playerGrid[rowNum+1][columnNum] || playerGrid[rowNum+2][columnNum] || playerGrid[rowNum+3][columnNum] || playerGrid[rowNum+4][columnNum]){
                        System.out.println("The coordinate is already taken by another ship! Please try again: ");
                        a-=1;
                        changeArrays = false;
                    }
                }
                if(changeArrays){
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
                }
                changeArrays = true;
            }else{
                System.out.println("Please enter a valid number");
                a-=1; 
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
                        System.out.print("*" + "   ");
                    }else{
                        System.out.print(" " + "   ");
                    }
                }
                System.out.println("");
            }
        }

        //------------------------------------------set up computer's ship positions-----------------------------------------------//
        System.out.println("\nNow setting up the computer's ships...");
        for(int a = 0; a<5; a++){
            Random randInt = new Random();
            Random randChar = new Random();
            boolean doAgain = false;
            changeArrays = true;
            //0 is horizontal, 1 is vertical
            int direction = randInt.nextInt(2);
            if(direction==0){
                if(a==0){
                    //2 spaces
                    columnNum = randInt.nextInt(9);
                    rowNum = randInt.nextInt(10);
                    if(computerGrid[rowNum][columnNum] || computerGrid[rowNum][columnNum+1]){
                        a-=1;
                        changeArrays = false;
                    }
                }else if(a==1){
                    //3 spaces
                    columnNum = randInt.nextInt(8);
                    rowNum = randInt.nextInt(10);
                    if(computerGrid[rowNum][columnNum] || computerGrid[rowNum][columnNum+1] || computerGrid[rowNum][columnNum+2]){
                        a-=1;
                        changeArrays = false;
                    }
                }else if(a==2){
                    //3 spaces
                    columnNum = randInt.nextInt(8);
                    rowNum = randInt.nextInt(10);
                    if(computerGrid[rowNum][columnNum] || computerGrid[rowNum][columnNum+1] || computerGrid[rowNum][columnNum+2]){
                        a-=1;
                        changeArrays = false;
                    }
                }else if(a==3){
                    //4 spaces
                    columnNum = randInt.nextInt(7);
                    rowNum = randInt.nextInt(10);
                    if(computerGrid[rowNum][columnNum] || computerGrid[rowNum][columnNum+1] || computerGrid[rowNum][columnNum+2] || computerGrid[rowNum][columnNum+3]){
                        a-=1;
                        changeArrays = false;
                    }
                }else if(a==4){
                    //5 spaces
                    columnNum = randInt.nextInt(6);
                    rowNum = randInt.nextInt(10);
                    if(computerGrid[rowNum][columnNum] || computerGrid[rowNum][columnNum+1] || computerGrid[rowNum][columnNum+2] || computerGrid[rowNum][columnNum+3] || computerGrid[rowNum][columnNum+4]){
                        a-=1;
                        changeArrays = false;
                    }
                }
                if(changeArrays){
                    if(a==0){
                        computerGrid[rowNum][columnNum] = true;
                        computerGrid[rowNum][columnNum+1] = true;
                    }else if(a==1){
                        computerGrid[rowNum][columnNum] = true;
                        computerGrid[rowNum][columnNum+1] = true;
                        computerGrid[rowNum][columnNum+2] = true;
                    }else if(a==2){
                        computerGrid[rowNum][columnNum] = true;
                        computerGrid[rowNum][columnNum+1] = true;
                        computerGrid[rowNum][columnNum+2] = true;
                    }else if(a==3){
                        computerGrid[rowNum][columnNum] = true;
                        computerGrid[rowNum][columnNum+1] = true;
                        computerGrid[rowNum][columnNum+2] = true;
                        computerGrid[rowNum][columnNum+3] = true;
                    }else if(a==4){
                        computerGrid[rowNum][columnNum] = true;
                        computerGrid[rowNum][columnNum+1] = true;
                        computerGrid[rowNum][columnNum+2] = true;
                        computerGrid[rowNum][columnNum+3] = true;
                        computerGrid[rowNum][columnNum+4] = true;
                    }
                }
            }else if(direction==1){
                if(a==0){
                    //2 spaces
                    columnNum = randInt.nextInt(10);
                    rowNum = randInt.nextInt(9);
                    if(computerGrid[rowNum][columnNum] || computerGrid[rowNum+1][columnNum]){
                        a-=1;
                        changeArrays = false;
                    }
                }else if(a==1){
                    //3 spaces
                    columnNum = randInt.nextInt(10);
                    rowNum = randInt.nextInt(8);
                    if(computerGrid[rowNum][columnNum] || computerGrid[rowNum+1][columnNum] || computerGrid[rowNum+2][columnNum]){
                        a-=1;
                        changeArrays = false;
                    }
                }else if(a==2){
                    //3 spaces
                    columnNum = randInt.nextInt(10);
                    rowNum = randInt.nextInt(8);
                    if(computerGrid[rowNum][columnNum] || computerGrid[rowNum+1][columnNum] || computerGrid[rowNum+2][columnNum]){
                        a-=1;
                        changeArrays = false;
                    }
                }else if(a==3){
                    //4 spaces
                    columnNum = randInt.nextInt(10);
                    rowNum = randInt.nextInt(7);
                    if(computerGrid[rowNum][columnNum] || computerGrid[rowNum+1][columnNum] || computerGrid[rowNum+2][columnNum] || computerGrid[rowNum+3][columnNum]){
                        a-=1;
                        changeArrays = false;
                    }
                }else if(a==4){
                    //5 spaces
                    columnNum = randInt.nextInt(10);
                    rowNum = randInt.nextInt(6);
                    if(computerGrid[rowNum][columnNum] || computerGrid[rowNum+1][columnNum] || computerGrid[rowNum+2][columnNum] || computerGrid[rowNum+3][columnNum] || computerGrid[rowNum+4][columnNum]){
                        a-=1;
                        changeArrays = false;
                    }
                }
                if(changeArrays){
                    if(a==0){
                        computerGrid[rowNum][columnNum] = true;
                        computerGrid[rowNum+1][columnNum] = true;
                    }else if(a==1){
                        computerGrid[rowNum][columnNum] = true;
                        computerGrid[rowNum+1][columnNum] = true;
                        computerGrid[rowNum+2][columnNum] = true;
                    }else if(a==2){
                        computerGrid[rowNum][columnNum] = true;
                        computerGrid[rowNum+1][columnNum] = true;
                        computerGrid[rowNum+2][columnNum] = true;
                    }else if(a==3){
                        computerGrid[rowNum][columnNum] = true;
                        computerGrid[rowNum+1][columnNum] = true;
                        computerGrid[rowNum+2][columnNum] = true;
                        computerGrid[rowNum+3][columnNum] = true;
                    }else if(a==4){
                        computerGrid[rowNum][columnNum] = true;
                        computerGrid[rowNum+1][columnNum] = true;
                        computerGrid[rowNum+2][columnNum] = true;
                        computerGrid[rowNum+3][columnNum] = true;
                        computerGrid[rowNum+4][columnNum] = true;
                    }
                }
            }
        }

        //-----------------------------------------------test display of the computer's battleship board-----------------------------------------//
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
                if(computerGrid[i][x]){
                    System.out.print("*" + "   ");
                }else{
                    System.out.print(" " + "   ");
                }
            }
            System.out.println("");
        }
        System.out.println("Computer ships have been set!");
        System.out.println("");

        //-------------------------------------------combat begins!!!--------------------------------------------------------------//
        loop = true;
        do{
            System.out.println("\f");
            System.out.println("Player Hit Grid");
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
                    if(playerHitGrid[i][x] && computerGrid[i][x]){
                        System.out.print("H" + "   ");
                    }else if(playerHitGrid[i][x]){
                        System.out.print("X" + "   ");
                    }else{
                        System.out.print(" " + "   ");
                    }
                }
                System.out.println("");
            }
            boolean repeat = false;
            do{
                repeat = false;
                letterLegit = false;
                //-----------------------------------PLAYERS TURN----------------------------------//
                System.out.println("Where would you like to hit?");
                userInput = input.nextLine();
                if(userInput.length() <=1 || userInput.length() > 3){
                    System.out.println("Invalid input. Please try again: ");
                    repeat = true;
                }else if(userInput.length() == 3){
                    inputLetter = userInput.charAt(0);
                    inputNumber = Character.getNumericValue(userInput.charAt(1));
                    inputNumber2 = Character.getNumericValue(userInput.charAt(2));
                    inputNumber = (inputNumber*10) + inputNumber2;
                    for(int i = 0; i<10; i++){
                        if(inputLetter == letters[i]){
                            letterLegit = true;
                            break;
                        }
                    }
                    if(!letterLegit || inputNumber < 1){
                        System.out.println("Invalid input. Please try again: ");
                        repeat = true;
                    }
                }else{
                    inputLetter = userInput.charAt(0);
                    inputNumber = Character.getNumericValue(userInput.charAt(1));
                    for(int i = 0; i<10; i++){
                        if(inputLetter == letters[i]){
                            letterLegit = true;
                            break;
                        }
                    }
                    if(!letterLegit || inputNumber < 1){
                        System.out.println("Invalid input. Please try again: ");
                        repeat = true;
                    }
                }
            }while(repeat);
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
            playerHitGrid[rowNum][columnNum] = true;
            try{
                Thread.sleep(2600);
            }catch(Exception e){
                System.out.println("\fThere was an error processing your game. Please restart.");
            }
            System.out.print("   ");
            for(int i = 0; i<10; i++){
                System.out.print(numbers[i] + "   ");
            }
            System.out.println("");
            for(int i = 0; i<10; i++){
                System.out.print(letters[i]);
                System.out.print("  ");
                for(int x = 0; x<10; x++){
                    if(playerHitGrid[i][x] && computerGrid[i][x]){
                        System.out.print("H" + "   ");
                    }else if(playerHitGrid[i][x]){
                        System.out.print("X" + "   ");
                    }else{
                        System.out.print(" " + "   ");
                    }
                }
                System.out.println("");
            }

            try{
                Thread.sleep(2600);
            }catch(Exception e){
                System.out.println("\fThere was an error processing your game. Please restart.");
            }
            if(computerGrid[rowNum][columnNum]){
                System.out.println("It is a hit!");
                playerHits++;
            }else{
                System.out.println("It's a miss!");
            }
            try{
                Thread.sleep(2600);
            }catch(Exception e){
                System.out.println("\fThere was an error processing your game. Please restart.");
            }
            if(playerHits == 17){
                System.out.println("You win! Congrats!");
                loop = false;
                System.exit(0);
            }
            //--------------------------------------COMPUTER TURN---------------------------------------------//
            System.out.println("\f");
            if(isHit){
                if(firstStep && !secondStep){
                    boolean tempLoop = true;
                    do{
                        if(count==1){
                            //to the right
                            if((baseColumn+1) <= 9){
                                columnNum = baseColumn+1;
                                rowNum = baseRow;
                            }else{
                                count = 2;
                            }
                        }
                        if(count==2){
                            //to the left
                            if((baseColumn-1) >= 0){
                                columnNum = baseColumn-1;
                                rowNum = baseRow;
                            }else{
                                count = 3;
                            }
                        }
                        if(count==3){
                            //to the up
                            if((baseRow-1) >= 0){
                                rowNum = baseRow-1;
                                columnNum = baseColumn;
                            }else{
                                count = 4;
                            }
                        }
                        if(count==4){
                            //to the down
                            if((baseRow+1) <= 9){
                                rowNum = baseRow+1;
                                columnNum = baseColumn;
                            }
                        }
                        if(!computerHitGrid[rowNum][columnNum]){
                            tempLoop = false;
                        }else{
                            count++;
                        }
                    }while(tempLoop);
                }else if(secondStep){
                    if(right){
                        if((baseColumn+counter) <= 9){
                            columnNum = baseColumn + counter;
                            rowNum = baseRow;
                            counter++;
                        }else{
                            secondStep = false;
                            isHit = false;
                            count = 1;
                            counter = 2;
                            right = false;
                            left = false;
                            up = false;
                            down = false;
                        }
                    }else if(left){
                        if((baseColumn-counter) >= 0){
                            columnNum = baseColumn - counter;
                            rowNum = baseRow;
                            counter++;
                        }else{
                            secondStep = false;
                            isHit = false;
                            count = 1;
                            counter = 2;
                            right = false;
                            left = false;
                            up = false;
                            down = false;
                        }
                    }else if(up){
                        if((baseRow-counter) >= 0){
                            rowNum = baseRow - counter;
                            columnNum = baseColumn;
                            counter++;
                        }else{
                            secondStep = false;
                            isHit = false;
                            count = 1;
                            counter = 2;
                            right = false;
                            left = false;
                            up = false;
                            down = false;
                        }
                    }else if(down){
                        if((baseRow+counter) <= 9){
                            rowNum = baseRow + counter;
                            columnNum = baseColumn;
                            counter++;
                        }else{
                            secondStep = false;
                            isHit = false;
                            count = 1;
                            counter = 2;
                            right = false;
                            left = false;
                            up = false;
                            down = false;
                        }
                    }
                }
            }else{
                Random rand = new Random();
                boolean tempLoop = true;
                do{
                    columnNum = rand.nextInt(10);
                    rowNum = rand.nextInt(10);
                    if(!computerHitGrid[rowNum][columnNum]){
                        tempLoop = false;
                    }
                }while(tempLoop);
            }
            System.out.println("");
            System.out.println("The computer strikes at " + letters[rowNum] + "" + (columnNum+1) + "!");
            try{
                Thread.sleep(1000);
            }catch(Exception e){
                System.out.println("\fThere was an error processing your game. Please restart.");
            }
            computerHitGrid[rowNum][columnNum] = true;
            System.out.println("\nPlayer Ship Grid");
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
                    if(computerHitGrid[i][x] && playerGrid[i][x]){
                        System.out.print("H" + "   ");
                    }else if(computerHitGrid[i][x]){
                        System.out.print("X" + "   ");
                    }else if(playerGrid[i][x]){
                        System.out.print("*" + "   ");
                    }else{
                        System.out.print(" " + "   ");
                    }
                }
                System.out.println("");
            }
            try{
                Thread.sleep(1000);
            }catch(Exception e){
                System.out.println("\fThere was an error processing your game. Please restart.");
            }
            if(playerGrid[rowNum][columnNum]){
                System.out.println("It is a hit!");
                computerHits++;

                isHit = true;
                if(!firstStep){
                    firstStep = true;
                    baseRow = rowNum;
                    baseColumn = columnNum;
                }else if(firstStep){
                    if(count==1){
                        right = true;
                    }else if(count==2){
                        left = true;
                    }else if(count==3){
                        up = true;
                    }else if(count==4){
                        down = true;
                    }
                    secondStep = true;
                }
            }else{
                System.out.println("It's a miss!");
                if(firstStep && !secondStep){
                    count++;
                }else if(secondStep){
                    firstStep = false;
                    secondStep = false;
                    isHit = false;
                    count = 1;
                    counter = 2;
                    right = false;
                    left = false;
                    up = false;
                    down = false;
                }
            }
            System.out.println("");
            if(computerHits == 17){
                System.out.println("You lose. Better luck next time!");
                loop = false;
            }
            try{
                Thread.sleep(2600);
            }catch(Exception e){
                System.out.println("\fThere was an error processing your game. Please restart.");
            }
        }while(loop);
    }
}