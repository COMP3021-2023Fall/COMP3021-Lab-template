package base;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Folder {
    private ArrayList<Note> notes;
    private String name;

    public Folder (String name) {
        this.name = name;
        this.notes = new ArrayList<>();
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public String getName() {
        return name;
    }

    public void addNote(Note note) {
        notes.add(note);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Folder folder = (Folder) o;
        return Objects.equals(name, folder.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        int nText = 0;
        int nImage= 0;

        for (Note note : notes) {
            if (note instanceof ImageNote) {
                nImage++;
            } else if (note instanceof TextNote) {
                nText++;
            }
        }

        return name + ':' + nText + ':' + nImage;
    }
}
