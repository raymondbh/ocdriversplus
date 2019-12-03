package org.rbh.ocdriversplus;

import blusunrize.immersiveengineering.common.blocks.metal.TileEntityMetalBarrel;
import li.cil.oc.api.driver.NamedBlock;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.ManagedEnvironment;
import li.cil.oc.api.prefab.DriverSidedTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DriverIEBarrel extends DriverSidedTileEntity {
    @Override
    public Class<?> getTileEntityClass() {
        return TileEntityMetalBarrel.class;
    }

    @Override
    public ManagedEnvironment createEnvironment(World world, BlockPos pos, EnumFacing side) {
        return new DriverIEBarrel.Environment((TileEntityMetalBarrel) world.getTileEntity(pos));
    }

    public static final class Environment extends ManEnv<TileEntityMetalBarrel> implements NamedBlock {
        public Environment(TileEntityMetalBarrel tileEntity) {
            super(tileEntity, "ie_barrel");
        }

        @Override
        public String preferredName() {
            return "ie_barrel";
        }

        @Override
        public int priority() {
            return 0;
        }

        @Callback(doc = "function():number -- Returns amount in mb.")
        public Object[] getAmount(final Context context, final Arguments args) {
            if(tileEntity == null) {
                return new Object[]{0};
            }
            return new Object[]{tileEntity.tank.getFluidAmount()};

        }

        @Callback(doc = "function():number -- Returns capacity in mb.")
        public Object[] getCapacity(final Context context, final Arguments args) {
            if(tileEntity == null) {
                return new Object[]{0};
            }
            return new Object[]{tileEntity.tank.getCapacity()};

        }

        @Callback(doc = "function():number -- Returns fluid name.")
        public Object[] getFluid(final Context context, final Arguments args) {
            if(tileEntity == null) {
                return new Object[]{0};
            }
            if(tileEntity.tank.getFluidAmount() == 0) {
                return new Object[]{null};
            }
            return new Object[]{tileEntity.tank.getFluid().getLocalizedName()};
        }
    }
}
