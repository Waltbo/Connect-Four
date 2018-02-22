package connect4;

import java.util.Random;
import java.util.Scanner;

/*
 *Author:Walter Smith
 *Date:1/25/2018
 * Assignment:Program #1- Connect Four
 *File:Connect4.java
 *Description:This file plays connect four against a bot
 */
public class Connect4 {

    static char player = 'R';
    static char bot = 'B';

    public static void main(String[] args) {
        char[][] characters = createBoard();
        System.out.println("Choose a color, R or B");//Player gets to select which color
        Scanner input = new Scanner(System.in);
        player = input.next().charAt(0);

        while (player != 'R' && player != 'B') {//Stops players from not selecting either of the two options
            System.out.println("Please select R or B");
            player = input.next().charAt(0);
        }
        if(player=='R'){
            bot='B';
        }else{
            bot='R';
        }
        

        boolean keepPlaying = true;
        printBoard(characters);
        while (keepPlaying) {
            placeCoin(characters);
            printBoard(characters);
            if (winCondition(characters) != 0) {
                if (winCondition(characters) == 'R') {
                    System.out.println("Red Wins");
                    keepPlaying = false;
                } else if (winCondition(characters) == 'B') {
                    System.out.println("Black Wins");
                    keepPlaying = false;
                }
            }
        }
    }

    public static char[][] createBoard() {//Fills a 2d array full of ' '
        char[][] characters = new char[6][7];
        for (int i = 0; i < characters.length; i++) {
            for (int j = 0; j < characters[i].length; j++) {
                characters[i][j] = ' ';
            }
        }
        return characters;
    }

    public static void printBoard(char[][] characters) {//Prints out the board 
        for (int i = 0; i < characters.length; i++) {
            for (int j = 0; j < characters[i].length; j++) {
                System.out.print("|" + characters[i][j]);
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("---------------");
        System.out.println(" 1 2 3 4 5 6 7");
    }

    public static void placeCoin(char[][] characters) {//Asks user where to place their token and then places it into that column.]
        System.out.println("What column would you like to place your token? (1-7)");
        Scanner keyboard = new Scanner(System.in);
        int input = keyboard.nextInt() - 1;
        while ((input < 0 || input > 6) || characters[0][input] != ' ' || !keyboard.hasNextInt()) {
            System.out.println("Column is full or your input is out of bounds. Please select another column");
            input = keyboard.nextInt() - 1;
        }
        for (int i = 5; i >= 0; i--) {//is at 5 and works backwords to go from bottom to top and the array is from 0-5 horrizontaly 

            if (characters[i][input] == ' ') {
                characters[i][input] = player;
                break;
            }
 
        }
        Random rand = new Random();//This makes it so the bot can randomly place a token between the range of columns
        int AI = rand.nextInt(7);
        while (characters[0][AI] != ' ') {
            AI = rand.nextInt(7);
        }
        for (int i = 5; i >= 
                0; i--) {
            if (characters[i][AI] == ' ') {
                characters[i][AI] = bot;
                break;
            }
        }
        //printBoard(characters);
    }

    public static char winCondition(char[][] characters) {
        for (int i = 0; i < characters.length; i++) {//checks to see if there is a win horizontally
            for (int j = 0; j < 4; j++) {
                if ((characters[i][j] != ' ')
                        && (characters[i][j] == characters[i][j + 1])
                        && (characters[i][j] == characters[i][j + 2])
                        && (characters[i][j] == characters[i][j + 3])) {
                    return characters[i][j];
                }
            }
        }
        for (int i = 0; i < 3; i++) {//checks to see if there is a win vertically
            for (int j = 0; j < characters[i].length; j++) {
                if ((characters[i][j] != ' ')
                        && (characters[i][j] == characters[i + 1][j])
                        && (characters[i][j] == characters[i + 2][j])
                        && (characters[i][j] == characters[i + 3][j])) {
                    return characters[i][j];
                }
            }
        }
        for (int i = 0; i < 3; i++) {//checks to see if there is a win diagonally from left to right
            for (int j = 0; j < 4; j++) {
                if ((characters[i][j] != ' ')
                        && (characters[i][j] == characters[i + 1][j + 1])
                        && (characters[i][j] == characters[i + 2][j + 2])
                        && (characters[i][j] == characters[i + 3][j + 3])) {
                    return characters[i][j];
                }
            }
        }
        for (int i = 0; i < 3; i++) {//checks to see if there is a win diagonally from right to left
            for (int j = 6; j >= 3; j--) {
                if ((characters[i][j] != ' ')
                        && (characters[i][j] == characters[i + 1][j - 1])
                        && (characters[i][j] == characters[i + 2][j - 2])
                        && (characters[i][j] == characters[i + 3][j - 3])) {
                    return characters[i][j];
                }
            }
        }
        return 0;
    }

}
