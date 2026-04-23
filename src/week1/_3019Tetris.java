package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

//약 30분 정답
public class _3019Tetris {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        HashMap<Integer, int[][]> blockMap = new HashMap<>();
        blockMap.put(1, new int[][] {
                {1},
                {1,1,1,1},
        });
        blockMap.put(2, new int[][] {
                {1,1}
        });
        blockMap.put(3, new int[][] {
                {1,1,2},
                {2,1}
        });
        blockMap.put(4, new int[][] {
                {2,1,1},
                {1,2}
        });
        blockMap.put(5, new int[][] {
                {1,1,1},
                {2,1,2},
                {1,2},
                {2,1}
        });
        blockMap.put(6, new int[][] {
                {1,1,1},
                {3,1},
                {1,1},
                {1,2,2}
        });
        blockMap.put(7, new int[][] {
                {1,1,1},
                {1,3},
                {1,1},
                {2,2,1}
        });

        try {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int size = Integer.parseInt(st.nextToken());
            int type = Integer.parseInt(st.nextToken());
            int[][] block = blockMap.get(type);

            st = new StringTokenizer(br.readLine(), " ");

            int[] map = new int[104];

            for(int i=0;i<size;i++) {
                int n = Integer.parseInt(st.nextToken());
                map[i] = n;
            }

            int cnt = 0;

            for(int turn = 0; turn < block.length; turn++) {
                for (int offset = 0; offset <= size - block[turn].length; offset++) {
                    cnt += canPut(map, block[turn], offset) ? 1 : 0;
                }
            }

            System.out.println(cnt);

        } catch (Exception e) {

        }
    }

    public static boolean canPut(int[] map, int[] block, int offset) {
        int tmp = map[offset] - block[0];
        for(int i=1;i<block.length;i++) {
            if(tmp != (map[offset + i] - block[i]))
                return false;
        }
        return true;
    }
}
