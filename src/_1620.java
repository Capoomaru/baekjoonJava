import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class _1620 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashMap<String, Integer> strToIntMap = new HashMap<>();
        HashMap<Integer, String> intToStrMap = new HashMap<>();

        for(int i=1;i<=N;i++) {
            String s = br.readLine();
            strToIntMap.put(s, i);
            intToStrMap.put(i, s);
        }

        for(int i=0;i<M;i++) {
            String s = br.readLine();
            char c = s.charAt(0);
            if(c >= '0' && c<= '9') {
                System.out.println(intToStrMap.get(Integer.parseInt(s)));
            }
            else {
                System.out.println(strToIntMap.get(s));
            }

        }
    }
}
