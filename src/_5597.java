import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _5597 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = 28;
        boolean[] attendance = new boolean[30];
        for (int i = 0; i < n; i++) {
            attendance[Integer.parseInt(br.readLine()) - 1] = true;
        }

        boolean bit = false;
        for (int i = 0; i < 30; i++) {
            if (attendance[i])
                continue;
            System.out.println(i + 1);
            if (bit)
                return;
            bit = true;
        }
    }
}
