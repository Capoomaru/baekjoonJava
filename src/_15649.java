import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//11분 16초 성공
public class _15649 {
    public static int M;
    public static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[N];
        int[] route = new int[N];

        back(route, visited, 0);
    }

    public static void back(int[] route, boolean[] visited, int turn) {
        if(M == turn) {
            for(int i=0;i<M;i++) {
                System.out.print(route[i]+" ");
            }
            System.out.println();
            return;
        }


        for(int i=0;i<N;i++) {
            if(visited[i]) continue;

            route[turn] = i + 1;
            visited[i] = true;
            back(route, visited, turn+1);
            visited[i] = false;
        }
    }
}
