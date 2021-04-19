package io.github.eddiediamondfire.enchantx;

import com.moandjiezana.toml.Toml;
import io.github.eddiediamondfie.enchantx.command.CommandManager;
import io.github.eddiediamondfie.enchantx.listener.PernEnchantment;
import io.github.eddiediamondfire.enchantx.datahandler.TomlManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnchantX extends JavaPlugin {

    public static EnchantX plugin;
    private final CEnchant CEnchant;
    private final CommandManager commandManager;
    private final TomlManager tomlManager;
    public boolean isPaper;
    private Toml configFile = null;

    public EnchantX(){
        CEnchant = new CEnchant(this);
        commandManager = new CommandManager(this);
        tomlManager = new TomlManager(this);
    }

    private final Logger logger = LoggerFactory.getLogger(EnchantX.class);

    @Override
    public void onEnable() {
        plugin = this;

        try{
            Class.forName("com.destroystokyo.paper.utils.PaperPluginLogger");
            isPaper = true;
            logger.info("Detected server is using PaperMC, enabling Paper only logic");
        }catch(ClassNotFoundException ex){
            isPaper = false;
        }

        // Load data files
        logger.info("Loading configuration file");
        configFile = tomlManager.getTomlFile("config");

        // Load Enchantments
        logger.info("Registering Enchantments");
        loadEnchantments();

        // Register Listeners
        logger.info("Registering Event Listeners");
        getServer().getPluginManager().registerEvents(new PernEnchantment(this), this);
    }

    private void loadEnchantments(){
        CEnchant.registerCustomEnchantment(io.github.eddiediamondfire.enchantx.CEnchant.PERN_ENCHANTMENT);
    }
    @Override
    public void onDisable() {
    }

    public CEnchant getEnchantManager() {
        return CEnchant;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }
}
