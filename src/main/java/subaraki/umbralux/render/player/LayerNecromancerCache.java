package subaraki.umbralux.render.player;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import subaraki.umbralux.item.UmbraLuxItems;
import subaraki.umbralux.item.armor.model.ModelNecromancerArmor;

public class LayerNecromancerCache implements LayerRenderer<AbstractClientPlayer>{

	ModelNecromancerArmor head = new ModelNecromancerArmor(0.0625f);

	private RenderPlayer rp;
	public LayerNecromancerCache(RenderPlayer rp) {
		this.rp = rp;
	}

	@Override
	public void doRenderLayer(AbstractClientPlayer entitylivingbaseIn, float limbSwing, float limbSwingAmount,
			float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

		ItemStack stack = entitylivingbaseIn.getItemStackFromSlot(EntityEquipmentSlot.HEAD);

		head = new ModelNecromancerArmor(0.0625f);
		if(stack.isEmpty() || !stack.getItem().equals(UmbraLuxItems.necro_head))
			return;

		GlStateManager.pushMatrix();

		rp.getMainModel().bipedHead.postRender(0.0625f);

		GlStateManager.enableAlpha();
		GlStateManager.enableBlend();

		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("umbralux","textures/models/armor/necromancer_layer_1.png"));
		head.bipedHead.render(0.0625f);

		GlStateManager.disableAlpha();
		GlStateManager.disableBlend();

		GlStateManager.popMatrix();
	}

	@Override
	public boolean shouldCombineTextures() {
		return false;
	}
}
