package ssafy_prev;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class _7465 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            int M = sc.nextInt();

            HashMap<Integer, LinkedList<Integer>> map = new HashMap<>();
            for (int i = 0; i < N; i++)
                map.put(i, new LinkedList<>());
            boolean[] visited = new boolean[N];
            for (int i = 0; i < M; i++) {
                int from = sc.nextInt() - 1;
                int to = sc.nextInt() - 1;
                map.get(from).add(to);
                map.get(to).add(from);
            }

            int count = 0;

            for(int i=0;i<N;i++) {
                if(visited[i])
                    continue;

                makeSwarm(map, visited, i);
                count++;
            }

            System.out.printf("#%d %d\n", test_case, count);

        }
    }

    public static void makeSwarm(HashMap<Integer, LinkedList<Integer>> map, boolean[] visited, int target) {
        LinkedList<Integer> list = map.get(target);
        visited[target] = true;

        while(!list.isEmpty()) {
            int node = list.pop();
            if(visited[node])
                continue;
            visited[node] = true;
            map.get(node).forEach(link -> {
                if(!visited[link])
                    list.add(link);
            });
        }
    }
}
