package kkaylium.TechliumCraft.tileentities.machines;

import kkaylium.TechliumCraft.recipes.GlowFurnaceRecipes;
import kkaylium.TechliumCraft.recipes.Recipes1_1;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityGlowFurnace extends TileEntity implements ISidedInventory {

    public static final int IN1_LOC = 0;
    public static final int IN2_LOC = 1;
    public static final int OUT_LOC = 2;
    public static final int FUEL_LOC = 3;
    public static final int BUCKET_OUT_LOC = 4;
    public static final int ROOM_TEMP = 20000;
    public static final int MIN_TEMP = -50000;

    public static int INV_SIZE = 5;
    private ItemStack[] inventory = new ItemStack[INV_SIZE];
    private int ticksLeft = 0;
    private int maxTicks = 0;
    private int magic = ROOM_TEMP;
    private int magicAmount = MIN_TEMP;

	public TileEntityGlowFurnace(){
		
	}

    @Override
    public int getSizeInventory(){
        return INV_SIZE;
    }

    @Override
    public ItemStack getStackInSlot(int par1){
        if(par1 > INV_SIZE) return null;
        return inventory[par1];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount){
        ItemStack itemStack = getStackInSlot(slot);
        if(itemStack != null){
            if(itemStack.stackSize <= amount){
                setInventorySlotContents(slot, null);
            } else{
                itemStack = itemStack.splitStack(amount);
                if(itemStack.stackSize == 0){
                    setInventorySlotContents(slot, null);
                }
            }
        }
        return itemStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot){
        ItemStack itemStack = getStackInSlot(slot);
        if(itemStack != null){
            setInventorySlotContents(slot, null);
        }
        return itemStack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack){
        inventory[slot] = itemStack;
        if(itemStack != null && itemStack.stackSize > getInventoryStackLimit()){
          itemStack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName() {
        return "container.glowFurnace";
    }

    @Override
    public boolean hasCustomInventoryName(){
        return false;
    }

    @Override
    public int getInventoryStackLimit(){
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer par1){
        return true;
    }

    @Override
    public void openInventory(){}

    @Override
    public void closeInventory(){}

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack){
        if(slot == IN1_LOC || slot == IN2_LOC){
            //return recipes here
        }
        if(slot == FUEL_LOC){
            //return isItemFuel
        }
        return false;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side){
        if(ForgeDirection.UP.ordinal() == side){
            return new int[] {IN1_LOC, IN2_LOC};
        }
        if(ForgeDirection.DOWN.ordinal() == side){
            return new int[] {OUT_LOC, BUCKET_OUT_LOC};
        }
        return new int[]{FUEL_LOC};
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack itemstack, int side){
        if(slot == OUT_LOC || slot == BUCKET_OUT_LOC && side == ForgeDirection.DOWN.ordinal()){
            return false;
        }
        return isItemValidForSlot(slot, itemstack);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack itemstack, int side){
        return(slot == OUT_LOC || slot == BUCKET_OUT_LOC) && side == ForgeDirection.DOWN.ordinal();
    }

    @Override
    public void writeToNBT(NBTTagCompound tags){
        super.writeToNBT(tags);

        tags.setInteger("timeleft", ticksLeft);
        tags.setInteger("maxTime", maxTicks);
        tags.setInteger("magic", magic);
        tags.setInteger("magicAmount", magicAmount);

        if(inventory[0] != null){
            NBTTagCompound slt1 = new NBTTagCompound();
            inventory[0].writeToNBT(slt1);
            tags.setTag("slot1", slt1);
        }
        if(inventory[1] != null){
            NBTTagCompound slt2 = new NBTTagCompound();
            inventory[1].writeToNBT(slt2);
            tags.setTag("slot2", slt2);
        }
        if(inventory[2] != null){
            NBTTagCompound slt3 = new NBTTagCompound();
            inventory[2].writeToNBT(slt3);
            tags.setTag("slot3", slt3);
        }
        if(inventory[3] != null){
            NBTTagCompound slt4 = new NBTTagCompound();
            inventory[3].writeToNBT(slt4);
            tags.setTag("slot4", slt4);
        }
        if(inventory[4] != null){
            NBTTagCompound slt5 = new NBTTagCompound();
            inventory[4].writeToNBT(slt5);
            tags.setTag("slot5", slt5);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound tags){
        super.readFromNBT(tags);

        ticksLeft = tags.getInteger("timeleft");
        maxTicks = tags.getInteger("maxTime");
        magic = tags.getInteger("magic");
        magicAmount = tags.getInteger("magicAmount");

        if(tags.hasKey("slot1")){
            inventory[0] = ItemStack.loadItemStackFromNBT(tags.getCompoundTag("slot1"));
        }
        if(tags.hasKey("slot2")){
            inventory[1] = ItemStack.loadItemStackFromNBT(tags.getCompoundTag("slot2"));
        }
        if(tags.hasKey("slot3")){
            inventory[2] = ItemStack.loadItemStackFromNBT(tags.getCompoundTag("slot3"));
        }
        if(tags.hasKey("slot4")){
            inventory[3] = ItemStack.loadItemStackFromNBT(tags.getCompoundTag("slot4"));
        }
        if(tags.hasKey("slot5")){
            inventory[4] = ItemStack.loadItemStackFromNBT(tags.getCompoundTag("slot5"));
        }
    }

    @Override
    public void updateEntity()
    {
        //Something in input and nothing currently processing
        if (inventory[0] != null && inventory[1] != null && ticksLeft == 0)
        {
            Recipes1_1 r = GlowFurnaceRecipes.GetRecipeFromStack(inventory[0], inventory[1]);
            if (r != null)
            {
                maxTicks = r.getTime();
                magicAmount = r.getTemp();
            }
        }
        //Actual processing
        if (magic < magicAmount)
        {
            if (ticksLeft < maxTicks && GlowFurnaceRecipes.GetRecipeFromStack(inventory[0], inventory[1]) != null)
            {
                ItemStack output = GlowFurnaceRecipes.GetRecipeFromStack(inventory[0], inventory[1]).getOutput();
                if (inventory[2] == null ||
                        (output.getItem().equals(inventory[2].getItem()) && output.getMaxStackSize() > inventory[2].stackSize) &&
                                (inventory[3] == null || inventory[3].getMaxStackSize() > inventory[3].stackSize))
                {
                    ticksLeft++;
                    magic += 3;
                }
                else
                {
                    ticksLeft = 0;
                }
            }
            if (GlowFurnaceRecipes.GetRecipeFromStack(inventory[0], inventory[1]) == null && ticksLeft > 0)
            {
                ticksLeft = 0;
            }
            if (ticksLeft == maxTicks && maxTicks != 0)
            {
                ticksLeft = 0;
                make();
            }
        }

        if (inventory[3] != null && this.worldObj.getTotalWorldTime() % 3 == 0)
        {
            if (magic - iceFuelValue(inventory[3]) > MIN_TEMP)
            {
                if (isIceFuel(inventory[3]))
                {
                    magic -= iceFuelValue(inventory[3]);

                    inventory[3].stackSize--;
                    if (inventory[3].stackSize <= 0)
                    {
                        inventory[3] = null;
                    }
                }
            }
        }

        if (magic < ROOM_TEMP && this.worldObj.getTotalWorldTime() % 10 == 0)
        {
            magic++;
        }
    }

    private void make()
    {
        if (inventory[0] == null || inventory[1] == null) return;
        ItemStack res = GlowFurnaceRecipes.GetRecipeFromStack(inventory[0], inventory[1]).getOutput();
        if (inventory[2] == null)
            inventory[2] = res.copy();
        else
            inventory[2].stackSize += res.stackSize;

        inventory[0].stackSize--;
        if (inventory[0].getItem() instanceof ItemBucketMilk || inventory[0].getItem() instanceof ItemBucket)
        {
            if (inventory[BUCKET_OUT_LOC] == null)
            {
                inventory[BUCKET_OUT_LOC] = new ItemStack(Items.bucket);
            }
            else
            {
                inventory[BUCKET_OUT_LOC].stackSize++;
            }
        }
        if (inventory[0].stackSize <= 0) inventory[0] = null;

        inventory[1].stackSize--;
        if (inventory[1].getItem() instanceof ItemBucketMilk || inventory[1].getItem() instanceof ItemBucket)
        {
            if (inventory[BUCKET_OUT_LOC] == null)
            {
                inventory[BUCKET_OUT_LOC] = new ItemStack(Items.bucket);
            }
            else
            {
                inventory[BUCKET_OUT_LOC].stackSize++;
            }
        }
        if (inventory[1].stackSize <= 0) inventory[1] = null;
    }

    /* Packets */
    @Override
    public Packet getDescriptionPacket()
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

    public int getScaledProgress(int scale)
    {
        if (maxTicks == 0) return 0;
        return ticksLeft * scale / maxTicks;
    }

    public int getScaledTemp(int scale)
    {
        return (ROOM_TEMP - magic) * scale / (ROOM_TEMP - MIN_TEMP);
    }

    public int getTemp()
    {
        return magic;
    }

    public int getFreezeTemp()
    {
        return magicAmount;
    }

    public int getScaledFreezeTemp(int scale)
    {
        return (ROOM_TEMP - magicAmount) * scale / (ROOM_TEMP - MIN_TEMP);
    }

    public static boolean isIceFuel(ItemStack stack)
    {
        return iceFuelValue(stack) != 0;
    }

    public static int iceFuelValue(ItemStack stack)
    {
        if (stack == null) return 0;
        Item i = stack.getItem();
        if (i.equals(Items.snowball))
        {
            return 100;
        }
        if (i.equals(Item.getItemFromBlock(Blocks.snow)))
        {
            return 400;
        }
        if (i.equals(Item.getItemFromBlock(Blocks.ice)))
        {
            return 300;
        }
        if (i.equals(Item.getItemFromBlock(Blocks.packed_ice)))
        {
            return 1000;
        }
        return 0;
    }

//    public void dropContents()
//    {
//        for (ItemStack stack : inventory)
//        {
//            ItemHelper.spawnItemStackInWorld(stack, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
//        }
//    }

}
