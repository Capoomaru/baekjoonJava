import java.util.*;
import java.io.*;

public class Test1000 {

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        for(int testCase = 1; testCase <= t; testCase++){
            long n = Long.parseLong(br.readLine());

            long res = binarySearch(n);
            if(checked(res) != n){
                sb.append("#").append(testCase).append(" ").append(-1).append("\n");
            }else {
                sb.append("#").append(testCase).append(" ").append(binarySearch(n)).append("\n");
            }
        }

        System.out.println(sb.toString());
    }

    public static long binarySearch(long n){
        long left = 1;
        long right = 10000000000L;

        while(left + 1 < right){
            long mid = (left + right)/2;

            if(checked(mid) <= n){
                left = mid;
            }else{
                right = mid;
            }
        }
        return left;
    }

    public static long checked(long target){
        return target * (target + 1) / 2;
    }

}