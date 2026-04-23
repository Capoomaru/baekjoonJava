import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _14865 {

    public static int aCnt, bCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int startX = x, startY = y;

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] == o2[0])
                return o2[1] - o1[1];

            return o1[0] - o2[0];
        });

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int nextX = Integer.parseInt(st.nextToken());
            int nextY = Integer.parseInt(st.nextToken());

            if (y < 0 && nextY < 0) {
                x = nextX;
                y = nextY;
                continue;
            }

            if ((y < 0) && nextY > 0) {
                stack.add(x);
            }
            if (y > 0 && nextY < 0) {
                if (stack.isEmpty()) {
                    stack.add(x);
                } else {
                    pq.add(new int[]{Math.min(stack.getLast(), x), Math.max(stack.getLast(), x)});
                    stack.pollLast();
                }
            }

            x = nextX;
            y = nextY;
        }

        if (stack.size() == 2) {
            int first = stack.poll();
            int second = stack.poll();
            pq.add(new int[]{Math.min(first, second), Math.max(first, second)});
        } else {

            if ((y < 0) && startY > 0) {
                pq.add(new int[]{Math.min(stack.getLast(), x), Math.max(stack.getLast(), x)});
            }
            if (y > 0 && startY < 0) {
                pq.add(new int[]{Math.min(stack.getLast(), x), Math.max(stack.getLast(), x)});
            }
        }

        while (!pq.isEmpty()) {
            run(pq, null);
        }

        System.out.println(aCnt + " " + bCnt);

    }

    public static void run(PriorityQueue<int[]> pq, int[] target) {

        if (target == null)
            aCnt++;

        int[] p = pq.poll();

        if (pq.isEmpty())
            bCnt++;

        boolean hasMore = false;
        while (!pq.isEmpty()) {

            int[] p2 = pq.peek();
            if (p2 == null || p[1] < p2[1]) {
                if(!hasMore)
                    bCnt++;
                return;
            } else {
                hasMore = true;
                run(pq, p2);
            }

        }
    }

}

