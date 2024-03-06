package noppes.npcs.api.gui;

public interface ITexturedRect extends ICustomGuiComponent {

    String getTexture();
	/**
	 * <i class="method-chaining"></i>
	 */
    ITexturedRect setTexture(String texture);

    float getScale();
	/**
	 * <i class="method-chaining"></i>
	 */
    ITexturedRect setScale(float scale);

    int getTextureX();
    int getTextureY();
	/**
	 * <i class="method-chaining"></i>
	 */
    ITexturedRect setTextureOffset(int offsetX, int offsetY);
	
	/**
	 * <i class="method-chaining"></i>
	 */
    ITexturedRect setRepeatingTexture(int width, int height, int borderSize);
}
