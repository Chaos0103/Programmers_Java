class Solution {

    public int n, m;
    public int[][] lockBoard;

    public void newLock(int[][] lock) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                lockBoard[n + i][n + j] = lock[i][j];
            }
        }
    }

    public int[][] rotateAMatrixBy90Degree(int[][] arr) {
        int rowLength = arr.length;
        int columnLength = arr[0].length;

        int[][] res = new int[columnLength][rowLength];
        for (int r = 0; r < rowLength; r++) {
            for (int c = 0; c < columnLength; c++) {
                res[c][rowLength - 1 - r] = arr[r][c];
            }
        }
        return res;
    }

    public boolean isUnlock() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (lockBoard[n + i][n + j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean solution(int[][] key, int[][] lock) {
        n = lock.length;
        m = key.length;
        lockBoard = new int[n * 3][n * 3];

        newLock(lock);

        for (int step = 0; step < 4; step++) {
            key = rotateAMatrixBy90Degree(key);
            for (int i = 0; i < lockBoard.length - m + 1; i++) {
                for (int j = 0; j < lockBoard.length - m + 1; j++) {
                    //키 삽입
                    for (int x = 0; x < m; x++) {
                        for (int y = 0; y < m; y++) {
                            lockBoard[i + x][j + y] += key[x][y];
                        }
                    }
                    //unlock
                    if (isUnlock()) {
                        return true;
                    }
                    //키 제거
                    for (int x = 0; x < m; x++) {
                        for (int y = 0; y < m; y++) {
                            lockBoard[i + x][j + y] -= key[x][y];
                        }
                    }
                }
            }
        }
        return false;
    }
}
