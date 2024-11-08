package com.rejectedpixels.gateway_spawners.registries;

import com.rejectedpixels.gateway_spawners.GatewaySpawners;
import net.minecraft.world.item.BlockItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ItemRegistry {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(GatewaySpawners.MOD_ID);

    public static final Supplier<BlockItem> GATEWAY_SPAWNER_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(BlockRegistry.GATEWAY_SPAWNER_BLOCK);
}
