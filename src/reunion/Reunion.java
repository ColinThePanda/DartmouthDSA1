package reunion;

import java.util.Objects;

public class Reunion {
    int maxAttendees;
    int currentAttendees;
    Attendee[] attendees;

    public Reunion(int maxAttendees) {
        attendees = new Attendee[maxAttendees];
        this.maxAttendees = maxAttendees;
        this.currentAttendees = 0;
    }

    public void addAttendee(Attendee attendee) {
        if (this.currentAttendees + 1 > maxAttendees) {
            System.out.printf("Unable to accomodate %s, max attendees is %d\n", attendee, this.maxAttendees);
            return;
        }
        this.attendees[currentAttendees] = attendee;
        this.currentAttendees += 1;
        System.out.printf("Added %s\n", attendee);
    }

    public void rsvp(String name, boolean rsvp) {
        for (int i = 0; i < this.currentAttendees; i += 1) {
            if (Objects.equals(this.attendees[i].name, name)) {
                this.attendees[i].rsvp = rsvp;
                return;
            }
        }
        System.out.printf("%s not found\n", name);
    }

    public String toString() {
        String outString = "";
        outString += "Reunion attendees that have RSVP:\n";
        for (int i = 0; i < this.currentAttendees; i += 1) {
            if (this.attendees[i].rsvp) {
                outString += "    " + this.attendees[i] + "\n";
            }
        }
        outString += "\n" + "Reunion attendees that have NOT RSVP:\n";
        for (int i = 0; i < this.currentAttendees; i += 1) {
            if (!this.attendees[i].rsvp) {
                outString += "    " + this.attendees[i] + "\n";
            }
        }
        return outString;
    }

    public static void main(String[] args) {
        Reunion r = new Reunion(5);
        r.addAttendee(new Attendee("Alice", true));
        r.addAttendee(new Attendee("Bob", false));
        r.addAttendee(new Graduate("Charlie", true, 22, "Government"));
        r.addAttendee(new Graduate("Denise", false, 21, "Econ"));
        r.addAttendee(new Graduate("Elvis", true, 23, "Computer Science"));
        r.addAttendee(new Graduate("Falcon", false, 26, "Biology"));
        System.out.println(r);
        System.out.println();

        System.out.println("Update rsvp status");
        r.rsvp("George", false);  //print not found
        r.rsvp("Bob", true);  //update
        System.out.println(r);
    }
}