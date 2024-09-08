package Tetris;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TetrisVideo1 extends JPanel {

  // all of the game pieces - Tetromino
  int[][][] PIECES = {
    { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } },  // Square
    { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 0, 3 } },  // Line
    { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 2, 1 } },  // L
    { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 1, 1 } },  // T
    { { 0, 0 }, { 1, 0 }, { 1, 1 }, { 2, 1 } }   // Z
  };
 
              //{ { 0, 0 }, { 1, 0 }, { 2, 0 }, { 2, 1 } },  // L
             //   0 | 1 | 2 | 3
             //0  *    
             //1  *
             //2  *   *



  // the size of the game board
  public static final int WIDTH = 10;
  public static final int HEIGHT = 22;


  // the size of each block
  private static final int BLOCK_SIZE = 30;

  // the current piece
  private int[][] currentPiece;


  // the current row and column of the piece
  private int currentRow;
  private int currentCol;

  //which piece
  int pnum = 0;

  // the game board
  private int[][] board;

  // the constructor
  public TetrisVideo1() {

      // create the board
      board = new int[HEIGHT][WIDTH];//row,col

      // add the key listener
      addKeyListener(new KeyListener() {

          public void keyPressed(KeyEvent e) {
             

          }

          public void keyReleased(KeyEvent e) {
          }

          public void keyTyped(KeyEvent e) {
          }
      });

      // set the focus
      setFocusable(true);

      // create the first piece
      createNewPiece();
  }
  


 

  // create a new piece
  private void createNewPiece() {
    pnum = (int)(Math.random() * PIECES.length);
    currentPiece = PIECES[pnum];
    currentRow = 0;
    currentCol = 4;
  }

   // move the piece down
   private void moveDown() {
       if (canMove(currentRow + 1, currentCol)) {
           currentRow++;
       } else {
           createNewPiece();
       }
   }
  
   // check if the piece can move to the specified row and column
  private boolean canMove(int row, int col) {
    for (int i = 0; i < 4; i++) {
      int r = currentRow + currentPiece[i][0];
      int c = currentCol + currentPiece[i][1];
      if (r >= HEIGHT-1|| c < 0 || c >= WIDTH || board[r][c] != 0) {
        return false;
      }
    }
    return true;
  }


  // draw the game
  public void paint(Graphics g) {
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, WIDTH * BLOCK_SIZE, HEIGHT * BLOCK_SIZE);

    // draw the board
    for (int r = 0; r < HEIGHT; r++) {
      for (int c = 0; c < WIDTH; c++) {
        if (board[r][c] == 0) {
          g.setColor(Color.GREEN);
          g.drawRect(c * BLOCK_SIZE, r * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
          g.setColor(Color.BLUE);
          g.fillRect(c * BLOCK_SIZE, r * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
        }
        
      }
    }

    // draw the current piece
    for (int i = 0; i < 4; i++) {
      int r = currentRow + currentPiece[i][0];
      int c = currentCol + currentPiece[i][1];
      g.setColor(Color.BLACK);
      g.drawRect(c * BLOCK_SIZE, r * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
      g.setColor(Color.RED);
      g.fillRect(c * BLOCK_SIZE, r * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
    }
  }

  // the main program
  public static void main(String[] args) {
    JFrame f = new JFrame();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setSize(WIDTH * BLOCK_SIZE, HEIGHT * BLOCK_SIZE);
    f.setLocationRelativeTo(null);
    f.setResizable(false);
    TetrisVideo1 game = new TetrisVideo1();
    f.add(game);
    f.setVisible(true);

    // run the game
    while (true) {
      game.moveDown();
      game.repaint();
      try {
        Thread.sleep(300);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}