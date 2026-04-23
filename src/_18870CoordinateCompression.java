import com.sun.source.tree.Tree;

import java.io.*;
import java.util.*;

public class _18870CoordinateCompression {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        TreeSet<Integer> set = new TreeSet<>(Integer::compareTo);

        try {
            int n = Integer.parseInt(br.readLine());
            List<Integer> list = new ArrayList<>();

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < n; i++) {
                list.add(Integer.parseInt(st.nextToken()));
                set.add(list.get(i));
            }

            HashMap<Integer, Integer> map = new HashMap<>(list.size());

            int i=0;

            while (!set.isEmpty()) {
                map.put(set.pollFirst(), i++);
            }

            list.forEach(item -> {
                try {
                    bw.write(map.get(item)+" ");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            bw.flush();


        } catch (IOException e) {

        }

    }
}
