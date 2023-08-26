
package net.mcreator.worldtravaler.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.BlockState;

import net.mcreator.worldtravaler.WorldTravalerModElements;

import java.util.List;

@WorldTravalerModElements.ModElement.Tag
public class KristalSkintaItem extends WorldTravalerModElements.ModElement {
	@ObjectHolder("world_travaler:kristal_skinta")
	public static final Item block = null;

	public KristalSkintaItem(WorldTravalerModElements instance) {
		super(instance, 28);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(ItemGroup.MISC).maxStackSize(64).rarity(Rarity.EPIC));
			setRegistryName("kristal_skinta");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}

		@Override
		public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
			super.addInformation(itemstack, world, list, flag);
			list.add(new StringTextComponent(
					"\u0421\u043A\u043E\u043F\u043B\u0435\u043D\u0438\u0435 \u0444\u0440\u0430\u0433\u043C\u0435\u043D\u0442\u043E\u0432 \u0441\u043A\u0438\u043D\u0442\u0430"));
		}
	}
}
