import java.util.*;

class Data implements Comparable<Data> {
    private Integer index;
    private String fileName;
    private String head;
    private Integer number;
    private String tail;

    public Data(Integer index, String fileName, String head, Integer number, String tail) {
        this.index = index;
        this.fileName = fileName;
        this.head = head;
        this.number = number;
        this.tail = tail;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public int compareTo(Data other) {
        if (this.head.compareTo(other.head) < 0) {
            return -1;
        } else if (this.head.compareTo(other.head) == 0) {
            if (this.number < other.number) {
                return -1;
            } else {
                if (this.number.equals(other.number)) {
                    if (this.index < other.index) {
                        return -1;
                    }
                }
            }
        }
        return 1;
    }
}
class Solution {

    public List<Data> store = new ArrayList<>();

    //0->start index, 1->end index
    public Integer[] getNumberIndex(String str) {
        boolean flag = false;
        int startIndex = 0;
        int endIndex = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (flag && !('0' <= ch && ch <= '9')) {
                break;
            }
            if ('0' <= ch && ch <= '9') {
                if (!flag) {
                    startIndex = i;
                    flag = true;
                }
                endIndex = i;
            }
        }
        return new Integer[]{startIndex, endIndex + 1};
    }

    public String[] solution(String[] files) {
        String[] answer = new String[files.length];

        int index = 0;
        for (String str : files) {
            Integer[] numberIndex = getNumberIndex(str);
            String head = str.substring(0, numberIndex[0]);
            String number = str.substring(numberIndex[0], numberIndex[1]);
            String tail = str.substring(numberIndex[1]);

            store.add(new Data(index++, str, head.toUpperCase(), Integer.parseInt(number), tail.toUpperCase()));
        }

        Collections.sort(store);

        for (int i = 0; i < store.size(); i++) {
            answer[i] = store.get(i).getFileName();
        }

        return answer;
    }
}
