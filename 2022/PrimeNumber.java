class Solution {

    public boolean primeNumber(long number) {
        for (int i = 2; i < (int) Math.sqrt(number) + 1; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }


    public int solution(int n, int k) {
        int answer = 0;
        //k진수 변경
        StringBuilder builder = new StringBuilder();
        while (n != 0) {
            builder.append(n % k);
            n /= k;
        }
        String transfer = String.valueOf(builder.reverse());
        String[] data = transfer.split("0");

        for (String str : data) {
            if (str.isEmpty() || str.equals("1")) {
                continue;
            }
            long number = Long.parseLong(str);
            if (primeNumber(number)) {
                answer++;
            }
        }

        return answer;
    }
}
