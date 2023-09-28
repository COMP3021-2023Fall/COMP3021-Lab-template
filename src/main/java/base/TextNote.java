package base;

public class TextNote extends Note {
    private String content;

    public TextNote(String title) {
        super(title);
        this.content = null;
    }

    public TextNote(String title, String content) {
        super(title);
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        String s = super.toString();
        if (this.content == null || !content.contains(".")) {
            return s;
        }
        return s + "\t" + content.substring(0, Math.min(30, content.indexOf(".")));
    }
}
