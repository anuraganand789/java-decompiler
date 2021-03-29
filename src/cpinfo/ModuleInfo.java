package cpinfo;

public class ModuleInfo implements ConstantPoolInfo{
    private final int tag = 19;
    private final int nameIndex;

    public ModuleInfo(final int nameIndex){
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
        return String.format("Module %s #%d %n", 
                              ConstantPoolInfo.SPACE_PADDING,
                              nameIndex);
    }

}
