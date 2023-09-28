package base;

import java.util.*;

public class NoteBook {
    private ArrayList<Folder> folders;

    public NoteBook() {
        this.folders = new ArrayList<>();
    }

    public ArrayList<Folder> getFolders() {
        return folders;
    }

    public boolean insertNote(String folderName, Note note) {
        Folder folder = null;
        for (Folder f : this.folders)
            if (Objects.equals(f.getName(), folderName))
                folder = f;
        if (folder == null) {
            folder = new Folder(folderName);
            folders.add(folder);
        }

        for (Note folderNote : folder.getNotes()) {
            if (folderNote.equals(note)) {
                System.out.println("Creating note " + note.getTitle() + " under folder " + folderName + " failed.");
                return false;
            }
        }
        folder.addNote(note);
        return true;
    }

    public boolean createTextNote(String folderName, String title) {
        TextNote note = new TextNote(title);
        return insertNote(folderName, note);
    }

    public boolean createTextNote(String folderName, String title, String content) {
        TextNote note = new TextNote(title, content);
        return insertNote(folderName, note);
    }

    public boolean createImageNote(String folderName, String title) {
        ImageNote note = new ImageNote(title);
        return insertNote(folderName, note);
    }

    public void sortFolders() {
        for (Folder folder : folders) {
            folder.sortNotes();
        }
        Collections.sort(folders);
    }

    public List<Note> searchNotes(String keywords) {
        List<Note> filteredNotes = new ArrayList<>();
        for (Folder folder : folders) {
            filteredNotes.addAll(folder.searchNotes(keywords));
        }
        return filteredNotes;
    }
}
