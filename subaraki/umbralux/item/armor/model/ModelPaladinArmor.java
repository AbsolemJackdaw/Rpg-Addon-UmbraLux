package subaraki.umbralux.item.armor.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHandSide;

public class ModelPaladinArmor extends ModelBiped {

	private ModelRenderer LspoulderDown;
	private ModelRenderer helmV1;
	private ModelRenderer hemlv4;
	private ModelRenderer helmV2;
	private ModelRenderer helmV3;
	private ModelRenderer plume1;
	private ModelRenderer plume2;
	private ModelRenderer plume3;
	private ModelRenderer plume4;
	private ModelRenderer plume5;
	private ModelRenderer plume6;
	private ModelRenderer plume7;
	private ModelRenderer plume8;
	private ModelRenderer plume10;
	private ModelRenderer plume9;
	private ModelRenderer lspoulderMid;
	private ModelRenderer lspoulderTop;
	private ModelRenderer lspoulderBase;
	private ModelRenderer rspoulderMid;
	private ModelRenderer RspoulderTop;
	private ModelRenderer RspoulderBottom;
	private ModelRenderer rspoulderBase;

	public ModelPaladinArmor(float par1) {
		super(par1, 0, 65, 64);

		textureWidth = 65;
		textureHeight = 64;

		helmV1 = new ModelRenderer(this, 0, 32);
		helmV1.addBox(-3.5F, -7F, -4.7F, 1, 6, 1);
		helmV1.setRotationPoint(0F, 0F, 0F);
		helmV1.setTextureSize(65, 64);
		setRotation(helmV1, 0F, 0F, 0F);
		hemlv4 = new ModelRenderer(this, 0, 32);
		hemlv4.addBox(2.5F, -7F, -4.7F, 1, 6, 1);
		hemlv4.setRotationPoint(0F, 0F, 0F);
		hemlv4.setTextureSize(65, 64);
		setRotation(hemlv4, 0F, 0F, 0F);
		helmV2 = new ModelRenderer(this, 0, 32);
		helmV2.addBox(-1.5F, -7F, -4.7F, 1, 6, 1);
		helmV2.setRotationPoint(0F, 0F, 0F);
		helmV2.setTextureSize(65, 64);
		setRotation(helmV2, 0F, 0F, 0F);
		helmV3 = new ModelRenderer(this, 0, 32);
		helmV3.addBox(0.5F, -7F, -4.7F, 1, 6, 1);
		helmV3.setRotationPoint(0F, 0F, 0F);
		helmV3.setTextureSize(65, 64);
		setRotation(helmV3, 0F, 0F, 0F);
		plume1 = new ModelRenderer(this, 0, 41);
		plume1.addBox(-0.5F, -9F, 0F, 1, 1, 1);
		plume1.setRotationPoint(0F, 0F, 0F);
		plume1.setTextureSize(65, 64);
		setRotation(plume1, 0F, 0F, 0F);
		plume2 = new ModelRenderer(this, 0, 40);
		plume2.addBox(-0.5F, -10F, -1F, 1, 1, 4);
		plume2.setRotationPoint(0F, 0F, 0F);
		plume2.setTextureSize(65, 64);
		setRotation(plume2, 0F, 0F, 0F);
		plume3 = new ModelRenderer(this, 0, 34);
		plume3.addBox(-0.5F, -11F, -2F, 1, 1, 10);
		plume3.setRotationPoint(0F, 0F, 0F);
		plume3.setTextureSize(65, 64);
		setRotation(plume3, 0F, 0F, 0F);
		plume4 = new ModelRenderer(this, 0, 33);
		plume4.addBox(-0.5F, -12F, -3F, 1, 1, 11);
		plume4.setRotationPoint(0F, 0F, 0F);
		plume4.setTextureSize(65, 64);
		setRotation(plume4, 0F, 0F, 0F);
		plume5 = new ModelRenderer(this, 0, 34);
		plume5.addBox(-0.5F, -13F, -3F, 1, 1, 10);
		plume5.setRotationPoint(0F, 0F, 0F);
		plume5.setTextureSize(65, 64);
		setRotation(plume5, 0F, 0F, 0F);
		plume6 = new ModelRenderer(this, 0, 35);
		plume6.addBox(-0.5F, -14F, -2F, 1, 1, 9);
		plume6.setRotationPoint(0F, 0F, 0F);
		plume6.setTextureSize(65, 64);
		setRotation(plume6, 0F, 0F, 0F);
		plume7 = new ModelRenderer(this, 0, 37);
		plume7.addBox(-0.5F, -15F, -1F, 1, 1, 7);
		plume7.setRotationPoint(0F, 0F, 0F);
		plume7.setTextureSize(65, 64);
		setRotation(plume7, 0F, 0F, 0F);
		plume8 = new ModelRenderer(this, 0, 39);
		plume8.addBox(-0.5F, -16F, 0F, 1, 1, 5);
		plume8.setRotationPoint(0F, 0F, 0F);
		plume8.setTextureSize(65, 64);
		setRotation(plume8, 0F, 0F, 0F);
		plume10 = new ModelRenderer(this, 0, 41);
		plume10.addBox(-0.5F, -10F, 6F, 1, 1, 3);
		plume10.setRotationPoint(0F, 0F, 0F);
		plume10.setTextureSize(65, 64);
		setRotation(plume10, 0F, 0F, 0F);
		plume9 = new ModelRenderer(this, 0, 42);
		plume9.addBox(-0.5F, -9F, 7F, 1, 1, 2);
		plume9.setRotationPoint(0F, 0F, 0F);
		plume9.setTextureSize(65, 64);
		setRotation(plume9, 0F, 0F, 0F);
		
		LspoulderDown = new ModelRenderer(this, 40, 46);
		LspoulderDown.addBox(4F, 2F, -2.5F, 3, 1, 5, par1/3);
		LspoulderDown.setRotationPoint(0F, 0F, 0F);
		LspoulderDown.setTextureSize(65, 64);
		setRotation(LspoulderDown, 0F, 0F, 0.3F);
		lspoulderMid = new ModelRenderer(this, 36, 32);
		lspoulderMid.addBox(1F, -3F, -3F, 4, 8, 6, par1/3);
		lspoulderMid.setRotationPoint(0F, 0F, 0F);
		lspoulderMid.setTextureSize(65, 64);
		setRotation(lspoulderMid, 0F, 0F, -0.3F);
		lspoulderTop = new ModelRenderer(this, 42, 52);
		lspoulderTop.addBox(0F, -5F, -2.5F, 2, 3, 5, par1/3);
		lspoulderTop.setRotationPoint(0F, 0F, 0F);
		lspoulderTop.setTextureSize(65, 64);
		setRotation(lspoulderTop, 0F, 0F, 0F);
		lspoulderBase = new ModelRenderer(this, 26, 52);
		lspoulderBase.addBox(0F, -3F, -2.5F, 3, 7, 5, par1/1.5f);
		lspoulderBase.setRotationPoint(0F, 0F, 0F);
		lspoulderBase.setTextureSize(65, 64);
		setRotation(lspoulderBase, 0F, 0F, 0F);
		
		
		rspoulderMid = new ModelRenderer(this, 36, 32);
		rspoulderMid.addBox(1F, -3F, -3F, 4, 8, 6, par1/3f);
		rspoulderMid.setRotationPoint(0F, 0F, 0F);
		rspoulderMid.setTextureSize(65, 64);
		setRotation(rspoulderMid, 0F, -3.2F, 0.3F);
		RspoulderTop = new ModelRenderer(this, 42, 52);
		RspoulderTop.addBox(-2F, -5F, -2.5F, 2, 3, 5, par1/3f);
		RspoulderTop.setRotationPoint(0F, 0F, 0F);
		RspoulderTop.setTextureSize(65, 64);
		setRotation(RspoulderTop, 0F, 0F, 0F);
		RspoulderBottom = new ModelRenderer(this, 40, 46);
		RspoulderBottom.addBox(-7F, 2F, -2.5F, 3, 1, 5, par1/3f);
		RspoulderBottom.setRotationPoint(0F, 0F, 0F);
		RspoulderBottom.setTextureSize(65, 64);
		setRotation(RspoulderBottom, 0F, 0F, -0.3F);
		rspoulderBase = new ModelRenderer(this, 26, 52);
		rspoulderBase.addBox(-3F, -3F, -2.5F, 3, 7, 5, par1/1.5f);
		rspoulderBase.setRotationPoint(0F, 0F, 0F);
		rspoulderBase.setTextureSize(65, 64);
		setRotation(rspoulderBase, 0F, 0F, 0F);

		this.bipedHeadwear.addChild(helmV1);
		this.bipedHeadwear.addChild(helmV2);
		this.bipedHeadwear.addChild(helmV3);
		this.bipedHeadwear.addChild(hemlv4);

		this.bipedHead.addChild(plume1);
		this.bipedHead.addChild(plume2);
		this.bipedHead.addChild(plume3);
		this.bipedHead.addChild(plume4);
		this.bipedHead.addChild(plume5);
		this.bipedHead.addChild(plume6);
		this.bipedHead.addChild(plume7);
		this.bipedHead.addChild(plume8);
		this.bipedHead.addChild(plume9);
		this.bipedHead.addChild(plume10);
		
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {

		if(entity instanceof EntityLivingBase){
			EntityLivingBase elb = (EntityLivingBase)entity;
			if(elb.getPrimaryHand() == EnumHandSide.RIGHT){
				if(bipedLeftArm.childModels != null && !bipedLeftArm.childModels.isEmpty())
					bipedLeftArm.childModels.clear();
				if(bipedRightArm.childModels == null || bipedRightArm.childModels.isEmpty()){
					this.bipedRightArm.addChild(rspoulderBase);
					this.bipedRightArm.addChild(rspoulderMid);
					this.bipedRightArm.addChild(RspoulderTop);
					this.bipedRightArm.addChild(RspoulderBottom);
				}
			}else{
				if(elb.getPrimaryHand() == EnumHandSide.LEFT){
					if(bipedRightArm.childModels != null && !bipedRightArm.childModels.isEmpty())
						bipedRightArm.childModels.clear();
					if(bipedLeftArm.childModels == null || bipedLeftArm.childModels.isEmpty()){
						this.bipedLeftArm.addChild(lspoulderBase);
						this.bipedLeftArm.addChild(lspoulderMid);
						this.bipedLeftArm.addChild(lspoulderTop);
						this.bipedLeftArm.addChild(LspoulderDown);
					}
				}
			}
		}
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		super.render(entity, f, f1, f2, f3, f4, f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}