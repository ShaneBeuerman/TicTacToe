package tictactoe;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main {

    /*
        Main method
    */
    public static void main(String[] args) {
        setupGUI();
    }

    /*
        Checks to see if there is a winner in the tic tac toe game.
    */
    public static boolean checkBoard(char[][] board) {
        if (board[0][0] == board[0][1] && board[0][0] == board[0][2] && board[0][0] != '-') {
            return true;
        }
        if (board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] != '-') {
            return true;
        }
        if (board[0][0] == board[1][0] && board[0][0] == board[2][0] && board[0][0] != '-') {
            return true;
        }
        if (board[1][0] == board[1][1] && board[1][0] == board[1][2] && board[1][0] != '-') {
            return true;
        }
        if (board[2][0] == board[2][1] && board[2][0] == board[2][2] && board[2][0] != '-') {
            return true;
        }
        if (board[0][1] == board[1][1] && board[0][1] == board[1][2] && board[0][1] != '-') {
            return true;
        }
        if (board[0][2] == board[1][2] && board[0][2] == board[2][2] && board[0][2] != '-') {
            System.out.println("You win");
            return true;
        }
        if (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] != '-') {
            return true;
        }
        return false;
    }
    
    /*
        Checks to see if a given spot on the tic tac toe board is
        available.
    */
    public static boolean isAvailable(char[][] board, int x, int y){
        if(board[x][y] == '-'){
            return true;
        }
        return false;
    }
    
    /*
        Marks the board with an X
    */
    public static void mark(char[][] board, int x, int y){
        board[x][y] = 'X';
    }
    
    /*
        setupGUI() sets up a 3x3 grid that acts as the tic
        tac toe board for the game. It allows the user to mark the board
        and take turns with the computer.
    */
    public static void setupGUI(){
        char[][] board = new char[3][3]; 
        JFrame game = new JFrame("TicTacToe");
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton[][] buttons = new JButton[3][3];
        GridLayout gridLayout = new GridLayout(3, 3);
        JPanel grid = new JPanel();
        grid.setLayout(gridLayout);
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("-");
                buttons[i][j].setPreferredSize(new Dimension(60, 60));
                board[i][j] = '-';
                int currentI = i;
                int currentJ = j;
                buttons[i][j].addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent arg) {
                        if(isAvailable(board, currentI, currentJ)){
                            buttons[currentI][currentJ].setText("X");
                            mark(board, currentI, currentJ);
                            if(!checkBoard(board)){
                                if(!gameEnd(board)){
                                    int[] coor = computerTurn(board);
                                    buttons[coor[0]][coor[1]].setText("O");
                                }
                                if(checkBoard(board)){
                                    System.out.println("You lose");
                                }
                            }
                            else{
                                System.out.println("You win.");
                            }
                        }
                    }
                });
                grid.add(buttons[i][j]);
            }
        }

        game.add(grid);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.pack();
        game.setVisible(true);
    }
    
    /*
        The computer chooses a place on the board at random and marks it.
    */
    public static int[] computerTurn(char[][] board){
        int[] coordinates = new int[2];
        Random rand = new Random();
        boolean correct = false;
        int x = rand.nextInt(3);
        int y = rand.nextInt(3);
        while(board[x][y] != '-'){
            x = rand.nextInt(3);
            y = rand.nextInt(3);
        }
        board[x][y] = 'O';
        coordinates[0] = x;
        coordinates[1] = y;
        return coordinates;
    }
    
    /*
        Checks if the game is over.
    */
    public static boolean gameEnd(char[][] board){
        int counter = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(board[i][j] != '-'){
                    counter++;
                }
            }
        }
        if(counter == 9){
            return true;
        }
        else{
            return false;
        }
    }
    
}
