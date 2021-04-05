package cpinfo;

import classfile.ClassFile;

public class ModuleInfo implements ConstantPoolInfo{
    private final int tag = 19;
    private final int nameIndex;
    private final ClassFile classFile;

    public ModuleInfo(final int nameIndex,
                      final ClassFile classFile){
        this.nameIndex = nameIndex;
        this.classFile = classFile;
    }

    public int nameIndex(){ return nameIndex; }

    @Override
    public String utf8(){ return classFile.constantPool[nameIndex].utf8(); }

    @Override public int tag() { return tag; }

    @Override public String value(){ return toString(); }

    @Override
    public String toString(){
        return 
        String.format("%-19s #%d", 
                      "Module",
                      nameIndex);
    }

}
