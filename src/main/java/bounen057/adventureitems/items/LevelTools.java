package bounen057.adventureitems.items;

import bounen057.adventureitems.AdventureItems;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class LevelTools implements Listener{
    String itemname = "§7§lLevel";
    String lv_str = " §6Lv.§e§l";

    String toolname = "";
    String tooltype = "";
    int toollv = -1;
    List<String> toolnames = new ArrayList<>();
    List<String> lore = new ArrayList<>();

    private AdventureItems plugin;


    public LevelTools(AdventureItems plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void Break(BlockBreakEvent e){
        ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
        ItemMeta itemMeta = item.getItemMeta();
        tooltype = null;
        lore.clear();
        gettoolname();

        // 名前がなければ終了
        if(!item.getItemMeta().hasDisplayName()){
            return;
        }

        toolname = itemMeta.getDisplayName();
        // ツール名 を取得する
        if(toolname.contains(itemname)){
            for(int i = 0;i < toolnames.size();i++) {
                if (toolname.contains(toolnames.get(i))) {
                    tooltype = toolnames.get(i);
                }
            }

            if(tooltype == null){
                return;
            }
        }

        // レベルを取得
        toollv = Integer.parseInt(toolname.replace(itemname,"").replace(tooltype,"").replace(lv_str,""));

        switch (tooltype){
            case "Pickel":
                Meta_Pickel1(e.getPlayer(),itemMeta,toollv);
            case "Shovel":
                Meta_Shovel1(e.getPlayer(),itemMeta,toollv);
        }

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        e.getPlayer().getInventory().setItem(e.getPlayer().getInventory().getHeldItemSlot(),item);
    }



    public boolean getItem(Player p,String type,int lv){
        ItemStack item = new ItemStack(Material.STONE_PICKAXE);
        ItemMeta itemMeta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        switch (type){
            case "Pickel":
                item = new ItemStack(Material.STONE_PICKAXE);
                itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(
                        itemname +
                        type +
                        lv_str +
                        lv);
                lore.add("§7掘れば掘るほど性能が上がる!");
                lore.add("");
                lore.add("§7採掘数: "+Pickel_next[lv]);
                lore.add("§e使って見てね!");

            case "Shovel":
                item = new ItemStack(Material.IRON_SPADE);
                itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(
                        itemname +
                                type +
                                lv_str +
                                lv);
                lore.add("§7掘れば掘るほど性能が上がる!");
                lore.add("");
                lore.add("§7採掘数: "+Shovel_next[lv]);
                lore.add("§e使って見てね!");
        }

        if(!itemMeta.hasDisplayName()){
            return false;
        }

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        p.getInventory().addItem(item);

        return true;
    }



    private void gettoolname(){
        toolnames.add("Pickel");
        toolnames.add("Shovel");
    }



    // 数値を取得する。+1する。
    private int getamount(String lore,String rep){
        return Integer.parseInt(lore.replace(rep,"")) + 1;
    }



    private void Levelup(Player p){
        p.sendMessage("§e§l≫ 所持ツールのレベルが上がりました!");
    }












    // 1 ~ 6
    int[] Pickel_next = {0,0,50,500,5000,25000,100000,-1};
    private void Meta_Pickel1(Player p,ItemMeta itemMeta,int lv){

        String amount_str = "§7採掘数: ";
        int amount = getamount(itemMeta.getLore().get(2),amount_str);


        lore.add("§7掘れば掘るほど性能が上がる!");
        lore.add("");
        lore.add(amount_str + amount);
        if(Pickel_next[lv+1] != -1) {
            if(Pickel_next[lv + 1] <= amount){
                lv++;
                Levelup(p);
            }

            lore.add("§a次のレベルまであと: " + (Pickel_next[lv + 1] - amount));
        }else{
            lore.add("§e次のレベルまであと: 最大");
        }

        itemMeta.setUnbreakable(true);
        switch (lv){
            case 2:
                itemMeta.addEnchant(Enchantment.DIG_SPEED,1,false);
                break;
            case 3:
                itemMeta.addEnchant(Enchantment.DIG_SPEED,2,false);
                break;
            case 4:
                itemMeta.addEnchant(Enchantment.DIG_SPEED,3,false);
                break;
            case 5:
                itemMeta.addEnchant(Enchantment.DIG_SPEED,4,false);
                break;
            case 6:
                itemMeta.addEnchant(Enchantment.DIG_SPEED,5,false);
                break;
        }

        itemMeta.setDisplayName(itemname + tooltype + lv_str + lv);
    }

    // 1 ~ 3
    int[] Shovel_next = {0,0,2000,50000,-1};
    private void Meta_Shovel1(Player p,ItemMeta itemMeta,int lv){

        String amount_str = "§7採掘数: ";
        int amount = getamount(itemMeta.getLore().get(2),amount_str);


        lore.add("§7掘れば掘るほど性能が上がる!");
        lore.add("");
        lore.add(amount_str + amount);
        if(Shovel_next[lv+1] != -1) {
            if(Shovel_next[lv + 1] <= amount){
                lv++;
                Levelup(p);
            }

            lore.add("§a次のレベルまであと: " + (Shovel_next[lv + 1] - amount));
        }else{
            lore.add("§e次のレベルまであと: 最大");
        }

        itemMeta.setUnbreakable(true);
        switch (lv){
            case 2:
                itemMeta.addEnchant(Enchantment.DIG_SPEED,1,false);
                break;
            case 3:
                itemMeta.addEnchant(Enchantment.DIG_SPEED,2,false);
                break;
            case 4:
                itemMeta.addEnchant(Enchantment.DIG_SPEED,3,false);
                break;
            case 5:
                itemMeta.addEnchant(Enchantment.DIG_SPEED,4,false);
                break;
            case 6:
                itemMeta.addEnchant(Enchantment.DIG_SPEED,5,false);
                break;
        }

        itemMeta.setDisplayName(itemname + tooltype + lv_str + lv);
    }
}
