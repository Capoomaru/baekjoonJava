package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//9분 1초 통과
public class _2980RoadTrafficLight {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            int position = 0;
            int time = 0;
            for(int i=0;i<n;i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int distance = Integer.parseInt(st.nextToken());
                int red = Integer.parseInt(st.nextToken());
                int green = Integer.parseInt(st.nextToken());
                int cycle = red+green;

                time += distance - position;
                position = distance;

                while(time % cycle < red) {
                    time++;
                }
            }
            time += L - position;

            System.out.println(time);
        } catch (Exception e) {

        }
    }
}
