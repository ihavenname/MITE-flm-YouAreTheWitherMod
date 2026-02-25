package com.ZXiaosMod.server;

import com.ZXiaosMod.api.IMySkillInvoker;
import moddedmite.rustedironcore.api.event.listener.ITickListener;
import net.minecraft.*;
import net.minecraft.server.MinecraftServer;

import java.util.List;

public class ServerTick implements ITickListener {

    public static final ServerTick INSTANCE = new ServerTick();

    private long lastNightVisionTime = 0;
    private static final long NIGHTVISION_COOLDOWN = 1000;

    @Override
    public void onServerTick(MinecraftServer server) {
        long now = System.currentTimeMillis();
        if (now - lastNightVisionTime < NIGHTVISION_COOLDOWN) return;

        ServerConfigurationManager cm = server.getConfigurationManager();
        if (cm == null) return;

        List<ServerPlayer> players = cm.playerEntityList;
        for (ServerPlayer player : players) {
            IMySkillInvoker invoker = (IMySkillInvoker)(Object)player;

            if (invoker.isNightVisionActive()) {
                player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 1200, 0));
            } else {
                if (player.isPotionActive(Potion.nightVision.id)) {
                    player.removePotionEffect(Potion.nightVision.id);
                }
            }
        }

        lastNightVisionTime = now;
    }
}