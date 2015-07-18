package kkaylium.TechliumCraft.recipes;

import com.google.common.collect.ImmutableList;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kayla Marie on 7/7/14.
 */
public class GlowFurnaceRecipes {

    //TODO: make meta sensitive
    public static List<Recipes1_1> recipe21List = new ArrayList<Recipes1_1>();

    public static void AddRecipe(ItemStack input1, ItemStack input2, ItemStack output, int time, int temp)
    {
        Recipes1_1 r = new Recipes1_1(input1, input2, output, time, temp);
        recipe21List.add(r);
    }

    public static Recipes1_1 GetRecipeFromStack(ItemStack stack1, ItemStack stack2)
    {
        if (stack1 == null || stack2 == null) return null;
        for (Recipes1_1 r : recipe21List)
        {
            if (r.getInput1().getItem().equals(stack1.getItem()) && r.getInput2().getItem().equals(stack2.getItem()))
                return r;
            if (r.getInput2().getItem().equals(stack1.getItem()) && r.getInput1().getItem().equals(stack2.getItem()))
                return r;
        }
        return null;
    }

    public static Recipes1_1[] GetRecipesFromStack(ItemStack stack)
    {
        List<Recipes1_1> out = new ArrayList<Recipes1_1>();
        if (stack == null) return null;
        for (Recipes1_1 r : recipe21List)
        {
            if (r.getInput1().getItem().equals(stack.getItem()) || r.getInput2().getItem().equals(stack.getItem()))
                out.add(r);
        }
        return out.toArray(new Recipes1_1[0]);
    }

    public static List<Recipes1_1> GetAllRecipes()
    {
        return ImmutableList.copyOf(recipe21List);
    }
}
