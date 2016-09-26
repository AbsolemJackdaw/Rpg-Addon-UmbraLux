package subaraki.umbralux.entity;

import static net.minecraft.client.renderer.GlStateManager.popMatrix;
import static net.minecraft.client.renderer.GlStateManager.pushMatrix;
import static net.minecraft.client.renderer.GlStateManager.rotate;
import static net.minecraft.client.renderer.GlStateManager.scale;
import static net.minecraft.client.renderer.GlStateManager.translate;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import subaraki.umbralux.item.UmbraLuxItems;

public class RenderSwordSpecial extends Render<EntitySwordSpecial>{

	private float translateY = 10F;

	public RenderSwordSpecial(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySwordSpecial entity) {
		return null;
	}

	@Override
	public void doRender(EntitySwordSpecial entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		ItemStack sword = entity.getItemStackFromSlot(null);
		if(sword == null)
			sword = new ItemStack(UmbraLuxItems.paladin_sword);

		RenderItem render = Minecraft.getMinecraft().getRenderItem();

		translateY = 10f - entity.ticksExisted;

		if(translateY < 1.3f)
			translateY = 1.3f;

		pushMatrix();
		translate(x, y, z);
		scale(1.2f,1.2f,1.2f);

		if(translateY > 1.3f)
			translate(0, translateY, 0);
		else
			translate(0, 1.3f + Math.sin(entity.ticksExisted/10f)/10f, 0);


		rotate(180,1,0,0);

		render.renderItem(sword, ItemCameraTransforms.TransformType.FIXED);
		popMatrix();

	}

}
