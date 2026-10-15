package reunion;

public class Graduate extends Attendee {
    int year;
    String department;

    public Graduate(String name, boolean rsvp, int year, String department) {
        super(name, rsvp);
        this.year = year;
        this.department = department;
    }

    public String toString() {
        return String.format("%s '%d %s", this.name, this.year, this.department);
    }
}
