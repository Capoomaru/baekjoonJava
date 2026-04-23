package week1;

import com.sun.source.tree.Tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

//34분 25초 시간초과

public class _17503BeerFestival {

    public static class Beer {
        int preference;
        int alcohol;

        Beer(int preference, int alcohol) {
            this.preference = preference;
            this.alcohol = alcohol;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Beer beer = (Beer) o;

            if (preference != beer.preference) return false;
            return alcohol == beer.alcohol;
        }

        @Override
        public int hashCode() {
            int result = preference;
            result = 31 * result + alcohol;
            return result;
        }
    }
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken()); //축제 기간(최대 맥주 수)
            int m = Integer.parseInt(st.nextToken()); //선호도 합
            int k = Integer.parseInt(st.nextToken()); //맥주 종류

            PriorityQueue<Beer> beerPQ = new PriorityQueue<>((o1, o2) -> {
                if(o1.alcohol == o2.alcohol)
                    return Integer.compare(o2.preference, o1.preference);
                return Integer.compare(o1.alcohol, o2.alcohol);
            });

            for(int i=0;i<k;i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int preference = Integer.parseInt(st.nextToken());
                int alcohol = Integer.parseInt(st.nextToken());
                beerPQ.add(new Beer(preference, alcohol));
            }

            PriorityQueue<Integer> queue = new PriorityQueue<>(Integer::compareTo);

            int sum = 0;

            while(!beerPQ.isEmpty()) {
                Beer beer = beerPQ.poll();
                if(queue.size() < n) {
                    queue.add(beer.preference);
                    sum += beer.preference;
                }
                else {
                    if(beer.preference > queue.peek()) {
                        sum -= queue.poll();
                        sum += beer.preference;
                        queue.add(beer.preference);
                    }
                }

                if(sum >= m && queue.size() == n) {
                    System.out.println(beer.alcohol);
                    return;
                }
            }

            System.out.println("-1");

        } catch (Exception e ) {
            e.printStackTrace();
        }
    }
}

/*
2/5 0.4
4/6 0.66
3/3 1
4/3 1.33
1/4 0.25
 */