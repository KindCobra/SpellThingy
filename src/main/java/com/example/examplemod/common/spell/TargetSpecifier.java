package com.example.examplemod.common.spell;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;

import java.util.Collections;
import java.util.List;

public abstract class TargetSpecifier extends ParseableSpellComponent {

    public TargetSpecifier(List<String> tokens) {
        super(tokens);
    }

    public List<Entity> getTargetedEntities(ServerPlayer caster) {
        return Collections.emptyList();
    }

    public List<Block> getTargetedBlocks(ServerPlayer caster) {
        return Collections.emptyList();
    }
}
