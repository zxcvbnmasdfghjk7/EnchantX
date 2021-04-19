package io.github.eddiediamondfire.enchantx.datahandler;

import com.moandjiezana.toml.Toml;
import io.github.eddiediamondfire.enchantx.EnchantX;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TomlManager {

    private final EnchantX plugin;
    private final Logger logger;
    public TomlManager(EnchantX plugin){
        this.plugin = plugin;
        logger = LoggerFactory.getLogger(TomlManager.class);
    }

    /**
     * @param fileName A file name (Do Not add .toml suffix)
     * @return Returns a Toml File
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Toml getTomlFile(String fileName){
        if(!plugin.getDataFolder().exists()){
            plugin.getDataFolder().mkdirs();
        }

        File file = new File(plugin.getDataFolder(), fileName + ".toml");

        boolean failedIOFileLoading = false;
        if(!file.exists()){
            try{
                Files.copy(plugin.getResource(fileName + ".toml"), file.toPath());
                failedIOFileLoading = false;
            }catch (IOException ex){
                logger.error("An error has occured while loading " + fileName + ".toml ", ex);
                failedIOFileLoading = true;
            }finally{
                if(failedIOFileLoading){
                    logger.error("Failed to load " + fileName + ".toml");
                }
            }
        }
        return new Toml(new Toml().read(plugin.getResource(fileName + ".toml"))).read(file);
    }
}
