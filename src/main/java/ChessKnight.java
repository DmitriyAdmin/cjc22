import java.util.ArrayList;
import java.util.List;

public class ChessKnight {

   public static int countMoves(
       int width, int height,
       int startCol, int startRow,
       int endCol, int endRow) {

     if ((width < 3 && height < 3) ||
         (width == 3 && height == 3 && (endCol == 2 && endRow == 2))) return -1;

     int x = Math.abs(startCol - endCol);
     int y = Math.abs(startRow - endRow);
     int d1 = (int) Math.ceil((x + y) / 3.);
     int d2 = (int) Math.ceil(x / 2.);
     int d3 = (int) Math.ceil(y / 2.);
     int moves = Math.max(Math.max(d2, d3), d1);

     if (((startCol + startRow + endCol + endRow) % 2 == 0 && moves % 2 != 0) ||
         ((startCol + startRow + endCol + endRow) % 2 != 0 && moves % 2 == 0)) {
       moves++;
     }

     if (width < 5 && height < 5) {
       moves+=2;
     }
     return moves;
   }

  public static int countMovesV2(
      int width, int height,
      int startCol, int startRow,
      int endCol, int endRow) {
    /**                            x|rows  y|columns */
    boolean[][] board = new boolean[width][height];
    int[][] dirs = {{1, -2}, {-2, 1}, {-1, -2}, {-1, 2}, {1, 2}, {-2, -1}, {2, 1}, {2, -1}};

    List<int[]> listPositions = new ArrayList<>();
    List<int[]> listNewPositions;
    listPositions.add(new int[]{startCol - 1, startRow - 1});
    int moves = 0;

    while (!listPositions.isEmpty()) {
      listNewPositions = new ArrayList<>();
      while (!listPositions.isEmpty()) {
        int[] position = listPositions.remove(listPositions.size() - 1);

        if (position[0] + 1 == endCol && position[1] + 1 == endRow) {return moves;}
        if (board[position[0]][position[1]]) {continue;}

        board[position[0]][position[1]] = true;
        for (int[] dir : dirs) {
          int newX = position[0] + dir[0];
          int newY = position[1] + dir[1];
          if (newX < 0 || newY < 0 || newX >= width || newY >= height || board[newX][newY]) {
            continue;
          }
          listNewPositions.add(new int[]{newX, newY});
        }
      }
      listPositions = listNewPositions;
      moves++;
    }
    return -1;
  }
}
