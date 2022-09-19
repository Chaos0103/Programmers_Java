import java.util.*;

class Node implements Comparable<Node> {
    private int stage;
    private float failRate;

    public Node(int stage, float failRate) {
        this.stage = stage;
        this.failRate = failRate;
    }

    public int getStage() {
        return stage;
    }

    public float getFailRate() {
        return failRate;
    }

    @Override
    public int compareTo(Node other) {
        if (this.failRate > other.failRate) {
            return -1;
        } else if (this.failRate == other.failRate) {
            if (this.stage < other.stage) {
                return -1;
            }
        }
        return 1;
    }
}

class Solution {

    public ArrayList<Node> nodes = new ArrayList<>();

    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        int[] stageCount = new int[N + 2];

        int size = stages.length;
        for (int stage : stages) {
            stageCount[stage]++;
        }

        for (int i = 1; i <= N; i++) {
            int count = stageCount[i];
            if (count == 0) {
                nodes.add(new Node(i, 0.0f));
                continue;
            }
            nodes.add(new Node(i, (float) count / size));
            size -= count;
        }

        Collections.sort(nodes);

        for (int i = 0; i < N; i++) {
            answer[i] = nodes.get(i).getStage();
        }

        return answer;
    }
}
