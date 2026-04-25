package io.github.techtastic.simulated_gauges.content.logistics.board;

import com.simibubi.create.content.logistics.factoryBoard.FactoryPanelBlock;
import com.simibubi.create.content.logistics.factoryBoard.FactoryPanelBlockEntity;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import dev.ryanhcode.sable.Sable;
import dev.ryanhcode.sable.sublevel.SubLevel;
import io.github.techtastic.simulated_gauges.registry.ItemsRegistry;
import io.github.techtastic.simulated_gauges.registry.PartialModelsRegistry;
import net.liukrast.deployer.lib.logistics.board.PanelType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;
import org.joml.Vector3d;

public class GimbalSensorPanelBehaviour extends AbstractVector3PanelBehaviour {
    public GimbalSensorPanelBehaviour(PanelType<?> type, FactoryPanelBlockEntity be, FactoryPanelBlock.PanelSlot slot) {
        super(type, be, slot);
    }

    @Override
    public Vector3d getVector() {
        SubLevel sublevel = Sable.HELPER.getContaining(blockEntity.getLevel(), blockEntity.getBlockPos());
        if (sublevel == null) return new Vector3d();
        return sublevel.logicalPose().orientation().getEulerAnglesYXZ(new Vector3d());
    }

    @Override
    public Item getItem() {
        return ItemsRegistry.GIMBAL_PANEL.get();
    }

    @Override
    public PartialModel getModel(FactoryPanelBlock.PanelState panelState, FactoryPanelBlock.PanelType panelType) {
        return PartialModelsRegistry.GIMBAL_PANEL;
    }
}
