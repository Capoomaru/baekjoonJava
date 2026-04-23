import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _14427 {

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
            if (op.equals("1")) {
                int idx = Integer.parseInt(st.nextToken()) - 1;
                int num = Integer.parseInt(st.nextToken());

                update(tree, N, idx, num);
            } else {
                System.out.println(query(tree, 1, tree[1]) - N + 1);
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
            return node;

        int mid = (start + end) >> 1;
        int leftIdx = query(tree, node << 1, start, mid, a, b);
        int rightIdx = query(tree, node << 1 | 1, mid + 1, end, a, b);
        if(tree[leftIdx] <= tree[rightIdx])
            return leftIdx;
        return rightIdx;
    }

    public static int query(int[] tree, int idx, int target) {
        if(idx >= (tree.length >> 1) && tree[idx] == target)
            return idx;

        if(tree[idx << 1] == target)
            return query(tree, idx << 1, target);
        return query(tree, idx << 1 | 1, target);
    }

}

