package com.example.examplemod.common.spell;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class BasicParser {
    private static final Logger logger = LogUtils.getLogger();

    private static final Map<String, Class<?>> components = new HashMap<>();

    /**
     *
     * @param tokens the input token stream
     * @param componentClass the class of component to parse (e.g. Effect, TargetSpecifier, PushEffect)
     * @return {@code null}, if <ul>
     *     <li>the token stream is empty</li>
     *     <li>the token at the front of the token stream is not a valid component word</li>
     *     <li>the class of the component linked to the token is not assignable to the given componentClass</li>
     * </ul>
     * @param <T> the class of component to parse (e.g. Effect, TargetSpecifier, PushEffect)
     */
    private static <T> T parseComponent(List<String> tokens, Class<T> componentClass) throws SpellParsingException {
        if (tokens.size() == 0 || !components.containsKey(tokens.get(0))) {
            return null;
        }

        Class<? extends T> wordClass;
        try {
            wordClass = components.get(tokens.get(0)).asSubclass(componentClass);
        } catch (ClassCastException e) {
            return null;
        }

        try {
            tokens.remove(0);

            return wordClass.getConstructor(List.class).newInstance(tokens);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            logger.error("Class {} registered as a spell effect, but does not have a public Effect(List<String> tokens) constructor.", wordClass.getCanonicalName());
            throw new SpellParsingException(wordClass.getCanonicalName() + " registered as a spell effect, but does not have a public Effect(List<String> tokens) constructor.", e);
        } catch (InstantiationException e) {
            logger.error(String.join(", ", tokens) + ": could not parse as a spell effect, encountered exception.", e);
            return null;
        }
    }

    public static Effect parseEffect(String spell) throws SpellParsingException {
        return parseComponent(new ArrayList(Arrays.asList(spell.split(" "))), Effect.class);
    }

    public static TargetSpecifier parseTargetSpecifier(List<String> tokens) throws SpellParsingException {
        return parseComponent(tokens, TargetSpecifier.class);
    }

    public static void registerSpellComponent(String word, Class<?> component) {
        components.put(word, component);
    }
}
