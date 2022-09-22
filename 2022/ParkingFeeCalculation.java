import java.util.*;

class Solution {
    //fees -> 0: 기본시간, 1: 기본요금, 2: 단위시간, 3: 단위요금
    //records -> "시각(HH:MM) 차량번호 IN/OUT"
    //return -> 차량 번호가 작은것부터 주차요금 반환

    //청구시간
    public Map<String, Integer> charge = new HashMap<>();
    //주차내역
    public Map<String, Integer> parking = new HashMap<>();

    public int minuteToHour(String time) {
        String[] date = time.split(":");
        int hour = Integer.parseInt(date[0]) * 60;
        int minute = Integer.parseInt(date[1]);
        return hour + minute;
    }

    public int[] solution(int[] fees, String[] records) {
        int defaultTime = fees[0], defaultFee = fees[1], plusTime = fees[2], plusFee = fees[3];

        for (String record : records) {
            String[] str = record.split(" ");
            int minute = minuteToHour(str[0]);
            String carNumber = str[1];
            String type = str[2];

            if (type.equals("IN")) {
                parking.put(carNumber, minute);
            } else {
                Integer startTime = parking.get(carNumber);
                parking.remove(carNumber);
                int term = minute - startTime;
                Integer prevTime = charge.getOrDefault(carNumber, 0);
                charge.put(carNumber, prevTime + term);
            }
        }

        if (!parking.isEmpty()) {
            int lastTime = minuteToHour("23:59");
            for (String carNumber : parking.keySet()) {
                Integer startTime = parking.get(carNumber);
                int time = lastTime - startTime;
                Integer prevTime = charge.getOrDefault(carNumber, 0);
                charge.put(carNumber, prevTime + time);
            }
        }

        ArrayList<String> carNumbers = new ArrayList<>(charge.keySet());
        Collections.sort(carNumbers);

        int[] answer = new int[carNumbers.size()];

        for (int i = 0; i < carNumbers.size(); i++) {
            Integer totalTime = charge.get(carNumbers.get(i));
            if (totalTime <= defaultTime) {
                answer[i] = defaultFee;
            } else {
                int result = (int) Math.ceil((totalTime - defaultTime) / (double) plusTime);
                answer[i] = defaultFee + result * plusFee;
            }
        }

        return answer;
    }
}
