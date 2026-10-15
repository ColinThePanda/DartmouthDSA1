package reunion;

public class Attendee {
    String name;
    boolean rsvp;

    public Attendee(String name, boolean rsvp) {
        this.name = name;
        this.rsvp = rsvp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRsvp(boolean rsvp) {
        this.rsvp = rsvp;
    }

    public String getName() {
        return this.name;
    }

    public boolean getRsvp() {
        return this.rsvp;
    }

    public String toString() {
        return this.name;
    }
}