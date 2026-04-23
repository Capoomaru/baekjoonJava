import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _10875Snake {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int L = Integer.parseInt(br.readLine()), N = Integer.parseInt(br.readLine());
        int[] timeList = new int[N];
        int[] rotateLeftList = new int[N];
        for (int i = 0; i < N; i++) {
            //String input = sc.nextLine();
            //timeList[i] = Integer.parseInt(input.split(" ")[0]);
            StringTokenizer st = new StringTokenizer(br.readLine());

            timeList[i] = Integer.parseInt(st.nextToken());
            rotateLeftList[i] = st.nextToken().equals("L") ? 1 : -1;
        }
        br.close();

        boolean[][] map = new boolean[2 * L + 1][2 * L + 1];
        int x, y;
        x = y = L;
        int xDirection = 1, yDirection = 0;

        int i = 0, time = 0, total = 0;
        while (x >= 0 && x <= 2 * L && y >= 0 && y <= 2 * L && !map[y][x]) {
            map[y][x] = true;
            if (time == timeList[i]) {
                total += time;
                time = 0;
                if (xDirection == 0) {
                    xDirection = -yDirection * rotateLeftList[i];
                    yDirection = 0;
                } else {
                    yDirection = xDirection * rotateLeftList[i];
                    xDirection = 0;
                }
                i++;
            }


            x += xDirection;
            y += yDirection;
            time++;
        }
        total += time;
        System.out.println(total);
    }
}
