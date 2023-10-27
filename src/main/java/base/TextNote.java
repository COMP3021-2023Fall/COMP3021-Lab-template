package base;

import java.io.*;
import java.util.HashMap;


public class TextNote extends Note implements Serializable, Iconifiable {
    private String content;

    public TextNote (String title) {
        super(title);
        this.content = null;
    }

    public TextNote (File f) {
        super(f.getName());
        this.content = getTextFromFile(f.getAbsolutePath());
    }

    public TextNote (String title, String content) {
        super(title);
        this.content = content;
    }

    public TextNote (TextNote note) {
        super(note);
        this.content = note.content;
    }

    public String getContent() {
        return content;
    }

    private String getTextFromFile(String absolutePath) {
        String result = "";
        FileInputStream fis;
        InputStreamReader in;
        BufferedReader reader;
        try {
            fis = new FileInputStream(absolutePath);
            in = new InputStreamReader(fis);
            reader = new BufferedReader(in);
            result = reader.readLine();
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void exportTextToFile(String pathFolder) {
        FileWriter fw;
        BufferedWriter out;
        try {
            File file = new File( pathFolder + File.separator + this.getTitle().replaceAll(" ", "_") + ".txt");
            fw = new FileWriter(file);
            out = new BufferedWriter(fw);
            out.write(this.getContent());
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        String s = "TextNote: " + super.toString();
        if (this.content == null || !content.contains(".")) {
            return s;
        }
        return s + "\t" + content.substring(0, Math.min(30, content.indexOf(".")));
    }

    @Override
    public void iconify() {
        char firstCharacter = content.charAt(0);
        if ('a' <= firstCharacter && firstCharacter <= 'z') {
            content = new IconLowerCase(firstCharacter).base + content.substring(1);
        } else if ('A' <= firstCharacter && firstCharacter <= 'Z') {
            content = new IconUpperCase(firstCharacter).base + content.substring(1);
        } else if ('0' <= firstCharacter && firstCharacter <= '9') {
            content = new IconDigit(firstCharacter).base + content.substring(1);
        }
    }

    public Character unknownFunction(){
        HashMap<Character,Integer> count = new HashMap<Character,Integer>();
        String a = this.getTitle() + this.getContent();
        int b = 0;
        Character r = ' ';
        for (int i = 0; i < a.length(); i++) {
            Character c = a.charAt(i);
            if (c <= 'Z' && c >= 'A' || c <= 'z' && c >= 'a') {
                if (!count.containsKey(c)) {
                    count.put(c, 1);
                } else {
                    count.put(c, count.get(c) + 1);
                    if (count.get(c) > b) {
                        b = count.get(c);
                        r = c;
                    }
                }
            }
        }
        return r;
    }
}
