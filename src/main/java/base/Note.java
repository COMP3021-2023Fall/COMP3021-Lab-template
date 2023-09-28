package base;

import java.util.*;

public class Note implements Comparable<Note> {
    private Date date;
    private String title;

    public Note (String title) {
        this.title = title;
        this.date = new Date(System.currentTimeMillis());
    }

    public String getTitle() {
        return title;
    }

    public boolean equals(Note note) {
        return Objects.equals(this.title, note.title);
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(title, note.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public int compareTo(Note o) {
        return o.date.compareTo(this.date);
    }

    @Override
    public String toString() {
        return date.toString() + "\t" + title;
    }
}
