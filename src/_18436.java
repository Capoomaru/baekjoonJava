import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _18436 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int[] evenTree = new int[getSize(N)];


        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            evenTree[i + evenTree.length/2] = Integer.parseInt(st.nextToken()) % 2 == 0 ? 1 : 0;
        }

        init(evenTree);

        int M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String op = st.nextToken();
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (op.equals("1")) {
                updateTree(evenTree, a - 1, b % 2 == 0 ? 1 : 0);
            } else {
                int result = query(evenTree, 1, 0, evenTree.length / 2 - 1, Math.min(a, b) - 1, Math.max(a, b) - 1);
                sb.append(op.equals("2") ? result : (b - a + 1) - result).append('\n');
            }
        }

        System.out.println(sb);
    }

    public static void init(int[] tree) {
        for (int i = tree.length / 2 - 1; i > 0; --i)
            tree[i] = tree[i << 1] + tree[i << 1 | 1];
    }

    public static int getSize(int N) {
        return (int) Math.pow(2, 1 + Math.ceil(Math.log(N) / Math.log(2)));
    }

    public static void updateTree(int[] tree, int idx, int num) {
        tree[idx + tree.length / 2] = num;
        for (int i = (tree.length / 2 + idx) >> 1; i > 0; i >>= 1) {
            tree[i] = tree[i << 1] + tree[i << 1 | 1];
        }
    }

    public static int query(int[] tree, int idx, int start, int end, int a, int b) {
        if (a > end || b < start)
            return 0;
        if (a <= start && end <= b)
            return tree[idx];
        int mid = (start + end) / 2;
        return query(tree, idx << 1, start, mid, a, b) + query(tree, idx << 1 | 1, mid + 1, end, a, b);
    }
}
