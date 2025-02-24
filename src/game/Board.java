package game;

import IO.Colors;
import java.util.Stack;

class BlockPosition {
    public Block block;
    public int x, y;

    public BlockPosition(Block block, int x, int y) {
        this.block = block;
        this.x = x;
        this.y = y;
    }

    public Block getBlock() {
        return this.block;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}

public class Board {
    public char[][] board;
    public Integer row, col;
    public Stack<BlockPosition> placedBlocks = new Stack<>();

    public Board(Integer row, Integer col, String S) {
        // initialize board with empty space

        if (S.equals("DEFAULT")) {
            generateDefaultBoard(row, col);
        }
        // else if (S.equals("CUSTOM"))
        // generateCustomBoard(row, col);
        // else if (S.equals("PYRAMID"))
        // generatePyramidBoard(row, col);

    }

    private void generateDefaultBoard(Integer row, Integer col) {
        this.row = row;
        this.col = col;
        board = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public void print() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(Colors.COLORS[board[i][j] - 'A'] + board[i][j] + Colors.RESET);
            }
            System.out.println();
        }
    }

    public boolean place(Block block) {
        int[] emptySpace = findEmptySpace();
        if (emptySpace == null) {
            return false;
        }

        int placeAtX = emptySpace[0];
        int placeAtY = emptySpace[1];

        int blockY = -1;
        for (int i = 0; i < block.getWidth(); i++) {
            if (block.getBlock()[0][i] != ' ') {
                blockY = i;
                break;
            }
        }

        if (blockY == -1) {
            return false;
        }

        placeAtY -= blockY;
        if (placeAtY < 0) {
            return false;
        }

        for (int i = 0; i < block.getHeight(); i++) {
            for (int j = 0; j < block.getWidth(); j++) {
                if (block.info(i, j) != ' ') {
                    int newX = placeAtX + i;
                    int newY = placeAtY + j;

                    if (newX >= row || newY >= col || newY < 0 || board[newX][newY] != ' ') {
                        return false;
                    }
                }
            }
        }

        for (int i = 0; i < block.getHeight(); i++) {
            for (int j = 0; j < block.getWidth(); j++) {
                if (block.info(i, j) != ' ') {
                    board[placeAtX + i][placeAtY + j] = block.info(i, j);
                }
            }
        }

        placedBlocks.addLast(new BlockPosition(block, placeAtX, placeAtY));
        return true;
    }

    public Block remove() {
        if (placedBlocks.isEmpty()) {
            return null;
        }
        //
        Block block = placedBlocks.lastElement().block;
        int x = placedBlocks.lastElement().x;
        int y = placedBlocks.lastElement().y;

        for (int i = 0; i < block.getHeight(); i++) {
            for (int j = 0; j < block.getWidth(); j++) {
                if (block.info(i, j) != ' ') {
                    board[x + i][y + j] = ' ';
                }
            }
        }
        placedBlocks.pop();
        return block;

    }

    public int[] findEmptySpace() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == ' ') {
                    return new int[] { i, j };
                }
            }
        }
        return null;
    }

    // getters
    public Integer getRow() {
        return this.row;
    }

    public Integer getCol() {
        return this.col;
    }
}
