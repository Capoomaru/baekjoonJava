import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//https://www.acmicpc.net/problem/11047
public class Coin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int target = sc.nextInt();
        List<Integer> coinList = new ArrayList<>(n);
        for(int i=0;i<n;i++)
            coinList.add(sc.nextInt());

        int answer = 0;
        int sum = 0;
        while(sum != target) {
            for(int i=n-1;i>=0;i--) {
                int coin = coinList.get(i);
                while((target - sum) >= coin) {
                    sum += coin;
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }
}
