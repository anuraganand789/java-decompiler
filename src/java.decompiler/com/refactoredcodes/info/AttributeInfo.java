package com.refactoredcodes.info;

public record AttributeInfo(short   attributeNameIndex, 
                            int     attributeLength,
			    byte[]  info);
