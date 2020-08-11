// Author: Samy Barakat

package tictactoe;

import java.util.*;

public class TicTacToe {

    static ArrayList<Integer> playerSpots = new ArrayList<Integer>();
    static ArrayList<Integer> comSpots = new ArrayList<Integer>();

    public static void main(String[] args) {
        char[][] board = {{' ', '|', ' ', '|', ' '},
        {'-', '+', '-', '+', '-'},
        {' ', '|', ' ', '|', ' '},
        {'-', '+', '-', '+', '-'},
        {' ', '|', ' ', '|', ' '}};
        printBoard(board);

        

        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Please enter your placement using numbers 1 to 9:");
            int playerSpot = scan.nextInt();
            while(playerSpots.contains(playerSpot) || comSpots.contains(playerSpot)) {
                System.out.println("Sorry! Position is taken. Enter a correct position.");
                playerSpot = scan.nextInt();
            }

            setPiece(board, playerSpot, "player");
            
            String result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }
            
            Random r = new Random();
            int comSpot = r.nextInt(9) + 1;
            while(playerSpots.contains(comSpot) || comSpots.contains(comSpot)) {
                comSpot = r.nextInt(9) + 1;
            }
            
            

            setPiece(board, comSpot, "cpu");

            printBoard(board);
            
            result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
        }

    }
    }

    public static void printBoard(char[][] board) {
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c);
            }

            System.out.println();

        }

    }

    public static void setPiece(char[][] board, int pos, String user) {

        char character = ' ';

        if (user.equals("player")) {
            character = 'X';
            playerSpots.add(pos);
        } else if (user.equals("cpu")) {
            character = 'O';
            comSpots.add(pos);
        }

        switch (pos) {
            case 1:
                board[0][0] = character;
                break;
            case 2:
                board[0][2] = character;
                break;
            case 3:
                board[0][4] = character;
                break;
            case 4:
                board[2][0] = character;
                break;
            case 5:
                board[2][2] = character;
                break;
            case 6:
                board[2][4] = character;
                break;
            case 7:
                board[4][0] = character;
                break;
            case 8:
                board[4][2] = character;
                break;
            case 9:
                board[4][4] = character;
            default:
                break;
        }
    }

    public static String checkWinner() {
        List topR = Arrays.asList(1, 2, 3);
        List midR = Arrays.asList(4, 5, 6);
        List botR = Arrays.asList(7, 8, 9);
        List leftC = Arrays.asList(1, 4, 7);
        List midC = Arrays.asList(2, 5, 8);
        List rightC = Arrays.asList(3, 6, 9);
        List diag1 = Arrays.asList(1, 5, 9);
        List diag2 = Arrays.asList(7, 5, 3);

        // we created an array list of the above list
        List<List> winSet = new ArrayList<List>();
        winSet.add(topR);
        winSet.add(midR);
        winSet.add(botR);
        winSet.add(leftC);
        winSet.add(midC);
        winSet.add(rightC);
        winSet.add(diag1);
        winSet.add(diag2);

        // so now each list is in separate brackets in a surrounding list
        for (List l : winSet) {
            if (playerSpots.containsAll(l)) {
                return "Good job, you won!";
            } else if (comSpots.containsAll(l)) {
                return "The computer beat you! Sorry.";
            } else if (playerSpots.size() + comSpots.size() == 9) {
                return "Tie Game!";
            }
        }

        return "";
    }

}
