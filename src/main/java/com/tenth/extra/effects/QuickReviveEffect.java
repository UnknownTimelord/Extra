package com.tenth.extra.effects;

import com.tenth.extra.setup.Register;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class QuickReviveEffect extends MobEffect
{
    public QuickReviveEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if(!livingEntity.level.isClientSide()) {
            ServerPlayer serverplayer = (ServerPlayer)livingEntity;
            BlockPos pos = livingEntity.blockPosition();

            if(livingEntity.getHealth() == 0)
            {
                serverplayer.setRespawnPosition(livingEntity.level.dimension(), pos, 0f, true, true);
                Register.hasDied = true;
            }
        }
        super.applyEffectTick(livingEntity, amplifier);
    }

    @Override
    public boolean isDurationEffectTick(int Duration, int Amplifier) {
        return true;
    }
}
