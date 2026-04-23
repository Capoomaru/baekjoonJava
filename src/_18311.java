import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _18311 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        long target = Long.parseLong(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        long[] line = new long[n];
        long sum = 0;
        for (int i = 0; i < n; i++) {
            int len = Integer.parseInt(st.nextToken());
            sum += len;
            line[i] -= sum;
            if (sum > target) {
                System.out.println(i + 1);
                return;
            }
        }
        //여기까지 왔으면 왕복하는 구간임
        target -= sum;
        line[n - 1] += sum;
        for (int i = n - 2; i >= 0; i--) {
            line[i] += sum;
            if ( line[i] > target) {
                System.out.println( i + 2 );
                return;
            }
        }

        System.out.println(1);

    }
}
