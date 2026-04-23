package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

//14분 틀림
//17분 맞음
public class _1417Vote {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int sum = 0;

        try {
            int n = Integer.parseInt(br.readLine());

            int target = Integer.parseInt(br.readLine());

            PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

            for (int i=0;i<n-1;i++) {
                pq.add(Integer.parseInt(br.readLine()));
            }

            while (!pq.isEmpty() && pq.peek() >= target) {
                int top = pq.poll();
                target += 1;
                sum += 1;
                pq.add(--top);
            }

            System.out.println(sum);

        } catch (Exception e) {

        }
    }
}
