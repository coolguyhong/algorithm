package coding_test.kakao.shuttlebus;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution {
    private static int Num;
    private static int Interval;
    private static int Max_Crew;
    private static int Crew_Cnt;
    private static String[] Timetable;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();
        while (testCase > 0) {
            Num = sc.nextInt();
            Interval = sc.nextInt();
            Max_Crew = sc.nextInt();
            Crew_Cnt = sc.nextInt();
            Timetable = new String[Crew_Cnt];
            for (int i = 0; i < Crew_Cnt; i++) {
                Timetable[i] = sc.next();
            }

            findLatestTime();

            testCase--;
        }
    }

    private static void findLatestTime() {
        PriorityQueue<Time> queue = new PriorityQueue<>();
        for (String time : Timetable) {
            queue.add(new Time(time));
        }

        Time conTime = new Time("00:00");

        for (int i = 0; i < Num; i++) {
            Time busTime = new Time("09:00");
            busTime.add(i, Interval);

            conTime = busTime;
            int capacity = Max_Crew;
            // 버스태우기
            while (!queue.isEmpty() && capacity > 0 && queue.peek().compareTo(busTime) <= 0) {
                Time crewTime = queue.poll();

                if (capacity == 1) {
                    conTime = crewTime;
                    conTime.add(1, -1);
                }

                capacity--;
            }
        }
        System.out.println(conTime.toString());
    }
}

class Time implements Comparable<Time> {
    int h;
    int m;

    Time(String time) {
        this.h = Integer.parseInt(time.substring(0,2));
        this.m = Integer.parseInt(time.substring(3,5));
    }

    public void add(int n, int t) {
        int addHour = (n * t) / 60;
        int addMin = (n * t) % 60;

        h += addHour;
        m += addMin;

        if (m >= 60) {
            h++;
            m %= 60;
        } else if (m < 0) {
            h--;
            m = 60 + m;
        }
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", this.h, this.m);
    }

    @Override
    public int compareTo(Time o) {
        return this.h < o.h ? -1 : this.h > o.h ? 1 : this.m < o.m ? -1 : this.m > o.m ? 1 : 0;
    }
}
