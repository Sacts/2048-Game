/**
 * @File: BoardT.java
 * @Author: Muhammad Ibtehaaj Cheema - cheemm9
 * @Date: April, 12th, 2021
 * @Description: a model module for storing the state of the game and possible changes
 * that can be made to the board.
 */

package src;

// Import Java libraries
import java.util.Random;


public class BoardT {

    // State Variables
    int[][] board;
    Random rand = new Random();
    int points = 0;

    /**
     * @brief constructor for BoardT
     * @details generates a 4x4 board with a 2 or 4 value in any 2 random coordinates
     * and the rest of the values are filled with 0's
     */

    public BoardT(){
        board = new int[4][4];
        int x1 = rand.nextInt(4);
        int y1 = rand.nextInt(4);
        int x2 = rand.nextInt(4);
        int y2 = rand.nextInt(4);
        while (x1 == x2 && y1 == y2){
            x2 = rand.nextInt(4);
            y2 = rand.nextInt(4);
        }
        int[] twoorfour = new int[2];
        twoorfour[0] = 2;
        twoorfour[1] = 4;
        int randomindexchooser = rand.nextInt(2);
        int firstvalue = twoorfour[randomindexchooser];
        randomindexchooser = rand.nextInt(2);
        int secondvalue = twoorfour[randomindexchooser];
        board[x1][y1] = firstvalue;
        board[x2][y2] = secondvalue;
        for (int i = 0; i < 4; i++){
            for (int z = 0; z < 4; z++){
                if (!(board[i][z]>0)){
                    board[i][z] = 0;
                }
            }
        }
    }

    /**
     * @brief constructor for BoardT with parameter
     * @details User can choose to use their own 4x4 board. Also easier for
     * testing since the values are not randomized,
     * @param board1 board given by user which has values of the power of 2,
     *               on the board
     * @param points1 The points of the board given by the user.
     */

    public BoardT(int[][] board1, int points1){
        board = board1;
        points = points1;
    }


    /**
     * @brief the points of the current game state
     * @return points of the game
     */

    public int getPoints() {
        return points;
    }

    /**
     * @brief the board of the current game state
     * @return board of the game
     */

    public int[][] getBoard() {
        return board;
    }


    /**
     * @brief This function checks if the game is over by
     * checking if there arent any 0 values and no valid moves can be made.
     * @return over which is a boolean value
     */
    public boolean gameover(){
        boolean over = true;
        for (int i = 0; i < 4; i++){
            for (int z = 0; z < 4; z++){
                if ((board[i][z] == 0)) {
                    over = false;
                    break;
                }
            }
        }

        for(int i = 1; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if (board[i][j] == board[i - 1][j]) {
                    over = false;
                    break;
                }
            }
        }

        for(int i = 0; i < 4; i++) {
            for(int j = 2; j > -1; j--) {
                if (board[i][j] == board[i][j + 1]) {
                    over = false;
                    break;
                }
            }
        }

        for(int i = 0; i < 4; i++) {
            for(int j = 1; j < 4; j++) {
                if (board[i][j] == board[i][j - 1]) {
                    over = false;
                    break;
                }
            }
        }

        for(int i = 2; i >= 0; i--) {
            for(int j = 0; j < 4; j++) {
                if (board[i][j] == board[i + 1][j]) {
                    over = false;
                    break;
                }
            }
        }

