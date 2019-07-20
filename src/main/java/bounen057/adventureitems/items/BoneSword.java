package bounen057.adventureitems.items;

import bounen057.adventureitems.AdventureItems;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class BoneSword implements Listener {
    private AdventureItems plugin;

    public BoneSword(AdventureItems plugin) {
        this.plugin = plugin;
    }

    private String name = "§7§lBONE SWORD";
    private int amount = 7;

    public ItemStack item() {
        ItemStack item = new ItemStack(Material.BONE);
        ItemMeta itemMeta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        // Display name
        itemMeta.setDisplayName(name);
        itemMeta.setUnbreakable(true);

        itemMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, false);
        lore.add("§c攻撃力 "+amount);
        lore.add("§7なかなかかたい骨である");


        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }

    @EventHandler
    public void OnDamage(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) {
            return;
        }

        Player p = (Player) e.getDamager();
        ItemStack item = p.getInventory().getItemInMainHand();

        if (item == null) {
            return;
        }

        if (item.getItemMeta().hasDisplayName()) {
            if (item.getItemMeta().getDisplayName().equals(name)) {
                System.out.println(amount);
                e.setDamage(amount);
            }
        }
    }
}
