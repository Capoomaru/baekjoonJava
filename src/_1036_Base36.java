import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class _1036_Base36 {

    static class Base36 {
        int len;
        int num;

        public Base36(char num, int len) {
            if(num >= '0' && num <= '9')
                this.num = num - '0';
            else
                this.num = num - 'A' + 10;
            this.len = len;
        }
    }

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            int size = Integer.parseInt(br.readLine());

            ArrayList<String> inputList = new ArrayList<>();
            PriorityQueue<Base36> pq = new PriorityQueue<>((o1, o2) -> {
                if(o1.len == o2.len)
                    return o1.num - o2.num;
                return o1.len - o2.len;
            });

            for (int i = 0; i < size; i++) {
                String input = br.readLine();
                for(int j=0;j<input.length();j++) {
                    pq.add(new Base36(input.charAt(j), j));
                }
            }

            int k = Integer.parseInt(br.readLine());

            //TODO : Z로 바꿔서 덧셈연산하기
            for(int i=0;i<k;i++) {
                Base36 num = pq.poll();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
