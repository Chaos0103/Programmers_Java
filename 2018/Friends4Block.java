import java.util.*;

class Solution {

    public char[][] newBoard;
    public int[][] store;
    //터지는지 확인
    public boolean isCheck(char[][] arr) {
        char ch = arr[0][0];
        if (ch == '#') {
            return false;
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (arr[i][j] != ch) {
                    return false;
                }
            }
        }
        return true;
    }

    public char[][] part(int x, int y) {
        char[][] result = new char[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                result[i][j] = newBoard[x + i][y + j];
            }
        }
        return result;
    }

    public void checkStore(int x, int y) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                store[x + i][y + j] = 1;
            }
        }
    }

    public int count(int m, int n) {
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (store[i][j] == 1) {
                    newBoard[i][j] = '#';
                    cnt++;
                }
            }
        }
        return cnt;
    }
    //m:height, n:width
    public int solution(int m, int n, String[] board) {
        newBoard = new char[m][n];
        store = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                newBoard[i][j] = board[i].charAt(j);
            }
        }
        int answer = 0;
        while (true) {
            boolean flag = false;
            //2x2 check
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    char[][] part = part(i, j);
                    if (isCheck(part)) {
                        checkStore(i, j);
                        flag = true;
                    }
                }
            }
            if (!flag) {
                break;
            }
            //터트리며 카운트
            answer += count(m, n);
            //밑으로 내리기
            for (int i = 0; i < n; i++) {
                for (int j = m - 1; j >= 0; j--) {
                    if (newBoard[j][i] == '#') {
                        for (int k = j; k >= 0; k--) {
                            if (newBoard[k][i] != '#') {
                                newBoard[j][i] = newBoard[k][i];
                                newBoard[k][i] = '#';
                                break;
                            }
                        }
                    }
                }
            }

            //store clear
            for (int i = 0; i < m; i++) {
                Arrays.fill(store[i], 0);
            }
        }

        return answer;
    }
}
