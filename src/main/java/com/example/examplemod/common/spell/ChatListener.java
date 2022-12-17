package com.example.examplemod.common.spell;

import com.example.examplemod.ExampleMod;
import com.mojang.logging.LogUtils;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod.EventBusSubscriber(modid = ExampleMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ChatListener {
    private static final Logger logger = LogUtils.getLogger();

    @SubscribeEvent
    public static void onChatSent(ServerChatEvent.Submitted event) {
        try {
            Effect spell = BasicParser.parseEffect(event.getMessage().getString());
            logger.info("Parsing succeeded: {}", spell);
            spell.execute(event.getPlayer());
        } catch (SpellParsingException e) {
            logger.info("Parsing failed", e);
        }
    }
}
