package io.github.techtastic.simulated_gauges.content.logistics.board;

import com.simibubi.create.content.logistics.factoryBoard.FactoryPanelBlock;
import com.simibubi.create.content.logistics.factoryBoard.FactoryPanelBlockEntity;
import net.liukrast.deployer.lib.logistics.board.PanelType;
import net.liukrast.deployer.lib.logistics.board.ScrollOptionPanelBehaviour;
import net.liukrast.deployer.lib.logistics.board.connection.PanelConnectionBuilder;
import net.liukrast.deployer.lib.registry.DeployerPanelConnections;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.joml.Vector3d;

public abstract class AbstractVector3PanelBehaviour extends ScrollOptionPanelBehaviour<Vector3SelectionMode> {
    public AbstractVector3PanelBehaviour(PanelType<?> type, FactoryPanelBlockEntity be, FactoryPanelBlock.PanelSlot slot) {
        super(Component.translatable("create.logistics.vector_selection"), type, be, slot, Vector3SelectionMode.class);
    }

    public abstract Vector3d getVector();

    public double getVectorValue() {
        return switch (get()) {
            case X -> getVector().x;
            case Y -> getVector().y;
            case Z -> getVector().z;
        };
    }

    @Override
    public void addConnections(PanelConnectionBuilder builder) {
        builder.registerOutput(DeployerPanelConnections.NUMBERS.get(), () -> (float) getVectorValue());
        builder.registerOutput(DeployerPanelConnections.STRING.get(), () -> getDisplayLinkComponent(false).toString());
    }

    @Override
    public MutableComponent getDisplayLinkComponent(boolean shortVersion) {
        if (shortVersion)
            return Component.literal("%.2f".formatted(getVectorValue()));
        return Component.literal(getVectorValue() + "");
    }
}
