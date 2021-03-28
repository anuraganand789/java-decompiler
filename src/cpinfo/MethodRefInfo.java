package cpinfo;

public final class MethodRefInfo implements ConstantPoolInfo{
    private final int tag = 10;
    private final int classIndex;
    private final int nameAndTypeIndex;

    public MethodRefInfo(final int classIndex, 
                         final int nameAndTypeIndex){
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    public int classIndex() { 
        return  this.classIndex; 
    }

    public int nameAndTypeIndex() { 
        return this.nameAndTypeIndex; 
    } 

    @Override
    public int tag(){ 
        return this.tag; 
    } 

    @Override
    public String value(){ 
        return this.toString(); 
    }

    @Override
    public String toString(){
        return String.format("MethodRef %s #%d.#%d %n", 
                              ConstantPoolInfo.SPACE_PADDING, 
                              classIndex, 
                              nameAndTypeIndex);
    }

}
