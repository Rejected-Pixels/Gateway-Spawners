package com.rejectedpixels.gateway_spawners.data;

import com.rejectedpixels.gateway_spawners.GatewaySpawners;
import com.rejectedpixels.gateway_spawners.registries.BlockRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.RenderTypeHelper;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, GatewaySpawners.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlockWithItem(BlockRegistry.GATEWAY_SPAWNER_BLOCK.get(), models().singleTexture(BlockRegistry.GATEWAY_SPAWNER_BLOCK.getRegisteredName(), mcLoc("block/cube_all_inner_faces"), "all", mcLoc("block/spawner")).renderType(RenderType.CUTOUT.name));
    }
}
