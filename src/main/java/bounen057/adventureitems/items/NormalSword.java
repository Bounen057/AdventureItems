package bounen057.adventureitems.items;

import bounen057.adventureitems.AdventureItems;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class NormalSword {
    private AdventureItems plugin;


    public NormalSword(AdventureItems plugin) {
        this.plugin = plugin;
    }

    public ItemStack item(){
        ItemStack item = new ItemStack(Material.STONE_SWORD);
        ItemMeta itemMeta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        // Display name
        itemMeta.setDisplayName("§a§lNORMAL SWORD");
        itemMeta.setUnbreakable(true);
        itemMeta.addEnchant(Enchantment.DAMAGE_ALL,1,false);
        lore.add("§7一番最初に手にするであろう剣");
        lore.add("§7そこまで強くはない");


        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }
}
