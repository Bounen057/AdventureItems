package bounen057.adventureitems;

import bounen057.adventureitems.commands.GetCommand;
import bounen057.adventureitems.items.LevelTools;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * TODO: items.LevelToolsのコードのリファクタリング (時間が余れば)
 *
 */
public final class AdventureItems extends JavaPlugin {

    // プラグインのロゴ
    public String logo = "§3§l[§bAdventureItems§3§l]§f";

    @Override
    public void onEnable() {
        Bukkit.getPluginCommand("aitem").setExecutor(new GetCommand(this));

        Bukkit.getPluginManager().registerEvents(new ChangePaperListener(this),this);
        Bukkit.getPluginManager().registerEvents(new LevelTools(this),this);

    }

    @Override
    public void onDisable() {
    }
}
