import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _5676 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input;
        while((input = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(input, " ");
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            long[] tree = new long[getSize(N)];
            Arrays.fill(tree, tree.length / 2 + N, tree.length, 1);

            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++)
                tree[i + (tree.length >> 1)] = Integer.compare(Integer.parseInt(st.nextToken()), 0);

            N = tree.length / 2;
            init(tree, N);

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                String op = st.nextToken();
                int idx = Integer.parseInt(st.nextToken()) - 1;
                if (op.equals("C")) {
                    int num = Integer.parseInt(st.nextToken());

                    update(tree, N, idx, num);
                } else {
                    int idx2 = Integer.parseInt(st.nextToken()) - 1;
                    long min = query(tree, 1, 0, N - 1, idx, idx2);
                    if(min == 0)
                        sb.append('0');
                    else if(min < 0)
                        sb.append('-');
                    else
                        sb.append('+');
                }
            }

            System.out.println(sb);
            sb = new StringBuilder();
        }


    }

    public static int getSize(int N) {
        return (int) Math.pow(2, 1 + (int) Math.ceil(Math.log(N) / Math.log(2)));
    }

    public static void init(long[] tree, int N) {
        for (int i = N - 1; i > 0; --i) {
            tree[i] = (tree[i << 1] * tree[i << 1 | 1]);
        }
    }

    public static void update(long[] tree, int N, int idx, int num) {
        tree[idx + N] = Integer.compare(num, 0);
        for (int i = (idx + N) >> 1; i > 0; i >>= 1) {
            tree[i] = (tree[i << 1] * tree[i << 1 | 1]);
        }
    }

    public static long query(long[] tree, int node, int start, int end, int a, int b) {
        if (a > end || b < start)
            return 1;

        if (a <= start && end <= b) {
            return tree[node];
        }

        int mid = (start + end) >> 1;
        long left = query(tree, node << 1, start, mid, a, b);
        long right = query(tree, node << 1 | 1, mid + 1, end, a, b);
        return left * right;
    }


}

