import java.util.*;
class Solution {

    public final int INF = (int) 1e9;
    public int[][] graph = new int[201][201];

    public int solution(int n, int s, int a, int b, int[][] fares) {

        for (int i = 0; i < 201; i++) {
            Arrays.fill(graph[i], INF);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                }
            }
        }

        //fares [c,d,f] -> c와 d 사이의 요금이 f
        for (int[] fare : fares) {
            graph[fare[0]][fare[1]] = fare[2];
            graph[fare[1]][fare[0]] = fare[2];
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        int answer = INF;
        for (int mid = 1; mid <= n; mid++) {

            if (graph[s][mid] == INF || graph[mid][a] == INF || graph[mid][b] == INF) {
                continue;
            }
            int result = graph[s][mid] + graph[mid][a] + graph[mid][b];
            answer = Math.min(answer, result);
        }

        return answer;
    }
}
