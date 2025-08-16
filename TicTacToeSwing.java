//Java Swing Tic-Tac-Toe (2-player) app with win/draw detection, winning-cell highlight, and a Reset button.

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeSwing {

    private JFrame frame;
    private JButton[] cells = new JButton[9];
    private JLabel status;
    private JButton resetBtn;

    private char current = 'X';
    private boolean gameOver = false;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Optional: native look & feel
            try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {}
            new TicTacToeSwing().createAndShow();
        });
    }

    private void createAndShow() {
        frame = new JFrame("Tic Tac Toe - Swing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        // Board
        JPanel board = new JPanel(new GridLayout(3, 3, 6, 6));
        board.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        Font cellFont = new Font("Arial", Font.BOLD, 42);

        for (int i = 0; i < 9; i++) {
            JButton b = new JButton("");
            b.setFont(cellFont);
            b.setFocusPainted(false);
            b.setBackground(Color.WHITE);
            final int idx = i;
            b.addActionListener(e -> handleMove(idx));
            cells[i] = b;
            board.add(b);
        }

        // Status + Reset
        JPanel bottom = new JPanel(new BorderLayout(8, 8));
        status = new JLabel("Turn: X", SwingConstants.LEFT);
        status.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
        resetBtn = new JButton("Reset");
        resetBtn.addActionListener(e -> resetGame());
        bottom.add(status, BorderLayout.CENTER);
        bottom.add(resetBtn, BorderLayout.EAST);

        frame.add(board, BorderLayout.CENTER);
        frame.add(bottom, BorderLayout.SOUTH);

        frame.setSize(360, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void handleMove(int idx) {
        if (gameOver) return;

        JButton cell = cells[idx];
        if (!cell.getText().isEmpty()) return; // already taken

        cell.setText(String.valueOf(current));

        int[] winLine = checkWin();
        if (winLine != null) {
            gameOver = true;
            highlightWin(winLine);
            status.setText("Winner: " + current + " 🎉");
            disableBoard();
            return;
        }

        if (isBoardFull()) {
            gameOver = true;
            status.setText("Draw 🤝");
            return;
        }

        // Switch player
        current = (current == 'X') ? 'O' : 'X';
        status.setText("Turn: " + current);
    }

    private void highlightWin(int[] line) {
        for (int i : line) {
            cells[i].setBackground(new Color(180, 255, 180));
        }
    }

    private void disableBoard() {
        for (JButton b : cells) b.setEnabled(false);
    }

    private void enableBoard() {
        for (JButton b : cells) b.setEnabled(true);
    }

    private void resetGame() {
        for (JButton b : cells) {
            b.setText("");
            b.setBackground(Color.WHITE);
            b.setEnabled(true);
        }
        current = 'X';
        gameOver = false;
        status.setText("Turn: X");
    }

    private boolean isBoardFull() {
        for (JButton b : cells) {
            if (b.getText().isEmpty()) return false;
        }
        return true;
    }

    // Returns winning triple indices if someone won, else null
    private int[] checkWin() {
        int[][] lines = {
                {0,1,2}, {3,4,5}, {6,7,8}, // rows
                {0,3,6}, {1,4,7}, {2,5,8}, // cols
                {0,4,8}, {2,4,6}           // diagonals
        };
        for (int[] line : lines) {
            String a = cells[line[0]].getText();
            String b = cells[line[1]].getText();
            String c = cells[line[2]].getText();
            if (!a.isEmpty() && a.equals(b) && b.equals(c)) return line;
        }
        return null;
    }
}
