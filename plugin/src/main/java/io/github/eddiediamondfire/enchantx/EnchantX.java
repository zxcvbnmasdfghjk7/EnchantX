    private final TomlManager tomlManager;
    public boolean isPaper;
    private Toml configFile = null;
    public EnchantX(){
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
    }
    @Override
    public void onDisable() {
    }
