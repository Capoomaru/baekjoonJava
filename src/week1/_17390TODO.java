package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

//6분 30초 메모리 초과
//25분 1초 시간 초과
//28분 3초 시간 초과
public class _17390TODO {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int len = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int[] arr = new int[len];
            int[] sumArr = new int[len+1];

            st = new StringTokenizer(br.readLine(), " ");
            for(int i=0;i<len;i++)
                arr[i] = Integer.parseInt(st.nextToken());

            Arrays.sort(arr);

            int tmp = 0;
            for(int i=0;i<len;i++) {
                tmp += arr[i];
                sumArr[i+1] = tmp;
            }

            StringBuilder sb = new StringBuilder();

            for(int i=0;i<n;i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                sb.append(sumArr[y] - sumArr[x-1]);
                sb.append("\n");
            }

            System.out.println(sb);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
