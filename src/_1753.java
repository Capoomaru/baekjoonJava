import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class _1753 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int start = Integer.parseInt(br.readLine()) - 1;

        ArrayList<HashMap<Integer, Integer>> map = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            HashMap<Integer, Integer> costMap = new HashMap<>();
            costMap.put(i, 0);
            map.add(costMap);
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            HashMap<Integer, Integer> costMap = map.get(u);
            costMap.put(v, Math.min(w, costMap.getOrDefault(v, Integer.MAX_VALUE)));
        }

        int[] startDistance = dijkstra(map, start);

        for (int i = 0; i < V; i++) {
            if (startDistance[i] >= Integer.MAX_VALUE || startDistance[i] < 0)
                bw.append("INF");
            else
                bw.append(Integer.toString(startDistance[i]));
            bw.newLine();
        }

        bw.flush();
    }

    public static int[] dijkstra(ArrayList<HashMap<Integer, Integer>> map, int from) {
        int[] distance = new int[map.size()];
        Arrays.fill(distance, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.add(new int[]{from, 0});
        distance[from] = 0;

        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            if (distance[p[0]] < p[1]) {
                continue;
            }

            for (Map.Entry<Integer, Integer> entry : map.get(p[0]).entrySet()) {
                if (distance[entry.getKey()] > p[1] + entry.getValue()) {
                    distance[entry.getKey()] = p[1] + entry.getValue();
                    pq.add(new int[]{entry.getKey(), distance[entry.getKey()]});
                }
            }
        }

        return distance;
    }

}
