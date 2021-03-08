package com.refactoredcodes.info.attribute;

public record ExceptionsAttribute(short attributeNameIndex,
                                  int   attributeLength,
				  short numberOfExceptions,
				  short[] exceptionIndexTable);
