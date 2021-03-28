package cpinfo;

public class MethodRefInfo implements ConstantPoolInfo{
    private int tag;
    private int classIndex;
    private int nameAndTypeIndex;

    public MethodRefInfo(final int tag, 
                         final int classIndex, 
                         final int nameAndTypeIndex){
        this.tag        = tag;
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
