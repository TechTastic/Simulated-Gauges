package io.github.techtastic.simulated_gauges.mixin;

import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import dev.simulated_team.simulated.content.blocks.nameplate.NameplateBlockEntity;
import io.github.techtastic.simulated_gauges.mixinducks.IAbstractPanelSupport;
import net.liukrast.deployer.lib.logistics.board.connection.AbstractPanelSupportBehaviour;
import net.liukrast.deployer.lib.logistics.board.connection.PanelConnectionBuilder;
import net.liukrast.deployer.lib.registry.DeployerPanelConnections;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(NameplateBlockEntity.class)
public class MixinNameplateBlockEntity implements IAbstractPanelSupport {
    @Unique
    private AbstractPanelSupportBehaviour panelSupport;

    @Inject(method = "addBehaviours", at = @At("TAIL"))
    private void simulated_gauges$addLinkBehaviour(List<BlockEntityBehaviour> behaviours, CallbackInfo ci) {
        behaviours.add(simulated_gauges$getOrCreateSupport((NameplateBlockEntity)(Object)this));
    }

    @Override
    public AbstractPanelSupportBehaviour simulated_gauges$getOrCreateSupport(SmartBlockEntity sbe) {
        if (panelSupport == null) {
            panelSupport = new AbstractPanelSupportBehaviour(sbe, () -> true, () ->
                    panelSupport.getConnectionValue(DeployerPanelConnections.STRING.get()).ifPresent(value -> {
                        if (((NameplateBlockEntity) sbe).allowsEditing())
                            ((NameplateBlockEntity) sbe).setName(value, true, null);
                    })
            ) {
                @Override
                public void addConnections(PanelConnectionBuilder builder) {
                    builder.registerBoth(DeployerPanelConnections.STRING.get(), () -> ((NameplateBlockEntity)sbe).getName());
                }
            };
        }
        return panelSupport;
    }
}
