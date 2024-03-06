package noppes.npcs.api.gui;

import noppes.npcs.api.function.gui.GuiComponentAction;
import noppes.npcs.api.function.gui.GuiComponentState;

import java.util.UUID;

public interface ICustomGuiComponent {

    int getID();
	/**
	 * <i class="method-chaining"></i>
	 */
    ICustomGuiComponent setID(int id);

    UUID getUniqueID();

    int getPosX();
    int getPosY();
	/**
	 * <i class="method-chaining"></i>
	 */
    ICustomGuiComponent setPos(int x, int y);

    int getWidth();
    int getHeight();
	/**
	 * <i class="method-chaining"></i>
	 */
    ICustomGuiComponent setSize(int width, int height);

    default boolean isInside(double x, double y){
        return x >= getPosX() && x < (getPosX() + getWidth()) && y >= getPosY() && y < (getPosY() + getHeight());
    }

    boolean hasHoverText();
    String[] getHoverText();
	/**
	 * <i class="method-chaining"></i>
	 */
    ICustomGuiComponent setHoverText(String text);
	/**
	 * <i class="method-chaining"></i>
	 */
    ICustomGuiComponent setHoverText(String[] text);

    boolean getEnabled();
	/**
	 * <i class="method-chaining"></i>
	 */
    ICustomGuiComponent setEnabled(boolean bo);
	/**
	 * <i class="method-chaining"></i>
	 */
    ICustomGuiComponent setEnabledCondition(GuiComponentState condition);

    boolean getVisible();
	/**
	 * <i class="method-chaining"></i>
	 */
    ICustomGuiComponent setVisible(boolean bo);
	/**
	 * <i class="method-chaining"></i>
	 */
    ICustomGuiComponent setVisibleCondition(GuiComponentState condition);

    boolean getHovered();
	/**
	 * <i class="method-chaining"></i>
	 */
    ICustomGuiComponent setHovered(boolean bo);
	
	/**
	 * <i class="method-chaining"></i>
	 */
    ICustomGuiComponent setOnHover(GuiComponentAction<? extends ICustomGuiComponent> onHover);
	/**
	 * <i class="method-chaining"></i>
	 */
    ICustomGuiComponent setOnHoverExit(GuiComponentAction<? extends ICustomGuiComponent> onHoverExit);

    int getType();
}
