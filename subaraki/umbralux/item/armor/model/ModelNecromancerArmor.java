package subaraki.umbralux.item.armor.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelNecromancerArmor extends ModelBiped {
	// fields
	private ModelRenderer head;
	private ModelRenderer body;
	private ModelRenderer rightarm;
	private ModelRenderer leftarm;
	private ModelRenderer rightleg;
	private ModelRenderer leftleg;
	private ModelRenderer Spaulder_1;
	private ModelRenderer Spaulder_2;
	private ModelRenderer Shape3;
	private ModelRenderer ToeTipR;
	private ModelRenderer Shape5;
	private ModelRenderer ToeTipL;
	private ModelRenderer TieTop;
	private ModelRenderer TieBottom;

	public ModelNecromancerArmor(float par1) {

		super(par1, 0, 65, 64);

		textureWidth = 65;
		textureHeight = 64;

		Spaulder_1 = new ModelRenderer(this, 0, 32);
		Spaulder_1.addBox(0F, -2F, -2.5F, 5, 1, 5, par1 / 1.9f);
		Spaulder_1.setRotationPoint(0F, 0F, 0F);
		Spaulder_1.setTextureSize(65, 64);
		Spaulder_1.mirror = true;
		setRotation(Spaulder_1, 0F, 0F, 0.5235988F);
		Spaulder_2 = new ModelRenderer(this, 0, 32);
		Spaulder_2.addBox(0F, 0F, -2.5F, 5, 1, 5, par1 / 1.9f);
		Spaulder_2.setRotationPoint(0F, 0F, 0F);
		Spaulder_2.setTextureSize(65, 64);
		Spaulder_2.mirror = true;
		setRotation(Spaulder_2, 0F, 0F, 0.7872665F);
		Shape3 = new ModelRenderer(this, 0, 41);
		Shape3.addBox(-2.5F, 5F, -2.5F, 5, 2, 5, par1 / 1.5f);
		Shape3.setRotationPoint(0F, 0F, 0F);
		Shape3.setTextureSize(65, 64);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 0, 41);
		Shape5.addBox(-2.5F, 5F, -2.5F, 5, 2, 5, par1 / 1.5f);
		Shape5.setRotationPoint(0F, 0F, 0F);
		Shape5.setTextureSize(65, 64);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
		ToeTipR = new ModelRenderer(this, 0, 38);
		ToeTipR.addBox(-2F, 11F, -4F, 4, 2, 1);
		ToeTipR.setRotationPoint(0F, 0F, 0F);
		ToeTipR.setTextureSize(65, 64);
		ToeTipR.mirror = true;
		setRotation(ToeTipR, 0F, 0F, 0F);
		ToeTipL = new ModelRenderer(this, 0, 38);
		ToeTipL.addBox(-2F, 11F, -4F, 4, 2, 1);
		ToeTipL.setRotationPoint(0F, 0F, 0F);
		ToeTipL.setTextureSize(65, 64);
		ToeTipL.mirror = true;
		setRotation(ToeTipL, 0F, 0F, 0F);
		TieTop = new ModelRenderer(this, 22, 32);
		TieTop.addBox(1F, 1F, -3.5F, 1, 1, 1);
		TieTop.setRotationPoint(0F, 0F, 0F);
		TieTop.setTextureSize(65, 64);
		TieTop.mirror = true;
		setRotation(TieTop, 0F, 0F, 0.7853982F);
		TieBottom = new ModelRenderer(this, 22, 34);
		TieBottom.addBox(-0.5F, 2.5F, -3.4F, 1, 5, 1);
		TieBottom.setRotationPoint(0F, 0F, 0F);
		TieBottom.setTextureSize(65, 64);
		TieBottom.mirror = true;
		setRotation(TieBottom, 0F, 0F, 0F);

		bipedBody.addChild(TieBottom);
		bipedBody.addChild(TieTop);
		bipedRightLeg.addChild(ToeTipR);
		bipedRightLeg.addChild(Shape3);
		bipedLeftLeg.addChild(Shape5);
		bipedLeftLeg.addChild(ToeTipL);
		bipedLeftArm.addChild(Spaulder_1);
		bipedLeftArm.addChild(Spaulder_2);

	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}