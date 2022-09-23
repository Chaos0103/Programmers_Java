class Solution {

    public int[][] sum;
    public int n, m;

    public void prefixSum() {
        //상하
        for (int j = 0; j < m; j++) {
            for (int i = 1; i < n; i++) {
                sum[i][j] += sum[i - 1][j];
            }
        }
        //좌우

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < m; j++) {
                sum[i][j] += sum[i][j - 1];
            }
        }
    }

    public int solution(int[][] board, int[][] skill) {
        n = board.length;
        m = board[0].length;

        sum = new int[n + 1][m + 1];
        for (int[] s : skill) {
            int type = s[0];
            int x1 = s[1], y1 = s[2];
            int x2 = s[3], y2 = s[4];
            int degree = type == 1 ? s[5] * -1 : s[5];

            sum[x1][y1] += degree;
            sum[x1][y2 + 1] += degree * -1;
            sum[x2 + 1][y1] += degree * -1;
            sum[x2 + 1][y2 + 1] += degree;
        }
        prefixSum();

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] + sum[i][j] > 0) {
                    answer++;
                }
            }
        }
        return answer;
    }
}
