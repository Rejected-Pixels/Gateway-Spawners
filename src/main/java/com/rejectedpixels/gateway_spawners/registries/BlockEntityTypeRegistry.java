package com.rejectedpixels.gateway_spawners.registries;

import com.rejectedpixels.gateway_spawners.GatewaySpawners;
import com.rejectedpixels.gateway_spawners.common.block.entity.GatewaySpawnerBlockEntity;
import dev.shadowsoffire.placebo.block_entity.TickingBlockEntityType;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashSet;
import java.util.List;
import java.util.function.Supplier;

public class BlockEntityTypeRegistry {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, GatewaySpawners.MOD_ID);

    public static final Supplier<BlockEntityType<GatewaySpawnerBlockEntity>> GATEWAY_SPAWNER_BLOCK_ENTITY = BLOCK_ENTITY_TYPES.register("gateway_spawner_block_entity",
            () -> new TickingBlockEntityType<>(
                    GatewaySpawnerBlockEntity::new,
                    new HashSet<>(List.of(BlockRegistry.GATEWAY_SPAWNER_BLOCK.get())),
                    TickingBlockEntityType.TickSide.SERVER)
    );

}
