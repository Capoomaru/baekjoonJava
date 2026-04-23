import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _12846 {

    public static class Node {
        int num;
        int idx;
        int size;

        long getSize() {
            return (long) num * size;
        }

        @Override
        public String toString() {
            return "Node [idx= " + idx + " num=" + num + ", size=" + size + ", sum=" + size * num + "]";
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Node[] tree = new Node[getSize(N)];
        for (int i = 0; i < tree.length; i++)
            tree[i] = new Node();
        tree[0].num = Integer.MAX_VALUE;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            tree[i + tree.length / 2].num = Integer.parseInt(st.nextToken());
            tree[i + tree.length / 2].size = 1;
            tree[i + tree.length / 2].idx = i + tree.length / 2;
        }
        for (int i = N + tree.length / 2; i < tree.length; i++) {
            tree[i].num = Integer.MAX_VALUE;
            tree[i].idx = i;
        }

        init(tree, tree.length / 2);

        max(tree, 0, N - 1);
        System.out.println(max);
    }

    public static int getSize(int N) {
        return (int) Math.pow(2, 1 + Math.ceil(Math.log(N) / Math.log(2)));
    }

    public static long max = 0;

    public static void init(Node[] tree, int N) {
        for (int i = N - 1; i > 0; --i) {
            if (tree[i << 1].num <= tree[i << 1 | 1].num) {
                tree[i].idx = tree[i << 1].idx;
                tree[i].num = tree[tree[i].idx].num;
            } else {
                tree[i].idx = tree[i << 1 | 1].idx;
            }
            tree[i].num = tree[tree[i].idx].num;
            tree[i].size = tree[i << 1].size + tree[i << 1 | 1].size;
        }
    }

    public static void max(Node[] tree, int left, int right) {
        if (left == right)
            return;
        int idx = query(tree, 1, 0, tree.length / 2 - 1, left, right);

        max = Math.max(max, (long) tree[idx].num * (right - left + 1));

        idx -= tree.length / 2;

        if (left <= idx - 1) {
            max(tree, left, idx - 1);
        }
        if (idx + 1 <= right) {
            max(tree, idx + 1, right);
        }

    }

    public static int query(Node[] tree, int idx, int start, int end, int a, int b) {
        if (a > end || b < start)
            return 0;

        if (a <= start && end <= b)
            return tree[idx].idx;

        int mid = (start + end) / 2;
        int leftIdx = query(tree, idx << 1, start, mid, a, b);
        int rightIdx = query(tree, idx << 1 | 1, mid + 1, end, a, b);
        if (tree[leftIdx].num <= tree[rightIdx].num)
            return leftIdx;
        else
            return rightIdx;
    }
}
