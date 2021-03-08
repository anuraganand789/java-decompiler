package com.refactoredcodes.info.attribute;

public record InnerClassAttribute( short attributeNameIndex,
                                    int   attributeLength,
				    Classes[] classes);
