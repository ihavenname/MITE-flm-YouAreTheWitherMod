package com.ZXiaosMod.mixin.server;

import com.ZXiaosMod.api.IMySkillInvoker;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Random;

@Mixin(ServerPlayer.class)
public abstract class MixinServerPlayer implements IMySkillInvoker {

    @Override
    public void skullSkill() {
        EntityPlayer player = (EntityPlayer)(Object)this;
        World world = player.worldObj;

        Vec3 look = player.getLook(1.0F);
        double spawnX = player.posX + look.xCoord * 2;
        double spawnY = player.posY + look.yCoord * 2;
        double spawnZ = player.posZ + look.zCoord * 2;

        Random rand = world.rand;
        double velX = look.xCoord + rand.nextGaussian() * 0.00;
        double velY = look.yCoord + rand.nextGaussian() * 0.00;
        double velZ = look.zCoord + rand.nextGaussian() * 0.00;

        EntityWitherSkull fireball = new EntityWitherSkull(world, spawnX, spawnY, spawnZ, velX, velY, velZ);
        world.spawnEntityInWorld(fireball);
        world.playAuxSFX(1014, (int)player.posX, (int)player.posY, (int)player.posZ, 0);

        System.out.println("技能：发射头颅");
    }

    private long lastHealTime = 0;
    private final long HEAL_COOLDOWN = 500;

    @Override
    public void healSkill() {
        EntityPlayer player = (EntityPlayer)(Object)this;
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastHealTime >= HEAL_COOLDOWN) {
            player.heal(1.0F);
            lastHealTime = currentTime;
            System.out.println("技能：治疗玩家 1 点血量");
        }
    }

    @Override
    public void boomSkill() {
        EntityPlayer player = (EntityPlayer)(Object)this;
        World world = player.worldObj;
        world.createExplosion(player, player.posX, player.posY, player.posZ, 2.0F, 4.0F, true);
        System.out.println("技能：玩家爆炸");
    }

    private boolean nightVisionActive = false;

    @Override
    public void nightVisionSkill() {
        nightVisionActive = !nightVisionActive;
        System.out.println("技能：夜视 " + (nightVisionActive ? "开启" : "关闭"));
    }

    @Override
    public boolean isNightVisionActive() {
        return nightVisionActive;
    }
}