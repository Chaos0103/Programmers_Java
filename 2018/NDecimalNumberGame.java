class Solution {
    public String solution(int n, int t, int m, int p) {

        StringBuilder result = new StringBuilder();
        result.append(0);
        for (int number = 1; ; number++) {
            StringBuilder transfer = new StringBuilder();
            int num = number;
            while (num != 0) {
                int res = num % n;

                if (res == 10) {
                    transfer.append('A');
                } else if (res == 11) {
                    transfer.append('B');
                } else if (res == 12) {
                    transfer.append('C');
                } else if (res == 13) {
                    transfer.append('D');
                } else if (res == 14) {
                    transfer.append('E');
                } else if (res == 15) {
                    transfer.append('F');
                } else {
                    transfer.append(res);
                }

                num /= n;
            }
            result.append(transfer.reverse());
            if (result.length() >= t * m) {
                break;
            }
        }

        String str = String.valueOf(result);
        result = new StringBuilder();
        for (int i = 0; i < t; i++) {
            char ch = str.charAt(m * i + (p - 1));
            result.append(ch);
        }

        return String.valueOf(result);
    }
}
