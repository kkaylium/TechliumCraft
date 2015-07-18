package kkaylium.TechliumCraft.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityGlowPlanks extends TileEntity{

	public int color; // 14 is base color

    public boolean isDirty = false;

    public TileEntityGlowPlanks()
    {
        color = 12;
    }

    @Override
    public void updateEntity()
    {
        if (isDirty)
        {
            isDirty = false;
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);   
        }
    }

    public int getCrystalUsed(int cc)
    {
        if (cc <= 12) color = cc;
        else color = 12;
        isDirty = true;
        return color;
    }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);

        compound.setShort("Color", (short) color);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);

        color = compound.getShort("Color");
    }

    /* Packets */
    @Override
    public S35PacketUpdateTileEntity getDescriptionPacket()
    {
        NBTTagCompound tag = new NBTTagCompound();
        writeToNBT(tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
    {
        readFromNBT(packet.func_148857_g());
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }
	
}
