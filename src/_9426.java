import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _9426 {

    static int MAX_NUM = 65535;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        int[] tree = new int[getSize(MAX_NUM)];
        int treeHalfSize = tree.length >> 1;

        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            tree[treeHalfSize + arr[i]]++;
        }

        long answer = 0;
        init(tree);
        answer += query(tree, 1, (K + 1) / 2) - treeHalfSize;
        for (int i = K; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            update(tree, arr[i], arr[i - K]);
            answer += query(tree, 1, (K + 1) / 2) - treeHalfSize;
        }

        System.out.println(answer);

    }

    public static int getSize(int N) {
        return (int) Math.pow(2, 1 + (int) Math.ceil(Math.log(N) / Math.log(2)));
    }

    public static void init(int[] tree) {
        for (int i = (tree.length >> 1) - 1; i > 0; --i) {
            tree[i] = tree[i << 1] + tree[i << 1 | 1];
        }
    }

    public static void update(int[] tree, int plus, int minus) {

        int plusIdx = (tree.length >> 1) + plus;
        int minusIdx = (tree.length >> 1) + minus;
        ++tree[plusIdx];
        --tree[minusIdx];

        plusIdx >>= 1;
        minusIdx >>= 1;

        while (plusIdx != minusIdx) {
            tree[plusIdx] = tree[plusIdx << 1] + tree[plusIdx << 1 | 1];
            tree[minusIdx] = tree[minusIdx << 1] + tree[minusIdx << 1 | 1];

            plusIdx >>= 1;
            minusIdx >>= 1;
        }

        for (int i = plusIdx; i > 0; i >>= 1) {
            tree[i] = tree[i << 1] + tree[i << 1 | 1];
        }
    }

    public static int query(int[] tree, int node, int target) {
        if (node >= (tree.length >> 1))
            return node;

        if (tree[node << 1] >= target)
            return query(tree, node << 1, target);

        return query(tree, node << 1 | 1, target - tree[node << 1]);
    }
}
