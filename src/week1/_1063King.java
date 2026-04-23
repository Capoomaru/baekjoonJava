package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//15분 오답
//46분 정답
public class _1063King {
    public static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Point(Point p) {
            this.x = p.x;
            this.y = p.y;
        }

        public static void move(Point point, String op) {
            if(!canMove(point, op)) return;
            if(op.length() == 1) {
                switch (op) {
                    case "R":
                        point.x += 1;
                        break;
                    case "L":
                        point.x -= 1;
                        break;
                    case "B":
                        point.y -= 1;
                        break;
                    case "T":
                        point.y += 1;
                        break;
                }
            }
            else {
                move(point, op.substring(0, 1));
                move(point, op.substring(1, 2));
            }
        }

        private static boolean canMove(Point point, String op) {
            if(op.length() == 1) {
                switch (op) {
                    case "R":
                        return (point.x + 1) <= 8;
                    case "L":
                        return (point.x - 1) >= 1;
                    case "B":
                        return (point.y - 1) >= 1;
                    case "T":
                        return (point.y + 1) <= 8;
                }
            }
            return canMove(point, op.substring(0, 1))
            && canMove(point, op.substring(1, 2));
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(Character.toString(this.x + 'A' - 1));
            sb.append(Character.toString(this.y + '0'));

            return sb.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            if (x != point.x) return false;
            return y == point.y;
        }
    }
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Point king, stone;

        try {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String input = st.nextToken();
            king = new Point(input.charAt(0)-'A' + 1, input.charAt(1)-'0');
            input = st.nextToken();
            stone = new Point(input.charAt(0)-'A' + 1, input.charAt(1)-'0');
            input = st.nextToken();
            int n = Integer.parseInt(input);

            for(int i=0;i<n;i++) {
                input = br.readLine();

                Point kingTmp = new Point(king);
                Point.move(kingTmp, input);
                Point stoneTmp = new Point(stone);
                if(kingTmp.equals(stone))
                    Point.move(stoneTmp, input);

                if(!kingTmp.equals(stoneTmp)) {
                    king = kingTmp;
                    stone = stoneTmp;
                }

            }

            System.out.println(king);
            System.out.println(stone);
        } catch (Exception e) {

        }
    }
}
