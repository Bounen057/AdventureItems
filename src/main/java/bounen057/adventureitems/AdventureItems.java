package bounen057.adventureitems;

import bounen057.adventureitems.commands.GetCommand;
import bounen057.adventureitems.items.LevelTools;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdventureItems extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginCommand("adventureitems").setExecutor(new GetCommand(this));

        Bukkit.getPluginManager().registerEvents(new LevelTools(this),this);

    }

    @Override
    public void onDisable() {
    }
}
