package com.byabie.cubeic;

import com.byabie.mustardlib.util.CItem;
import com.byabie.mustardlib.util.Registry;

import net.minecraft.world.item.Rarity;

public class CubeicRegister {
     public static final Registry re = new Registry(Cubeic.MODID);
     public static Registry register() {
          //Block registry
          re.initBlock("vanguard_carpet_corner", new CubeicContent.RotatableCarpetBlock());
          re.initBlock("vanguard_carpet_side", new CubeicContent.RotatableCarpetBlock());
          //Item registry
          CItem c = new CItem(Rarity.COMMON, false);
          re.initItem("prismal_amethyst", c);
          re.initItem("carbonite_fragment", c);
          re.initItem("carbonite_ingot", c);
          re.initItem("carbonite_arrow", c);
          re.initItem("prismal_amethyst", c);
          re.initItem("sculkbone_shards", c);
          re.initItem("sculkbone", c);
          re.initItem("bottle_of_souls", c);
          re.initItem("tnt_arrow", c);
          re.initItem("amethyst_sword", new CubeicContent.AmethystSword());
          re.initItem("inferno_helmet", new CubeicContent.InfernoHelmet());
          return re;
     }
}