
package net.mcreator.worldtravaler.gui;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.Minecraft;

import net.mcreator.worldtravaler.procedures.LifeCounter4Procedure;
import net.mcreator.worldtravaler.procedures.LifeCounter3Procedure;
import net.mcreator.worldtravaler.procedures.LifeCounter2Procedure;
import net.mcreator.worldtravaler.procedures.LifeCounter1Procedure;
import net.mcreator.worldtravaler.WorldTravalerMod;

import java.util.stream.Stream;
import java.util.Map;
import java.util.HashMap;
import java.util.AbstractMap;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class LocalisGiveGuiWindow extends ContainerScreen<LocalisGiveGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	private final static HashMap guistate = LocalisGiveGui.guistate;

	public LocalisGiveGuiWindow(LocalisGiveGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 176;
		this.ySize = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("world_travaler:textures/screens/localis_give.png");

	@Override
	public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderHoveredTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.color4f(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		Minecraft.getInstance().getTextureManager().bindTexture(texture);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.blit(ms, k, l, 0, 0, this.xSize, this.ySize, this.xSize, this.ySize);
		if (LifeCounter1Procedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new,
				(_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("world_travaler:textures/screens/life_ind-1.png"));
			this.blit(ms, this.guiLeft + 15, this.guiTop + 34, 0, 0, 16, 16, 16, 16);
		}
		if (LifeCounter2Procedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new,
				(_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("world_travaler:textures/screens/life_ind-2.png"));
			this.blit(ms, this.guiLeft + 15, this.guiTop + 34, 0, 0, 16, 16, 16, 16);
		}
		if (LifeCounter3Procedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new,
				(_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("world_travaler:textures/screens/life_ind-3.png"));
			this.blit(ms, this.guiLeft + 15, this.guiTop + 34, 0, 0, 16, 16, 16, 16);
		}
		if (LifeCounter4Procedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new,
				(_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("world_travaler:textures/screens/life_ind-4-1.png"));
			this.blit(ms, this.guiLeft + 15, this.guiTop + 34, 0, 0, 16, 16, 16, 16);
		}
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeScreen();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void tick() {
		super.tick();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack ms, int mouseX, int mouseY) {
	}

	@Override
	public void onClose() {
		super.onClose();
		Minecraft.getInstance().keyboardListener.enableRepeatEvents(false);
	}

	@Override
	public void init(Minecraft minecraft, int width, int height) {
		super.init(minecraft, width, height);
		minecraft.keyboardListener.enableRepeatEvents(true);
		this.addButton(new Button(this.guiLeft + 33, this.guiTop + 60, 108, 20, new StringTextComponent("Получить семя локолиса"), e -> {
			if (true) {
				WorldTravalerMod.PACKET_HANDLER.sendToServer(new LocalisGiveGui.ButtonPressedMessage(0, x, y, z));
				LocalisGiveGui.handleButtonAction(entity, 0, x, y, z);
			}
		}));
	}
}
