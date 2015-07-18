package kkaylium.TechliumCraft.guis;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class GuiGlowFurnaceL1 extends GuiScreen{

	public GuiGlowFurnaceL1() {
		super();
	}

//	@Override
//	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
//		
//	}
	
	@Override
	public void initGui() {
		// TODO Auto-generated method stub
		super.initGui();
	}
	@Override
	public void drawScreen(int par1, int par2, float par3)
    {
		drawDefaultBackground();
		
		//draw image
		this.mc.renderEngine.bindTexture(new ResourceLocation(""));
		//drawTexturedModalRect(posX, poY, imageX, imageY, width, height);
		
		drawString(this.fontRendererObj, "Testing", 3, 50, 0xFFFFFF);
    }
	
	public void updateScreen() {};
	@Override
	public boolean doesGuiPauseGame() {
		// TODO Auto-generated method stub
		return super.doesGuiPauseGame();
	}

}
