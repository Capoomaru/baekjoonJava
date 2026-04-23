import java.io.*;
import java.util.*;

public class _13913 {

    public static int MAX = 100_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        boolean[] visited = new boolean[MAX + 1];
        StringBuilder[] list = new StringBuilder[MAX + 1];

        Queue<int[]> queue = new ArrayDeque<>();

        queue.add(new int[]{N, 0});
        visited[N] = true;
        list[N] = new StringBuilder();
        list[N].append(N).append(' ');

        while (!queue.isEmpty()) {
            int[] p = queue.poll();

            if (p[0] == K) {
                bw.append(Integer.toString(p[1]));
                bw.newLine();
                bw.append(list[p[0]]).append(' ');
                bw.newLine();
                bw.flush();
                return;
            }

            if (p[0] * 2 <= Math.min(K * 2, MAX) && !visited[p[0] * 2]) {
                visited[p[0] * 2] = true;
                queue.add(new int[]{p[0] * 2, p[1] + 1});
                list[p[0] * 2] = new StringBuilder(list[p[0]]);
                list[p[0] * 2].append(p[0] * 2).append(' ');

            }

            if (p[0] + 1 <= 100000 && !visited[p[0] + 1]) {
                visited[p[0] + 1] = true;
                queue.add(new int[]{p[0] + 1, p[1] + 1});
                list[p[0] + 1] = new StringBuilder(list[p[0]]);
                list[p[0] + 1].append(p[0] + 1).append(' ');
            }

            if (p[0] - 1 < 0 || visited[p[0] - 1])
                continue;
            visited[p[0] - 1] = true;
            queue.add(new int[]{p[0] - 1, p[1] + 1});
            list[p[0] - 1] =new StringBuilder(list[p[0]]);
            list[p[0] - 1].append(p[0] - 1).append(' ');
        }

    }
}