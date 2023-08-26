
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
public class OskolokItem extends WorldTravalerModElements.ModElement {
	@ObjectHolder("world_travaler:oskolok")
	public static final Item block = null;

	public OskolokItem(WorldTravalerModElements instance) {
		super(instance, 27);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(ItemGroup.MISC).maxStackSize(64).rarity(Rarity.EPIC));
			setRegistryName("oskolok");
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
					"\u0421\u043A\u043E\u043F\u043B\u0435\u043D\u0438\u0435 \u043A\u0440\u0443\u043F\u0438\u0446 \u0441\u043A\u0438\u043D\u0442\u0430"));
		}
	}
}