        return over;
    }

    /**
     * @brief This function uses gameover to see if the game is finished
     * it then checks if a 2048 tile was made and prints an appropiate message
     * regarding if the user won or lost.
     * @return StringBuilder which prints the result of the game
     */
    public StringBuilder gameresult(){
        boolean is_winner = false;
        for (int i = 0; i < 4; i++){
            for (int z = 0; z < 4; z++){
                if ((board[i][z] >= 2048)){
                    is_winner = true;
                    break;
                }
            }
        }
        if (is_winner){
            return new StringBuilder("Player has WON after achieving" +
                    " a 2048 tile, The total points are " + getPoints());
        } else{
            return new StringBuilder("Player has LOST because did not" +
                    " achieve a 2048 tile, The total points are " + getPoints());
        }

    }

    /**
     * @brief This function adds a random 2 or 4 value in a random
     * coordinate on the board after a move is made. Makes sure to
     * replace a 0 value instead of a value which is not 0.
     */
    private void addrandomvalue(){
        int[] twoorfour = new int[2];
        twoorfour[0] = 2;
        twoorfour[1] = 4;
        int randomindexchooser = rand.nextInt(2);
        int value = twoorfour[randomindexchooser];
        int x1 = rand.nextInt(4);
        int y1 = rand.nextInt(4);
        while (board[x1][y1] != 0){
            x1 = rand.nextInt(4);
            y1 = rand.nextInt(4);
        }
        board[x1][y1] = value;


    }

    /**
     * @brief This method copies a board that its given as a parameter
     * into another board.
     * @param tempboard This is a board which needs to be copied
     * @return A board which is a copy of the parameter into a 2D array of ints.
     */
    private int[][] cloneBoard(int[][] tempboard){
        int[][] temp = new int[4][4];
        for (int i = 0; i < 4; i++){
            System.arraycopy(tempboard[i], 0, temp[i], 0, 4);
        }
        return temp;
    }

    /**
     * @brief This method checks if the board given as a parameter is equal to
     * the current object's board.
     * @param temp This is a board which needs to be compared to the current object's board
     * @return A boolean value which returns True if the two boards are equal otherwise returns false
     */
    private boolean Equals(int[][] temp){
        boolean answer= true;
        for (int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if (this.board[i][j] != temp[i][j]){
                    answer= false;
                    break;
                }
            }
        }
        return answer;
    }

    /**
     * @brief This method moves every non-zero digit all the way up it can
     * go and replaces its original position to a 0. It can only go up
     * if the position above it has a 0 value.
     */
    private void helpermoveup(){
        for (int counter = 0; counter < 3; counter++){
            for(int i = 1; i < 4; i++) {
                for(int j = 0; j < 4; j++) {
                    if(board[i][j] > 0 && board[i - 1][j] == 0) {
                        board[i - 1][j] = board[i][j];
                        board[i][j] = 0;
                    }
                }
            }
        }
    }

    /**
     * @brief This method merges all the values in the up direction if the
     * two values are the same it sums them up and puts it in the position
     * of the value which is on the above slot of the two value slots.
     */
    private void mergeup(){
        for(int i = 1; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(board[i][j] == board[i - 1][j]) {
                    int temp;
                    temp = (board[i][j] + board[i - 1][j]);
                    points += temp;
                    board[i - 1][j] = temp;
                    board[i][j] = 0;
                }
            }
        }
    }


    /**
     * @brief This method uses the helpermoveup and mergeup methods, and
     * it moves everything up and merges them, it also checks if the move is
     * made then it spawns a random 2 or 4 value onto the board.
     */
    public void moveup(){

        int[][] clonematrix;
        clonematrix = cloneBoard(board);
        helpermoveup();
        mergeup();
        helpermoveup();
        if (!(Equals(clonematrix))){
            addrandomvalue();
        }
    }

    /**
     * @brief This method moves every non-zero digit all the way right it can
     * go and replaces its original position to a 0. It can only go right
     * if the position on the right of it has a 0 value.
     */
    private void helpermoveright(){
        for (int counter = 0; counter < 3; counter++){
            for(int i = 0; i < 4; i++) {
                for(int j = 2; j > -1; j--) {
                    if(board[i][j] > 0 && board[i][j+1] == 0) {
                        board[i][j+1] = board[i][j];
                        board[i][j] = 0;
                    }
                }
            }
        }
    }

    /**
     * @brief This method merges all the values in the right direction if the
     * two values are the same it sums them up and puts it in the position
     * of the value which is on the right slot of the both value slots.
     */
    private void mergeright(){
        for(int i = 0; i < 4; i++) {
            for(int j = 2; j > -1; j--) {
                if(board[i][j] == board[i][j+1]) {
                    int temp;
                    temp = (board[i][j] + board[i][j+1]);
                    points += temp;
                    board[i][j+1] = temp;
                    board[i][j] = 0;
                }
            }
        }

    }

    /**
     * @brief This method uses the helpermoveright and mergeright methods, and
     * it moves everything right and merges them, it also checks if the move is
     * made then it spawns a random 2 or 4 value onto the board.
     */
    public void moveright(){

        int[][] clonematrix;
        clonematrix = cloneBoard(board);
        helpermoveright();
        mergeright();
        helpermoveright();
        if (!(Equals(clonematrix))){
            addrandomvalue();
        }
    }

    /**
     * @brief This method moves every non-zero digit all the way left it can
     * go and replaces its original position to a 0. It can only go left
     * if the position on the left of it has a 0 value.
     */
    private void helpermoveleft(){
        for (int counter = 0; counter < 3; counter++){
            for(int i = 0; i < 4; i++) {
                for(int j = 1; j < 4; j++) {
                    if(board[i][j] > 0 && board[i][j-1] == 0) {
                        board[i][j-1] = board[i][j];
                        board[i][j] = 0;
                    }
                }
            }
        }
    }

    /**
     * @brief This method merges all the values in the left direction if the
     * two values are the same it sums them up and puts it in the position
     * of the value which is on the left slot of the both value slots.
     */
    private void mergeleft(){
        for(int i = 0; i < 4; i++) {
            for(int j = 1; j < 4; j++) {
                if(board[i][j] == board[i][j-1]) {
                    int temp;
                    temp = (board[i][j] + board[i][j-1]);
                    points += temp;
                    board[i][j-1] = temp;
                    board[i][j] = 0;
                }
            }
        }
    }

    /**
     * @brief This method uses the helpermoveleft and mergeleft methods, and
     * it moves everything left and merges them, it also checks if the move is
     * made then it spawns a random 2 or 4 value onto the board.
     */
    public void moveleft(){

        int[][] clonematrix;
        clonematrix = cloneBoard(board);
        helpermoveleft();
        mergeleft();
        helpermoveleft();
        if (!(Equals(clonematrix))){
            addrandomvalue();
        }
    }


    /**
     * @brief This method moves every non-zero digit all the way down it can
     * go and replaces its original position to a 0. It can only go down
     * if the position below it has a 0 value.
     */
    private void helpermovedown(){
        for (int counter = 0; counter < 3; counter++){
            for(int i = 2; i >= 0; i--) {
                for(int j = 0; j < 4; j++) {
                    if(board[i][j] > 0 && board[i + 1][j] == 0) {
                        board[i + 1][j] = board[i][j];
                        board[i][j] = 0;
                    }
                }
            }
        }
    }

    /**
     * @brief This method merges all the values in the down direction if the
     * two values are the same it sums them up and puts it in the position
     * of the value which is on the bottom slot of the two value slots.
     */
    private void mergedown(){
        for(int i = 2; i >= 0; i--) {
            for(int j = 0; j < 4; j++) {
                if(board[i][j] == board[i + 1][j]) {
                    int temp;
                    temp = (board[i][j] + board[i + 1][j]);
                    points += temp;
                    board[i + 1][j] = temp;
                    board[i][j] = 0;
                }
            }
        }

    }

    /**
     * @brief This method uses the helpermovedown and mergedown methods, and
     * it moves everything down and merges them, it also checks if the move is
     * made then it spawns a random 2 or 4 value onto the board.
     */
    public void movedown(){

        int[][] clonematrix;
        clonematrix = cloneBoard(board);
        helpermovedown();
        mergedown();
        helpermovedown();
        if (!(Equals(clonematrix))){
            addrandomvalue();

        }
    }

}
