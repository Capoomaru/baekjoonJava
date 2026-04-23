import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;

public class _19644Zombie {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            int L = Integer.parseInt(br.readLine());
            String[] read = br.readLine().split(" ");
            int ML = Integer.parseInt(read[0]);
            int MK = Integer.parseInt(read[1]);
            int C = Integer.parseInt(br.readLine());
            HashSet<Integer> bombSet = new HashSet<>();

            for (int i = 0; i < L && (L - i) > C; i++) {
                int target = Integer.parseInt(br.readLine());

                if (((i - ML <= 0 ? i + 1 : ML) - bombSet.size()) * MK < target) {
                    if (C == 0) {
                        System.out.println("NO");
                        return;
                    }
                    C--;
                    bombSet.add(i);
                }

                bombSet.remove(i - ML);

            }
        }
        catch (Exception e) {

        }

        System.out.println("YES");

    }
}
