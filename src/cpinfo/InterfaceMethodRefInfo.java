package cpinfo;

public final class InterfaceMethodRefInfo implements ConstantPoolInfo{
    private final int tag = 11;
    private final int classIndex;
    private final int nameAndTypeIndex;

    public InterfaceMethodRefInfo(final int classIndex, 
                                  final int nameAndTypeIndex){
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    public int classIndex() { 
        return classIndex; 
    }

    public int nameAndTypeIndex() { 
        return nameAndTypeIndex; 
    } 

    @Override
    public int tag(){ 
        return tag; 
    } 
    
    @Override
    public String value(){ 
        return toString(); 
    }

    @Override
    public String toString(){
        return String.format("InterfaceMethodRef %s #%d.#%d %n", 
                              ConstantPoolInfo.SPACE_PADDING, 
                              classIndex, 
                              nameAndTypeIndex);
    }

}
