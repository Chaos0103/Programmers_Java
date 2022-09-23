import java.util.*;

class Solution {

    public int maxSheep;
    public ArrayList<ArrayList<Integer>> tree = new ArrayList<ArrayList<Integer>>();

    public int solution(int[] info, int[][] edges) {

        //초기화
        for (int i = 0; i < info.length; i++) {
            tree.add(new ArrayList<Integer>());
        }

        for (int[] edge : edges) {
            int parent = edge[0];
            int child = edge[1];
            tree.get(parent).add(child);
        }

        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        dfs(0, 0, 0, list, info);

        return maxSheep;
    }

    public void dfs(int idx, int sheepCount, int wolfCount, ArrayList<Integer> list, int[] info) {
        if (info[idx] == 0) {
            sheepCount++;
        } else {
            wolfCount++;
        }

        if (sheepCount <= wolfCount) {
            return;
        }

        maxSheep = Math.max(maxSheep, sheepCount);

        ArrayList<Integer> newList = new ArrayList<>(list); //기존 리스트를 사용하면 44번째 줄에서 리스트가 변경되어 예외 발생
        newList.addAll(tree.get(idx));
        newList.remove(Integer.valueOf(idx)); //int와 Integer은 다름
        for (Integer num : newList) {
            dfs(num, sheepCount, wolfCount, newList, info);
        }
    }
}
