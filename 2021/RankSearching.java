import java.util.*;

class Solution {

    public Map<String, ArrayList<Integer>> stores = new HashMap<>();

    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        //info -> Map
        //Key: 경우의수, Value: 점수
        for (String str : info) {
            String[] data = str.split(" ");
            inputStore(data, "", 0);
        }

        //점수만 오름차순 정렬
        for (String key : stores.keySet()) {
            Collections.sort(stores.get(key));
        }

        for (int i = 0; i < query.length; i++) {
            query[i] = query[i].replace(" and ", "");
            String[] data = query[i].split(" ");
            answer[i] = stores.containsKey(data[0]) ? binarySearch(data[0], Integer.parseInt(data[1])) : 0;
        }

        return answer;
    }

    public int binarySearch(String key, int score) {
        ArrayList<Integer> scores = stores.get(key);
        int start = 0;
        int end = scores.size() - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (scores.get(mid) < score) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return scores.size() - start;
    }

    public void inputStore(String[] data, String str, int cnt) {
        if (cnt == 4) {
            if (!stores.containsKey(str)) {
                ArrayList<Integer> array = new ArrayList<>();
                stores.put(str, array);
            }
            stores.get(str).add(Integer.parseInt(data[4]));
            return;
        }
        inputStore(data, str + "-", cnt + 1);
        inputStore(data, str + data[cnt], cnt + 1);
    }
}
