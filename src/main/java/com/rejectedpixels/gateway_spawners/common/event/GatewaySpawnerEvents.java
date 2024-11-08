package com.rejectedpixels.gateway_spawners.common.event;

import com.rejectedpixels.gateway_spawners.common.command.GatewaySpawnerCommand;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

public class GatewaySpawnerEvents {
    @SubscribeEvent
    public void registerCommands(RegisterCommandsEvent pEvent) {
        GatewaySpawnerCommand.register(pEvent.getDispatcher());
    }
}
