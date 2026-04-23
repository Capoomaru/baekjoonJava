import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1572 {

    public static int MAX_NUM = 65536;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] tree = new int[getSize(MAX_NUM + 1)];
        int halfTreeN = tree.length >> 1;
        int[] arr = new int[N];

        long answer = 0;
        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            tree[(halfTreeN) + arr[i]]++;
        }

        init(tree, halfTreeN);
        answer += query(tree, 1, (K + 1) / 2) - halfTreeN;
        for (int i = K; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());

            update(tree, halfTreeN, arr[i], arr[i - K]);
            int result = query(tree, 1, (K + 1) / 2) - halfTreeN;
            answer += result;
        }

        System.out.println(answer);

    }

    public static int getSize(int N) {
        return (int) Math.pow(2, 1 + (int) Math.ceil(Math.log(N) / Math.log(2)));
    }

    public static void init(int[] tree, int N) {
        for (int i = N - 1; i > 0; --i)
            tree[i] = tree[i << 1] + tree[i << 1 | 1];
    }

    public static void update(int[] tree, int N, int add, int delete) {
        tree[N + add]++;
        tree[N + delete]--;

        int addIdx = (N + add) >> 1;
        int deleteIdx = (N + delete) >> 1;

        while (addIdx != deleteIdx) {
            tree[addIdx] = tree[addIdx << 1] + tree[addIdx << 1 | 1];
            tree[deleteIdx] = tree[deleteIdx << 1] + tree[deleteIdx << 1 | 1];

            addIdx >>= 1;
            deleteIdx >>= 1;
        }

        for (int i = addIdx; i > 0; i >>= 1)
            tree[i] = tree[i << 1] + tree[i << 1 | 1];

    }

    public static int query(int[] tree, int node, int target) {
        if ((node >= (tree.length >> 1)))
            return node;

        if ((tree[node << 1]) >= target)
            return query(tree, node << 1, target);

        return query(tree, node << 1 | 1, target - tree[node << 1]);

    }
}
