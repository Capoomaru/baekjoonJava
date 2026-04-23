import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _2357 {

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] minTree = new int[getSize(N)];
        int[] maxTree = new int[getSize(N)];
        Arrays.fill(minTree, Integer.MAX_VALUE);

        for (int i = 0; i < N; i++) {
            minTree[i + minTree.length / 2] = Integer.parseInt(br.readLine());
            maxTree[i + minTree.length / 2] = minTree[i + minTree.length / 2];
        }

        init(minTree, N, true);
        init(maxTree, N, false);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            bw.append(Integer.toString(find(minTree, 1, 0, minTree.length/2 - 1, a, b, true)));
            bw.append(' ');
            bw.append(Integer.toString(find(maxTree, 1, 0, minTree.length/2 - 1, a, b, false)));
            bw.newLine();
        }


        bw.flush();
    }

    public static int getSize(int N) {
        int i = 1;
        while (i < N) {
            i <<= 1;
        }

        return i << 1;
    }

    public static void init(int[] tree, int N, boolean isMin) {
        for (int i = tree.length / 2 - 1; i > 0; --i) {
            if (isMin) {
                tree[i] = Math.min(tree[i << 1], tree[i << 1 | 1]);
            } else {
                tree[i] = Math.max(tree[i << 1], tree[i << 1 | 1]);
            }
        }
    }

    public static int find(int[] tree, int node, int start, int end, int left, int right, boolean isMin) {
        if (left > end || right < start)
            return isMin ? Integer.MAX_VALUE : 0;

        if (left <= start && end <= right)
            return tree[node];

        int mid = (start + end) / 2;
        if (isMin)
            return Math.min(find(tree, (node << 1), start, mid, left, right, isMin), find(tree, (node << 1) + 1, mid + 1, end, left, right, isMin));
        else
            return Math.max(find(tree, (node << 1), start, mid, left, right, isMin), find(tree, (node << 1) + 1, mid + 1, end, left, right, isMin));
    }
}