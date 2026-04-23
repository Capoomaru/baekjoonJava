import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _14438 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] tree = new int[getSize(N)];
        tree[0] = Integer.MAX_VALUE;

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++)
            tree[i + (tree.length >> 1)] = Integer.parseInt(st.nextToken());
        for (int i = N; i < (tree.length >> 1); i++)
            tree[i + (tree.length >> 1)] = Integer.MAX_VALUE;

        N = tree.length / 2;
        init(tree, N);

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String op = st.nextToken();
            int idx = Integer.parseInt(st.nextToken()) - 1;
            if (op.equals("1")) {
                int num = Integer.parseInt(st.nextToken());

                update(tree, N, idx, num);
            } else {
                int idx2 = Integer.parseInt(st.nextToken()) - 1;
                int min = query(tree, 1, 0, N - 1, idx, idx2);
                System.out.println(min);
            }
        }

    }

    public static int getSize(int N) {
        return (int) Math.pow(2, 1 + (int) Math.ceil(Math.log(N) / Math.log(2)));
    }

    public static void init(int[] tree, int N) {
        for (int i = N - 1; i > 0; --i) {
            tree[i] = Math.min(tree[i << 1], tree[i << 1 | 1]);
        }
    }

    public static void update(int[] tree, int N, int idx, int num) {
        tree[idx + N] = num;
        for (int i = (idx + N) >> 1; i > 0; i >>= 1) {
            tree[i] = Math.min(tree[i << 1], tree[i << 1 | 1]);
        }
    }

    public static int query(int[] tree, int node, int start, int end, int a, int b) {
        if (a > end || b < start)
            return Integer.MAX_VALUE;

        if (a <= start && end <= b)
            return tree[node];

        int mid = (start + end) >> 1;
        int left = query(tree, node << 1, start, mid, a, b);
        int right = query(tree, node << 1 | 1, mid + 1, end, a, b);
        return Math.min(left, right);
    }

}

