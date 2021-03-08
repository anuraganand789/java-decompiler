package com.refactoredcodes.info.attribute;

public record(short attributeNameIndex,
              int   attributeLength,
	      short maxStack,
	      short maxLocals,
	      int   codeLength,
	      byte[] code,
	      short exceptionTableLength,
	      ExceptionTable[] exceptionsTable,
	      short attributesCount,
	      AttributeInfo[] attributesInfo) ;

