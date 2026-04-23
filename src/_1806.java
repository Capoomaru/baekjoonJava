import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        int[] map = new int[N];
        for (int i = 0; i < N; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0, sum = map[0];
        int min = Integer.MAX_VALUE;
        if (sum >= S) {
            System.out.println(1);
            return;
        }
        for (int i = 1; i < N; i++) {
            sum += map[i];
            if (sum >= S) {
                min = Math.min(min, i - start + 1);
                while (sum > S) {
                    sum -= map[start++];

                    if(sum < S) {
                        min = Math.min(min, i - start + 2);
                    }
                    else if(sum ==S)
                        min = Math.min(min, i - start + 1);
                }

            }
        }

        if (min == Integer.MAX_VALUE)
            System.out.println(0);
        else
            System.out.println(min);
    }

}


