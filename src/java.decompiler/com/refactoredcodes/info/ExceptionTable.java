package com.refactoredcodes.info;

public record ExceptionTable(short startPC,
                             short endPC,
			     short handlerPC,
			     short catchType);
