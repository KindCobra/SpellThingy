package com.example.examplemod.common.spell;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

/**
 * Part of a Spell that describes the <i>effect</i> to be applied to the target (e.g. "apply potion effect", "move").
 *
 * @see Spell
 * @see Target
 */
public abstract class Effect {

    protected Logger logger = LogUtils.getLogger();

    /**
     * Check if the supplied target type is suitable for this effect.
     *
     * @param target the target type to check
     * @return {@code true} if this effect produces a result when applied to the specified target type, {@code false} otherwise.
     */
    public abstract boolean isTargetSuitable(Target target);

    public abstract String getName();
}
