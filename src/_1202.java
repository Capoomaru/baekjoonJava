import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _1202 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> jewelPQ = new PriorityQueue<>(((o1, o2) -> {
            if (o1[1] == o2[1])
                return o2[0] - o1[0];
            return o2[1] - o1[1];
        }));

        TreeMap<Integer, Integer> bagTreeMap = new TreeMap<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            jewelPQ.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }
        for (int i = 0; i < K; i++) {
            int key = Integer.parseInt(br.readLine());
            bagTreeMap.put(key, bagTreeMap.getOrDefault(key, 0) + 1);
        }

        long answer = 0;
        while(!bagTreeMap.isEmpty() && !jewelPQ.isEmpty()) {
            int[] target = jewelPQ.poll();
            Integer key = bagTreeMap.ceilingKey(target[0]);
            if(key == null) {
                continue;
            }
            answer += target[1];
            int size = bagTreeMap.get(key);
            if(size == 1) {
                bagTreeMap.remove(key);
            }
            else {
                bagTreeMap.put(key, size - 1);
            }
        }

        System.out.println(answer);

    }
}
