class Solution {

    public int[] timeCount = new int[1081];

    public String getTime(int minute) {
        int h = minute / 60;
        int m = minute % 60;
        return String.format("%02d", h) + ":" + String.format("%02d", m);
    }

    public int getMinute(String time) {
        String[] str = time.split(":");
        return Integer.parseInt(str[0]) * 60 + Integer.parseInt(str[1]);
    }

    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "00:00";

        for (String time : timetable) {
            int minute = getMinute(time);
            //18:00 이후는 버스 운영이 안됨
            if (minute > 1080) {
                continue;
            }
            timeCount[minute]++;
        }


        int crewCount = 0;
        int busTime = 540;
        String buffer = null;
        for (int i = 1; i < 1081; i++) {
            crewCount += timeCount[i];
            if (crewCount < m * n) {
                answer = getTime(i);
            }
            //버스 출발시간 로직 추가
            if (i == busTime) {
                crewCount -= m;
                if (crewCount < 0) {
                    crewCount = 0;
                }
                busTime += t;
                n -= 1;
                if (crewCount < m && n > 0) {
                    answer = getTime(i);
                }
            }
            if (n <= 0) {
                break;
            }
        }

        return answer;
    }
}
