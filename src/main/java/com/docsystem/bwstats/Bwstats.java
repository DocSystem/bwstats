package com.docsystem.bwstats;

import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
 
@Mod(modid = Bwstats.MODID, name = "Bedwars Stats", version = "0.1", acceptedMinecraftVersions = "[1.8.9]")
public class Bwstats
{
    public static final String MODID = "bwstats";
 
    @Instance(Bwstats.MODID)
    public static Bwstats instance;
 
    @SidedProxy(clientSide = "com.docsystem.bwstats.BwstatsClient", serverSide = "com.docsystem.bwstats.BwstatsServer")
    public static BwstatsCommon proxy;
    
    public static Logger logger;
 
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        proxy.preInit(event.getSuggestedConfigurationFile());
    }
    
	@EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init();
    }
    @EventHandler
    public void PostInit(FMLPostInitializationEvent event)
    {
            // Integration avec les autres mods - integration with others mods
    }
}