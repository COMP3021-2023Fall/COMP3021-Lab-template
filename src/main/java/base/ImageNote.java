package base;

import java.io.File;

public class ImageNote extends Note implements Cloneable {
    File image;
    Icon icon;

    public ImageNote (String title) {
        super(title);
        char firstCharacter = title.charAt(0);
        if ('a' <= firstCharacter && firstCharacter <= 'z') {
            this.icon = new IconLowerCase(firstCharacter);
        } else if ('A' <= firstCharacter && firstCharacter <= 'Z') {
            this.icon = new IconUpperCase(firstCharacter);
        } else if ('0' <= firstCharacter && firstCharacter <= '9') {
            this.icon = new IconDigit(firstCharacter);
        }
    }

    public ImageNote (ImageNote note) {
        super(note);
        this.icon = note.icon;
    }

    @Override
    public String toString() {
        return "ImageNote: " + icon + " " + super.toString();
    }
}
