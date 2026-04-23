import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _1463 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N + 1];

        for (int i = 2; i <= N; i++) {
            int min = arr[i - 1] + 1;
            if (i % 2 == 0)
                min = Math.min(min, arr[i / 2] + 1);
            if (i % 3 == 0)
                min = Math.min(min, arr[i / 3] + 1);
            arr[i] = min;
        }

        System.out.println(arr[N]);

    }
}
