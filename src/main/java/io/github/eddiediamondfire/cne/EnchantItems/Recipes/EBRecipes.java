package io.github.eddiediamondfire.cne.EnchantItems.Recipes;

import io.github.eddiediamondfire.cne.CNE;
import io.github.eddiediamondfire.cne.EnchantItems.EBooks;
import io.github.eddiediamondfire.cne.Ultils.CEnchantment;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class EBRecipes {
    private EBooks books = new EBooks();

    public void EXPLOSION_BOOK(Player player){
        ItemStack item = books.giveEnchantmentBooks_COMMON(player, CEnchantment.EXPLOSION, 1);

        NamespacedKey key = new NamespacedKey(CNE.instance, "explosion_book");
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("   ", " EE", " ET");

        recipe.setIngredient('E', Material.PAPER);
        recipe.setIngredient('T', Material.TNT);

        Bukkit.addRecipe(recipe);
    }
}
