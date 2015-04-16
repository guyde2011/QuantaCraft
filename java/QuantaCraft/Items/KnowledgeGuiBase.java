package QuantaCraft.Items;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface KnowledgeGuiBase {

    public void renderGuiName(Gui gui , RenderItem render , FontRenderer fontRender , int x , int y);
    
    public void renderGuiRecipe(Gui gui , RenderItem render , FontRenderer fontRender , int x , int y);
}
