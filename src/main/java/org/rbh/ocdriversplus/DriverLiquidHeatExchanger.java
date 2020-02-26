package org.rbh.ocdriversplus;

import ic2.core.block.machine.tileentity.TileEntityLiquidHeatExchanger;
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
 * Created by Raymond on 30.01.2020.
 */
public final class DriverLiquidHeatExchanger extends DriverSidedTileEntity {
    @Override
    public Class<?> getTileEntityClass() {
        return TileEntityLiquidHeatExchanger.class;
    }

    @Override
    public ManagedEnvironment createEnvironment(World world, BlockPos blockPos, EnumFacing enumFacing) {
        return new Environment((TileEntityLiquidHeatExchanger) world.getTileEntity(blockPos));
    }

    public static final class Environment extends ManEnv<TileEntityLiquidHeatExchanger> implements NamedBlock {
        public Environment(final TileEntityLiquidHeatExchanger tileEntity) {
            super(tileEntity, "ic2_liquid_heat_exchanger");
        }

        @Override
        public String preferredName() {
            return "ic2_liquid_heat_exchanger";
        }

        @Override
        public int priority() {
            return 0;
        }

        @Callback(doc = "function():number -- Get the fluid amount of the input tank")
        public Object[] getInputTank(final Context context, final Arguments args){
            return new Object[] {tileEntity.inputTank.getFluidAmount()};
        }

        @Callback(doc = "function():number -- Get the fluid amount of the input tank")
        public Object[] getOutputTank(final Context context, final Arguments args){
            return new Object[] {tileEntity.outputTank.getFluidAmount()};
        }
    }
}
