package base;

public abstract class Icon {
    public char base;

    public Icon(char base){
        this.base = encircle(base);
    }
    abstract char encircle(char c);
    @Override
    public String toString() {
        return ""+base;
    }
}

