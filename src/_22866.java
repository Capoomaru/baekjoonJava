import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class _22866 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        int[][] answer = new int[N][2];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        ArrayDeque<int[]> stack = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            System.out.println("time : " + i);

            while (!stack.isEmpty() && stack.peekLast()[1] <= arr[i]) {
                int[] p = stack.pollLast();
                System.out.print(p[0]+" ");
                answer[i][0]++;
                if (answer[i][1] == 0)
                    answer[i][1] = p[1];
            }

            stack.addLast(new int[]{i, arr[i]});
            System.out.println();
        }

        stack = new ArrayDeque<>();

        for (int i = N - 1; i > 0; --i) {
            while (!stack.isEmpty() && stack.peekLast()[1] <= arr[i]) {
                int[] p = stack.pollLast();
                answer[i][0]++;
                if (answer[i][1] == 0)
                    answer[i][1] = p[1];
            }

            stack.addLast(new int[]{i, arr[i]});
        }

        for (int i = 0; i < N; i++) {
            if (answer[i][0] == 0) {
                System.out.println(0);
            } else {
                System.out.println(answer[i][0] + " " + answer[i][1]);
            }
        }
    }
}
