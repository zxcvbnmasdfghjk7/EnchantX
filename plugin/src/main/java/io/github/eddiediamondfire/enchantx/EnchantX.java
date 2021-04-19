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
    }
    @Override
    public void onDisable() {
    }
