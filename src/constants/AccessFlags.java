package constants;

public enum AccessFlags{
    ACC_PUBLIC     (0x0001, "0x0001", "public"), 
    ACC_FINAL      (0x0010, "0x0010", "final"), 
    ACC_SUPER      (0x0020, "0x0020", "superclass"),
    ACC_INTERFACE  (0x0200, "0x0200", "interface"),
    ACC_ABSTRACT   (0x0400, "0x0400", "abstract"),
    ACC_SYNTHETIC  (0x1000, "0x1000", "synthetic"), 
    ACC_ANNOTATION (0x2000, "0x2000", "annotation"),
    ACC_ENUM       (0x4000, "0x4000", "enum"),
    ACC_MODULE     (0x8000, "0x8000", "module");
    
    AccessFlags(final int hexCode, 
                final String hexCodeString, 
                final String javaName){
        this.hexCode        = hexCode;
        this.hexCodeString  = hexCodeString;
        this.javaName       = javaName;
    }

    public final int    hexCode;
    public final String hexCodeString;
    public final String javaName;

    public static String accessFlags(final int accessCode){
        final AccessFlags[] accessFlags = AccessFlags.values();
        final StringBuilder accessStrings = new StringBuilder(6 * accessFlags.length);
        int tempAccessCode = accessCode;
        for(AccessFlags accessFlag : accessFlags){
           //tempaccesscode -> 010011
           //hexCode        -> 000001
           //&              -> 000001
           //tempAccessCode =  010011 & ~hexCode(000001 -> 111110) 
           //=> tempAccessCode = 010011 & 111110 
           //=> tempAccessCode = 010010

           if((tempAccessCode & accessFlag.hexCode) != 0){
               accessStrings.append(accessFlag).append(" ");
               tempAccessCode = tempAccessCode & ~accessFlag.hexCode;
           }
        }

        return accessStrings.toString();
    }
}
