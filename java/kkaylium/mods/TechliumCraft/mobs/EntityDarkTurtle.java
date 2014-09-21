package kkaylium.mods.TechliumCraft.mobs;

import kkaylium.mods.TechliumCraft.init.TCInits;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Kayla Marie on 8/3/14.
 */
public class EntityDarkTurtle extends EntityAnimal {

    private int inLove;
    private int breeding;
    private EntityPlayer field_146084_br;

    public EntityDarkTurtle(World par1World)
    {
        super(par1World);

        this.setHealth(10.0F);
        this.getNavigator().setSpeed(0.222);
        this.setSize(0.5F, 0.8F);
        this.isImmuneToFire = false;
        float var2 = 0.27F;

        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 0.33F));
        this.tasks.addTask(2, new EntityAIMate(this, var2));
        this.tasks.addTask(3, new EntityAITempt(this, 0.3F, Items.coal, false));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 0.28F));
        this.tasks.addTask(5, new EntityAIWander(this, 0.25F));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 5.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
    }

    public boolean isAIEnabled()
    {
        return true;
    }

    public boolean isBreedingItem(ItemStack par1ItemStack)
    {
        return par1ItemStack.getItem() == Items.coal;
    }

    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (this.getGrowingAge() != 0)
        {
            this.inLove = 0;
        }

        if (this.inLove > 0)
        {
            --this.inLove;
            String s = "heart";

            if (this.inLove % 10 == 0)
            {
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                double d2 = this.rand.nextGaussian() * 0.02D;
                this.worldObj.spawnParticle(s, this.posX + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.posY + 0.5D + (double) (this.rand.nextFloat() * this.height), this.posZ + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, d0, d1, d2);
            }
        }
        else
        {
            this.breeding = 0;
        }
    }

    private void procreate(EntityDarkTurtle par1EntityAnimal)
    {
        EntityAgeable entityageable = this.createChild(par1EntityAnimal);

        if (entityageable != null)
        {
            if (this.field_146084_br == null && par1EntityAnimal.func_146083_cb() != null)
            {
                this.field_146084_br = par1EntityAnimal.func_146083_cb();
            }

            if (this.field_146084_br != null)
            {
                this.field_146084_br.triggerAchievement(StatList.field_151186_x);

            }

            this.setGrowingAge(6000);
            par1EntityAnimal.setGrowingAge(6000);
            this.inLove = 0;
            this.breeding = 0;
            par1EntityAnimal.breeding = 0;
            par1EntityAnimal.inLove = 0;
            entityageable.setGrowingAge(-24000);
            entityageable.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);

            for (int i = 0; i < 7; ++i)
            {
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                double d2 = this.rand.nextGaussian() * 0.02D;
                this.worldObj.spawnParticle("heart", this.posX + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.posY + 0.5D + (double) (this.rand.nextFloat() * this.height), this.posZ + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, d0, d1, d2);
            }

            this.worldObj.spawnEntityInWorld(entityageable);
        }
    }

    protected boolean canDespawn()
    {
        return false;
    }

    public boolean isChild()
    {
        return this.getGrowingAge() < 0;
    }

    protected String getLivingSound()
    {
        return "mob.glog.say";
    }

    protected String getHurtSound()
    {
        return "mob.glog.say";
    }

    protected String getDeathSound()
    {
        return "mob.glog.death";
    }

    protected void playStepSound(int par1, int par2, int par3, int par4)
    {
        this.worldObj.playSoundAtEntity(this, "mob.glog.step", 0.1F, 1.0F);
    }

    public boolean interact(EntityPlayer par1EntityPlayer)
    {
        ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();

        Random random = new Random();
        int n = random.nextInt(12 - 1) + 1;
        n--;

        if(par1EntityPlayer.isSneaking() && itemstack != null)
        {
            if (itemstack.getItem() == TCInits.darkCrystal_WHITE || itemstack.getItem() == TCInits.darkCrystal_BLACK || itemstack.getItem() == TCInits.darkCrystal_RED || itemstack.getItem() == TCInits.darkCrystal_ORANGE || itemstack.getItem() == TCInits.darkCrystal_YELLOW || itemstack.getItem() == TCInits.darkCrystal_LIME || itemstack.getItem() == TCInits.darkCrystal_GREEN || itemstack.getItem() == TCInits.darkCrystal_SKY || itemstack.getItem() == TCInits.darkCrystal_BLUE || itemstack.getItem() == TCInits.darkCrystal_LILAC || itemstack.getItem() == TCInits.darkCrystal_PURPLE || itemstack.getItem() == TCInits.darkCrystal_PINK && !par1EntityPlayer.capabilities.isCreativeMode)
            {
                switch(n){
                    case 0:
                        par1EntityPlayer.inventory.addItemStackToInventory(new ItemStack(TCInits.darkCrystal_WHITE));
                        par1EntityPlayer.inventory.consumeInventoryItem(par1EntityPlayer.inventory.decrStackSize(par1EntityPlayer.inventory.currentItem, 1).getItem());
                        // par1EntityPlayer.inventory.decrStackSize(par1EntityPlayer.inventory.currentItem, 1);
                        break;
                    case 1:
                        par1EntityPlayer.inventory.addItemStackToInventory(new ItemStack(TCInits.darkCrystal_BLACK));
                        par1EntityPlayer.inventory.consumeInventoryItem(par1EntityPlayer.getHeldItem().getItem());
                        break;
                    case 2:
                        par1EntityPlayer.inventory.addItemStackToInventory(new ItemStack(TCInits.darkCrystal_RED));
                        par1EntityPlayer.inventory.consumeInventoryItem(par1EntityPlayer.getHeldItem().getItem());
                        break;
                    case 3:
                        par1EntityPlayer.inventory.addItemStackToInventory(new ItemStack(TCInits.darkCrystal_ORANGE));
                        par1EntityPlayer.inventory.consumeInventoryItem(par1EntityPlayer.getHeldItem().getItem());
                        break;
                    case 4:
                        par1EntityPlayer.inventory.addItemStackToInventory(new ItemStack(TCInits.darkCrystal_YELLOW));
                        par1EntityPlayer.inventory.consumeInventoryItem(par1EntityPlayer.getHeldItem().getItem());
                        break;
                    case 5:
                        par1EntityPlayer.inventory.addItemStackToInventory(new ItemStack(TCInits.darkCrystal_LIME));
                        par1EntityPlayer.inventory.consumeInventoryItem(par1EntityPlayer.getHeldItem().getItem());
                        break;
                    case 6:
                        par1EntityPlayer.inventory.addItemStackToInventory(new ItemStack(TCInits.darkCrystal_GREEN));
                        par1EntityPlayer.inventory.consumeInventoryItem(par1EntityPlayer.getHeldItem().getItem());
                        break;
                    case 7:
                        par1EntityPlayer.inventory.addItemStackToInventory(new ItemStack(TCInits.darkCrystal_SKY));
                        par1EntityPlayer.inventory.consumeInventoryItem(par1EntityPlayer.getHeldItem().getItem());
                        break;
                    case 8:
                        par1EntityPlayer.inventory.addItemStackToInventory(new ItemStack(TCInits.darkCrystal_BLUE));
                        par1EntityPlayer.inventory.consumeInventoryItem(par1EntityPlayer.getHeldItem().getItem());
                        break;
                    case 9:
                        par1EntityPlayer.inventory.addItemStackToInventory(new ItemStack(TCInits.darkCrystal_LILAC));
                        par1EntityPlayer.inventory.consumeInventoryItem(par1EntityPlayer.getHeldItem().getItem());
                        break;
                    case 10:
                        par1EntityPlayer.inventory.addItemStackToInventory(new ItemStack(TCInits.darkCrystal_PURPLE));
                        par1EntityPlayer.inventory.consumeInventoryItem(par1EntityPlayer.getHeldItem().getItem());
                        break;
                    case 11:
                        par1EntityPlayer.inventory.addItemStackToInventory(new ItemStack(TCInits.darkCrystal_PINK));
                        par1EntityPlayer.inventory.consumeInventoryItem(par1EntityPlayer.getHeldItem().getItem());
                        break;
                    default:
                        break;
                }
                return true;
            }
            else
            {
                return super.interact(par1EntityPlayer);
            }
        }else{
            return super.interact(par1EntityPlayer);
        }

    }

    @Override
    protected Item getDropItem()
    {
        return null;
    }

    public EntityAgeable createChild(EntityAgeable var1)
    {
        return new EntityDarkTurtle(this.worldObj);
    }
}