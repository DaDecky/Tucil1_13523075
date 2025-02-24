
package game;

import java.util.ArrayList;

public class Block {
  private char[][] block;
  private int width, height;
  private char type;
  public int variance = 0;

  public Block(ArrayList<String> block_lines) {
    // get the type of the block
    this.type = block_lines.get(0).trim().charAt(0);

    int max_length = 0;
    for (String line : block_lines) {
      if (line.length() > max_length) {
        max_length = line.length();
      }
    }
    this.width = max_length;
    this.height = block_lines.size();
    this.block = new char[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        if (j < block_lines.get(i).length()) {
          this.block[i][j] = block_lines.get(i).charAt(j);
        } else {
          this.block[i][j] = ' ';
        }
      }
    }
  }

  public Block(char[][] block) {
    this.block = block;
    this.height = block.length;
    this.width = block[0].length;

    for (int i = 0; i < this.width; i++) {
      if (block[0][i] != ' ') {
        this.type = block[0][i];
        break;
      }
    }

  }

  public Block next() {
    if (this.variance == 7) {
      return null;
    }
    if (this.variance == 3) {
      Block b = this.rotate90().flip();
      b.variance--;
      return b;
    }
    Block b = this.rotate90();
    return b;
  }

  public Block rotate90() {
    char[][] new_block = new char[this.width][this.height];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        new_block[j][this.height - i - 1] = this.block[i][j];
      }
    }
    Block b = new Block(new_block);
    b.variance = this.variance + 1;
    return b;
  }

  public Block flip() {
    char[][] new_block = new char[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        new_block[i][j] = this.block[i][this.width - j - 1];
      }
    }
    Block b = new Block(new_block);
    b.variance = this.variance + 1;
    return b;

  }

  public void print() {
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        System.out.print(this.block[i][j]);
      }
      System.out.println();
    }
  }

  // seeters
  // public void setIsPlaced(boolean isPlaced) {
  // this.isPlaced = isPlaced;
  // }

  // public void setVariance(int variance) {
  // this.variance = variance;
  // }

  // getters
  public char info(int x, int y) {
    return this.block[x][y];
  }

  public char[][] getBlock() {
    return this.block;
  }

  public int getWidth() {
    return this.width;
  }

  public int getHeight() {
    return this.height;
  }

  public char getType() {
    return this.type;
  }

  // public boolean getIsPlaced() {
  // return this.isPlaced;
  // }

}
