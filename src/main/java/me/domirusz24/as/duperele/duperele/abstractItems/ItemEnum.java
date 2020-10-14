package me.domirusz24.as.duperele.duperele.abstractItems;

import javafx.scene.effect.Light;
import me.domirusz24.as.duperele.duperele.items.lightsaber.HiddenSaber;
import me.domirusz24.as.duperele.duperele.items.lightsaber.LightSaber;
import me.domirusz24.as.duperele.duperele.items.thorhammer.ThorHammer;

public enum ItemEnum {

    HiddenSaber(new HiddenSaber()),
    LightSaber(new LightSaber()),
    ThorHammer(new ThorHammer());

    private CustomItem i;

    ItemEnum(CustomItem i) {
        this.i = i;
    }

    public CustomItem getItem() {
        return i;
    }
}
