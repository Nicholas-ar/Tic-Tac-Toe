package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static boolean won(char[][] game, char smt) {
        boolean answer =
                (game[0][0] == smt && game[0][1] == smt && game[0][2] == smt) ||
                (game[1][0] == smt && game[1][1] == smt && game[1][2] == smt) ||
                (game[2][0] == smt && game[2][1] == smt && game[2][2] == smt) ||

                (game[0][0] == smt && game[1][0] == smt && game[2][0] == smt) ||
                (game[0][1] == smt && game[1][1] == smt && game[2][1] == smt) ||
                (game[0][2] == smt && game[1][2] == smt && game[2][2] == smt) ||

                (game[0][0] == smt && game[1][1] == smt && game[2][2] == smt) ||
                (game[0][2] == smt && game[1][1] == smt && game[2][0] == smt);
        return answer;
    }

    public static boolean hasEmptySpaces (char[][] game) {
        boolean flag = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (game[i][j] == ' ') {
                    flag = true;
                }
            }
        }
        return flag;
    }

    public static void printGame(char[][] game) {
        System.out.println("---------");
        System.out.println("| " + game[0][0] + " " + game[0][1] + " " + game[0][2] + " |");
        System.out.println("| " + game[1][0] + " " + game[1][1] + " " + game[1][2] + " |");
        System.out.println("| " + game[2][0] + " " + game[2][1] + " " + game[2][2] + " |");
        System.out.println("---------");
    }

    public static boolean hasOnlyNumbers(String input) {
        if (input.matches("[0-9]+")) {
            return true;
        }
        System.out.println("You should enter numbers!");
        return false;
    }

    public static boolean smallBigEnough(String input) {
        int first = Character.getNumericValue(input.charAt(0));
        int second = Character.getNumericValue(input.charAt(1));
        if( first > 0 && first < 4 && second > 0 && second < 4){
            return true;
        }
        System.out.println("Coordinates should be from 1 to 3!");
        return false;
    }

    public static boolean notOccupied(char[][] game, int x, int y) {
        if (game[3 - y][x - 1] != 'X' && game[3 - y][x - 1] != 'O') {
            return true;
        }
        System.out.println("This cell is occupied! Choose another one!");
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] game = new char[3][3];
        Arrays.fill(game[0],' ');
        Arrays.fill(game[1],' ');
        Arrays.fill(game[2],' ');
        String input;
        boolean flag = true;
        int x;
        int y;
        boolean xWon = false;
        boolean oWon = false;
        char currentPlay = 'X';
        printGame(game);
        while (flag) {
            xWon = won(game,'X');
            oWon = won(game,'O');
            if (xWon || oWon || !hasEmptySpaces(game)){
                flag = false;
                break;
            }
            System.out.println("Enter the coordinates:");
            input = scanner.nextLine();
            input = input.replaceAll("\\s", "");
            if (hasOnlyNumbers(input)) {
                if (smallBigEnough(input)) {
                    x = Character.getNumericValue(input.charAt(0));
                    y = Character.getNumericValue(input.charAt(1));
                    if (notOccupied(game,x,y)) {
                        game[3 - y][x - 1] = currentPlay;
                        printGame(game);
                        if (currentPlay == 'X') {
                            currentPlay = 'O';
                        } else {
                            currentPlay = 'X';
                        }
                    }
                }
            }
        }

        if (xWon) {
            System.out.println("X wins");
        } else if (oWon) {
            System.out.println("O wins");
        } else {
            System.out.println("Draw");
        }
    }
}
