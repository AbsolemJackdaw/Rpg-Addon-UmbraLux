package subaraki.umbralux.handler.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import subaraki.umbralux.entity.EntityNecroCloud;
import subaraki.umbralux.entity.EntitySwordSpecial;
import subaraki.umbralux.entity.RenderNecroCloud;
import subaraki.umbralux.entity.RenderSwordSpecial;
import subaraki.umbralux.entity.minion.EntityMinionSkeleton;
import subaraki.umbralux.entity.minion.EntityMinionZombie;
import subaraki.umbralux.entity.minion.RenderMinionSkeleton;
import subaraki.umbralux.entity.minion.RenderMinionZombie;
import subaraki.umbralux.item.UmbraLuxItems;
import subaraki.umbralux.item.armor.model.ModelNecromancerArmor;
import subaraki.umbralux.item.armor.model.ModelPaladinArmor;
import subaraki.umbralux.render.player.LayerNecromancerCache;

public class ClientProxy extends ServerProxy{

	private ModelNecromancerArmor necroChest = new ModelNecromancerArmor(1.0F);
	private ModelNecromancerArmor necroRest = new ModelNecromancerArmor(0.5F);
	public static final String NECROCHEST = "necroChest";
	public static final String NECROREST = "necroRest";

	private ModelPaladinArmor palaChest = new ModelPaladinArmor(0.8F);
	private ModelPaladinArmor palaRest = new ModelPaladinArmor(0.5F);
	public static final String PALACHEST = "palaChest";
	public static final String PALAREST = "palaRest";

	
	@Override
	public ModelBiped getArmorModel(String id){
		switch (id) {
		case NECROCHEST:
			return necroChest;
		case NECROREST:
			return necroRest;
		case PALACHEST:
			return palaChest;
		case PALAREST:
			return palaRest;
		}
		return null;
	}

	@Override
	public void registerRenders(){
		UmbraLuxItems.registerRenders();
		
		RenderingRegistry.registerEntityRenderingHandler(EntityNecroCloud.class, RenderNecroCloud::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityMinionZombie.class, RenderMinionZombie::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityMinionSkeleton.class, RenderMinionSkeleton::new);

		RenderingRegistry.registerEntityRenderingHandler(EntitySwordSpecial.class, RenderSwordSpecial::new);

	}

	@Override
	public void registerClientEvents(){}

	@Override
	public void registerColors() {
		ItemColors ic = Minecraft.getMinecraft().getItemColors();

		ic.registerItemColorHandler(

				new IItemColor() {
					@Override
					public int getColorFromItemstack(ItemStack stack, int tintIndex) {
						if(stack != ItemStack.EMPTY){
							switch(stack.getMetadata())
							{
							case 0 : 
								return 0xb60048;
							case 1 : 
								return 0xeebc23;
							}
						}

						return 0xffffff;
					}
				}, 
				UmbraLuxItems.craftLeather
				);
	}

	@Override
	public void addRenderLayers(){

		String types[] = new String[]{"default","slim"};

		for(String type : types){
			RenderPlayer renderer = ((RenderPlayer)Minecraft.getMinecraft().getRenderManager().getSkinMap().get(type));
			renderer.addLayer(new LayerNecromancerCache(renderer));
		}
	}
}
