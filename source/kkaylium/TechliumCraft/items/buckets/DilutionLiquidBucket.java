package kkaylium.TechliumCraft.items.buckets;

import kkaylium.TechliumCraft.TechliumCraft;
import kkaylium.TechliumCraft.inits.TCInits;
import kkaylium.TechliumCraft.lib.Strings;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DilutionLiquidBucket extends ItemBucket{

	public DilutionLiquidBucket(Block block) {
		super(block);
		this.setCreativeTab(TechliumCraft.GGTab);
	}
	
	 public ItemStack onItemRightClick (ItemStack stack, World world, EntityPlayer player)
	    {
	        float var4 = 1.0F;
	        double trueX = player.prevPosX + (player.posX - player.prevPosX) * (double) var4;
	        double trueY = player.prevPosY + (player.posY - player.prevPosY) * (double) var4 + 1.62D - (double) player.yOffset;
	        double trueZ = player.prevPosZ + (player.posZ - player.prevPosZ) * (double) var4;
	        boolean wannabeFull = false;
	        MovingObjectPosition position = this.getMovingObjectPositionFromPlayer(world, player, wannabeFull);

	        if (position == null)
	        {
	            return stack;
	        }
	        else
	        {
	            /*FillBucketEvent event = new FillBucketEvent(player, stack, world, position);
	            if (MinecraftForge.EVENT_BUS.post(event))
	            {
	            	return stack;
	            }

	            if (event.getResult() == Event.Result.ALLOW)
	            {
	            	if (player.capabilities.isCreativeMode)
	            	{
	            		return stack;
	            	}

	            	if (--stack.stackSize <= 0)
	            	{
	            		return event.result;
	            	}

	            	if (!player.inventory.addItemStackToInventory(event.result))
	            	{
	            		player.dropPlayerItem(event.result);
	            	}

	            	return stack;
	            }*/

	            if (position.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
	            {
	                int clickX = position.blockX;
	                int clickY = position.blockY;
	                int clickZ = position.blockZ;

	                if (!world.canMineBlock(player, clickX, clickY, clickZ))
	                {
	                    return stack;
	                }

	                if (position.sideHit == 0)
	                {
	                    --clickY;
	                }

	                if (position.sideHit == 1)
	                {
	                    ++clickY;
	                }

	                if (position.sideHit == 2)
	                {
	                    --clickZ;
	                }

	                if (position.sideHit == 3)
	                {
	                    ++clickZ;
	                }

	                if (position.sideHit == 4)
	                {
	                    --clickX;
	                }

	                if (position.sideHit == 5)
	                {
	                    ++clickX;
	                }

	                if (!player.canPlayerEdit(clickX, clickY, clickZ, position.sideHit, stack))
	                {
	                    return stack;
	                }

	                if (this.tryPlaceContainedLiquid(world, clickX, clickY, clickZ, TCInits.dilutionLiquid) && !player.capabilities.isCreativeMode)
	                {
	                    return new ItemStack(Items.bucket);
	                }
	            }

	            return stack;
	        }
	    }

	    public boolean tryPlaceContainedLiquid (World world, int clickX, int clickY, int clickZ, Block liquid)
	    {
	        if (!world.isAirBlock(clickX, clickY, clickZ)/*&& world.getBlock(clickX, clickY, clickZ).getMaterial().isSolid()*/)
	        {
	            return false;
	        }
	        else
	        {
	            int metadata = 1;
	            world.addBlockEvent(clickX, clickY, clickZ, TCInits.dilutionLiquid, metadata, 0);
	            return true;
	        }
	    }

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister){
			itemIcon = iconRegister.registerIcon(Strings.MOD_ID + ":dilutionLiquidBucket");
	}
}
