import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class _8972 {

    public static class Pos {
        int y;
        int x;

        public Pos() {
        }

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public boolean equals(Object o) {
            Pos pos = (Pos) o;

            if (y != pos.y) return false;
            return x == pos.x;
        }

        @Override
        public int hashCode() {
            int result = y;
            result = 31 * result + x;
            return result;
        }
    }

    public static int[] xDiff = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};
    public static int[] yDiff = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1};

    public static final String EXIT = "kraj ";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] map = new char[N][M];
        Arrays.stream(map).forEach(arr -> Arrays.fill(arr, '.'));

        ArrayList<Pos> crazyArduinoList = new ArrayList<>();
        Pos myArduino = new Pos();

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = input.charAt(j);
                if (c == 'I') {
                    myArduino.y = i;
                    myArduino.x = j;
                } else if (c == 'R') {
                    crazyArduinoList.add(new Pos(i, j));
                }
                map[i][j] = '.';
            }
        }

        String opList = br.readLine();

        for (int i = 0; i < opList.length(); i++) {
            myArduino.y += yDiff[opList.charAt(i) - '0'];
            myArduino.x += xDiff[opList.charAt(i) - '0'];

            HashMap<Pos, ArrayList<Pos>> arduinoMap = new HashMap<>();

            for (Pos arduino : crazyArduinoList) {
                int min = Integer.MAX_VALUE;
                int minIdx = 0;

                for (int j = 1; j <= 9; j++) {
                    int y = Math.abs(myArduino.y - arduino.y - yDiff[j]);
                    int x = Math.abs(myArduino.x - arduino.x - xDiff[j]);
                    if (y == 0 && x == 0) {
                        System.out.println(EXIT + (i + 1));
                        return;
                    }
                    if (min > (y + x)) {
                        min = y + x;
                        minIdx = j;
                    }
                }

                arduino.y += yDiff[minIdx];
                arduino.x += xDiff[minIdx];

                if (arduinoMap.containsKey(arduino)) {
                    arduinoMap.get(arduino).add(arduino);
                } else {
                    ArrayList<Pos> list = new ArrayList<>();
                    list.add(arduino);
                    arduinoMap.put(arduino, list);
                }
            }

            for (ArrayList<Pos> list : arduinoMap.values()) {
                if (list.size() == 1)
                    continue;

                list.forEach(crazyArduinoList::remove);
            }
        }

        map[myArduino.y][myArduino.x] = 'I';
        crazyArduinoList.forEach(arduino -> map[arduino.y][arduino.x] = 'R');

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                bw.append(map[i][j]);
            }
            bw.newLine();
        }

        bw.flush();
    }
}
