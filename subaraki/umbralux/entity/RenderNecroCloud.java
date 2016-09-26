package subaraki.umbralux.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderNecroCloud extends Render<EntityNecroCloud> implements IRenderFactory{

	public RenderNecroCloud(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityNecroCloud entity) {
		return null;
	}
	
	@Override
	public void doRender(EntityNecroCloud entity, double x, double y, double z, float entityYaw, float partialTicks) {

	}
	
	@Override
	public Render createRenderFor(RenderManager manager) {
		return this;
	}
}
