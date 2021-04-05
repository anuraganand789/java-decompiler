package cpinfo;

import classfile.ClassFile;
import constants.ReferenceKind;

public class MethodHandleInfo implements ConstantPoolInfo{
    private final int tag = 15;
    private final int referenceKind;
    private final int referenceIndex;
    private final ClassFile classFile;

    public MethodHandleInfo(final int referenceKind, 
                            final int referenceIndex,
                            final ClassFile classFile){
        this.referenceKind = referenceKind;
        this.referenceIndex = referenceIndex;
        this.classFile = classFile;
    }

    public int referenceKind(){ return this.referenceKind; }

    public int referenceIndex() { return this.referenceIndex; }

    @Override 
    public String utf8() { 
        return 
        String.format("%s %s", 
                      ReferenceKind.getKind(referenceKind).toString() ,
                      classFile.constantPool[referenceIndex].utf8()
                      );
    }

    @Override
    public int tag() { 
        return tag; 
    }

    @Override
    public String value() { 
        return toString(); 
    }

    @Override
    public String toString() { 
         return String.format("%-19s %-15s", 
                              "MethodHandle",
                              String.format("#%d.#%d", referenceKind, referenceIndex)
                              ); 
    }
}
