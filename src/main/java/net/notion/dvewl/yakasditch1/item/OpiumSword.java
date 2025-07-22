package net.notion.dvewl.yakasditch1.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import java.util.UUID;

public class OpiumSword extends SwordItem {

    public boolean isLightningStrikeEnabled = true;
    public boolean isBuffed = false;

    public OpiumSword(OpiumToolMaterial instance, int attackDamage, float attackSpeed, Settings settings) {
        super(instance, attackDamage, attackSpeed, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);


        if (!(entity instanceof PlayerEntity player))
            return;
        if (world.isClient)
            return;
        if (stack.getItem() != this)
            return;

        var nbt = stack.getOrCreateNbt();

        // Прив'язка меча до гравця
        if (!nbt.contains("OwnerUUID")) {
            nbt.putUuid("OwnerUUID", player.getUuid());
            player.sendMessage(Text.literal("You have bound the Opium Sword to yourself."), true);
            player.sendMessage(Text.literal("Your sword is bound!"), true);
        }

        // Якщо меч не належить цьому гравцю — нічого не робимо
        UUID owner = nbt.getUuid("OwnerUUID");
        if (!player.getUuid().equals(owner)) {

            return;
        }

        // Якщо меч обрано та ще не викликана блискавка
        if (selected && !nbt.getBoolean("HasStruck")) {
            var lightning = EntityType.LIGHTNING_BOLT.create(
                    (ServerWorld) world,
                    null,
                    null,
                    player.getBlockPos(),
                    net.minecraft.entity.SpawnReason.TRIGGERED,
                    true,
                    false);

            if (lightning != null) {
                world.spawnEntity(lightning);
                player.sendMessage(Text.literal("🧛🏼Opium sword⚡"), true);
                // Встановлюємо прапор, що блискавка вже била
                nbt.putBoolean("HasStruck", true);
            }
        }

        // Якщо меч **не обраний** — скидаємо прапор
        if (!selected && nbt.getBoolean("HasStruck")) {
            nbt.putBoolean("HasStruck", false);
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        var stack = user.getStackInHand(hand);
        var nbt = stack.getOrCreateNbt();

        if (!nbt.contains("OwnerUUID")) {
            nbt.putUuid("OwnerUUID", user.getUuid());
        }

        UUID owner = nbt.getUuid("OwnerUUID");
        if (!user.getUuid().equals(owner)) {
            if (!world.isClient) {
                user.sendMessage(Text.literal("You're not strong enough to hold it."), true);
            }
            return TypedActionResult.fail(stack);
        }

        if (!world.isClient) {
            user.getItemCooldownManager().set(this, 100);
        }

        if (isLightningStrikeEnabled && !world.isClient) {
            var lightning = EntityType.LIGHTNING_BOLT.create(
                    (ServerWorld) world,
                    null,
                    null,
                    user.getBlockPos(),
                    net.minecraft.entity.SpawnReason.TRIGGERED,
                    true,
                    false);

            if (lightning != null) {
                world.spawnEntity(lightning);
            }
            if (lightning != null) {
                isBuffed = true; // Активуємо бафф
                user.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(
                        net.minecraft.entity.effect.StatusEffects.STRENGTH, 200, 1)); // Додаємо ефект сили
                user.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(
                        net.minecraft.entity.effect.StatusEffects.SPEED, 200, 1)); // Додаємо ефект швидкості
                user.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(
                        net.minecraft.entity.effect.StatusEffects.RESISTANCE, 200, 1)); // Додаємо ефект опору
                user.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(
                        net.minecraft.entity.effect.StatusEffects.REGENERATION, 200, 1)); // Додаємо ефект регенерації
                user.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(
                        net.minecraft.entity.effect.StatusEffects.JUMP_BOOST, 200, 1)); // Додаємо ефект стрибка
                user.sendMessage(Text.literal("⚡You thor⚡"), true);
            }
        }

        return super.use(world, user, hand);
    }

}
