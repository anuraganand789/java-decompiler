package com.refactoredcodes.info;

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

