import java.io.*;
import java.util.StringTokenizer;

public class _12899 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] tree = new int[getSize(2_000_001)];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String op = st.nextToken();

            if (op.equals("1")) {
                int num = Integer.parseInt(st.nextToken());
                update(tree, num, 1);
            } else {
                int size = Integer.parseInt(st.nextToken());
                int result = query(tree, size, 1);
                update(tree, result - tree.length/ 2, -1);
                bw.append(Integer.toString(result - tree.length/2));
                bw.newLine();
            }
        }
        bw.flush();
    }

    public static int getSize(int N) {
        return (int) Math.pow(2, 1 + Math.ceil(Math.log(N) / Math.log(2)));
    }

    public static int query(int[] tree, int target, int idx) {
        if (idx >= tree.length / 2) {
            if (tree[idx] >= target)
                return idx;
            else
                return 0;
        }

        if ((idx << 1 | 1) >= tree.length)
            return 0;

        if (tree[idx << 1] >= target) {
            return query(tree, target, idx << 1);
        } else {
            return query(tree, target - tree[idx << 1], idx << 1 | 1);
        }

    }

    public static void update(int[] tree, int num, int size) {
        tree[tree.length / 2 + num] += size;
        for (int i = (tree.length / 2 + num) >> 1; i > 0; i >>= 1) {
            tree[i] = tree[i << 1] + tree[i << 1 | 1];
        }
    }
}