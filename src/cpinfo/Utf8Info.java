package cpinfo;

public final class Utf8Info implements ConstantPoolInfo{
    private final int    tag = 1;
    private final String utf8Value;

    public Utf8Info(final String utf8Value){
        this.utf8Value = utf8Value;
    }

    public String utf8Value() { return utf8Value; }

    @Override public String utf8() { return utf8Value; }

    @Override
    public int tag() { return tag; }

    @Override
    public String value() { return toString(); }

    @Override
    public String toString(){
        return 
        String.format("%-19s %s", 
                      "UTF8",
                      utf8Value);
    }
}

