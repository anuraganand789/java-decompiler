package cpinfo;

public final class ClassInfo implements ConstantPoolInfo{
    private final int tag = 7;
    private final int nameIndex;

    public ClassInfo(final int nameIndex){
        this.nameIndex = nameIndex;
    }

    public int nameIndex() { 
        return this.nameIndex;
    }

    @Override
    public int tag() {
        return this.tag;
    }

    @Override
    public String value(){
        return this.toString();
    }

    @Override
    public String toString(){
        return String.format("Class %s %d.%n", 
                          ConstantPoolInfo.SPACE_PADDING
                          );
    }
}
