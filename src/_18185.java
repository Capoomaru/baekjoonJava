import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _18185 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        long sum = 0;

        int[] map = new int[N + 2];
        int[] map2 = new int[N + 2];
        for (int i = 2; i < N + 2; i++) {
            map[i] = Integer.parseInt(st.nextToken());
            map2[i - 2] = map[i];
        }

        for (int i = N + 1; i > 1; i--) {
            int min1 = (Math.min(map[i], map[i - 1]));
            int min2 = (Math.min(min1, map[i - 2]));
            min1 -= min2;
            map[i] -= min1 + min2;
            map[i - 1] -= min1 + min2;
            map[i - 2] -= min2;
            sum += min1 * 5L + min2 * 7L + map[i] * 3L;
        }

        int min = (Math.min(map[2], map[3]));

        sum += min * 5L + (map[2] - min) * 3L + (map[3] - min) * 3L;

        long sum2 = 0;

        for (int i = 0; i < N - 2; i++) {
            int min1 = (Math.min(map2[i], map2[i + 1]));
            int min2 = (Math.min(min1, map2[i + 2]));
            min1 -= min2;
            map2[i] -= min1 + min2;
            map2[i + 1] -= min1 + min2;
            map2[i + 2] -= min2;
            sum2 += min1 * 5L + min2 * 7L + map2[i] * 3L;
        }

        min = (Math.min(map2[N - 2], map2[N - 1]));

        sum2+= min * 5L + (map2[N - 2] - min) * 3L + (map2[N - 1] - min) * 3L;


        System.out.println(sum);
        System.out.println(sum2);

    }
}
