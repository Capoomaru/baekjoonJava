import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 38분 51초 성공
public class _1260 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken()) - 1;

        int[][] dfsMap = new int[N][N];
        ArrayList<ArrayList<Integer>> bfsMap = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            bfsMap.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;

            dfsMap[from][to] = 1;
            dfsMap[to][from] = 1;
            bfsMap.get(from).add(to);
            bfsMap.get(to).add(from);
        }

        bfsMain(N, M, V, bfsMap);
        System.out.println();
        dfsMain(N, M, V, bfsMap);
    }

    static void bfsMain(int N, int M, int V, ArrayList<ArrayList<Integer>> map) {
        boolean[] visited = new boolean[N];

        BFS(map, visited, V);
    }

    static void BFS(ArrayList<ArrayList<Integer>> map, boolean[] visited, int i) {
        visited[i] = true;
        System.out.print((i + 1) + " ");

        map.get(i).sort((o1, o2) -> o1-o2);

        for (int node : map.get(i)) {
            if (visited[node])
                continue;
            BFS(map, visited, node);
        }
    }

    static void dfsMain(int N, int M, int V, ArrayList<ArrayList<Integer>> map) {
        boolean[] visited = new boolean[N];

        DFS(map, visited, new LinkedList<>(), V);

    }

    static void DFS(ArrayList<ArrayList<Integer>> map, boolean[] visited, Queue<Integer> queue, int target) {
        queue.add(target);
        visited[target] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            System.out.print((cur + 1) + " ");

            map.get(cur).sort((o1, o2) -> o1-o2);

            for (int node : map.get(cur)) {
                if (visited[node])
                    continue;

                visited[node] = true;
                queue.add(node);
            }
        }
    }
}
