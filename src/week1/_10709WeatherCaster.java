package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//11분 35초 성공
public class _10709WeatherCaster {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int H = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            int[][] map = new int[H][W];
            for (int i = 0; i < H; i++) {
                String input = br.readLine();
                int min = -1;
                for (int j = 0; j < W; j++) {
                    if(input.charAt(j) == '.') {
                        map[i][j] = min == -1 ? -1 : ++min;
                    }
                    else {
                        map[i][j] = 0;
                        min = 0;
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            for(int i=0;i<H;i++) {
                for(int j=0;j<W;j++) {
                    sb.append(map[i][j]);
                    sb.append(" ");
                }
                sb.append("\n");
            }
            System.out.println(sb);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
