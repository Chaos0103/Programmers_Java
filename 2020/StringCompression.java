class Solution {
    public int solution(String s) {
        int answer = s.length();

        for (int step = 1; step <= s.length() / 2; step++) {
            StringBuilder str = new StringBuilder();
            String prev = s.substring(0, step);
            int count = 1;
            for (int i = step; i < s.length(); i += step) {
                if (i + step > s.length()) {
                    str.append(s.substring(i));
                    break;
                }
                String now = s.substring(i, i + step);
                if (prev.equals(now)) {
                    count++;
                } else {
                    if (count > 1) {
                        str.append(count);
                    }
                    str.append(prev);
                    prev = now;
                    count = 1;
                }
            }
            if (count > 1) {
                str.append(count);
            }
            str.append(prev);
            answer = Math.min(answer, str.length());
        }


        return answer;
    }
}
