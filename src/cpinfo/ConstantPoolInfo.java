package cpinfo;

public interface ConstantPoolInfo{
    static String SPACE_PADDING =  " ".repeat(20);
    int tag();
    String value();
}
