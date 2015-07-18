package kkaylium.TechliumCraft.recipes;

import net.minecraft.item.ItemStack;

/**
 * Created by Kayla Marie on 7/7/14.
 */
public class Recipes1_1 {

    private ItemStack input1;
    private ItemStack input2;
    private ItemStack output;
    private int time;
    private int temp;

    /**
     * Creates a new recipe with 2 inputs and one output.
     *
     * @param input1 1st Input item stack
     * @param input2 2nd Input item stack
     * @param output Output item stack
     * @param time   Time in ticks
     * @param temp   Temperature of this recipes freezing point
     */
    public Recipes1_1(ItemStack input1, ItemStack input2, ItemStack output, int time, int temp)
    {
        this.input1 = input1.copy();
        this.input2 = input2.copy();
        this.output = output.copy();
        this.time = time;
        this.temp = temp;
    }

    public ItemStack getInput1()
    {
        return input1;
    }

    public ItemStack getInput2()
    {
        return input2;
    }

    public ItemStack getOutput()
    {
        return output;
    }

    public int getTime()
    {
        return time;
    }

    public int getTemp()
    {
        return temp;
    }
}
