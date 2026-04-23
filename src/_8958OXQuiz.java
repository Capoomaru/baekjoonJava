import java.util.Scanner;

// 3분? 정답
public class _8958OXQuiz {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();

        for(int i=0;i<size;i++) {
            String s = sc.next();

            int cnt = 0;
            int sum = 0;
            for(char c : s.toCharArray()) {
                if(c == 'O') {
                    cnt++;
                    sum+=cnt;
                }
                else {
                    cnt = 0;
                }
            }

            System.out.println(sum);
        }
    }
}
