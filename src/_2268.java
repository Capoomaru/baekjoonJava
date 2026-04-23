import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _2268 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] tree = new long[getSize(N)];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String op = st.nextToken();
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (op.equals("0")) {
                sb.append(query(tree, 1, 0, tree.length/2 - 1, Math.min(a, b) - 1, Math.max(a, b) - 1)).append('\n');
            } else {
                updateTree(tree, a - 1, b);
            }
        }

        System.out.println(sb);
    }

    public static int getSize(int N) {
        return (int) Math.pow(2, 1 + Math.ceil(Math.log(N) / Math.log(2)));
    }

    public static void updateTree(long[] tree, int idx, int num) {
        tree[idx + tree.length / 2] = num;
        for (int i = (tree.length / 2 + idx) >> 1; i > 0; i >>= 1) {
            tree[i] = tree[i << 1] + tree[i << 1 | 1];
        }
    }

    public static long query(long[] tree, int idx, int start, int end, int a, int b) {
        if (a > end || b < start)
            return 0;
        if (a <= start && end <= b)
            return tree[idx];
        int mid = (start + end) / 2;
        return query(tree, idx << 1, start, mid, a, b) + query(tree, idx << 1 | 1, mid + 1, end, a, b);
    }
}
