import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _1197 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        HashMap<Integer, ArrayList<int[]>> map = new HashMap<>();
        for (int i = 1; i <= V; i++)
            map.put(i, new ArrayList<>());

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            map.get(from).add(new int[]{to, weight});
            map.get(to).add(new int[]{from, weight});
        }

        int sum = 0;

        int[] distance = new int[V + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[1] = 0;
        boolean[] visited = new boolean[V + 1];

        PriorityQueue<int[]> pq = new PriorityQueue<>((Comparator.comparingInt(o -> o[1])));
        pq.add(new int[]{1, 0});

        int cnt = 0;
        while (!pq.isEmpty()) {
            int[] p = pq.poll();

            if (visited[p[0]])
                continue;

            visited[p[0]] = true;
            sum += p[1];

            if (++cnt == V)
                break;

            for (int[] next : map.get(p[0])) {
                if (visited[next[0]] || distance[next[0]] <= next[1])
                    continue;

                distance[next[0]] = next[1];
                pq.add(next);
            }
        }

        System.out.println(sum);
    }
}
