import java.util.*;

class Solution {

    public List<String> cache = new ArrayList<>();

    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        for (String city : cities) {
            city = city.toUpperCase(); //대문자로 변환
            if (cache.contains(city)) { //cache hit
                cache.remove(city);
                cache.add(city);
                answer++;
            } else { //cache miss
                cache.add(city);
                if (cache.size() > cacheSize) {
                    cache.remove(0);
                }
                answer += 5;
            }
        }

        return answer;
    }
}
