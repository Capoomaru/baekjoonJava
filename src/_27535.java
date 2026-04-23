import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//클
public class _27535 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int H = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());

        int sum = H + T + C + K + G;

        int M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            int tmp = sum;
            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
                if(o1[0] == o2[0])
                    return o1[1] - o2[1];
                return o2[0] - o1[0];
            });
            st = new StringTokenizer(br.readLine(), " ");
            H -= Integer.parseInt(st.nextToken());
            pq.add(new int[]{H, 'H'});
            T -= Integer.parseInt(st.nextToken());
            pq.add(new int[]{T, 'T'});
            C -= Integer.parseInt(st.nextToken());
            pq.add(new int[]{C, 'C'});
            K -= Integer.parseInt(st.nextToken());
            pq.add(new int[]{K, 'K'});
            G -= Integer.parseInt(st.nextToken());
            pq.add(new int[]{G, 'G'});
            sum = H + T + C + K + G;
            int radix = tmp % 10;
            if(radix <= 1)
                bw.append(Integer.toString(sum));
            else
                bw.append(Integer.toString(sum, tmp % 10));
            bw.append('7').append('H');
            bw.newLine();

            boolean flag = true;
            while(!pq.isEmpty()) {
                int[] p = pq.poll();
                if(p[0] <= 0)
                    continue;

                flag = false;

                bw.append((char)p[1]);
            }

            if(flag)
                bw.append("NULL");
            bw.newLine();
        }

        bw.flush();
    }
}
