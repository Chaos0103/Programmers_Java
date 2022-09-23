class Solution {

    public int maxDepth;
    public int[] apeachInfo;
    public int[] lionInfo;
    public int maxScoreDiff = 0;

    public int[] solution(int n, int[] info) {
        maxDepth = n;
        apeachInfo = info;
        int[] arr = new int[11];
        dfs(0, arr, -1);
        return maxScoreDiff > 0 ? lionInfo : new int[]{-1};
    }

    public void dfs(int depth, int[] info, int idx) {
        if (depth == maxDepth) {
            int apeachScore = 0;
            int lionScore = 0;
            for (int i = 0; i < 11; i++) {
                if (info[i] > apeachInfo[i]) {
                    lionScore += 10 - i;
                } else if (apeachInfo[i] > 0) {
                    apeachScore += 10 - i;
                }
            }
            int scoreDiff = lionScore - apeachScore;
            if (scoreDiff > maxScoreDiff) {
                maxScoreDiff = scoreDiff;
                lionInfo = info;
            } else if (maxScoreDiff > 0 && scoreDiff == maxScoreDiff){
                for (int i = 10; i >= 0; i--) {
                    if (info[i] > lionInfo[i]) {
                        lionInfo = info;
                    } else if (lionInfo[i] > info[i]) {
                        return;
                    }
                }
            }
            return;
        }

        for (int i = idx + 1; i < 11; i++) {
            int[] nextLionInfo = new int[11];
            for (int j = 0; j < 11; j++) {
                nextLionInfo[j] = info[j];
            }

            if (i == 10) {
                nextLionInfo[i] = maxDepth - depth;
                dfs(maxDepth, nextLionInfo, i);
            } else if (maxDepth - depth > apeachInfo[i]) {
                nextLionInfo[i] = apeachInfo[i] + 1;
                dfs(depth + nextLionInfo[i], nextLionInfo, i);
            }
        }
    }
}
