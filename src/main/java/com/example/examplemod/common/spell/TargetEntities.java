package com.example.examplemod.common.spell;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class TargetEntities extends TargetSpecifier {
    public TargetEntities(List<String> tokens) {
        super(tokens);
    }

    @Override
    public List<Entity> getTargetedEntities(ServerPlayer caster) {
        AABB boundingBox = AABB.ofSize(caster.position(), 10, 10, 10);

        return caster.level.getEntities(caster, boundingBox);
    }
}
