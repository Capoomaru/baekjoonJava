import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class _17471 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] cntMap = new int[N];
        int cntSum = 0;
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            cntMap[i] = Integer.parseInt(st.nextToken());
            cntSum += cntMap[i];
        }
        ArrayList<ArrayList<Integer>> map = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            ArrayList<Integer> newList = new ArrayList<>();
            st = new StringTokenizer(br.readLine(), " ");
            while (!st.hasMoreTokens()) {
                newList.add(Integer.parseInt(st.nextToken()));
            }
            map.add(newList);
        }

    }

    public static void backTrack(ArrayList<ArrayList<Integer>> map, int selected, int idx, int N, int sum, int cntSum) {
        if(idx == N) {

        }

        backTrack(map, selected, idx + 1, N, sum, cntSum);
        backTrack(map, selected + (1 << idx), idx + 1, N, sum, cntSum);
    }
}
