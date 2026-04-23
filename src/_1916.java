import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _1916 {
    public static int N, MAX = 100_000 * 100_000 + 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        HashMap<Integer, ArrayList<int[]>> edgeMap = new HashMap<>();

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            ArrayList<int[]> edgeList = edgeMap.getOrDefault(from, new ArrayList<>());
            edgeList.add(new int[]{to, weight});

            edgeMap.put(from, edgeList);
        }

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        System.out.println(dijkstra(edgeMap, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));


    }

    public static int dijkstra(HashMap<Integer, ArrayList<int[]>> edgeMap, int from, int to) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        boolean[] visited = new boolean[N + 1];
        pq.add(new int[]{from, 0});
        int[] distance = new int[N + 1];
        Arrays.fill(distance, MAX);
        distance[from] = 0;

        while(!pq.isEmpty()) {
            int[] p = pq.poll();

            if(visited[p[0]] || (distance[p[0]] < p[1]))
                continue;

            visited[p[0]] = true;

            for(int[] next : edgeMap.getOrDefault(p[0], new ArrayList<>())) {
                if(visited[next[0]] || (distance[next[0]] <= distance[p[0]] + next[1]))
                    continue;

                distance[next[0]] = distance[p[0]] + next[1];
                pq.add(new int[]{next[0], distance[next[0]]});
            }
        }

        return distance[to];
    }
}
