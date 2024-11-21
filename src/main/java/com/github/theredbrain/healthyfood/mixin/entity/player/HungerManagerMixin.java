package com.github.theredbrain.healthyfood.mixin.entity.player;

import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HungerManager.class)
public class HungerManagerMixin {

	@Inject(method = "add", at = @At("HEAD"), cancellable = true)
	public void healthyfood$add(int food, float saturationModifier, CallbackInfo ci) {
		ci.cancel();
	}

	@Inject(method = "eat", at = @At("HEAD"), cancellable = true)
	public void healthyfood$eat(Item item, ItemStack stack, CallbackInfo ci) {
		ci.cancel();
	}

	@Inject(method = "update", at = @At("HEAD"), cancellable = true)
	public void healthyfood$update(PlayerEntity player, CallbackInfo ci) {
		ci.cancel();
	}

	@Inject(method = "addExhaustion", at = @At("HEAD"), cancellable = true)
	public void healthyfood$addExhaustion(float exhaustion, CallbackInfo ci) {
		ci.cancel();
	}
}
