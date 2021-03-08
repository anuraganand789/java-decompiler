package com.refactoredcodes.info.attribute;

public record StackMapTableAttribute(short attributeNameIndex,
                                     int   attributeLength,
				     StackMapFrame[] stackMapFrames);
