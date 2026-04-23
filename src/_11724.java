import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//30분 성공
public class _11724 {

    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> map = new ArrayList<>();
        for(int i=0;i<N;i++)
            map.add(new ArrayList<>());

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            map.get(from-1).add(to-1);
            map.get(to-1).add(from-1);
        }

        visited = new boolean[N];

        int answer = 0;

        for(int i=0;i<N;i++) {
            if(!visited[i]) {
                bfs(i, map);
                answer++;
            }
        }

        System.out.println(answer);

    }

    public static void bfs(int i, ArrayList<ArrayList<Integer>> map) {
        visited[i] = true;

        ArrayList<Integer> reach = map.get(i);

        for(int node : reach) {
            if(visited[node])
                continue;

            bfs(node, map);
        }

    }
}
