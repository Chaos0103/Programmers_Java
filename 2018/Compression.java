import java.util.*;

class Solution {

    public Map<String, Integer> dictionary = new HashMap<>();
    public Integer id = 27;

    public void init() {
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < 26; i++) {
            dictionary.put(str.substring(i, i + 1), i + 1);
        }
    }

    public int[] solution(String msg) {

        int index = 0;
        int step = 1;
        List<Integer> result = new ArrayList<>();

        init();

        while (true) {
            String substring = msg.substring(index, index + step);
            //사전에 존재한다면
            if (dictionary.containsKey(substring)) {
                step++;
            } else { //사전에 존재하지 않는다면
                //이전까지 사전 순서 저장
                String key = msg.substring(index, index + step - 1);
                result.add(dictionary.get(key));
                //사전 등록
                dictionary.put(substring, id++);
                //값 초기화
                index += step - 1;
                step = 1;
            }
            if (index + step > msg.length()) {
                break;
            }
        }
        //마지막 조사
        String key = msg.substring(index, index + step - 1);
        if (dictionary.containsKey(key)) {
            result.add(dictionary.get(key));
        }

        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
}
