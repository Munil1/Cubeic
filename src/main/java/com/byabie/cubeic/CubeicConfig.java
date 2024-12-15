package com.byabie.cubeic;

import com.byabie.mustardlib.Config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CubeicConfig {
     public static Config cfg;
     public static ForgeConfigSpec spec;
     static {
          cfg = new Config();
          spec = cfg.getConfig();
     }
}
