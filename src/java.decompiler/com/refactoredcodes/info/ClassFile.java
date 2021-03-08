package com.refactoredcodes.info;

import java.util.List;
import java.util.ArrayList;

public ClassFile{
    private short                 minorVersion;
    private short                 majorVersion;
    private short                 accessFlags;
    private short                 thisClass;
    private short                 superClass;

    private int                   magic;

    private List<Method>          listOfMethods;
    private List<Interface>       listOfInterfaces;
    private List<FieldInfo>       listOfFieldInfos;
    private List<Attribute>       listOfAttributes;
    private List<ConstantPool>    listOfConstantPools;

    public ClassFile(){
        listOfMethods       = new ArrayList<>();
        listOfInterfaces    = new ArrayList<>();
        listOfAttributes    = new ArrayList<>();
        listOfFieldInfos    = new ArrayList<>();
        listOfConstantPools = new ArrayList<>();
    }

    public void setMagic(final int magic) { 
        this.magic = magic; 
    }

    public void setMinorVersion(final short minorVersion) { 
        this.minorVersion = minorVersion; 
    }

    public void setMajorVersion(final short majorVersion){
        this.majorVersion = majorVersion;
    }

    public void setAccessFlags(final short accessFlags){
        this.accessFlags = accessFlags;
    }

    public void setThisClass(final short thisClass){
        this.thisClass = thisClass;
    }

    public void setSuperClass(final short superClass){
        this.superClass = superClass;
    }

    public void setListOfMethods(final List<Method> listOfMethods){
        this.listOfMethods = listOfMethods;
    }

    public void setListOfIntefaces(final List<Interface> listOfInterfaces){
        this.listOfInterfaces = listOfInterfaces;
    }

    public void setListOfConstantPools(final List<ConstantPool> listOfConstantPools){
        this.listOfConstantPools  = listOfConstantPools;
    }

   public void setListOfAttributes(final List<Attribute> listOfAttributes){
       this.listOfAttributes = listOfAttributes;
   }

   public void setListOfFieldInfoss(final List<FieldInfo> listOfFieldInfos){
       this.listOfFieldInfos = listOfFieldsInfos;
   }

}
