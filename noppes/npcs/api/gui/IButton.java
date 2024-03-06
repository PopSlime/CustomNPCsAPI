package noppes.npcs.api.gui;

import noppes.npcs.api.function.gui.GuiComponentAction;
import noppes.npcs.api.item.IItemStack;

public interface IButton extends ICustomGuiComponent {

    String getLabel();
	/**
	 * <i class="method-chaining"></i>
	 */
    IButton setLabel(String label);
	/**
	 * <i class="method-chaining"></i>
	 */
    IButton appendLabel(String label, Object... args);

    ITexturedRect getTextureRect();
    void setTextureRect(ITexturedRect rect);

    /** use ITexturedRect */
    @Deprecated
    String getTexture();
    /** use ITexturedRect */
    @Deprecated
    boolean hasTexture();
    /**
     * use ITexturedRect 
	 *
	 * <i class="method-chaining"></i>
	 */
    @Deprecated
    IButton setTexture(String texture);

    /** use ITexturedRect */
    @Deprecated
    int getTextureX();
    /** use ITexturedRect */
    @Deprecated
    int getTextureY();
    /**
     * use ITexturedRect 
	 *
	 * <i class="method-chaining"></i>
	 */
    @Deprecated
    IButton setTextureOffset(int textureX, int textureY);

    int getTextureHoverOffset();
	/**
	 * <i class="method-chaining"></i>
	 */
    IButton setTextureHoverOffset(int height);

    IItemStack getDisplayItem();
	/**
	 * <i class="method-chaining"></i>
	 */
    IButton setDisplayItem(IItemStack item);

    int getColor();
	/**
	 * <i class="method-chaining"></i>
	 */
    IButton setColor(int color);
    
	/**
	 * <i class="method-chaining"></i>
	 */
    IButton setOnPress(GuiComponentAction<IButton> onPress);
}
