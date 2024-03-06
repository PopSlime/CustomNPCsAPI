 package noppes.npcs.api.gui;

 import noppes.npcs.api.function.gui.GuiComponentAction;

 public interface ISlider extends ICustomGuiComponent {

    float getValue();
	/**
	 * <i class="method-chaining"></i>
	 */
    ISlider setValue(float value);

    String getFormat();
	/**
	 * <i class="method-chaining"></i>
	 */
    ISlider setFormat(String format);

    float getMin();
	/**
	 * <i class="method-chaining"></i>
	 */
    ISlider setMin(float min);

    float getMax();
	/**
	 * <i class="method-chaining"></i>
	 */
    ISlider setMax(float max);

    int getDecimals();
	/**
	 * <i class="method-chaining"></i>
	 */
    ISlider setDecimals(int i);
	
	/**
	 * <i class="method-chaining"></i>
	 */
     ISlider setOnChange(GuiComponentAction<ISlider> onChange);
}
