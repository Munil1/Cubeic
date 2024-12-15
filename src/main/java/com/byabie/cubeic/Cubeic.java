package com.byabie.cubeic;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;


@Mod(Cubeic.MODID)
public class Cubeic {
    
    public static final String MODID = "cubeic";
    
    private static final Logger LOGGER = LogUtils.getLogger();
    
    public Cubeic() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CubeicConfig.spec, "cubeic-common.toml");
        LOGGER.info("Cubeic loaded.");
        MinecraftForge.EVENT_BUS.register(this);
        for(DeferredRegister<?> d : CubeicRegister.register().getRegisters()) {
            modEventBus.register(d);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        
    }
}
