package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

//12분 42초 정답
public class _1331NightTour {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashSet<String> set = new HashSet<>();
        int startX, startY;
        try {
            String input = br.readLine();
            char x = input.charAt(0);
            char y = input.charAt(1);
            startX = x;
            startY = y;
            set.add(input);
            for(int i=1;i<36;i++) {
                input = br.readLine();
                int a = Math.abs(input.charAt(0) - x);
                int b = Math.abs(input.charAt(1) - y);
                if(set.contains(input) || a==3 || b ==3 || a + b != 3) {
                    System.out.println("Invalid");
                    return;
                }
                set.add(input);
                x = input.charAt(0);
                y = input.charAt(1);
            }
            int a = Math.abs(startX - x);
            int b = Math.abs(startY - y);
            if(a==3 || b ==3 || a + b != 3)
                System.out.println("Invalid");
            else
                System.out.println("Valid");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
