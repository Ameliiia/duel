import command.duel;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        YamlConfiguration arenaConfig;
        File arenaFile;

        getLogger().info("[Duel] has been enable");
        getCommand("duel").setExecutor(new duel());

        if(!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        arenaFile = new File(getDataFolder() + File.separator + "arenas.yml");

        if(!arenaFile.exists()) {
            try {
                arenaFile.createNewFile();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        arenaConfig = YamlConfiguration.loadConfiguration(arenaFile);

        ConfigurationSection arenaSection = arenaConfig.getConfigurationSection("arenas");

        for(String string : arenaSection.getKeys(false)) {
            String loc1 = arenaSection.getString(string + ".loc1");
            String loc2 = arenaSection.getString(string + ".loc2");
        }

    }

    @Override
    public void onDisable() {

        getLogger().info( "[Duel] has been enable");

    }
}
