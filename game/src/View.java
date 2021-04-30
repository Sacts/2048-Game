/**
 * @File: BoardT.java
 * @Author: Muhammad Ibtehaaj Cheema - cheemm9
 * @Date: April, 12th, 2021
 * @Description: a view module for outputting the state of the game into a format
 * which is readable for the user
 */

package src;

public class View {

    // State variables
    private boolean introduction = true;

    /**
     * @brief A StringBuilder which represents the board in readable format
     * @details This first prints out a welcome message and rules of the game if
     * it starts up the game, It then takes the BoardT object and loops through the
     * board variable and appends it to a stringbuilder. It also checks the points
     * variable and displays it at the end with a message.
     * @param boardT BoardT object which needs to be printed.
     * @return It returns a Stringbuilder which shows the state of the board and the points.
     */
    public StringBuilder output(BoardT boardT){
        if (introduction){
            System.out.println("Welcome to the 2048 game! Please read the instructions below to play");
            System.out.println("1. To win the game you need to create a tile with 2048");
            System.out.println("2. You can continue to play even after getting a 2048" +
                    " tile and aim for a better high score");
            System.out.println("3. To move up,left,down,right. Use the W,A,S,D keys respectively");
            System.out.println("4. To exit the game press SPACEBAR");
            System.out.println("5. Tip: Make sure to press the keys in the small window that opens" +
                    " and read the game state off the terminal");
            System.out.println("6. Good luck :)");
            introduction = false;
        }
        StringBuilder matrix = new StringBuilder();
        matrix.append("_".repeat(27));
        matrix.append("\n");
        for (int[] row :boardT.board) {
            matrix.append("|");
            matrix.append(" ");
            for (int element : row) {
                if (String.valueOf(element).length() == 1) {
                    matrix.append(element);
                    matrix.append("     ");
                }
                if (String.valueOf(element).length() == 2) {
                    matrix.append(element);
                    matrix.append("    ");
                }
                if (String.valueOf(element).length() == 3) {
                    matrix.append(element);
                    matrix.append("   ");
                }
                if (String.valueOf(element).length() == 4) {
                    matrix.append(element);
                    matrix.append("  ");
                }
                if (String.valueOf(element).length() == 5) {
                    matrix.append(element);
                    matrix.append(" ");
                }

            }
            matrix.append("|");
            matrix.append("\n");
        }
        matrix.append("-".repeat(27));
        matrix.append("\n");
        matrix.append("Points: ").append(boardT.getPoints());
        matrix.append("\n");
        if (boardT.gameover()){
            System.out.println(matrix);
            return boardT.gameresult();
        } else {
            return matrix;
        }

    }
}
