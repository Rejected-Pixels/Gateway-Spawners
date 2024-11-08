package com.rejectedpixels.gateway_spawners.data;

import com.rejectedpixels.gateway_spawners.GatewaySpawners;
import com.rejectedpixels.gateway_spawners.registries.BlockRegistry;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockModelProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput output, String locale) {
        super(output, GatewaySpawners.MOD_ID, locale);
    }
    @Override
    protected void addTranslations() {
        addBlock(BlockRegistry.GATEWAY_SPAWNER_BLOCK, "Gateway Spawner");
    }
}
