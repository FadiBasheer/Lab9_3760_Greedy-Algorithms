import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class main {
    public static ArrayList<Meeting> read_file(String filename) throws IOException {
        //
        // Reads the input file given by "filename", assumed to contain a list of
        // integer numbers. Stores the numbers into an array and returns the
        // array.
        //
        File file = new File(filename);
        Scanner sc = new Scanner(file);

        //
        // Read the items initially into an ArrayList (for dynamic growth)
        //
        ArrayList<Meeting> input_list = new ArrayList<Meeting>();
        while (sc.hasNext()) {
            String name = sc.nextLine();
            String line2 = sc.nextLine();
            String[] parts = line2.split(" ");
            int start = Integer.parseInt(parts[0]);
            int end = Integer.parseInt(parts[1]);
            input_list.add(new Meeting(name, start, end));
        }
        return input_list;
    }

    static int Greedy_Algorithm(ArrayList<Meeting> data, ArrayList<Meeting> scheduled_meetings) {
        scheduled_meetings.add(data.get(0));
        int loop = 0;
        for (int i = 1; i < data.size(); i++) {
            if (!(data.get(i).overlapsWith(scheduled_meetings.get(loop)))) {
                scheduled_meetings.add(data.get(i));
                loop++;
            }
        }
        return scheduled_meetings.size();
    }

    static int Greedy_Algorithm2(ArrayList<Meeting> data, ArrayList<Meeting> scheduled_meetings) {
        scheduled_meetings.add(data.get(0));
        for (int i = 1; i < data.size(); i++) {
            boolean flag = true;
            for (int j = 0; j < scheduled_meetings.size(); j++) {
                if (data.get(i).overlapsWith(scheduled_meetings.get(j))) {
                    flag = false;
                }
            }
            if (flag) {
                scheduled_meetings.add(data.get(i));
            }
        }
        return scheduled_meetings.size();
    }

    public static void main(String[] args) throws IOException {
        String fn = "data1.txt";
        //String fn = "data2.txt";
        //String fn = "data3.txt";
        //String fn = "data4.txt";

        // Call helper function to read the input file
        ArrayList<Meeting> data = read_file(fn);
        ArrayList<Meeting> scheduled_meetings = new ArrayList<>();

        Collections.sort(data, Meeting.startComparator);
        System.out.println("Rank by Start: " + Greedy_Algorithm(data, scheduled_meetings));


        Collections.sort(data, Meeting.lengthComparator);
        scheduled_meetings.clear();
        System.out.println("Rank by Length: " + Greedy_Algorithm2(data, scheduled_meetings));


        Collections.sort(data, Meeting.endComparator);
        scheduled_meetings.clear();
        System.out.println("Rank by End: " + Greedy_Algorithm(data, scheduled_meetings));

    }
}