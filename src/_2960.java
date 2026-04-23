import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2960 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        boolean[] isNotPrime = new boolean[N+1];

        int cnt = 0;
        for(int i=2;i<=N;i++) {
            if(isNotPrime[i])
                continue;
            cnt++;
            if(cnt == K) {
                System.out.println(i);
                return;
            }
            for(int j=2;j * i <= N; j++) {
                if(isNotPrime[i * j])
                    continue;
                cnt++;
                isNotPrime[i * j] = true;
                if(cnt == K) {
                    System.out.println(i * j);
                    return;
                }
            }
        }
    }
}
