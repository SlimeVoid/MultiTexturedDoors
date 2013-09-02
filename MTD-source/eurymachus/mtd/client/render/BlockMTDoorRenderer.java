package eurymachus.mtd.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import eurymachus.mtd.blocks.BlockMTDoor;
import eurymachus.mtd.core.MTDBlocks;
import eurymachus.mtd.core.MTDCore;

public class BlockMTDoorRenderer implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderblocks) {
		if (block.blockID == MTDBlocks.mtDoor.id || block.blockID == MTDBlocks.mtSensibleDoor.id) {
			BlockMTDoor mtDoor = (BlockMTDoor) block;
			return renderblocks.renderBlockDoor(mtDoor, x, y, z);
		}
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return false;
	}

	@Override
	public int getRenderId() {
		return MTDCore.mtDoorRenderID;
	}
}
