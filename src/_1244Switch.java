import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

//틀림
//맞음 => 등호 안붙임
public class _1244Switch {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int size = Integer.parseInt(br.readLine());
            List<Integer> map = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::parseInt).collect(Collectors.toList());
            int n = Integer.parseInt(br.readLine());
            for(int i=0;i<n;i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                boolean isMan = st.nextToken().equals("1");
                int point = Integer.parseInt(st.nextToken());
                if(isMan) {
                    for(int j=1;j*point <= size;j++)
                        map.set(j*point-1, map.get(j*point-1) == 1 ? 0 : 1);
                } else {
                    map.set(point-1, map.get(point-1) == 1 ? 0 : 1);
                    for(int j=1;(j+point-1)<size && (point-1-j)>=0;j++) {
                        if(!map.get(point+j-1).equals(map.get(point-j-1)))
                            break;
                        map.set(point+j-1, map.get(point+j-1) == 1 ? 0 : 1);
                        map.set(point-j-1, map.get(point-j-1) == 1 ? 0 : 1);
                    }
                }
            }

            int i=0;
            for(int item : map) {
                System.out.print(item+" ");
                i++;
                if(i % 20 == 0)
                    System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
