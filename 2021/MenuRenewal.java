import java.util.*;
class Combination {
    private int n;
    private int r;
    private int[] now; // 현재 조합
    private ArrayList<ArrayList<String>> result; // 모든 조합

    public ArrayList<ArrayList<String>> getResult() {
        return result;
    }

    public Combination(int n, int r) { //전체 갯수, 뽑을 갯수
        this.n = n;
        this.r = r;
        this.now = new int[r];
        this.result = new ArrayList<ArrayList<String>>();
    }

    public void combination(ArrayList<String> arr, int depth, int index, int target) {
        if (depth == r) {
            ArrayList<String> temp = new ArrayList<>();
            for (int i = 0; i < now.length; i++) {
                temp.add(arr.get(now[i]));
            }
            result.add(temp);
            return;
        }
        if (target == n) return;
        now[index] = target;
        combination(arr, depth + 1, index + 1, target + 1);
        combination(arr, depth, index, target + 1);
    }
}
class Solution {
    public String[] solution(String[] orders, int[] course) {

        ArrayList<String> menus = new ArrayList<>();

        for (int i = 0; i < course.length; i++) {

            Map<String, Integer> store = new HashMap<>();

            for (int j = 0; j < orders.length; j++) {
                ArrayList<String> data = new ArrayList<>(List.of(orders[j].split("")));

                Combination combination = new Combination(orders[j].length(), course[i]);
                combination.combination(data, 0, 0, 0);
                ArrayList<ArrayList<String>> result = combination.getResult();

                for (ArrayList<String> strings : result) {
                    StringBuilder builder = new StringBuilder();
                    Collections.sort(strings);
                    for (String str : strings) {
                        builder.append(str);
                    }
                    String str = String.valueOf(builder);
                    store.put(String.valueOf(str), store.getOrDefault(String.valueOf(str), 0) + 1);
                }
            }

            Collection<Integer> values = store.values();
            int maxCount = 0;
            for (Integer value : values) {
                maxCount = Math.max(maxCount, value);
            }

            if (maxCount > 1) {
                Set<String> keys = store.keySet();
                for (String key : keys) {
                    if (store.get(key) == maxCount) {
                        menus.add(key);
                    }
                }
            }
        }

        Collections.sort(menus);

        String[] answer = new String[menus.size()];
        for (int i = 0; i < menus.size(); i++) {
            answer[i] = menus.get(i);
        }

        return answer;
    }
}
