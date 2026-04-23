import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _1725 {

    public static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int[] tree = new int[getSize(N + 1)];
        int[] arr = new int[N + 1];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            tree[tree.length / 2 + i] = i;
        }
        for (int i = tree.length / 2 + N; i < tree.length; i++)
            tree[i] = N;

        init(arr, tree);

        System.out.println(getMax(arr, tree, 0, tree.length / 2 - 1));
    }

    public static int getSize(int N) {
        return (int) Math.pow(2, 1 + Math.ceil(Math.log(N) / Math.log(2)));
    }

    public static void init(int[] arr, int[] tree) {
        for (int i = tree.length / 2 - 1; i > 0; --i) {
            if (arr[tree[i << 1]] <= arr[tree[i << 1 | 1]])
                tree[i] = tree[i << 1];
            else
                tree[i] = tree[i << 1 | 1];
        }
    }

    public static int query(int[] arr, int[] tree, int idx, int left, int right, int a, int b) {
        if (a > right || b < left)
            return N;

        if (a <= left && right <= b)
            return tree[idx];

        int mid = (left + right) / 2;
        int leftIdx = query(arr, tree, idx << 1, left, mid, a, b);
        int rightIdx = query(arr, tree, idx << 1 | 1, mid + 1, right, a, b);

        if(leftIdx == N)
            return rightIdx;
        if(rightIdx == N)
            return leftIdx;

        if (arr[leftIdx] <= arr[rightIdx])
            return leftIdx;
        else
            return rightIdx;
    }

    public static int getMax(int[] arr, int[] tree, int left, int right) {

        if (left == right)
            return arr[left];
        int idx = query(arr, tree, 1, 0, tree.length / 2 - 1, left, right);

        int max = arr[idx] * (right - left + 1);

        if (left <= idx - 1) {
            max = Math.max(max, getMax(arr, tree, left, idx - 1));
        }

        if (idx + 1 <= right) {
            max = Math.max(max, getMax(arr, tree, idx + 1, right));
        }

        return max;
    }
}
