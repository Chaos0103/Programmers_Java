import java.util.*;

class Solution {

    public Map<String, List<String>> store = new HashMap<>();
    public Map<String, Integer> reportCount = new HashMap<>();

    public int[] solution(String[] id_list, String[] report, int k) {

        for (int i = 0; i < report.length; i++) {
            String[] data = report[i].split(" ");
            String key = data[0];
            String target = data[1];
            if (!store.containsKey(key)) {
                List<String> list = new ArrayList<>();
                store.put(key, list);
            }
            if (!store.get(key).contains(target)) {
                store.get(key).add(target);
            }
        }

        for (int i = 0; i < id_list.length; i++) {
            if (store.containsKey(id_list[i])) {
                List<String> reportIds = store.get(id_list[i]);
                for (String reportId : reportIds) {
                    Integer count = reportCount.getOrDefault(reportId, 0);
                    reportCount.put(reportId, count + 1);
                }
            }
        }

        int[] answer = new int[id_list.length];
        for (int i = 0; i < id_list.length; i++) {
            if (store.containsKey(id_list[i])) {
                List<String> reportIds = store.get(id_list[i]);
                for (String reportId : reportIds) {
                    Integer count = reportCount.get(reportId);
                    if (count >= k) {
                        answer[i]++;
                    }
                }
            }
        }

        return answer;
    }
}
