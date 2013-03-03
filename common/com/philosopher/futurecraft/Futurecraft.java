package com.philosopher.futurecraft;

import net.minecraft.creativetab.CreativeTabs;

import com.philosopher.futurecraft.block.ModBlocks;
import com.philosopher.futurecraft.core.CommonProxy;
import com.philosopher.futurecraft.core.CreativeTabHandler;
import com.philosopher.futurecraft.core.GuiHandler;
import com.philosopher.futurecraft.core.WorldHandler;
import com.philosopher.futurecraft.items.ModItems;
import com.philosopher.futurecraft.lib.Recipes;
import com.philosopher.futurecraft.lib.References;
import com.philosopher.futurecraft.tile.TileEntityLoader;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid="Futurecraft", name="Futurecraft", version="Super Beta Mode")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class Futurecraft {

        @Instance("Futurecraft")
        public static Futurecraft instance;
   
        @SidedProxy(clientSide = References.CLTPROXY , serverSide = References.COMPROXY)
        public static CommonProxy proxy;
        
        public static CreativeTabs fcItemTab = new CreativeTabHandler("fcItemTab");
        public static CreativeTabs fcBlockTab = new CreativeTabHandler("fcBlockTab");
        
        @PreInit
        public void preInit(FMLPreInitializationEvent event) {
        	
        }
        
        @Init
        public void load(FMLInitializationEvent event) {
                proxy.registerRenderers();
                ModBlocks.init();
                ModBlocks.generate();
                ModItems.init();
                Recipes.init();
                TileEntityLoader.init();
                GameRegistry.registerWorldGenerator(new WorldHandler());
                NetworkRegistry.instance().registerGuiHandler(instance, new GuiHandler());
        }
        
        @PostInit
        public void postInit(FMLPostInitializationEvent event) {
        	
        }
}