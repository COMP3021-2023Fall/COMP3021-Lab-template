package base;

public class IconDigit extends Icon {
    public IconDigit(char base) {
        super(base);
    }

    @Override
    char encircle(char c) {
        return (char) (c - '0' + '\u2460');
    }
}
