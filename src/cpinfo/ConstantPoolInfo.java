package cpinfo;

public interface ConstantPoolInfo{
    static String SPACE_PADDING =  " ".repeat(10);
    int tag();
    String value();
    String utf8();
}
