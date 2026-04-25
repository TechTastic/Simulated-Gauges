package io.github.techtastic.simulated_gauges.content.logistics.board;

import com.simibubi.create.content.logistics.factoryBoard.FactoryPanelBlock;
import com.simibubi.create.content.logistics.factoryBoard.FactoryPanelBlockEntity;
import dev.ryanhcode.sable.Sable;
import dev.ryanhcode.sable.sublevel.SubLevel;
import net.liukrast.deployer.lib.logistics.board.AbstractPanelBehaviour;
import net.liukrast.deployer.lib.logistics.board.PanelType;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractSubLevelPanelBehaviour extends AbstractPanelBehaviour {
    public AbstractSubLevelPanelBehaviour(PanelType<?> type, FactoryPanelBlockEntity be, FactoryPanelBlock.PanelSlot slot) {
        super(type, be, slot);
    }

    @Nullable
    public SubLevel getSubLevel() {
        return Sable.HELPER.getContaining(blockEntity.getLevel(), blockEntity.getBlockPos());
    }
}
