import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _2437 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int sum = arr[0];
        if (arr[0] != 1) {
            System.out.println(1);
            return;
        }

        for (int i = 1; i < N; i++) {
            if (sum + 1 < arr[i]) {
                System.out.println(sum + 1);
                return;
            }
            sum += arr[i];
        }
        System.out.println(sum + 1);

    }
}
