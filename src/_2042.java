import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2042 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[] tree = new long[getSize(N)];

        for (int i = 0; i < N; i++) {
            tree[i + tree.length / 2] = Long.parseLong(br.readLine());
        }
        N = tree.length / 2;
        init(tree, N);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String op = st.nextToken();
            int a = Integer.parseInt(st.nextToken()) - 1;
            long b = Long.parseLong(st.nextToken());

            if (op.equals("1")) {
                update(tree, N, a, b);
            } else {
                sb.append(find(tree, 1, 0, N - 1, a, (int)b - 1)).append('\n');
            }
        }

        System.out.println(sb);
    }

    public static int getSize(int N) {
        int i = 1;
        while (i < N)
            i <<= 1;
        return i << 1;
    }

    public static void init(long[] tree, int N) {
        for (int i = N - 1; i > 0; --i) {
            tree[i] = tree[i << 1] + tree[(i << 1) | 1];
        }
    }

    public static void update(long[] tree, int N, int idx, long num) {
        tree[idx + N] = num;
        for (int i = (idx + N) >> 1; i > 0; i >>= 1) {
            tree[i] = tree[i << 1] + tree[(i << 1) | 1];
        }
    }

    public static long find(long[] tree, int node, int start, int end, int a, int b) {
        if (start > b || end < a)
            return 0;

        if (a <= start && end <= b)
            return tree[node];

        int mid = (start + end) / 2;
        return find(tree, node << 1, start, mid, a, b) + find(tree, (node << 1) + 1, mid + 1, end, a, b);
    }
}


