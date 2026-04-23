import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class _1541 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        Stack<Integer> stack = new Stack<>();
        Stack<Boolean> opStack = new Stack<>();
        int start = 0;
        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) == '+' || input.charAt(i) == '-') {
                stack.push(parse(input, start, i));
                opStack.push(input.charAt(i) == '-');
                start = i + 1;
                i++;
            }
        }
        stack.push(parse(input, start, input.length()));

        int sum = 0;

        while (!opStack.isEmpty()) {
            boolean minus = opStack.pop();

            int a = stack.pop();
            if (minus) {
                sum -= a;
            } else {
                int b = stack.pop();
                stack.add(a + b);
            }
        }

        sum += stack.pop();

        System.out.println(sum);
    }

    public static int parse(String str, int start, int end) {
        String s = new String(Arrays.copyOfRange(str.toCharArray(), start, end));
        return Integer.parseInt(s);
    }
}
