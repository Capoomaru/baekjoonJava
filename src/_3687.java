import java.io.*;
import java.util.HashMap;

public class _3687 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        StringBuilder[] maxDp = new StringBuilder[101];
        long[] minDp = new long[101];
        maxDp[2] = new StringBuilder("1");
        maxDp[3] = new StringBuilder("7");
        for (int i = 4; i < 100; i += 2) {
            maxDp[i] = new StringBuilder(maxDp[i - 2]).append('1');
            maxDp[i + 1] = new StringBuilder(maxDp[i - 1]).append('1');
        }
        maxDp[100] = new StringBuilder(maxDp[98]).append('1');

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(2, 1);
        map.put(3, 7);
        map.put(4, 4);
        map.put(5, 2);
        map.put(6, 0);
        map.put(7, 8);

        minDp[2] = 1;
        minDp[3] = 7;
        minDp[4] = 4;
        minDp[5] = 2;
        minDp[6] = 6;
        minDp[7] = 8;
        minDp[8] = 10;
        minDp[9] = 18;
        for (int i = 10; i <= 100; ++i) {
            minDp[i] = Math.min(minDp[i - 2] * 10 + minDp[2], minDp[i - 3] * 10 + minDp[3]);
            minDp[i] = Math.min(minDp[i], minDp[i - 4] * 10 + map.get(4));
            minDp[i] = Math.min(minDp[i], minDp[i - 5] * 10 + map.get(5));
            minDp[i] = Math.min(minDp[i], minDp[i - 6] * 10 + map.get(6));
            minDp[i] = Math.min(minDp[i], minDp[i - 7] * 10 + map.get(7));
        }

        for (int testCase = 1; testCase <= T; testCase++) {
            int N = Integer.parseInt(br.readLine());

            bw.append(Long.toString(minDp[N])).append(' ').append(maxDp[N]);
            bw.newLine();
        }

        bw.flush();
    }
}
