package base;

import java.util.*;
import java.io.*;

public class NoteBook implements Serializable {
    private ArrayList<Folder> folders;
    private static final long serialVersionUID = 1L;

    public NoteBook() {
        this.folders = new ArrayList<>();
    }

    public NoteBook(String file) {
        FileInputStream fis;
        ObjectInputStream in;
        try {
            fis = new FileInputStream(file);
            in = new ObjectInputStream(fis);
            NoteBook n = (NoteBook) in.readObject();
            this.folders = n.folders;
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public boolean createNote(String folderName, String title) {
        return createImageNote(folderName, title);
    }

    public boolean createNote(String folderName, String title, String content) {
        return createTextNote(folderName, title, content);
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

    public boolean save(String file){
        FileOutputStream fos;
        ObjectOutputStream out;
        try {
            fos = new FileOutputStream(file);
            out = new ObjectOutputStream(fos);
            out.writeObject(this);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
