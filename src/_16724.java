import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class _16724 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashMap<Character, Integer> diffMap = new HashMap<>();
        diffMap.put('D', M);
        diffMap.put('L', -1);
        diffMap.put('R', 1);
        diffMap.put('U', -M);

        int[] head = new int[N * M];

        for (int i = 0; i < head.length; i++)
            head[i] = i;

        for (int i = 0; i < N; i++) {
            String opList = br.readLine();
            for (int j = 0; j < M; j++) {
                int idx = i * M + j;
                union(head, idx, idx + diffMap.get(opList.charAt(j)));
            }
        }

        HashSet<Integer> headSet = new HashSet<>();
        for (int i = 0; i < head.length; i++) {
            headSet.add(find(head, i));
        }
        System.out.println(headSet.size());
    }

    public static void union(int[] head, int A, int B) {
        int headA = find(head, A);
        int headB = find(head, B);
        if (headA == headB)
            return;

        head[headA] = headB;
    }

    public static int find(int[] head, int X) {
        if (head[X] == X)
            return X;

        return head[X] = find(head, head[X]);
    }
}
