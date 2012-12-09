package mtd.render;

import mtd.blocks.BlockMTDoor;
import mtd.core.MTDBlocks;
import mtd.core.MTDCore;
import net.minecraft.src.Block;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.Tessellator;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class BlockMTDoorRenderer implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderblocks) {
		if (block.blockID == MTDBlocks.mtDoor.id) {
			Tessellator tesselator = Tessellator.instance;
			BlockMTDoor mtDoor = (BlockMTDoor) block;
			boolean var7 = false;
			float var8 = 0.5F;
			float var9 = 1.0F;
			float var10 = 0.8F;
			float var11 = 0.6F;
			int var12 = mtDoor.getMixedBrightnessForBlock(
					renderblocks.blockAccess,
					x,
					y,
					z);
			tesselator.setBrightness(mtDoor.getBlockBoundsMinY() > 0.0D ? var12 : mtDoor
					.getMixedBrightnessForBlock(
							renderblocks.blockAccess,
							x,
							y - 1,
							z));
			tesselator.setColorOpaque_F(var8, var8, var8);
			renderblocks.renderBottomFace(mtDoor, x, y, z, mtDoor
					.getBlockTexture(renderblocks.blockAccess, x, y, z, 0));
			var7 = true;
			tesselator.setBrightness(mtDoor.getBlockBoundsMaxY() < 1.0D ? var12 : mtDoor
					.getMixedBrightnessForBlock(
							renderblocks.blockAccess,
							x,
							y + 1,
							z));
			tesselator.setColorOpaque_F(var9, var9, var9);
			renderblocks.renderTopFace(mtDoor, x, y, z, mtDoor.getBlockTexture(
					renderblocks.blockAccess,
					x,
					y,
					z,
					1));
			var7 = true;
			tesselator.setBrightness(mtDoor.getBlockBoundsMinZ() > 0.0D ? var12 : mtDoor
					.getMixedBrightnessForBlock(
							renderblocks.blockAccess,
							x,
							y,
							z - 1));
			tesselator.setColorOpaque_F(var10, var10, var10);
			int var13 = mtDoor.getBlockTexture(
					renderblocks.blockAccess,
					x,
					y,
					z,
					2);

			if (var13 < 0) {
				renderblocks.flipTexture = true;
				var13 = -var13;
			}

			renderblocks.renderEastFace(mtDoor, x, y, z, var13);
			var7 = true;
			renderblocks.flipTexture = false;
			tesselator.setBrightness(mtDoor.getBlockBoundsMaxZ() < 1.0D ? var12 : mtDoor
					.getMixedBrightnessForBlock(
							renderblocks.blockAccess,
							x,
							y,
							z + 1));
			tesselator.setColorOpaque_F(var10, var10, var10);
			var13 = mtDoor
					.getBlockTexture(renderblocks.blockAccess, x, y, z, 3);

			if (var13 < 0) {
				renderblocks.flipTexture = true;
				var13 = -var13;
			}

			renderblocks.renderWestFace(mtDoor, x, y, z, var13);
			var7 = true;
			renderblocks.flipTexture = false;
			tesselator.setBrightness(mtDoor.getBlockBoundsMinX() > 0.0D ? var12 : mtDoor
					.getMixedBrightnessForBlock(
							renderblocks.blockAccess,
							x - 1,
							y,
							z));
			tesselator.setColorOpaque_F(var11, var11, var11);
			var13 = mtDoor
					.getBlockTexture(renderblocks.blockAccess, x, y, z, 4);

			if (var13 < 0) {
				renderblocks.flipTexture = true;
				var13 = -var13;
			}

			renderblocks.renderNorthFace(mtDoor, x, y, z, var13);
			var7 = true;
			renderblocks.flipTexture = false;
			tesselator.setBrightness(mtDoor.getBlockBoundsMaxX() < 1.0D ? var12 : mtDoor
					.getMixedBrightnessForBlock(
							renderblocks.blockAccess,
							x + 1,
							y,
							z));
			tesselator.setColorOpaque_F(var11, var11, var11);
			var13 = mtDoor
					.getBlockTexture(renderblocks.blockAccess, x, y, z, 5);

			if (var13 < 0) {
				renderblocks.flipTexture = true;
				var13 = -var13;
			}

			renderblocks.renderSouthFace(mtDoor, x, y, z, var13);
			var7 = true;
			renderblocks.flipTexture = false;
			return var7;
		}
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getRenderId() {
		return MTDCore.mtDoorRenderID;
	}
}
