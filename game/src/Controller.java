/**
 * @File: BoardT.java
 * @Author: Muhammad Ibtehaaj Cheema - cheemm9
 * @Date: April, 12th, 2021
 * @Description: a controller module, which reads user inputs and
 * progresses and controls the game.
 */

package src;

// Imported Java libraries
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller extends Frame implements KeyListener {

    // State variables
    TextArea input_area;
    BoardT boardT = new BoardT();
    View view = new View();

    /**
     * @brief constructor for Controller
     * @details Generates a Key listener by creating a text area which is used to
     * enter the key
     */
    Controller(){

        input_area = new TextArea();
        input_area.setBounds(0,0,0, 0);
        input_area.addKeyListener(this);
        add(input_area);
        setSize(0,0);
        setLayout(null);
        setVisible(true);

        System.out.println(view.output(boardT));
    }

    /**
     * @brief Method which reads the keys pressed by a user
     * @details This method checks the keys pressed by the user according to
     * the rules and instructions in the beginning of the game and executes
     * an appropriate command depending on what key is pressed.
     */
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) {
            boardT.moveleft();
            System.out.println(view.output(boardT));

        }

        if (key == KeyEvent.VK_D) {
            boardT.moveright();
            System.out.println(view.output(boardT));
        }

        if (key == KeyEvent.VK_W) {
            boardT.moveup();
            System.out.println(view.output(boardT));
        }

        if (key == KeyEvent.VK_S) {
            boardT.movedown();
            System.out.println(view.output(boardT));
        }

        if (key == KeyEvent.VK_SPACE) {
            System.out.println("The amount of points achieved are " + boardT.getPoints());
            System.out.println("Closing the game, Hope you enjoyed :)");
            System.exit(0);
        }
    }
    /**
     * @brief method for when a key is released, it does nothing
     */
    public void keyReleased(KeyEvent e) {}

    /**
     * @brief method for when a key is typed, it does nothing
     */
    public void keyTyped(KeyEvent e) {}

    /**
     * @brief Main method which creates a Controller that is used to read keys by a user.
     */
    public static void main(String[] args) {
        new Controller();
    }


}
