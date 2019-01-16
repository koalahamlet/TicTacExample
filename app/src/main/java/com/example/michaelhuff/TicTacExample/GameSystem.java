package com.example.michaelhuff.TicTacExample;

import java.util.Arrays;

import static com.example.michaelhuff.TicTacExample.Constants.*;

public class GameSystem {

    private final UpdateViewListener wonListener;
    private Player currentPlayer; // will be 'O' or 'X'
    private final char[] board = new char[9];
    public Player playerO = new Player(O);
    public Player playerX = new Player(X);

    // GameSystem, which takes a move, determines if it's valid, and notifies who won.
    public GameSystem(UpdateViewListener wonListener) {
        this.wonListener = wonListener;
        Arrays.fill(board, EMPTY);
        currentPlayer = playerX;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public char[] getBoard() {
        return board;
    }

    // boolean to represent if it was a valid move
    public boolean play(int position) {
        // is playable
        char square = board[position];
            if (square == ' '){
                // then make move
                    board[position] = currentPlayer.letter;

                // check if winning condition and update.

                char playerLetter = currentPlayer.letter;

                // horizontal 012
                if (board[0] == playerLetter && board[1] == playerLetter && board[2] == playerLetter) {
                    wonListener.notifyWon(currentPlayer);
                }

                // horizontal 345
                if (board[3] == playerLetter && board[4] == playerLetter && board[5] == playerLetter) {
                    wonListener.notifyWon(currentPlayer);
                }

                // horizontal 678
                if (board[6] == playerLetter && board[7] == playerLetter && board[8] == playerLetter) {
                    wonListener.notifyWon(currentPlayer);
                }

                // vertical 036
                if (board[0] == playerLetter && board[3] == playerLetter && board[6] == playerLetter) {
                    wonListener.notifyWon(currentPlayer);
                }

                // vertical 147
                if (board[1] == playerLetter && board[4] == playerLetter && board[7] == playerLetter) {
                    wonListener.notifyWon(currentPlayer);
                }

                // vertical 258
                if (board[2] == playerLetter && board[5] == playerLetter && board[8] == playerLetter) {
                    wonListener.notifyWon(currentPlayer);
                }

                //diagonal 048
                if (board[0] == playerLetter && board[4] == playerLetter && board[8] == playerLetter) {
                    wonListener.notifyWon(currentPlayer);
                }

                //diagonal 246
                if (board[2] == playerLetter && board[4] == playerLetter && board[6] == playerLetter) {
                    wonListener.notifyWon(currentPlayer);
                }

                // switch player
                if (currentPlayer.equals(playerO)) {
                    currentPlayer = playerX;
                    wonListener.updatePlayerName(currentPlayer.letter);
                } else {
                    currentPlayer = playerO;
                    wonListener.updatePlayerName(currentPlayer.letter);
                }

                return true;
            } else {
                //notify user the square has already been played
                return false;
            }
    }


    public class Player {

        public Player(char letter) {
            this.letter = letter;
        }
        char letter;
    }

    public interface UpdateViewListener {
        void notifyWon(Player player);
        void updatePlayerName(char x);
    }
}
