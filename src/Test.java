import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;

public class Test {
    public static void main(String[] args) {
        HashSet<int[]> hashSet = new HashSet<>();
        hashSet.add(new int[]{1,2,3});
        hashSet.add(new int[]{1,2,3});

        System.out.println("HashSet");
        for(int[] arr : hashSet) {
            System.out.println(Arrays.toString(arr));
        }

        TreeSet<int[]> treeSet = new TreeSet<>(((o1, o2) -> {
            if(o1[0] == o2[0]) {
                if(o1[1] == o2[1]) {
                    if(o1[2] == o2[2])
                        return o1.hashCode() - o2.hashCode();
                    return o1[2] - o2[2];
                }
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        }));

        treeSet.add(new int[]{1,2,3});
        treeSet.add(new int[]{1,2,3});

        System.out.println("TreeSet");
        for(int[] arr : treeSet) {
            System.out.println(Arrays.toString(arr));
        }
    }
}
