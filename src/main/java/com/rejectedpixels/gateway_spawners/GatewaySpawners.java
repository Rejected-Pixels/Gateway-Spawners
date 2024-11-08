package com.rejectedpixels.gateway_spawners;

import com.rejectedpixels.gateway_spawners.common.command.GatewaySpawnerCommand;
import com.rejectedpixels.gateway_spawners.common.event.GatewaySpawnerEvents;
import com.rejectedpixels.gateway_spawners.data.ModBlockStateProvider;
import com.rejectedpixels.gateway_spawners.data.ModLanguageProvider;
import com.rejectedpixels.gateway_spawners.registries.BlockEntityTypeRegistry;
import com.rejectedpixels.gateway_spawners.registries.BlockRegistry;
import com.rejectedpixels.gateway_spawners.registries.ItemRegistry;
import net.minecraft.commands.Commands;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@Mod(GatewaySpawners.MOD_ID)
public class GatewaySpawners {
    public static final String MOD_ID = "gateway_spawners";

    public GatewaySpawners(IEventBus pEventBus) {
        pEventBus.register(this);
        BlockRegistry.BLOCKS.register(pEventBus);
        ItemRegistry.ITEMS.register(pEventBus);
        BlockEntityTypeRegistry.BLOCK_ENTITY_TYPES.register(pEventBus);
        NeoForge.EVENT_BUS.register(new GatewaySpawnerEvents());
    }

    @SubscribeEvent
    public void data(GatherDataEvent pEvent) {
        DataGenerator generator = pEvent.getGenerator();
        PackOutput packOutput = generator.getPackOutput();

        generator.addProvider(pEvent.includeClient(), new ModLanguageProvider(packOutput, "en_us"));

        generator.addProvider(pEvent.includeServer(), new ModBlockStateProvider(packOutput, pEvent.getExistingFileHelper()));
    }

}
