import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _20925 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int[][] expMap = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            expMap[i][0] = Integer.parseInt(st.nextToken());
            expMap[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] distanceMap = new int[N][N];

        for (int from = 0; from < N; from++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int to = 0; to < N; to++) {
                int cost = Integer.parseInt(st.nextToken());
                distanceMap[from][to] = cost;
            }
        }
        //경험치 단위로 가장 큰 값을 저장하는 DP


    }

}
