package org.rbh.ocic2drivers;

import li.cil.oc.api.Driver;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = OCIC2Drivers.MODID, version = OCIC2Drivers.VERSION, serverSideOnly = true, acceptableRemoteVersions = "*")
public class OCIC2Drivers
{
    public static final String MODID = "ocic2drivers";
    public static final String VERSION = "1.0";

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        Driver.add(new DriverBoiler());
    }
}
