package base;

import java.io.*;


public class TextNote extends Note implements Serializable {
    private String content;

    public TextNote(String title) {
        super(title);
        this.content = null;
    }

    public TextNote(File f) {
        super(f.getName());
        this.content = getTextFromFile(f.getAbsolutePath());
    }

    public TextNote(String title, String content) {
        super(title);
        this.content = content;
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
}
