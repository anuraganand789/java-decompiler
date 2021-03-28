package cpinfo;

public final class Utf8Info implements ConstantPoolInfo{
    private final int    tag = 1;
    private final String utf8Value;

    public Utf8Info(final String utf8Value){
        this.utf8Value = utf8Value;
    }

    public String utf8Value() { return this.utf8Value; }

    @Override
    public int tag() { return this.tag; }

    @Override
    public String value() { return this.toString(); }

    @Override
    public String toString(){
        return String.format("UTF8 %s %s %n", ConstantPoolInfo.SPACE_PADDING, utf8Value);
    }
}

