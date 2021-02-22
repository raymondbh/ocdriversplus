package org.rbh.ocdriversplus;

import blusunrize.immersiveengineering.common.blocks.metal.TileEntitySilo;
import li.cil.oc.api.driver.NamedBlock;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.ManagedEnvironment;
import li.cil.oc.api.prefab.DriverSidedTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DriverIESilo extends DriverSidedTileEntity {
    @Override
    public Class<?> getTileEntityClass() {
        return TileEntitySilo.class;
    }

    @Override
    public ManagedEnvironment createEnvironment(World world, BlockPos pos, EnumFacing enumFacing) {
        return new Environment((TileEntitySilo) world.getTileEntity(pos));
    }

    public static final class Environment extends ManEnv<TileEntitySilo> implements NamedBlock {
        public Environment(TileEntitySilo tileEntity) {
            super(tileEntity, "ie_silo");
        }

        @Override
        public String preferredName() {
            return "ie_silo";
        }

        @Override
        public int priority() {
            return 0;
        }

        @Callback(doc = "function():number -- Returns amount in number of items.")
        public Object[] getAmount(final Context context, final Arguments args) {
            TileEntitySilo master = tileEntity.master();
            if (master == null) {
                return new Object[]{0};
            }
            return new Object[]{master.storageAmount};
        }

        @Callback(doc = "function():number -- Returns amount in number of items.")
        public Object[] getItemName(final Context context, final Arguments args) {
            TileEntitySilo master = tileEntity.master();
            if (master == null) {
                return new Object[]{0};
            }
            return new Object[]{master.identStack.getDisplayName()};
        }
    }
}
