class Solution {

    public boolean isNumber(char ch) {
        if ('0' <= ch && ch <= '9') {
            return true;
        }
        return false;
    }

    public boolean isAlpha(char ch) {
        if ('A' <= ch && ch <= 'Z') {
            return true;
        }
        return false;
    }

    public int solution(String dartResult) {
        int[] scores = new int[3];
        int index = 0;
        for (int i = 0; i < dartResult.length(); i++) {
            char ch = dartResult.charAt(i);
            if (isNumber(ch)) {
                scores[index] *= 10;
                scores[index] += ch - '0';
            } else if (isAlpha(ch)) {
                if (ch == 'D') {
                    scores[index] = (int) Math.pow(scores[index], 2);
                } else if (ch == 'T') {
                    scores[index] = (int) Math.pow(scores[index], 3);
                }
                index++;
            } else {
                if (ch == '*') {
                    if (index <= 1) {
                        scores[index - 1] *= 2;
                    } else {
                        scores[index - 1] *= 2;
                        scores[index - 2] *= 2;
                    }
                } else if (ch == '#') {
                    scores[index - 1] *= -1;
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < 3; i++) {
            answer += scores[i];
        }

        return answer;
    }
}
