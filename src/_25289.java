import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _25289 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] map = new int[N];
        for(int i=0;i<N;i++)
            map[i] = Integer.parseInt(st.nextToken());

        int start = 0;
        int diff = map[1] - map[0];
        int max = 0;
        for(int i=1;i<N-1;i++) {
            if(map[i + 1] - map[i] == diff) {
                continue;
            }
            max = Math.max(max, i - start + 1);
            diff = map[i + 1] - map[i];
            start = i;
        }
        max = Math.max(max, N - start );

        System.out.println(max);
    }
}
