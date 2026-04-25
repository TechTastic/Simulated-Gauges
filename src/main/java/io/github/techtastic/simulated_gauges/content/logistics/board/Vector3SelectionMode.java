package io.github.techtastic.simulated_gauges.content.logistics.board;

import com.simibubi.create.foundation.blockEntity.behaviour.scrollValue.INamedIconOptions;
import com.simibubi.create.foundation.gui.AllIcons;
import io.github.techtastic.simulated_gauges.registry.Icons;
import net.createmod.catnip.lang.Lang;

public enum Vector3SelectionMode implements INamedIconOptions {
    X(Icons.X),
    Y(Icons.Y),
    Z(Icons.Z);

    private final String translationKey;
    private final AllIcons icon;

    Vector3SelectionMode(AllIcons icon) {
        this.icon = icon;
        translationKey = "vector_gauge.mode." + Lang.asId(name());
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
