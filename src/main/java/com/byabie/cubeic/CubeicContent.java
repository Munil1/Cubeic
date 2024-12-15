package com.byabie.cubeic;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public abstract class CubeicContent {
     public static class AmethystSword extends SwordItem {
          private static final int BASE_DAMAGE = 6;
          public AmethystSword() {
               super(Tiers.IRON, BASE_DAMAGE, -2.4F, new Item.Properties().rarity(Rarity.RARE).stacksTo(1).durability(200));
          }
          @Override
          public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
               if (attacker instanceof Player player) {

               int bonusDamage = player.experienceLevel / 3;
               float totalDamage = BASE_DAMAGE + bonusDamage;

               target.hurt(player.damageSources().playerAttack(player), totalDamage);

               stack.hurtAndBreak(1, player, (entity) -> entity.broadcastBreakEvent(InteractionHand.MAIN_HAND));
               }
               return true;
          }
          @Override
          public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
               tooltip.add(Component.translatable("item.cubeic.amethyst_sword.description"));
          }
     }
     public static class RotatableCarpetBlock extends Block {
          public static DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
          public RotatableCarpetBlock() {
               super(BlockBehaviour.Properties.copy(Blocks.RED_WOOL));
               this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
          }
          @Override
          public BlockState getStateForPlacement(BlockPlaceContext context) {
               return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
          }
          @Override
          public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
               return Shapes.box(0.0D, 0.0D, 0.0D, 1D, 1D / 16D, 1D);
          }
     }
     public static class InfernoHelmet extends ArmorItem {
          private int previousFireTicks = 0;
          public InfernoHelmet() {
               super(ArmorMaterials.DIAMOND, ArmorItem.Type.HELMET, new Item.Properties().stacksTo(1));
          }
          @Override
          public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean isSelected) { 
               if (entity instanceof Player player && player.getItemBySlot(EquipmentSlot.HEAD) == stack) { 
                    if (player.isOnFire()) { 
                         int currentFireTicks = player.getRemainingFireTicks(); 
                         if (currentFireTicks > previousFireTicks) { 
                              int halvedFireTicks = currentFireTicks / 2; 
                              player.setRemainingFireTicks(halvedFireTicks); 
                         } 
                         previousFireTicks = player.getRemainingFireTicks();
                    } else { 
                         previousFireTicks = 0; 
                    } 
               } 
          }
          @Override
          public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
               tooltip.add(Component.translatable("item.cubeic.inferno_helmet.description"));
          }
     }
}