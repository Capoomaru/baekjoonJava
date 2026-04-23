import java.util.LinkedList;
import java.util.Scanner;

public class _8949RoughlyAdd {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        LinkedList<Integer> list = new LinkedList<>();

        String first = sc.next();
        String second = sc.next();

        int min = Math.min(first.length(), second.length());

        for(int i=1;i<=min;i++) {
            list.addFirst(first.charAt(first.length() - i)
                    + second.charAt(second.length() - i)
                    - (2 * '0'));
        }

        for(int i = min+1;i <= first.length();i++) {
            list.addFirst(first.charAt(first.length() - i) - '0');
        }
        for(int i = min+1;i <= second.length();i++) {
            list.addFirst(second.charAt(second.length() - i) - '0');
        }

        StringBuilder sb = new StringBuilder();
        for(int i : list)
            sb.append(i);

        System.out.println(sb);
    }
}
