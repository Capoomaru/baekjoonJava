import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _15729 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        boolean[] arr = new boolean[N + 2];
        for (int i = 0; i < N; i++) {
            arr[i] = st.nextToken().equals("1");
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (!arr[i]) continue;
            cnt++;

            arr[i] ^= true;
            arr[i + 1] ^= true;
            arr[i + 2] ^= true;
        }
        System.out.println(cnt);
    }
}