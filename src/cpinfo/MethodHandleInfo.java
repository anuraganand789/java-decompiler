package cpinfo;

import classfile.ClassFile;
import constants.ReferenceKind;

public record MethodHandleInfo(int referenceKind,
                               int referenceIndex,
                               ClassFile classFile) implements ConstantPoolInfo{

    @Override 
    public String utf8() { 
        return 
        String.format("%s %s", 
                      ReferenceKind.getKind(referenceKind).toString() ,
                      classFile.constantPool[referenceIndex].utf8()
                      );
    }

    @Override public int tag() { return 15; }

    @Override public String value() { return toString(); }

    @Override
    public String toString() { 
         return String.format("%-19s %-15s", 
                              "MethodHandle",
                              String.format("#%d.#%d", referenceKind, referenceIndex)
                              ); 
    }
}
