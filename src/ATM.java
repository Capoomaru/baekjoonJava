import java.util.PriorityQueue;
import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        PriorityQueue<Integer> pq = new PriorityQueue<>(n);

        for(int i=0;i<n;i++) {
            pq.add(sc.nextInt());
        }

        int sum = 0;
        int answer = 0;

        while(!pq.isEmpty()) {
            int tmp = pq.poll();
            sum += tmp;
            answer = answer + sum;
        }

        System.out.println(answer);
    }
}
