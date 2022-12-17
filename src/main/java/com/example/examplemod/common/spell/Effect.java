package com.example.examplemod.common.spell;

import com.mojang.logging.LogUtils;
import net.minecraft.server.level.ServerPlayer;
import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;

import java.util.List;

/**
 * Part of a Spell that describes the <i>effect</i> to be applied to the target (e.g. "apply potion effect", "move").
 *
 * @see Spell
 * @see TargetSpecifier
 */
public abstract class Effect extends ParseableSpellComponent {
    protected Logger logger = LogUtils.getLogger();

    public Effect(List<String> tokens) throws SpellParsingException {
        super(tokens);
    }

    public abstract void execute(ServerPlayer caster);

    public abstract String getName();

    @Override
    public String toString() {
        return getName();
    }
}
