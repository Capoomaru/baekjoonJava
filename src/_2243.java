import java.io.*;
import java.util.StringTokenizer;

public class _2243 {

    public static int MAX = 1_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] tree = new int[getSize(MAX)];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String op = st.nextToken();

            if (op.equals("1")) {
                int rank = Integer.parseInt(st.nextToken());
                int result = query(tree, rank, 1);
                bw.append(Integer.toString(result - tree.length / 2));
                bw.newLine();
                update(tree, result - tree.length / 2, -1);
            } else {
                int taste = Integer.parseInt(st.nextToken());
                int size = Integer.parseInt(st.nextToken());
                update(tree, taste, size);
            }
        }

        bw.flush();

    }

    public static int getSize(int N) {
        return (int) Math.pow(2, 1 + Math.ceil(Math.log(N) / Math.log(2)));
    }

    public static void update(int[] tree, int num, int size) {
        tree[tree.length / 2 + num] += size;
        for (int i = (tree.length / 2 + num) >> 1; i > 0; i >>= 1) {
            tree[i] = tree[i << 1] + tree[i << 1 | 1];
        }
    }

    public static int query(int[] tree, int target, int idx) {
        if(idx >= (tree.length /2)) {
            if(tree[idx] >= target)
                return idx;
            else
                return 0;
        }

        if(tree[idx << 1] >= target) {
            return query(tree, target, idx << 1);
        }
        else {
            return query(tree, target - tree[idx << 1], idx << 1 | 1);
        }
    }
}
