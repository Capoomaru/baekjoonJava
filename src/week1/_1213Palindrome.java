package week1;

import java.util.*;

//11분 39초 정답
public class _1213Palindrome {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.next();

        TreeMap<Character, Integer> countMap = new TreeMap<>();

        for(char c : s.toCharArray()) {
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }

        if(countMap.values().stream().filter(cnt -> cnt % 2 == 1).count() > 2) {
            System.out.println("I'm Sorry Hansoo");
            return;
        }

        char[] answer = new char[s.length()];
        int i=0;
        boolean hasOdd = s.length() % 2 == 0;
        for(Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            if(entry.getValue() % 2 == 1) {
                if(hasOdd) {
                    System.out.println("I'm Sorry Hansoo");
                    return;
                }
                hasOdd = true;
                answer[s.length()/2] = entry.getKey();
            }

            int size = entry.getValue() / 2;
            for(int j = 0;j<size;j++) {
                answer[i] = entry.getKey();
                answer[s.length() - i - 1] = entry.getKey();
                i++;
            }
        }

        System.out.println(answer);
    }
}
