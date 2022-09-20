import java.util.*;

class Node implements Comparable<Node> {
    private int x;
    private int y;
    private int type;

    public Node(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getType() {
        return type;
    }

    @Override
    public int compareTo(Node other) {
        if (this.x < other.x) {
            return -1;
        } else if (this.x == other.x) {
            if (this.y < other.y) {
                return -1;
            } else if (this.y == other.y) {
                if (this.type < other.type) {
                    return -1;
                }
            }
        }
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return x == node.x && y == node.y && type == node.type;
    }
}

class Solution {

    public List<Node> nodes = new ArrayList<>();

    public boolean isCheck() {
        for (Node node : nodes) {
            int x = node.getX();
            int y = node.getY();
            int type = node.getType();
            if (type == 0) {
                if (y == 0 || nodes.contains(new Node(x, y - 1, 0)) || nodes.contains(new Node(x - 1, y, 1)) || nodes.contains(new Node(x, y, 1))) {
                    continue;
                }
            } else {
                if (nodes.contains(new Node(x, y - 1, 0)) || nodes.contains(new Node(x + 1, y - 1, 0))
                        || (nodes.contains(new Node(x - 1, y, 1))) && nodes.contains(new Node(x + 1, y, 1))) {
                    continue;
                }
            }
            return false;
        }
        return true;
    }

    public int[][] solution(int n, int[][] build_frame) {
        //[x, y, a, b]
        //a: 기둥0, 보1
        //b: 삭제0, 설치1
        for (int[] frame : build_frame) {
            int x = frame[0];
            int y = frame[1];
            int a = frame[2];
            int b = frame[3];
            if (b == 0) { //delete
                nodes.remove(new Node(x, y, a));
                if (!isCheck()) {
                    nodes.add(new Node(x, y, a));
                }
            } else { //install
                nodes.add(new Node(x, y, a));
                if (!isCheck()) {
                    nodes.remove(nodes.size() - 1);
                }
            }
        }

        Collections.sort(nodes);

        int[][] answer = new int[nodes.size()][3];

        for (int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);
            answer[i][0] = node.getX();
            answer[i][1] = node.getY();
            answer[i][2] = node.getType();
        }

        return answer;
    }
}
