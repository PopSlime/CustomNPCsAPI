package noppes.npcs.api.gui;

public interface IButtonList extends IButton {
    
	/**
	 * <i class="method-chaining"></i>
	 */
    IButtonList setValues(String... values);
    String[] getValues();
    
	/**
	 * <i class="method-chaining"></i>
	 */
    IButtonList setSelected(int selected);
    int getSelected();

    ITexturedRect getLeftTexture();
    ITexturedRect getRightTexture();
}
