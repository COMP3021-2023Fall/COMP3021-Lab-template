package base;

import java.io.Serializable;
import java.util.*;

public class Folder implements Comparable<Folder>, Serializable {
    private ArrayList<Note> notes;
    private String name;

    public Folder(String name) {
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

    public void sortNotes() {
        Collections.sort(notes);
    }

    public List<Note> searchNotes(String keywords) {
        List<Note> filteredNotes = new ArrayList<>();

        ArrayList<ArrayList<String>> patterns = new ArrayList<>();

        //process keywords
        String[] keyList = keywords.split(" ");
        int i = 0;
        while (i < keyList.length) {
            if (keyList[i].equalsIgnoreCase("or")) {
                i++;
                patterns.get(patterns.size() - 1).add(keyList[i].toLowerCase());
            } else {
                ArrayList<String> newArr = new ArrayList<>();
                newArr.add(keyList[i].toLowerCase());
                patterns.add(newArr);
            }
            i++;
        }

        for (Note n : notes) {
            String toBeSearched = n.getTitle();
            if (n instanceof TextNote) {
                toBeSearched += ((TextNote) n).getContent();
            }
            toBeSearched = toBeSearched.toLowerCase();

            boolean flag = true;
            for (ArrayList<String> pattern : patterns) {
                boolean flag2 = false;
                for (String oneKey : pattern) {
                    if (toBeSearched.contains(oneKey)) {
                        flag2 = true;
                        break;
                    }
                }
                if (!flag2) {
                    flag = false;
                    break;
                }
            }
            if (flag) filteredNotes.add(n);
        }

        return filteredNotes;
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
        int nImage = 0;

        for (Note note : notes) {
            if (note instanceof ImageNote) {
                nImage++;
            } else if (note instanceof TextNote) {
                nText++;
            }
        }

        return name + ':' + nText + ':' + nImage;
    }

    @Override
    public int compareTo(Folder o) {
        return name.compareTo(o.getName());
    }
}
