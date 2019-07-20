package bounen057.adventureitems;

import bounen057.adventureitems.items.BoneSword;
import bounen057.adventureitems.items.NormalSword;
import com.sun.tools.javac.comp.Check;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ChangePaperListener implements Listener {
    private AdventureItems plugin;

    public ChangePaperListener(AdventureItems plugin) {
        this.plugin = plugin;
    }

    public String[] items = {"normalsword","bonesword"};

    public ItemStack get(String name){
        switch (name) {
            case "normalsword":
                return new NormalSword(plugin).item();
            case "bonesword":
                return new BoneSword(plugin).item();

        }
        return null;
    }



    private ItemStack Check(ItemStack item){
        ItemMeta itemMeta = item.getItemMeta();
        String type = "";

        if(!(item.getType() == Material.PAPER)){
            return null;
        }

        String lore = itemMeta.getLore().get(0);

        if(lore.contains("adventureitem ")){
            type = lore.replace("adventureitem ","");
            if(get(type) != null){
                item = get(type);
            }
        }else{
            return null;
        }

        return item;
    }

    @EventHandler
    public void OnSpawnItem(ItemSpawnEvent e){
        ItemStack item = Check(e.getEntity().getItemStack());

        if(item != null) {
            e.getEntity().getItemStack().setType(item.getType());
            e.getEntity().getItemStack().setItemMeta(item.getItemMeta());
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void OnPickItem(PlayerPickupItemEvent e){
        ItemStack item = Check(e.getItem().getItemStack());
        if(item != null) {
            e.getPlayer().getInventory().addItem(item);
            e.getItem().remove();
            e.setCancelled(true);
        }

    }
}
