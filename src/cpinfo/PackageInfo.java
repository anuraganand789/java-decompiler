package cpinfo;

public class PackageInfo implements ConstantPoolInfo{
    private final int tag = 20;
    private final int nameIndex;

    public PackageInfo(final int nameIndex){
        this.nameIndex = nameIndex;
    }

    public int nameIndex(){ 
        return nameIndex; 
    }

    @Override
    public int tag() { 
        return tag; 
    }

    @Override
    public String value(){ 
        return toString(); 
    }

    @Override
    public String toString(){
        return String.format("Package %s #%d %n", 
                              ConstantPoolInfo.SPACE_PADDING,
                              nameIndex);
    }

}

