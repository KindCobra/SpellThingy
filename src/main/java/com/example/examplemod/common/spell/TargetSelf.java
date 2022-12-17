package com.example.examplemod.common.spell;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.Collections;
import java.util.List;

public class TargetSelf extends TargetSpecifier {
    public TargetSelf(List<String> tokens) {
        super(tokens);
    }

    @Override
    public List<Entity> getTargetedEntities(ServerPlayer caster) {
        return Collections.singletonList(caster);
    }

    @Override
    public String toString() {
        return "Self";
    }
}
