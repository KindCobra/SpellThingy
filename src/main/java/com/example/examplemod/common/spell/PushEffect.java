package com.example.examplemod.common.spell;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class PushEffect extends Effect {
    private final TargetSpecifier target;

    private Vec3 strength;

    public PushEffect(List<String> tokens) throws SpellParsingException {
        super(tokens);

        target = BasicParser.parseTargetSpecifier(tokens);

        if (target == null) {
            throw new SpellParsingException("Target could not be parsed");
        }

        strength = new Vec3(2.5, 2.5, 2.5);

        try {
            final double x = Double.parseDouble(tokens.get(0));
            tokens.remove(0);
            strength = new Vec3(x, x, x);

            final double y = Double.parseDouble(tokens.get(0));
            tokens.remove(0);
            strength = new Vec3(x, y, 0);

            final double z = Double.parseDouble(tokens.get(0));
            tokens.remove(0);
            strength = new Vec3(x, y, z);
        } catch (IndexOutOfBoundsException | NumberFormatException ignored) {}
    }

    @Override
    public void execute(ServerPlayer caster) {
        logger.info("Executing {} for player {}", this, caster.getName());

        List<Entity> targetedEntities = target.getTargetedEntities(caster);

        for (Entity entity : targetedEntities) {
            logger.info("Pushing entity {}", entity);
            Vec3 pushDirection = caster.position().vectorTo(entity.position()).normalize();
            entity.push(pushDirection.x * strength.x, strength.y, pushDirection.z * strength.z);
        }
    }

    @Override
    public String getName() {
        return "Push[" + target.toString() + "]";
    }
}
