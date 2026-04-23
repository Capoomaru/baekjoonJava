package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _2302TheaterSeat {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            int n = Integer.parseInt(br.readLine());
            int m = Integer.parseInt(br.readLine());
            int min = 0;
            int answer = 1;
            int[] caseN = new int[n + 2];
            caseN[0] = 1;
            caseN[1] = 1;
            caseN[2] = 2;
            for(int i=3;i<=n+1;i++) {
                caseN[i] = caseN[i-1] + caseN[i-2];
            }

            for(int i=0;i<m;i++) {
                int tmp = Integer.parseInt(br.readLine());
                if(tmp > n) continue;
                answer *= caseN[tmp - min - 1];
                min = tmp;
            }

            if(n >= min)
                answer *= caseN[n - min];

            answer = answer == 0 ? 1 : answer;

            System.out.println(answer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
