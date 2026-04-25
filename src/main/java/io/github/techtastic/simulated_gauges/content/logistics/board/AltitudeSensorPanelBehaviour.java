package io.github.techtastic.simulated_gauges.content.logistics.board;

import com.simibubi.create.content.logistics.factoryBoard.FactoryPanelBlock;
import com.simibubi.create.content.logistics.factoryBoard.FactoryPanelBlockEntity;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import dev.ryanhcode.sable.Sable;
import dev.ryanhcode.sable.api.sublevel.SubLevelContainer;
import dev.ryanhcode.sable.companion.SableCompanion;
import dev.ryanhcode.sable.physics.config.dimension_physics.DimensionPhysicsData;
import io.github.techtastic.simulated_gauges.registry.ItemsRegistry;
import io.github.techtastic.simulated_gauges.registry.PartialModelsRegistry;
import net.liukrast.deployer.lib.logistics.board.PanelType;
import net.liukrast.deployer.lib.logistics.board.ScrollOptionPanelBehaviour;
import net.liukrast.deployer.lib.logistics.board.connection.PanelConnectionBuilder;
import net.liukrast.deployer.lib.registry.DeployerPanelConnections;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3d;

public class AltitudeSensorPanelBehaviour extends ScrollOptionPanelBehaviour<AltitudeSensorSelectionMode> {
    public AltitudeSensorPanelBehaviour(PanelType<?> type, FactoryPanelBlockEntity be, FactoryPanelBlock.PanelSlot slot) {
        super(Component.translatable("create.logistics.altitude_selection"), type, be, slot, AltitudeSensorSelectionMode.class);
    }

    public double getOutput() {
        return switch (get()) {
            case HEIGHT -> getPosition().y;
            case PRESSURE -> getAirPressure();
        };
    }

    public Vector3d getPosition() {
        Vec3 center = getPos().getCenter();
        Vector3d pos = new Vector3d(center.x, center.y, center.z);
        return SableCompanion.INSTANCE.projectOutOfSubLevel(getWorld(), pos);
    }

    public double getAirPressure() {
        return DimensionPhysicsData.getAirPressure(getWorld(), getPosition());
    }

    @Override
    public void addConnections(PanelConnectionBuilder builder) {
        builder.registerOutput(DeployerPanelConnections.NUMBERS, () -> (float) getOutput());
        builder.registerOutput(DeployerPanelConnections.STRING, () -> getDisplayLinkComponent(false).toString());
    }

    @Override
    public Item getItem() {
        return ItemsRegistry.ALTITUDE_PANEL.get();
    }

    @Override
    public PartialModel getModel(FactoryPanelBlock.PanelState panelState, FactoryPanelBlock.PanelType panelType) {
        return PartialModelsRegistry.ALTITUDE_PANEL;
    }

    @Override
    public MutableComponent getDisplayLinkComponent(boolean shortVersion) {
        if (shortVersion)
            return Component.literal("%.2f".formatted(getOutput()));
        return Component.literal(getOutput() + "");
    }
}
