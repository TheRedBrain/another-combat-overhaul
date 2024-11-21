package com.github.theredbrain.healthyfood.entity.player;

import net.minecraft.item.ItemStack;

public interface DuckPlayerEntityMixin {
	boolean healthyfood$canConsumeItem(ItemStack itemStack);

	void healthyfood$addFullness(float fullness);

	float healthyfood$getFullness();

	void healthyfood$setFullness(float fullness);
}
