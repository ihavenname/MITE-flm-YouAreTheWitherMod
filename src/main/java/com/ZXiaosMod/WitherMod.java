package com.ZXiaosMod;

import com.ZXiaosMod.keybind.GLBKeys;
import com.ZXiaosMod.server.ServerTick;
import moddedmite.rustedironcore.api.event.Handlers;
import moddedmite.rustedironcore.api.util.FabricUtil;
import net.fabricmc.api.ModInitializer;
import net.xiaoyu233.fml.ModResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WitherMod implements ModInitializer {
    public static final String MOD_ID = "wither";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Wither Mod Load!");
        ModResourceManager.addResourcePackDomain(MOD_ID);

        if (!FabricUtil.isServer()) {
            Handlers.Keybinding.register(GLBKeys.INSTANCE);
            Handlers.Tick.register(GLBKeys.INSTANCE);
            Handlers.Tick.register(ServerTick.INSTANCE);
        }
    }
}