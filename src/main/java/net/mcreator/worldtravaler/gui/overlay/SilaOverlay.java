
package net.mcreator.worldtravaler.gui.overlay;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.client.Minecraft;

import net.mcreator.worldtravaler.procedures.OverbarProcedure;
import net.mcreator.worldtravaler.procedures.Overbar5Procedure;
import net.mcreator.worldtravaler.procedures.Overbar4Procedure;
import net.mcreator.worldtravaler.procedures.Overbar3Procedure;
import net.mcreator.worldtravaler.procedures.Overbar1Procedure;

import java.util.stream.Stream;
import java.util.Map;
import java.util.HashMap;
import java.util.AbstractMap;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.platform.GlStateManager;

@Mod.EventBusSubscriber
public class SilaOverlay {
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void eventHandler(GuiScreenEvent.DrawScreenEvent.Post event) {
		if (event.getGui() instanceof InventoryScreen) {
			int w = event.getGui().width;
			int h = event.getGui().height;
			int posX = w / 2;
			int posY = h / 2;
			World _world = null;
			double _x = 0;
			double _y = 0;
			double _z = 0;
			PlayerEntity entity = Minecraft.getInstance().player;
			if (entity != null) {
				_world = entity.world;
				_x = entity.getPosX();
				_y = entity.getPosY();
				_z = entity.getPosZ();
			}
			World world = _world;
			double x = _x;
			double y = _y;
			double z = _z;
			RenderSystem.disableDepthTest();
			RenderSystem.depthMask(false);
			RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
					GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
			RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
			RenderSystem.disableAlphaTest();
			if (true) {
				if (OverbarProcedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new,
						(_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
					Minecraft.getInstance().getTextureManager()
							.bindTexture(new ResourceLocation("world_travaler:textures/screens/texture_36_1_4.png"));
					Minecraft.getInstance().ingameGUI.blit(event.getMatrixStack(), posX + 45, posY + -22, 0, 0, 16, 16, 16, 16);
				}
				if (Overbar1Procedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new,
						(_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
					Minecraft.getInstance().getTextureManager()
							.bindTexture(new ResourceLocation("world_travaler:textures/screens/texture_36_1_3.png"));
					Minecraft.getInstance().ingameGUI.blit(event.getMatrixStack(), posX + 45, posY + -22, 0, 0, 16, 16, 16, 16);
				}
				if (Overbar3Procedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new,
						(_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
					Minecraft.getInstance().getTextureManager()
							.bindTexture(new ResourceLocation("world_travaler:textures/screens/texture_36_1_2.png"));
					Minecraft.getInstance().ingameGUI.blit(event.getMatrixStack(), posX + 45, posY + -22, 0, 0, 16, 16, 16, 16);
				}
				if (Overbar4Procedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new,
						(_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
					Minecraft.getInstance().getTextureManager()
							.bindTexture(new ResourceLocation("world_travaler:textures/screens/texture_36_1_1.png"));
					Minecraft.getInstance().ingameGUI.blit(event.getMatrixStack(), posX + 45, posY + -22, 0, 0, 16, 16, 16, 16);
				}
				if (Overbar5Procedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new,
						(_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
					Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("world_travaler:textures/screens/texture_36_1.png"));
					Minecraft.getInstance().ingameGUI.blit(event.getMatrixStack(), posX + 45, posY + -22, 0, 0, 16, 16, 16, 16);
				}
			}
			RenderSystem.depthMask(true);
			RenderSystem.enableDepthTest();
			RenderSystem.enableAlphaTest();
			RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		}
	}
}
