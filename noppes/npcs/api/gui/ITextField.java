package noppes.npcs.api.gui;

import noppes.npcs.api.function.gui.GuiComponentAction;

public interface ITextField extends ICustomGuiComponent {

    String getText();
	/**
	 * <i class="method-chaining"></i>
	 */
    ITextField setText(String text);

    int getColor();
	/**
	 * <i class="method-chaining"></i>
	 */
    ITextField setColor(int color);
    
	/**
	 * <i class="method-chaining"></i>
	 */
    ITextField setOnChange(GuiComponentAction<ITextField> onChange);
	/**
	 * <i class="method-chaining"></i>
	 */
    ITextField setOnFocusLost(GuiComponentAction<ITextField> onChange);
    
	/**
	 * <i class="method-chaining"></i>
	 */
    ITextField setFocused(boolean bo);
    boolean getFocused();

    /**
     * @param type 0:string, 1:int, 2:hex, 3:float
     *
	 * <i class="method-chaining"></i>
     */
    ITextField setCharacterType(int type);
    /**
     * @return 0:string, 1:int, 2:hex, 3:float
     */
    int getCharacterType();

    /**
     * @return Incase CharacterType is 1 or 2 it will convert text to an integer
     */
    int getInteger();
    /**
     * @param i Incase CharacterType is 1 or 2 set the text as the integer, if its CharacterType 2 it will convert to Hex
     *
	 * <i class="method-chaining"></i>
     */
    ITextField setInteger(int i);

    float getFloat();
	/**
	 * <i class="method-chaining"></i>
	 */
    ITextField setFloat(float f);

    /**
     * Incase CharacterType is 1 or 2, you can set the min and max value
     *
	 * <i class="method-chaining"></i>
     */
    ITextField setMinMax(int min, int max);
}
