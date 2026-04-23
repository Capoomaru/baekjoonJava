import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1074 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int size = (int) Math.pow(2, N);

        int sum = bfs(r, c, 0, size, 0, size);

        System.out.println(sum);
    }

    public static int bfs(int targetY, int targetX, int y1, int y2, int x1, int x2) {
        int size = (x2 - x1) / 2;
        int middleY = y1 + size;
        int middleX = x1 + size;

        if (middleY == targetY && middleX == targetX)
            return 3 * size * size;
        ;

        if (targetY >= middleY) {
            if (targetX >= middleX) {
                //4사분면
                return bfs(targetY, targetX, middleY, middleY, middleX, x2) + 3 * size * size;
            } else {
                //3사분면
                return bfs(targetY, targetX, middleY, y2, x1, middleX) + 2 * size * size;
            }
        } else {
            if (targetX >= middleX) {
                //2사분면
                return bfs(targetY, targetX, y1, middleY, middleX, x2) + size * size;
            } else {
                return bfs(targetY, targetX, y1, middleY, x1, middleX);
            }
        }
    }
}