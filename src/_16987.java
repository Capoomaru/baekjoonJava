import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _16987 {
    public static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] eggInfoList = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            eggInfoList[i][0] = Integer.parseInt(st.nextToken());
            eggInfoList[i][1] = Integer.parseInt(st.nextToken());
        }

        backTrack(eggInfoList, 0);

        System.out.println(max);
    }

    public static void backTrack(int[][] eggInfoList, int handEgg) {
        if (handEgg == eggInfoList.length) {
            int cnt = 0;
            for (int[] eggInfo : eggInfoList) {
                cnt += eggInfo[0] <= 0 ? 1 : 0;
            }
            max = Math.max(max, cnt);
            return;
        }


        boolean isAllBroken = true;
        for (int targetEgg = 0; targetEgg < eggInfoList.length; targetEgg++) {
            boolean isPossible = handEgg != targetEgg && eggInfoList[targetEgg][0] > 0 && eggInfoList[handEgg][0] > 0;
            if (!isPossible)
                continue;
            isAllBroken  = false;
            eggInfoList[targetEgg][0] -= eggInfoList[handEgg][1];
            eggInfoList[handEgg][0] -= eggInfoList[targetEgg][1];

            backTrack(eggInfoList, handEgg + 1);

            eggInfoList[targetEgg][0] += eggInfoList[handEgg][1];
            eggInfoList[handEgg][0] += eggInfoList[targetEgg][1];
        }

        if(isAllBroken)
            backTrack(eggInfoList, handEgg + 1);

    }
}