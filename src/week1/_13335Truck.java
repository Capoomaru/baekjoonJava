package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

//16분 오답
//22분 정답
public class _13335Truck {
    public static void main(String[] args) {
        int n; //트럭 수
        int w; //다리 길이
        int L; //최대 하중
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Integer> trucks = new LinkedList<>();
        try {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine(), " ");
            for(int i=0;i<n;i++) {
                trucks.add(Integer.parseInt(st.nextToken()));
            }

            int[] bridge = new int[w];
            int idx = 0;
            int time = 0;
            while(!trucks.isEmpty()) {
                bridge[idx] = 0;
                int truck = trucks.peek();
                if(Arrays.stream(bridge).sum() + truck <= L) {
                    trucks.poll();
                    bridge[idx] = truck;
                }

                idx = (idx + 1) % w;
                time++;
            }

            System.out.println(time+w);

        } catch (Exception e) {

        }
    }
}
