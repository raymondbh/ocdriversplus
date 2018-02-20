package org.rbh.ocdriversplus;

import li.cil.oc.api.Network;
import li.cil.oc.api.network.Visibility;
import li.cil.oc.api.prefab.AbstractManagedEnvironment;

/**
 * Created by Raymond on 20.04.2017.
 */
public class ManEnv<T> extends AbstractManagedEnvironment{
    protected final T tileEntity;

    public ManEnv(final T tileEntity, final String name) {
        this.tileEntity = tileEntity;
        setNode(Network.newNode(this, Visibility.Network).
                withComponent(name).
                create());
    }
}
