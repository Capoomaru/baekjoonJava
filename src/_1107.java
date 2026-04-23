import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _1107 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String targetStr = br.readLine().trim();
        int target = Integer.parseInt(targetStr);
        int n = Integer.parseInt(br.readLine().trim());
        boolean[] unusable = new boolean[10];
        if(n != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++)
                unusable[Integer.parseInt(st.nextToken())] = true;
        }
        if (n == 10) {
            System.out.println(Math.abs(target - 100));
            return;
        }


        int min = Math.abs(target - 100);

        int minNum = 0;
        while (minNum <= 9 && unusable[minNum])
            minNum++;

        int maxNum = 9;
        while (maxNum >= 0 && unusable[maxNum])
            maxNum--;

        int[] numList = Arrays.stream(targetStr.chars().toArray()).map(charNum -> charNum - '0').toArray();

        int i;
        for (i = 0; i < numList.length; i++) {
            int num = numList[i];
            if (!unusable[num])
                continue;

            //up
            int[] upList = Arrays.copyOf(numList, numList.length);
            int tmp = 0, j;
            for (j = i; j >= 0; j--) {
                tmp = upList[j] + 1;
                while (tmp <= 9 && unusable[tmp])
                    tmp++;
                if (tmp <= 9) {
                    upList[j] = tmp;
                    break;
                }
                upList[j] = minNum;
            }
            int upNum = 0;
            int len = upList.length;
            if (j < 0 && tmp > 9) {
                upNum = minNum;
                if(minNum == 0) {
                    int k = 1;
                    while (k <= 9 && unusable[k])
                        k++;
                    upNum = k;
                }
                len++;
            }

            for (j = i + 1; j < upList.length; j++) {
                upList[j] = minNum;
            }

            for (j = 0; j < upList.length; j++) {
                upNum *= 10;
                upNum += upList[j];
            }

            if(upNum > target)
                min = Math.min(min, upNum - target + len);

            //down
            int[] downList = Arrays.copyOf(numList, numList.length);
            tmp = 0;
            for (j = i; j >= 0; j--) {
                tmp = downList[j] - 1;
                while (tmp >= 0 && unusable[tmp])
                    tmp--;
                if (tmp >= 0) {
                    downList[j] = tmp;
                    break;
                }
                if (j != 0)
                    downList[j] = maxNum;
                else
                    downList[j] = 0;
            }

            int downNum = 0;

            for (j = i + 1; j < numList.length; j++) {
                downList[j] = maxNum;
            }

            len = downList.length;
            j=0;
            while(j < downList.length - 1 && downList[j++] == 0) {
                len--;
            }

            for (j = 0; j < downList.length; j++) {
                downNum *= 10;
                downNum += downList[j];
            }

            if(downNum < 10 && unusable[downNum])
                break;
            if(target > downNum)
                min = Math.min(min, target - downNum + len);

            break;
        }
        if (i == numList.length)
            min = Math.min(min, numList.length);

        System.out.println(min);

    }
}
