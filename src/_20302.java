import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class _20302 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int MAX = 100000;

        HashMap<Integer, HashSet<int[]>> factorizationMap = new HashMap<>();
        for (int i = 2; i <= MAX; i++)
            factorizationMap.put(i, new HashSet<>());
        for (int i = 2; i <= MAX; i++) {
            if (!factorizationMap.get(i).isEmpty())
                continue;

            factorizationMap.get(i).add(new int[]{i, 1});

            for (int j = 2; i * j <= MAX; j++) {
                int tmp = i * j;
                int cnt = 0;
                while(tmp % i == 0) {
                    tmp /= i;
                    cnt++;
                }
                factorizationMap.get(i * j).add(new int[]{i, cnt});
            }
        }

        HashMap<Integer, Integer> cntMap = new HashMap<>();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int num = Integer.parseInt(st.nextToken());
        if (num == 0) {
            System.out.println("mint chocolate");
            return;
        }
        if (num != 1) {
            for (int[] k : factorizationMap.get(num)) {
                cntMap.put(k[0], cntMap.getOrDefault(k[0], 0) + k[1]);
            }
        }

        for (int i = 1; i < N; i++) {
            String op = st.nextToken();
            num = Integer.parseInt(st.nextToken());
            if (num == 0) {
                System.out.println("mint chocolate");
                return;
            }
            if (num == 1)
                continue;
            if (op.equals("*")) {
                for (int[] k : factorizationMap.get(num)) {
                    cntMap.put(k[0], cntMap.getOrDefault(k[0], 0) + k[1]);
                }
            } else {
                for (int[] k : factorizationMap.get(num)) {
                    cntMap.put(k[0], cntMap.getOrDefault(k[0], 0) - k[1]);
                }
            }
        }

        String answer = "mint chocolate";
        for (int cnt : cntMap.values())
            if (cnt < 0)
                answer = "toothpaste";

        //System.out.println(cntMap);

        System.out.println(answer);
    }
}