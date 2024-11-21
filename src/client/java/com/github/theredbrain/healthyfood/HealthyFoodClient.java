package com.github.theredbrain.healthyfood;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class HealthyFoodClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClientPlayNetworking.registerGlobalReceiver(HealthyFood.ServerConfigSync.ID, (client, handler, buf, responseSender) -> {
			HealthyFood.serverConfig = HealthyFood.ServerConfigSync.read(buf);
		});
	}
}