package noppes.npcs.api.gui;

public interface ITexturedButton extends IButton {

    String getTexture();
	/**
	 * <i class="method-chaining"></i>
	 */
    ITexturedButton setTexture(String texture);

    int getTextureX();
    int getTextureY();
	/**
	 * <i class="method-chaining"></i>
	 */
    ITexturedButton setTextureOffset(int textureX, int textureY);

}
