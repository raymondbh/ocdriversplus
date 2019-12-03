package org.rbh.ocdriversplus;

import blusunrize.immersiveengineering.common.blocks.metal.TileEntitySheetmetalTank;
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
 * Created by Raymond on 20.04.2017.
 */
public class DriverIETank extends DriverSidedTileEntity {
    @Override
    public Class<?> getTileEntityClass() {
        return TileEntitySheetmetalTank.class;
    }

    @Override
    public ManagedEnvironment createEnvironment(World world, BlockPos pos, EnumFacing side) {
        return new Environment((TileEntitySheetmetalTank) world.getTileEntity(pos));
    }

    public static final class Environment extends ManEnv<TileEntitySheetmetalTank> implements NamedBlock {
        public Environment(TileEntitySheetmetalTank tileEntity) {
            super(tileEntity, "ie_tank");
        }

        @Override
        public String preferredName() {
            return "ie_tank";
        }

        @Override
        public int priority() {
            return 0;
        }

        @Callback(doc = "function():number -- Returns amount in mb.")
        public Object[] getAmount(final Context context, final Arguments args) {
            TileEntitySheetmetalTank master = tileEntity.master();
            if(master == null) {
                return new Object[]{0};
            }
            return new Object[]{master.tank.getFluidAmount()};
        }

        @Callback(doc = "function():number -- Returns capacity in mb.")
        public Object[] getCapacity(final Context context, final Arguments args) {
            TileEntitySheetmetalTank master = tileEntity.master();
            if(master == null) {
                return new Object[]{0};
            }
            return new Object[]{master.tank.getCapacity()};
        }

        @Callback(doc = "function():number -- Returns capacity in mb.")
        public Object[] getFluid(final Context context, final Arguments args) {
            TileEntitySheetmetalTank master = tileEntity.master();
            if(master == null) {
                return new Object[]{0};
            }
            if(master.tank.getFluidAmount() == 0){
                return new Object[]{null};
            }
            return new Object[]{master.tank.getFluid()};
        }
    }
}
