package week1;

import java.util.Scanner;
import java.util.StringTokenizer;

//8분 1초 정답
public class _24390Microwave {
    public static void main(String[] args) {
        int[] button = {600, 60, 30, 10};
        Scanner sc = new Scanner(System.in);

        StringTokenizer st = new StringTokenizer(sc.next(), ":");

        int target = 0;
        target += Integer.parseInt(st.nextToken()) * 60;
        target += Integer.parseInt(st.nextToken());

        int cnt = 0;
        for(int i=0;i<4;i++) {
            int divided = target/button[i];
            int rest = target%button[i];
            if(i == 2 && divided == 0)
                cnt++;
            cnt += divided;
            target = rest;
        }

        System.out.println(cnt);

    }
}
