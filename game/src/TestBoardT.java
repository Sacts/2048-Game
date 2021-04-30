/**
 * @File: BoardT.java
 * @Author: Muhammad Ibtehaaj Cheema - cheemm9
 * @Date: April, 12th, 2021
 * @Description: a module for testing different methods in BoardT.java
 */

package src;


import org.junit.*;


import static org.junit.Assert.*;

public class TestBoardT {
    private BoardT testcase1;
    private BoardT testcase2;
    private BoardT testcase3;
    private BoardT testcase4;

    @Before
    public void setUp(){
        int[][] board1 = {{0,0,0,0},{0,0,0,0},{4,0,0,0},{0,0,0,0}};
        int[][] board2 = {{0,512,2048,4},{0,2,16,0},{4,0,0,0},{0,0,4,1024}};
        int[][] board4 = {{2,512,16,4},{16,2,16,512},{4,4096,2,16},{512,2,2,1024}};
        int[][] board3 = {{2,4,2,4},{16,32,16,32},{64,128,64,128},{256,512,256,512}};
        testcase1 = new BoardT(board1, 0);
        testcase2 = new BoardT(board2, 5000);
        testcase3 = new BoardT(board3, 4500);
        testcase4 = new BoardT(board4, 514);
    }

    @After
    public void tearDown(){
        testcase1 = null;
        testcase2 = null;
        testcase3 = null;
        testcase4 = null;
    }

    @Test
    public void testGetBoard()
    {
        assertTrue(testcase1.getBoard() == testcase1.board);
        assertTrue(testcase2.getBoard() == testcase2.board);
        assertTrue(testcase3.getBoard() == testcase3.board);
        assertTrue(testcase4.getBoard() == testcase4.board);
    }

    @Test
    public void testGetPoints()
    {
        assertTrue(testcase1.getPoints() == testcase1.points);
        assertTrue(testcase2.getPoints() == testcase2.points);
        assertTrue(testcase3.getPoints() == testcase3.points);
        assertTrue(testcase4.getPoints() == testcase4.points);
    }

    @Test
    public void testGameOver()
    {
        assertTrue(!testcase1.gameover());
        assertTrue(!testcase2.gameover());
        assertTrue(testcase3.gameover());
        assertTrue(!testcase4.gameover());
    }

    @Test
    public void testMoveUpandMergeUp()
    {
        testcase1.moveup();
        int[][] boardtest1 = testcase1.getBoard();
        assertTrue(boardtest1[0][0] == 4);
        testcase2.moveup();
        int[][] boardtest2 = testcase2.getBoard();
        assertTrue(boardtest2[0][0] == 4 && boardtest2[2][2] == 4 && boardtest2[1][3] == 1024);
        testcase4.moveup();
        int[][] boardtest4 = testcase4.getBoard();
        assertTrue(boardtest4[0][2] == 32 && boardtest4[1][2] == 4);
    }

    @Test
    public void testMovedownandMergedown()
    {
        testcase1.movedown();
        int[][] boardtest1 = testcase1.getBoard();
        assertTrue(boardtest1[3][0] == 4);
        testcase2.movedown();
        int[][] boardtest2 = testcase2.getBoard();
        assertTrue(boardtest2[3][0] == 4 && boardtest2[2][2] == 16 && boardtest2[1][2] == 2048);
        testcase4.movedown();
        int[][] boardtest4 = testcase4.getBoard();
        assertTrue(boardtest4[2][2] == 32 && boardtest4[3][2] == 4);
    }

    @Test
    public void testMoverightandMergeright()
    {
        testcase1.moveright();
        int[][] boardtest1 = testcase1.getBoard();
        assertTrue(boardtest1[2][3] == 4);
        testcase2.moveright();
        int[][] boardtest2 = testcase2.getBoard();
        assertTrue(boardtest2[1][3] == 16 && boardtest2[1][2] == 2 && boardtest2[2][3] == 4);
        testcase4.moveright();
        int[][] boardtest4 = testcase4.getBoard();
        assertTrue(boardtest4[3][2] == 4 && boardtest4[3][1] == 512);
    }

    @Test
    public void testMovelefttandMergeleft()
    {
        int[][] boardtest1_1 = {{0,0,0,0},{0,0,0,0},{4,0,0,0},{0,0,0,0}};
        testcase1.moveleft();
        int[][] boardtest1 = testcase1.getBoard();
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                assertTrue(boardtest1[i][j] == boardtest1_1[i][j]);
            }
        }
        testcase2.moveleft();
        int[][] boardtest2 = testcase2.getBoard();
        assertTrue(boardtest2[0][0] == 512 && boardtest2[0][1] == 2048 && boardtest2[0][2] == 4);
        assertTrue(boardtest2[1][0] == 2 && boardtest2[1][1] == 16);
        assertTrue(boardtest2[3][0] == 4 && boardtest2[3][1] == 1024);
        testcase4.moveleft();
        int[][] boardtest4 = testcase4.getBoard();
        assertTrue(boardtest4[3][1] == 4 && boardtest4[3][2] == 1024);
    }

}
