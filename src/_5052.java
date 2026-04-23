import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class _5052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {
            int N = Integer.parseInt(br.readLine());
            Tries tries = new Tries();
            boolean isPossible = true;
            PriorityQueue<String> pq = new PriorityQueue<>(Comparator.comparingInt(String::length));
            for (int i = 0; i < N; i++) {
                pq.add(br.readLine());
            }
            while (!pq.isEmpty() && isPossible) {
                isPossible = Tries.makeTries(pq.poll(), 0, tries);
            }

            if (isPossible)
                bw.append("YES");
            else
                bw.append("NO");

            bw.newLine();
        }
        bw.flush();
    }

    public static class Tries {
        Tries parent;
        Tries[] next;
        boolean value;

        public Tries() {
            next = new Tries[10];
        }

        public static boolean makeTries(String str, int idx, Tries tries) {
            if (idx == str.length()) {
                tries.value = true;
                return true;
            }
            int nextIdx = str.charAt(idx) - '0';
            if (tries.next[nextIdx] == null) {
                Tries nextTries = new Tries();
                nextTries.parent = tries;
                tries.next[nextIdx] = nextTries;
            } else if (tries.next[nextIdx].value) {
                return false;
            }

            return makeTries(str, idx + 1, tries.next[nextIdx]);
        }
    }
}
