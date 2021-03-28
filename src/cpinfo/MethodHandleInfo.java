package cpinfo;

public class MethodHandleInfo implements ConstantPoolInfo{
    private final int tag;
    private final int referenceKind;
    private final int referenceIndex;

    public MethodHandleInfo(final int tag, final int referenceKind, final int referenceIndex){
        this.tag = tag;
        this.referenceKind = referenceKind;
        this.referenceIndex = referenceIndex;
    }

    public int referenceKind(){ return this.referenceKind; }
    public int referenceIndex() { return this.referenceIndex; }

    @Override
    public int tag() { return this.tag; }

    @Override
    public String value() { return this.toString(); }

    @Override
    public String toString() { return String.format("MethodHandle %s #%d.#%d %n", ConstantPoolInfo.SPACE_PADDING, referenceKind, referenceIndex); }
}
