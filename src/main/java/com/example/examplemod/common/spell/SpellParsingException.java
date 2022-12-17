package com.example.examplemod.common.spell;

public class SpellParsingException extends Exception {
    public SpellParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpellParsingException(String message) {
        super(message);
    }
}
