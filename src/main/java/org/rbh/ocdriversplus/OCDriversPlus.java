package org.rbh.ocdriversplus;

import li.cil.oc.api.Driver;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;



@Mod(modid = OCDriversPlus.MODID, version = OCDriversPlus.VERSION, useMetadata = true, acceptableRemoteVersions = "*", serverSideOnly = true)
public class OCDriversPlus
{
    public static final String MODID = "@MOD_ID@";
    public static final String VERSION = "@MOD_VERSION@";

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        if(Loader.isModLoaded("IC2")){
            Driver.add(new DriverBoiler());
        }
        if(Loader.isModLoaded("immersiveengineering")) {
            Driver.add(new DriverIETank());
        }
    }
}
