
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
public class OghraniennyiSkintItem extends WorldTravalerModElements.ModElement {
	@ObjectHolder("world_travaler:oghraniennyi_skint")
	public static final Item block = null;

	public OghraniennyiSkintItem(WorldTravalerModElements instance) {
		super(instance, 26);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(ItemGroup.MISC).maxStackSize(64).rarity(Rarity.EPIC));
			setRegistryName("oghraniennyi_skint");
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
					"\u0421\u043A\u043B\u0435\u0435\u043D\u043D\u044B\u0435 \u043D\u0435\u0437\u0435\u0440 \u0437\u0432\u0435\u0437\u0434\u043E\u0439 \u043A\u0440\u0438\u0441\u0442\u0430\u043B\u044B \u0441\u043A\u0438\u043D\u0442\u0430"));
		}
	}
}
