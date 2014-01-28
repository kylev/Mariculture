package mariculture.core.guide;

import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import mariculture.api.guide.PageParser;
import mariculture.api.guide.XMLHelper;

public class PageStack extends PageParser {
	String stack;
	
	@Override
	public void read(XMLHelper xml) {
		stack = xml.getAttribute("item");
	}

	@Override
	public void parse() {
		drawItemStack(gui, (ItemStack) GuideHandler.getIcon(stack), x, y + 0);
	}
}