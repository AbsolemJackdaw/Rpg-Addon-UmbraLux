package subaraki.umbralux.entity.minion;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderMinionSkeleton extends RenderBiped<EntityMinionSkeleton> implements IRenderFactory{

    private static final ResourceLocation SKELETON_TEXTURES = new ResourceLocation("textures/entity/skeleton/skeleton.png");

	public RenderMinionSkeleton(RenderManager renderManager) {
		super(renderManager, new ModelMinionSkeleton(), 0.5f, 0.5f);
		 this.addLayer(new LayerHeldItem(this));
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityMinionSkeleton entity) {
		return SKELETON_TEXTURES;
	}

	@Override
	public Render createRenderFor(RenderManager manager) {
		return this;
	}

	@Override
	public void doRender(EntityMinionSkeleton entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected void preRenderCallback(EntityMinionSkeleton entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(0.75F, 0.75F, 0.75F);
		super.preRenderCallback(entitylivingbaseIn, partialTickTime);
	}
	
	@Override
	public void transformHeldFull3DItemLayer()
    {
        GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
    }
}
