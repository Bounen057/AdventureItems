package bounen057.adventureitems.commands;

import bounen057.adventureitems.AdventureItems;
import bounen057.adventureitems.items.LevelTools;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetCommand implements CommandExecutor {
    private AdventureItems plugin;
    public GetCommand(AdventureItems plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player)sender;

        new LevelTools(plugin).getItem(p,"Pickel",1);
        return false;
    }

}
