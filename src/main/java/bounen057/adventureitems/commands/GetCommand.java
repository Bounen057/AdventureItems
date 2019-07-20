package bounen057.adventureitems.commands;

import bounen057.adventureitems.AdventureItems;
import bounen057.adventureitems.ChangePaperListener;
import bounen057.adventureitems.items.LevelTools;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GetCommand implements CommandExecutor {
    private AdventureItems plugin;
    public GetCommand(AdventureItems plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player)sender;

        if(args[0].equals("help")){
            p.sendMessage("§e§l[§a§l§m===="+ plugin.logo +"§a§l§m====§e§l]");
            p.sendMessage("§a/aitem leveltool get <type> <lv>" + "§7-レベルツールを出します");
            p.sendMessage("§a/aitem leveltool list" + "§7-type一覧を出します");
            p.sendMessage("§a/aitem offhand get <type>" + "§7-アクセサリーを出します");
            p.sendMessage("§a/aitem offhand list" + "§7-アクセサリーの種類");
            p.sendMessage("§a" + "§7-");
        }



        if(args[0].equals("leveltool")){
            if(args[1].equals("get")){
                String type = args[2];
                int lv = Integer.parseInt(args[3]);

                if(!new LevelTools(plugin).getItem(p,type,lv)){
                    p.sendMessage(plugin.logo+"§cERROR:正常な値が含まれていません");
                    return false;
                }

                p.sendMessage(plugin.logo+"§aツールを付与しました");
            }

            if(args[1].equals("list")){
                p.sendMessage(plugin.logo+" §6§lツールの種類一覧§e§l§m------------");
                p.sendMessage("§a-Pickel");
                p.sendMessage("§a-Shovel");
            }
        }


        if(args[0].equals("offhand")){
            if(args[1].equals("get")){
                ItemStack item = new ChangePaperListener(plugin).get(args[2]);

                if(item == null){
                    p.sendMessage(plugin.logo+"§cERROR:その値が存在しません");
                }else{
                    p.sendMessage(plugin.logo+"§aアクセサリーを付与しました");
                    p.getInventory().addItem(item);
                }
            }

            if(args[1].equals("list")){
                p.sendMessage(plugin.logo+" §6§lアクセサリーの種類一覧§e§l§m------------");
                p.sendMessage("§a-normalsword");
                p.sendMessage("§a-bonesword");
            }
        }

        return false;
    }

}
