import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.StringTokenizer;

//1시간 3분 27초 통과
public class _21608 {

    public static int[][] map;
    public static int[][] seat;

    public static int[] moveY = new int[]{-1, 1, 0, 0};
    public static int[] moveX = new int[]{0, 0, -1, 1};
    public static int[] scoreMap = new int[]{0, 1, 10, 100, 1000};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        map = new int[N * N + 1][4];
        seat = new int[N][N];
        int[] whoList = new int[N * N + 1];

        for (int i = 0; i < N * N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int who = Integer.parseInt(st.nextToken());

            for (int j = 0; j < 4; j++) {
                map[who][j] = Integer.parseInt(st.nextToken());
            }
            whoList[i] = who;
        }

        for (int i = 0; i < N * N; i++) {
            ArrayList<int[]> favoriteSeat = getFavoriteSeat(whoList[i]);

            if (favoriteSeat.size() == 1) {
                seat[favoriteSeat.get(0)[0]][favoriteSeat.get(0)[1]] = whoList[i];
                continue;
            }

            ArrayList<int[]> emptySeat = getEmptySeat(favoriteSeat);

            if (emptySeat.size() == 1) {
                seat[emptySeat.get(0)[0]][emptySeat.get(0)[1]] = whoList[i];
                continue;
            }
            int[] firstSeat = getFirstSeat(emptySeat);
            seat[firstSeat[0]][firstSeat[1]] = whoList[i];
        }

        int sum = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int[] target = map[seat[i][j]];

                int cnt = 0;

                for(int k=0;k<4;k++) {
                    int newY = i + moveY[k];
                    int newX = j + moveX[k];

                    if (newY < 0 || newY >= seat.length
                            || newX < 0 || newX >= seat.length)
                        continue;

                    int who = seat[newY][newX];

                    if(Arrays.stream(target).anyMatch(value -> value == who))
                        cnt++;
                }

                sum += scoreMap[cnt];
            }
        }

        System.out.println(sum);

    }

    public static ArrayList<int[]> getFavoriteSeat(int target) {
        ArrayList<int[]> arrayList = new ArrayList<>();
        int[][] cntMap = new int[seat.length][seat.length];
        int max = 0;

        for (int i = 0; i < cntMap.length; i++) {
            for (int j = 0; j < cntMap.length; j++) {
                int tmp = seat[i][j];
                if (tmp == 0) continue;

                boolean isFavorite = Arrays.stream(map[target]).anyMatch(value -> value == tmp);

                if (!isFavorite)
                    continue;

                for (int k = 0; k < 4; k++) {
                    int newY = i + moveY[k];
                    int newX = j + moveX[k];

                    if (newY < 0 || newY >= seat.length
                            || newX < 0 || newX >= seat.length)
                        continue;

                    if (seat[newY][newX] != 0)
                        continue;

                    cntMap[newY][newX]++;
                    max = Math.max(max, cntMap[newY][newX]);
                }
            }
        }

        for (int i = 0; i < cntMap.length; i++) {
            for (int j = 0; j < cntMap.length; j++) {
                if (cntMap[i][j] == max && seat[i][j] == 0)
                    arrayList.add(new int[]{i, j});
            }
        }

        return arrayList;
    }

    public static ArrayList<int[]> getEmptySeat(ArrayList<int[]> favoriteSeat) {
        ArrayList<int[]> arrayList = new ArrayList<>();
        int[] cntMap = new int[favoriteSeat.size()];
        int maxCnt = 0;

        for (int i = 0; i < favoriteSeat.size(); i++) {
            int y = favoriteSeat.get(i)[0];
            int x = favoriteSeat.get(i)[1];

            int cnt = 0;

            for (int j = 0; j < 4; j++) {
                int newY = y + moveY[j];
                int newX = x + moveX[j];

                if (newY < 0 || newY >= seat.length
                        || newX < 0 || newX >= seat.length)
                    continue;
                if (seat[newY][newX] == 0)
                    cnt++;
            }

            cntMap[i] = cnt;
            maxCnt = Math.max(maxCnt, cnt);
        }

        for (int i = 0; i < cntMap.length; i++) {
            if (cntMap[i] == maxCnt) {
                arrayList.add(favoriteSeat.get(i));
            }
        }

        return arrayList;
    }

    public static int[] getFirstSeat(ArrayList<int[]> emptySeat) {
        Optional<int[]> optional = emptySeat.stream().min(
                (o1, o2) -> {
                    if (o1[0] == o2[0])
                        return o1[1] - o2[1];
                    return o1[0] - o2[0];
                }
        );

        return optional.orElseGet(() -> new int[]{});
    }

}
