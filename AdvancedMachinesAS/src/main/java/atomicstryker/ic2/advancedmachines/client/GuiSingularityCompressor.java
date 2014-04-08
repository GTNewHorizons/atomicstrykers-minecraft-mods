package atomicstryker.ic2.advancedmachines.client;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import atomicstryker.ic2.advancedmachines.ContainerAdvancedMachine;
import atomicstryker.ic2.advancedmachines.IAdvancedMachine;
import atomicstryker.ic2.advancedmachines.TileEntityAdvancedCompressor;

public class GuiSingularityCompressor extends GuiContainer
{
    private static ResourceLocation tex = new ResourceLocation("advancedmachines", "textures/gui/GUISingularity.png");
    
    private ContainerAdvancedMachine container;

    public GuiSingularityCompressor(ContainerAdvancedMachine csm, TileEntityAdvancedCompressor te)
    {
        super(csm);
        container = csm;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        this.fontRendererObj.drawString(StatCollector.translateToLocal("item.advancedmachines:singularityCompressor.name"), 8, 6, 4210752);
        this.fontRendererObj.drawString("Inventory", 8, this.ySize - 96 + 2, 4210752);
        this.fontRendererObj.drawString("Pressure:", 4, 36, 4210752);
        this.fontRendererObj.drawString(((IAdvancedMachine)container.tileEntity).printFormattedData(), 10, 44, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(tex);
        int j = (this.width - this.xSize) / 2;
        int k = (this.height - this.ySize) / 2;
        drawTexturedModalRect(j, k, 0, 0, this.xSize, this.ySize);
        
        int chargeLevel = (int)(14.0F * container.tileEntity.getChargeLevel());
        int progress = (int)(24.0F * container.tileEntity.getProgress());
        
        if (chargeLevel > 0) drawTexturedModalRect(j + 56, k + 36 + 14 - chargeLevel, 176, 14 - chargeLevel, 14, chargeLevel);
        if (progress > 0) drawTexturedModalRect(j + 79, k + 34, 176, 14, progress + 1, 16);
    }
}