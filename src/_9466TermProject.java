import com.sun.source.tree.Tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.TreeSet;

//https://www.acmicpc.net/problem/9466

public class _9466TermProject {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            int n = Integer.parseInt(br.readLine());

            for(int i=0;i<n;i++) {
                int size = Integer.parseInt(br.readLine());
                int answer = 0;
                int[] preferMap = new int[size];
                boolean[] visited = new boolean[size];
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for(int j=0;j<size;j++) {
                    preferMap[j] = Integer.parseInt(st.nextToken())-1;
                    visited[j] = preferMap[j] == j;
                }
                for(int j=0;j<size;j++) {
                    if(visited[j])
                        continue;

                    if(visited[preferMap[j]])
                        continue;

                    TreeSet<Integer> list = new TreeSet<>();
                    list.add(j);
                    list.add(preferMap[j]);

                    int next = preferMap[list.last()];

                    while(j != next) {
                        //visited[list.last()] = true;
                        next = preferMap[list.last()];

                        if(visited[preferMap[next]])
                            break;

                        if(list.contains(next)) {
                            int next_next = preferMap[next];
                            while(next_next != next) {
                                visited[next_next] = true;
                                next_next = preferMap[next_next];
                            }
                            break;
                        }
                        else {
                            list.add(next);
                        }
                    }

                    if(!visited[j])
                        answer++;

                }

                System.out.println(answer);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
