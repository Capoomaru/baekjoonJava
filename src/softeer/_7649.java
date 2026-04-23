package softeer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class _7649 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String S = br.readLine();

        HashMap<Integer, HashMap<Integer, Character>> map = new HashMap<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            map.put(u, map.getOrDefault(u, new HashMap<>()));
            map.get(u).put(v, c);
            map.put(v, map.getOrDefault(v, new HashMap<>()));
            map.get(v).put(u, c);
        }


    }
}
