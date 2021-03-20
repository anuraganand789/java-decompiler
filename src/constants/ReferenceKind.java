package constants;

public enum ReferenceKind{
    REF_GETFIELD(1, "GETFIELD"),
    REF_GETSTATIC(2, "GETSTATIC"),
    REF_PUTFIELD(3, "PUTFIELD"),
    REF_PUTSTATIC(4, "PUTSTATIC"),
    REF_INVOKEVIRTUAL(5, "INVOKEVIRTUAL"),
    REF_INVOKESTATIC(6, "INVOKESTATIC"),
    REF_INVOKESPECIAL(7, "INVOKESPECIAL"),
    REF_NEWINVOKESPECIAL(8, "NEWINVOKESPECIAL"),
    REF_INVOKEINTERFACE(9, "INVOKEINTERFACE");
    
    private int kind;
    private String name;
    ReferenceKind(final int kind, final String name) { 
        this.kind = kind; 
        this.name = name;
    }

    public static ReferenceKind getKind(final int kind){
    return switch(kind){
            case 1 -> REF_GETFIELD;
            case 2 -> REF_GETSTATIC;
            case 3 -> REF_PUTFIELD;
            case 4 -> REF_PUTSTATIC;
            case 5 -> REF_INVOKEVIRTUAL;
            case 6 -> REF_INVOKESTATIC;
            case 7 -> REF_INVOKESPECIAL;
            case 8 -> REF_NEWINVOKESPECIAL;
            case 9 -> REF_INVOKEINTERFACE;
            default -> throw new IllegalArgumentException(String.format("%d is invalid Reference Kind" , kind));
        };
    }

    @Override
    public String toString() { return this.name; }

}
