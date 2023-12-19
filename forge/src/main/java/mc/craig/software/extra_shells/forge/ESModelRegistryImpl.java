package mc.craig.software.extra_shells.forge;

import mc.craig.software.extra_shells.ESModelRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class ESModelRegistryImpl {

    public static final Map<ModelLayerLocation, Supplier<LayerDefinition>> DEFINITIONS = new ConcurrentHashMap<>();

    public static ModelLayerLocation register(ModelLayerLocation location, Supplier<LayerDefinition> definition) {
        DEFINITIONS.put(location, definition);
        return location;
    }

    public static void register(EntityRenderersEvent.RegisterLayerDefinitions event) {
        DEFINITIONS.forEach(event::registerLayerDefinition);
        ESModelRegistry.setupModelInstances(Minecraft.getInstance().getEntityModels());
    }
}
