package cpinfo;

public sealed interface ConstantPoolInfo
    permits 
    ClassInfo ,  DoubleInfo       ,  DynamicInfo             , FieldRefInfo      ,
    FloatInfo ,  IntegerInfo      ,  InterfaceMethodRefInfo  , InvokeDynamicInfo , 
    LongInfo  ,  MethodHandleInfo ,  MethodRefInfo           , MethodTypeInfo    ,
    ModuleInfo,  NameAndTypeInfo  ,  PackageInfo             , StringInfo        , 
    Utf8Info  {

    String SPACE_PADDING =  " ".repeat(10);
    int tag();
    String value();
    String utf8();

}
