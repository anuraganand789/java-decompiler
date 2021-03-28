package cpinfo;

public class PackageInfo implements ConstantPoolInfo{
    private final int tag;
    private final int nameIndex;

    public PackageInfo(final int tag, final int nameIndex){
        this.tag = tag;
        this.nameIndex = nameIndex;
    }

    public int nameIndex(){ return this.nameIndex; }

    @Override
    public int tag() { return this.tag; }

    @Override
    public String value(){ return this.toString(); }

    @Override
    public String toString(){
        return String.format("Package %s #%d %n", 
                              ConstantPoolInfo.SPACE_PADDING,
                              this.nameIndex);
    }

}

