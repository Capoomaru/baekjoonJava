import java.util.HashMap;
import java.util.Scanner;

public class WordStudy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.next().toUpperCase();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int max = map.entrySet().stream().mapToInt((entry) -> entry.getValue()).max().getAsInt();
        char maxChar = 0;
        for (char c : map.keySet()) {
            if (map.get(c) == max) {
                if (maxChar == 0)
                    maxChar = c;
                else {
                    System.out.println("?");
                    return;
                }
            }
        }
        System.out.println(maxChar);
    }
}
