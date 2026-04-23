import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _2104 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // sum, min, res
        int[][] tree = new int[getSize(N)][3];
        tree[0][0] = Integer.MAX_VALUE;

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            tree[i + tree.length / 2][0] = Integer.parseInt(st.nextToken());
            tree[i + tree.length / 2][1] = i + tree.length / 2;
        }
        for (int i = N + tree.length / 2; i < tree.length; i++)
            tree[i][0] = Integer.MAX_VALUE;

        init(tree);

        for (int i = 0; i < tree.length; i++)
            System.out.println(Arrays.toString(tree[i]));
        System.out.println(tree[1][2]);

        int max = 0;
        for(int i=0;i<N;i++) {
            for(int j=i;j<N;j++) {
                int[] answer = query(tree, 1, i, j, 0, tree.length / 2 - 1);
                max = Math.max(max, answer[2]);
            }
        }

        System.out.println(max);

    }

    public static int getSize(int N) {
        return (int) Math.pow(2, 1 + Math.ceil(Math.log(N) / Math.log(2)));
    }

    public static void init(int[][] tree) {
        for (int i = tree.length / 2 - 1; i > 0; --i) {
            if (tree[i << 1][0] < Integer.MAX_VALUE)
                tree[i][0] += tree[i << 1][0];
            if (tree[i << 1 | 1][0] < Integer.MAX_VALUE)
                tree[i][0] += tree[i << 1 | 1][0];

            if (tree[tree[i << 1][1]][0] <= tree[tree[i << 1 | 1][1]][0]) {
                tree[i][1] = tree[i << 1][1];
            } else {
                tree[i][1] = tree[i << 1 | 1][1];
            }
            tree[i][2] = tree[i][0] * tree[tree[i][1]][0];
        }

    }

    public static int[] query(int[][] tree, int idx, int start, int end, int a, int b) {
        if (a > end || b < start)
            return null;

        if (a <= start && end <= b)
            return tree[idx];

        int mid = (start + end) / 2;
        int[] left = query(tree, idx << 1, start, mid, a, b);
        int[] right = query(tree, idx << 1 | 1, mid + 1, end, a, b);
        if (left == null || left[0] == Integer.MAX_VALUE)
            return right;
        if (right == null || right[0] == Integer.MAX_VALUE)
            return left;

        int[] returnValue = new int[]{left[0] + right[0], 0, 0};
        if (tree[left[1]][0] <= tree[right[1]][0])
            returnValue[1] = left[1];
        else
            returnValue[1] = right[1];
        returnValue[2] = returnValue[0] * tree[returnValue[1]][0];
        returnValue[2] = Math.max(returnValue[2], left[2]);
        returnValue[2] = Math.max(returnValue[2], right[2]);

        return returnValue;
    }
}
