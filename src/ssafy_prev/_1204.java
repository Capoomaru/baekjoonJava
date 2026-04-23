package ssafy_prev;

import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;
import java.util.StringTokenizer;

public class _1204 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = Integer.parseInt(sc.nextLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int caseNum = Integer.parseInt(sc.nextLine());
            StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
            HashMap<String, Integer> countMap = new HashMap<>();
            while(st.hasMoreTokens()) {
                String key = st.nextToken();
                countMap.put(key, countMap.getOrDefault(key, 0) + 1);
            }
            if(countMap.isEmpty())
                throw new Error("입력된 학생의 성적이 없습니다.");
            int max = Integer.parseInt(countMap.entrySet().stream().max((o1, o2) -> {
                if(Objects.equals(o1.getValue(), o2.getValue()))
                    return o1.getKey().compareTo(o2.getKey());
                return o1.getValue() - o2.getValue();
            }).get().getKey());

            System.out.printf("#%d %d\n", caseNum, max);
        }
    }
}
