class Solution {

    public String remove(String str) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if ('a' <= ch && ch <= 'z') {
                builder.append(ch);
            } else if (ch == '-' || ch == '_' || ch == '.') {
                builder.append(ch);
            } else if ('0' <= ch && ch <= '9') {
                builder.append(ch);
            }
        }
        return String.valueOf(builder);
    }

    public String solution(String new_id) {
        //step1
        new_id = new_id.toLowerCase();

        //step2
        new_id = remove(new_id);

        //step3
        int len = 0;
        while (new_id.length() != len) {
            len = new_id.length();
            new_id = new_id.replace("..", ".");
        }

        //step4
        //처음
        if (new_id.length() > 0 && new_id.charAt(0) == '.') {
            new_id = new_id.substring(1);
        }
        //끝
        if (new_id.length() > 0 && new_id.charAt(new_id.length() - 1) == '.') {
            new_id = new_id.substring(0, new_id.length() - 1);
        }

        //step5
        if (new_id.isEmpty()) {
            new_id = "a";
        }

        //step6
        if (new_id.length() > 15) {
            new_id = new_id.substring(0, 15);
        }
        if (new_id.charAt(new_id.length() - 1) == '.') {
            new_id = new_id.substring(0, new_id.length() - 1);
        }

        //step7
        for (int i = 0; i < 3; i++) {
            if (new_id.length() > 2) {
                break;
            }
            new_id += new_id.substring(new_id.length() - 1);
        }

        return new_id;
    }
}
