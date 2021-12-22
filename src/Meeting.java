import java.util.Comparator;

/*
 * Very simple class for use in the task-scheduling problem.
 * A task includes a name, a start time, and an end time.
 *
 * In Lab 9 this class is being used to represent "meeting requests".
 *
 * IF YOU MAKE ANY CHANGES TO THIS FILE, PLEASE ADD YOUR NAME/ID AND
 * A COMMENT WITH A SUMMARY OF THE CHANGES YOU HAVE MADE.
 *
 */
public class Meeting {
    private String name;
    private Integer start;
    private Integer end;

    /*
     * Simple constructor to set all three member values.
     */
    public Meeting(String n, Integer s, Integer e) {
        name = n;
        start = s;
        end = e;
    }

    /*
     * Three basic getters.
     */
    public String getName() {
        return name;
    }

    public Integer getStart() {
        return start;
    }

    public Integer getEnd() {
        return end;
    }

    /*
     * A useful helper function to return the length of a meeting, defined as end
     * time minus start time.
     */
    public Integer getLength() {
        return end - start;
    }

    /*
     * This was helpful while I was testing/debugging. It's not needed for any of
     * the official output requirements of the lab.
     */
    public String toString() {
        return name + "[" + start + "," + end + "]";
    }

    /*
     * Check whether this meeting conflicts with another one.
     */
    public boolean overlapsWith(Meeting otherMeeting) {
        return !((start >= otherMeeting.getEnd()) || (otherMeeting.getStart() >= end));
    }

    //Comparator for sorting the list by start
    public static Comparator<Meeting> startComparator = new Comparator<Meeting>() {
        public int compare(Meeting s1, Meeting s2) {
            return s1.start - s2.start;
        }
    };

    //Comparator for sorting the list by end
    public static Comparator<Meeting> endComparator = new Comparator<Meeting>() {
        public int compare(Meeting s1, Meeting s2) {
            return s1.end - s2.end;
        }
    };

    //Comparator for sorting the list by length
    public static Comparator<Meeting> lengthComparator = new Comparator<Meeting>() {
        public int compare(Meeting s1, Meeting s2) {
            return (s1.end - s1.start) - (s2.end - s2.start);
        }
    };
}
