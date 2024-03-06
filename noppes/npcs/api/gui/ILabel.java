package noppes.npcs.api.gui;

public interface ILabel extends ICustomGuiComponent {

    String getText();
	/**
	 * <i class="method-chaining"></i>
	 */
    ILabel setText(String label);
	/**
	 * <i class="method-chaining"></i>
	 */
    ILabel append(String label, Object... args);

    int getColor();
	/**
	 * <i class="method-chaining"></i>
	 */
    ILabel setColor(int color);

    float getScale();
	/**
	 * <i class="method-chaining"></i>
	 */
    ILabel setScale(float scale);

    @Deprecated
    boolean getCentered();
	/**
	 * <i class="method-chaining"></i>
	 */
    @Deprecated
    ILabel setCentered(boolean bo);

    /**
     * Where to align the text
     * @return 0: left, 1: center, 2: right
     */
    int getAlignment();

    /**
     * Where to align the text
     * @param type 0: left, 1: center, 2: right
     *
	 * <i class="method-chaining"></i>
     */
    ILabel setAlignment(int type);

}
