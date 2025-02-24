package game;

import java.util.ArrayList;

public class ISolve {
  public ArrayList<Block> blocks;
  public Board board;

  public ISolve(ArrayList<Block> blocks, Board board) {
    this.blocks = blocks;
    this.board = board;
  }
}