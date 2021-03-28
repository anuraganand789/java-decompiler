package cpinfo;

public final class FieldRefInfo implements ConstantPoolInfo{
    private int tag = 9;
    private int classIndex;
    private int nameAndTypeIndex;

    public FieldRefInfo(final int classIndex, 
                        final int nameAndTypeIndex){
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    public int classIndex() {
        return this.classIndex; 
    }

    public int nameAndTypeIndex() { 
        return this.nameAndTypeIndex; 
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
        return String.format("FieldRef %s #%d.#%d %n", 
                              ConstantPoolInfo.SPACE_PADDING, 
                              classIndex, 
                              nameAndTypeIndex);
    }

}
