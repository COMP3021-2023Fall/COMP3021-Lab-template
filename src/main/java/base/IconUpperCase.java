package base;

public class IconUpperCase extends Icon {
    public IconUpperCase(char base) {
        super(base);
    }

    @Override
    char encircle(char c) {
        return (char) (c - 'A' + '\u24B6');
    }
}
