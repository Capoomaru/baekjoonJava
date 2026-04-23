import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class _30804 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] map = new int[N];
        int start = 0;
        int max = 0;
        map[0] = Integer.parseInt(st.nextToken());
        HashSet<Integer> set = new HashSet<>();
        set.add(map[0]);
        for (int i = 1; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            map[i] = num;
            set.add(num);
            if (set.size() == 3) {
                max = Math.max(max, i - start);
                int tmp = i - 1;
                while (map[i - 1] == map[tmp]) {
                    tmp--;
                }
                set.remove(map[tmp]);
                start = tmp + 1;
            }
        }
        max = Math.max(max, N - start);

        System.out.println(max);
    }

}
