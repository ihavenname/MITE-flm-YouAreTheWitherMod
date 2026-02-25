package com.ZXiaosMod.keybind;

import net.minecraft.*;
import org.lwjgl.input.Keyboard;
import moddedmite.rustedironcore.api.event.listener.IKeybindingListener;
import moddedmite.rustedironcore.api.event.listener.ITickListener;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.util.function.Consumer;

@Environment(EnvType.CLIENT)
public class GLBKeys implements IKeybindingListener, ITickListener {

    private final Minecraft mc;

    public static final GLBKeys INSTANCE = new GLBKeys(Minecraft.getMinecraft());

    public static KeyBinding keyG;
    public static KeyBinding keyL;
    public static KeyBinding keyB;
    public static KeyBinding keyV;

    private GLBKeys(Minecraft mc) {
        this.mc = mc;
    }

    @Override
    public void onKeybindingRegister(Consumer<KeyBinding> registry) {
        keyG = new KeyBinding("key.glb.g_action", Keyboard.KEY_G);
        keyL = new KeyBinding("key.glb.l_action", Keyboard.KEY_L);
        keyB = new KeyBinding("key.glb.b_action", Keyboard.KEY_B);
        keyV = new KeyBinding("key.glb.v_action", Keyboard.KEY_V);

        registry.accept(keyG);
        registry.accept(keyL);
        registry.accept(keyB);
        registry.accept(keyV);
    }

    private long lastHealPacketTime = 0;
    private static final long HEAL_PACKET_COOLDOWN = 1000;

    @Override
    public void onClientTick(Minecraft client) {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc == null || mc.thePlayer == null) return;
        NetClientHandler handler = mc.thePlayer.sendQueue;
        if (handler == null) return;

        if (GLBKeys.keyG.isPressed()) {
            EntityClientPlayerMP player = Minecraft.getClientPlayer();
            player.sendQueue.addToSendQueue(new Packet19EntityAction(player, 10086));
        }

        if (GLBKeys.keyL.pressed) {
            EntityClientPlayerMP player = Minecraft.getClientPlayer();
            long now = System.currentTimeMillis();
            if (now - lastHealPacketTime >= HEAL_PACKET_COOLDOWN) {
                player.sendQueue.addToSendQueue(new Packet19EntityAction(player, 10085));
                lastHealPacketTime = now;
            }
        }

        if (GLBKeys.keyB.isPressed()) {
            EntityClientPlayerMP player = Minecraft.getClientPlayer();
            player.sendQueue.addToSendQueue(new Packet19EntityAction(player, 10084));
        }

        if (GLBKeys.keyV.isPressed()) {
            EntityClientPlayerMP player = Minecraft.getClientPlayer();
            player.sendQueue.addToSendQueue(new Packet19EntityAction(player, 10083));
        }
    }

    private void handleKey(KeyBinding key, boolean lastState, EntityPlayer player, Consumer<EntityPlayer> action) {
        if (key == null) return;
        boolean pressed = key.pressed;
        if (pressed && !lastState) {
            action.accept(player);
        }
    }
}