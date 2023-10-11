package base;

import java.io.Serializable;
import java.util.*;

public class Note implements Comparable<Note>, Serializable {
    private Date date;
    private String title;

    public Note (String title) {
        this.title = title;
        this.date = new Date(System.currentTimeMillis());
    }

    public Note (Note note) {
        this.title = note.title;
        this.date = note.date;
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
        if (this instanceof TextNote && o instanceof ImageNote) return -1;
        if (this instanceof ImageNote && o instanceof TextNote) return 1;
        return this.title.compareTo(o.title);
    }

    @Override
    public String toString() {
        return date.toString() + "\t" + title;
    }
}
