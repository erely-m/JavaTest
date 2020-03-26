package com.erely.leetcode;

/**
 * 在一个 8 x 8 的棋盘上，有一个白色车（rook）。也可能有空方块，白色的象（bishop）和黑色的卒（pawn）。它们分别以字符 “R”，“.”，“B” 和 “p” 给出。大写字符表示白棋，小写字符表示黑棋。
 * <p>
 * 车按国际象棋中的规则移动：它选择四个基本方向中的一个（北，东，西和南），然后朝那个方向移动，直到它选择停止、到达棋盘的边缘或移动到同一方格来捕获该方格上颜色相反的卒。另外，车不能与其他友方（白色）象进入同一个方格。
 * <p>
 * 返回车能够在一次移动中捕获到的卒的数量。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/available-captures-for-rook
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class numRookCaptures {

    public static void main(String[] args) {
        char[][] c = new char[][]{{'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', 'p', 'p', 'p', 'p', 'p', '.', '.'},
                {'.', 'p', 'p', 'B', 'p', 'p', '.', '.'},
                {'.', 'p', 'B', 'R', 'B', 'p', '.', '.'},
                {'.', 'p', 'p', 'B', 'p', 'p', '.', '.'},
                {'.', 'p', 'p', 'p', 'p', 'p', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'}};

        System.out.println(new numRookCaptures().f(c));
    }

    public int f(char[][] board) {
        int count = 0;
        int temp;
        for (int i = 1; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 'R') { //白色车
                    for (int x = i; x >= 0; x--) { //向上
                        temp = canEat(x, j, board);
                        if (temp == -1) break;
                        if (temp == 1) {
                            count++;
                            break;
                        }
                    }
                    for (int x = i; x < 8; x++) { //向下
                        temp = canEat(x, j, board);
                        if (temp == -1) break;
                        if (temp == 1) {
                            count++;
                            break;
                        }
                    }
                    for (int y = j; y >= 0; y--) { //向左
                        temp = canEat(i, y, board);
                        if (temp == -1) break;
                        if (temp == 1) {
                            count++;
                            break;
                        }
                    }
                    for (int y = j; y < 8; y++) { //向右
                        temp = canEat(i, y, board);
                        if (temp == -1) break;
                        if (temp == 1) {
                            count++;
                            break;
                        }
                    }
                    return count;
                }
            }
        }
        return 0;
    }

    public int canEat(int x, int y, char[][] board) {
        if (board[x][y] == '.') {
        } else if (board[x][y] == 'B') {
            return -1;
        } else if (board[x][y] == 'p') {
            return 1;
        } else {
        }
        return 0;
    }
}
