package cpinfo;

public record Utf8Info(String utf8Value) implements ConstantPoolInfo{

    @Override public String utf8() { return utf8Value; }

    @Override
    public int tag() { return 1; }

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

