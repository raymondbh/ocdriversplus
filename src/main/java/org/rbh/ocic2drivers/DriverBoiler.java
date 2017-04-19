package org.rbh.ocic2drivers;

import ic2.core.block.machine.tileentity.TileEntitySteamGenerator;
import li.cil.oc.api.driver.NamedBlock;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.ManagedEnvironment;
import li.cil.oc.api.prefab.DriverSidedTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


/**
 * Created by Raymond on 18.04.2017.
 */
public final class DriverBoiler extends DriverSidedTileEntity {

    @Override
    public Class<?> getTileEntityClass() {
        return TileEntitySteamGenerator.class;
    }

    @Override
    public ManagedEnvironment createEnvironment(World world, BlockPos pos, EnumFacing side) {
        return new Environment((TileEntitySteamGenerator) world.getTileEntity(pos));
    }

    public static final class Environment extends ManEnv<TileEntitySteamGenerator> implements NamedBlock {
         public Environment(final TileEntitySteamGenerator tileEntity) {
            super(tileEntity, "ic2_steam_boiler");
        }

        @Override
        public String preferredName() {
            return "ic2_steam_boiler";
        }

        @Override
        public int priority() {
            return 0;
        }

        private void inputMB(int mb){
            if(mb >= 2000 || mb <= -2000){
                return;
            }
            tileEntity.onNetworkEvent(null, mb);
        }

        private void pressure(int pressure){
            if(pressure < 0){
                pressure -= 2000;
            }
            else if(pressure > 0){
                pressure += 2000;
            }
            tileEntity.onNetworkEvent(null, pressure);
        }

        @Callback(doc = "function():number -- Get the steam boiler's inputMB")
        public Object[] getInputMB(final Context context, final Arguments args){
            return new Object[] {tileEntity.getInputMB()};
        }

        @Callback(doc = "function():number -- Get the steam boiler's pressure")
        public Object[] getPressure(final Context context, final Arguments args){
            return new Object[] {tileEntity.getPressure()};
        }

        @Callback(doc = "function():number -- Get the steam boiler's temperature")
        public Object[] getSystemHeat(final Context context, final Arguments args){
            return new Object[] {tileEntity.getSystemHeat()};
        }

        @Callback(doc = "function(mb:number):number -- Adds number to the steam boiler's inputMB")
        public Object[] addInputMB(final Context context, final Arguments args){
            inputMB(args.checkInteger(0));
            return new Object[] {tileEntity.getInputMB()};
        }

        @Callback(doc = "function(bar:number):number -- Adds number to the steam boiler's pressure")
        public Object[] addPressure(final Context context, final Arguments args){
            pressure(args.checkInteger(0));
            return new Object[] {tileEntity.getPressure()};
        }
    }
}
