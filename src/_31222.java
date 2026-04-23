import java.io.*;
import java.util.StringTokenizer;

public class _31222 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        int[][] tree = new int[getSize(N)][2];

        for (int i = 0; i < N; i++) {
            tree[i + (tree.length >> 1)][0] = Integer.parseInt(st.nextToken());
            tree[i + (tree.length >> 1)][1] = 1;
        }

        init(tree);

        for(int i=0;i<tree.length;i++)
            System.out.print(tree[i][0]+" ");
        System.out.println();
        for(int i=0;i<tree.length;i++)
            System.out.print(tree[i][1]+" ");

        for(int i=0;i<Q;i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String op = st.nextToken();
            if(op.equals("1")) {
                int idx = Integer.parseInt(st.nextToken()) - 1;
                int num = Integer.parseInt(st.nextToken());
                update(tree, idx, num);
            }
            else {
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                int[] result = query(tree, 1, 0, N - 1, a, b);
                bw.append(Integer.toString(result[1]));
                bw.newLine();
            }
        }

        bw.flush();



    }

    public static int getSize(int N) {
        return (int) Math.pow(2, 1 + Math.ceil(Math.log(N) / Math.log(2)));
    }

    public static int[] query(int[][] tree, int idx, int start, int end, int a, int b) {
        if(start > b || end < a)
            return null;

        int[] answer = new int[3];

        if(a <= start && end <= b && tree[idx][1] != 0) {
            answer[0] = tree[idx][1];
            answer[1] = a;
            answer[1] = b;
            return tree[idx];
        }

        int mid = (start + end) / 2;
        int[] left = query(tree, idx << 1, start, mid, a, b);
        int[] right = query(tree, idx << 1 | 1, mid + 1, end, a, b);

        if(left == null) {
            return right;
        }
        if(right == null) {
            return left;
        }
        if(left[0] == right[0]) {

            answer[0] = left[0];
            answer[1] = left[1] + right[1];
            return answer;
        }
        else if(left[1] >= right[1]) {
            return left;
        }

        return right;
    }

    public static void init(int[][] tree) {
        for (int i = (tree.length >> 1) - 1; i > 0; i--) {
            if (tree[i << 1][0] == tree[i << 1 | 1][0]) {
                tree[i][0] = tree[i << 1][0];
                tree[i][1] = tree[i << 1][1] + tree[i << 1 | 1][1];
            } else {
                tree[i][0] = 0;
                tree[i][1] = 0;
            }
        }
    }

    public static void update(int[][] tree, int idx, int num) {
        tree[idx + tree.length / 2][0] = num;
        for (int i = (idx + tree.length / 2) >> 1; i > 0; i >>= 1) {
            if (tree[i << 1][0] == tree[i << 1 | 1][0]) {
                tree[i][0] = tree[i << 1][0];
                tree[i][1] = tree[i << 1][1] + tree[i << 1 | 1][1];
            } else {
                tree[i][0] = 0;
                tree[i][1] = 0;
            }
        }
    }
}
