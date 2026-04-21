package io.github.techtastic.simulated_gauges.mixinducks;

import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import net.liukrast.deployer.lib.logistics.board.connection.AbstractPanelSupportBehaviour;

public interface IAbstractPanelSupport {
    AbstractPanelSupportBehaviour simulated_gauges$getOrCreateSupport(SmartBlockEntity sbe);
}
