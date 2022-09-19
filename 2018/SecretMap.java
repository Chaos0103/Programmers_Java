class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] arr1Binary = new String[n];
        for (int i = 0; i < n; i++) {
            String str = Integer.toBinaryString(arr1[i]);
            if (str.length() != n) {
                StringBuilder replace = new StringBuilder();
                for (int j = 0; j < n - str.length(); j++) {
                    replace.append("0");
                }
                str = String.valueOf(replace.append(str));
            }
            arr1Binary[i] = str;
        }

        String[] arr2Binary = new String[n];
        for (int i = 0; i < n; i++) {
            String str = Integer.toBinaryString(arr2[i]);
            if (str.length() != n) {
                StringBuilder replace = new StringBuilder();
                for (int j = 0; j < n - str.length(); j++) {
                    replace.append("0");
                }
                str = String.valueOf(replace.append(str));
            }
            arr2Binary[i] = str;
        }

        String[] answer = new String[n];
        for (int i = 0; i < n; i++) {
            StringBuilder str = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (arr1Binary[i].charAt(j) == '0' && arr2Binary[i].charAt(j) == '0') {
                    str.append(" ");
                } else {
                    str.append("#");
                }
            }
            answer[i] = String.valueOf(str);
        }

        return answer;
    }
}
