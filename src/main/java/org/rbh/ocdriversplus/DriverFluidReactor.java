package org.rbh.ocdriversplus;

import ic2.core.block.reactor.tileentity.TileEntityReactorVessel;
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
 * Created by Raymond on 22.04.2017.
 */
public class DriverFluidReactor extends DriverSidedTileEntity {
    @Override
    public Class<?> getTileEntityClass() {
        return TileEntityReactorVessel.class;
    }

    @Override
    public ManagedEnvironment createEnvironment(World world, BlockPos pos, EnumFacing side) {
        return new Environment((TileEntityReactorVessel) world.getTileEntity(pos));
    }
    public static final class Environment extends ManEnv<TileEntityReactorVessel> implements NamedBlock {

        public Environment(TileEntityReactorVessel tileEntity) {
            super(tileEntity, "ic2_fluid_reactor");
        }

        @Override
        public String preferredName() {
            return "ic2_fluid_reactor";
        }

        @Override
        public int priority() {
            return 0;
        }

        @Callback(doc = "function():number -- Get the fluid amount of the input tank in mb.")
        public Object[] getFluidAmountInput(final Context context, final Arguments args){
            return new Object[] {tileEntity.getReactorInstance().getinputtank().getFluidAmount()};
        }

        @Callback(doc = "function():number -- Get the fluid amount of the output tank in mb.")
        public Object[] getFluidAmountOutput(final Context context, final Arguments args){
            return new Object[] {tileEntity.getReactorInstance().getoutputtank().getFluidAmount()};
        }

        @Callback(doc = "function():number -- Get the reactors heat.")
        public Object[] getHeat(final Context context, final Arguments args){
            return new Object[] {tileEntity.getReactorInstance().getHeat()};
        }

        @Callback(doc = "function():number -- Get the reactors max heat before meltdown.")
        public Object[] getMaxHeat(final Context context, final Arguments args){
            return new Object[] {tileEntity.getReactorInstance().getMaxHeat()};
        }

        @Callback(doc = "function():number -- Get the reactors energy output.")
        public Object[] getEnergyOutput(final Context context, final Arguments args){
            return new Object[] {tileEntity.getReactorInstance().getReactorEnergyOutput()};
        }

        @Callback(doc = "function():number -- Get the reactors energy output in EU.")
        public Object[] getEnergyOutputEU(final Context context, final Arguments args){
            return new Object[] {tileEntity.getReactorInstance().getReactorEUEnergyOutput()};
        }
    }
}
