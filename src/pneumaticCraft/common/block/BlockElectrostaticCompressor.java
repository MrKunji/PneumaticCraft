package pneumaticCraft.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import pneumaticCraft.common.tileentity.TileEntityElectrostaticCompressor;
import pneumaticCraft.proxy.CommonProxy;

public class BlockElectrostaticCompressor extends BlockPneumaticCraftModeled{
    public BlockElectrostaticCompressor(Material par2Material){
        super(par2Material);
    }

    @Override
    protected Class<? extends TileEntity> getTileEntityClass(){
        return TileEntityElectrostaticCompressor.class;
    }

    @Override
    protected int getGuiID(){
        return CommonProxy.GUI_ID_ELECTROSTATIC_COMPRESSOR;
    }

    /**
     * Returns true if the block is emitting direct/strong redstone power on the
     * specified side. Args: World, X, Y, Z, side. Note that the side is
     * reversed - eg it is 1 (up) when checking the bottom of the block.
     */
    @Override
    public int isProvidingStrongPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5){
        return 0;
    }

    /**
     * Returns true if the block is emitting indirect/weak redstone power on the
     * specified side. If isBlockNormalCube returns true, standard redstone
     * propagation rules will apply instead and this will not be called. Args:
     * World, X, Y, Z, side. Note that the side is reversed - eg it is 1 (up)
     * when checking the bottom of the block.
     */
    @Override
    public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5){
        TileEntity te = par1IBlockAccess.getTileEntity(par2, par3, par4);
        if(te instanceof TileEntityElectrostaticCompressor) {
            TileEntityElectrostaticCompressor teEc = (TileEntityElectrostaticCompressor)te;
            return teEc.shouldEmitRedstone() ? 15 : 0;
        }

        return 0;
    }

    /**
     * Called to determine whether to allow the a block to handle its own indirect power rather than using the default rules.
     * @param world The world
     * @param x The x position of this block instance
     * @param y The y position of this block instance
     * @param z The z position of this block instance
     * @param side The INPUT side of the block to be powered - ie the opposite of this block's output side
     * @return Whether Block#isProvidingWeakPower should be called when determining indirect power
     */
    @Override
    public boolean shouldCheckWeakPower(IBlockAccess world, int x, int y, int z, int side){
        return true;
    }

    @Override
    public boolean func_149730_j(){
        return true;
    }
}
