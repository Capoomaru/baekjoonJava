import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _20056 {

    static int[] xDiff = { 0, 1, 1, 1, 0, -1, -1, -1 };
    static int[] yDiff = { -1, -1, 0, 1, 1, 1, 0, -1 };

    static ArrayList<int[]>[][] fireList;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        fireList = new ArrayList[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                fireList[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            fireList[y][x].add(new int[] { m, s, d });
        }

        for (int i = 0; i < K; i++) {
            move();
            divid();
        }

        int sum = Arrays.stream(fireList)
                .mapToInt(list -> Arrays.stream(list).mapToInt(arr -> arr.stream().mapToInt(x -> x[0]).sum()).sum())
                .sum();

        System.out.println(sum);
    }

    public static void move() {
        ArrayList<int[]>[][] newFireList = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newFireList[i][j] = new ArrayList<>();
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int[] fire : fireList[i][j]) {
                    int y = (fire[1] * N + i + yDiff[fire[2]] * fire[1]) % N;
                    int x = (fire[1] * N + j + xDiff[fire[2]] * fire[1]) % N;

                    newFireList[y][x].add(fire);
                }
            }
        }

        fireList = newFireList;
    }

    public static void divid() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (fireList[i][j].size() < 2)
                    continue;

                int size = fireList[i][j].size();

                int M = 0;
                int S = 0;
                boolean D = fireList[i][j].get(0)[2] % 2 == 0;
                boolean isEqualDirection = true;

                for (int[] fire : fireList[i][j]) {
                    M += fire[0];
                    S += fire[1];
                    isEqualDirection &= D == (fire[2] % 2 == 0);
                }

                fireList[i][j].clear();
                if (M < 5) {
                    continue;
                }

                M /= 5;
                S /= size;
                if (isEqualDirection) {
                    for (int k = 0; k < 8; k += 2)
                        fireList[i][j].add(new int[] { M, S, k });
                } else {
                    for (int k = 1; k < 8; k += 2)
                        fireList[i][j].add(new int[] { M, S, k });
                }

            }
        }
    }
}
