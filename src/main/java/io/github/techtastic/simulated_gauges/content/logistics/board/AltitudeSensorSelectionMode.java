package io.github.techtastic.simulated_gauges.content.logistics.board;

import com.simibubi.create.foundation.blockEntity.behaviour.scrollValue.INamedIconOptions;
import com.simibubi.create.foundation.gui.AllIcons;
import io.github.techtastic.simulated_gauges.registry.Icons;
import net.createmod.catnip.lang.Lang;

public enum AltitudeSensorSelectionMode implements INamedIconOptions {
    HEIGHT(Icons.METERS),
    PRESSURE(Icons.PASCALS);

    private final String translationKey;
    private final AllIcons icon;

    AltitudeSensorSelectionMode(AllIcons icon) {
        this.icon = icon;
        translationKey = "altitude_gauge.mode." + Lang.asId(name());
    }

    @Override
    public AllIcons getIcon() {
        return icon;
    }

    @Override
    public String getTranslationKey() {
        return translationKey;
    }
}
