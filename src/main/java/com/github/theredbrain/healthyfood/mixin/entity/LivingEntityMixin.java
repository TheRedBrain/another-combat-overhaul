package com.github.theredbrain.healthyfood.mixin.entity;

import com.github.theredbrain.healthyfood.HealthyFood;
import com.github.theredbrain.healthyfood.entity.DuckLivingEntityMixin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements DuckLivingEntityMixin {

	@Shadow
	public abstract double getAttributeValue(EntityAttribute attribute);

	public LivingEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Inject(method = "createLivingAttributes", at = @At("RETURN"))
	private static void healthyfood$createLivingAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
		cir.getReturnValue()
				.add(HealthyFood.FULLNESS_TICK_THRESHOLD)
				.add(HealthyFood.MAX_FULLNESS)
		;
	}

	@Override
	public int healthyfood$getFullnessTickThreshold() {
		return (int) this.getAttributeValue(HealthyFood.FULLNESS_TICK_THRESHOLD);
	}

	@Override
	public int healthyfood$getMaxFullness() {
		return (int) this.getAttributeValue(HealthyFood.MAX_FULLNESS);
	}

}
