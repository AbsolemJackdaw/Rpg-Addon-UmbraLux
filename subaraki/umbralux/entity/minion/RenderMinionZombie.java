package subaraki.umbralux.entity.minion;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderMinionZombie extends RenderBiped<EntityMinionZombie> implements IRenderFactory{

	private static final ResourceLocation ZOMBIE_TEXTURES = new ResourceLocation("textures/entity/zombie/zombie.png");

	public RenderMinionZombie(RenderManager renderManager) {
		super(renderManager, new ModelZombie(), 0.5f);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityMinionZombie entity) {
		return ZOMBIE_TEXTURES;
	}

	@Override
	public Render createRenderFor(RenderManager manager) {
		return this;
	}

	@Override
	public void doRender(EntityMinionZombie entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected void preRenderCallback(EntityMinionZombie entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(0.75F, 0.75F, 0.75F);
		super.preRenderCallback(entitylivingbaseIn, partialTickTime);
	}
}
