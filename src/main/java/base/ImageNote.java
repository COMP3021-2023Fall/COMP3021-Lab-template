package base;

import java.io.File;

public class ImageNote extends Note {
    File image;

    public ImageNote(String title) {
        super(title);
    }

    @Override
    public String toString() {
        return "ImageNote: " + super.toString();
    }
}
