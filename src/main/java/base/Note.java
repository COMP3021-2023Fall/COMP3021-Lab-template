package base;

import java.util.Date;
import java.util.Objects;

public class Note {
    private Date date;
    private String title;

    public Note (String title) {
        this.title = title;
        this.date = new Date();
    }

    public String getTitle() {
        return title;
    }

    public boolean equals(Note note) {
        return Objects.equals(this.title, note.title);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(title, note.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
