package com.rejectedpixels.gateway_spawners.registries;

import com.rejectedpixels.gateway_spawners.GatewaySpawners;
import com.rejectedpixels.gateway_spawners.common.block.GatewaySpawnerBlock;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockRegistry {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(GatewaySpawners.MOD_ID);

    public static final DeferredBlock<GatewaySpawnerBlock> GATEWAY_SPAWNER_BLOCK = BLOCKS.register("gateway_spawner", GatewaySpawnerBlock::new);


}
