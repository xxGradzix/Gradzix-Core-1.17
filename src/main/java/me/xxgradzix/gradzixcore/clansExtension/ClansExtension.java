package me.xxgradzix.gradzixcore.clansExtension;

public class ClansExtension {

//    private final Gradzix_Core plugin;
//    private final FunnyGuilds funnyGuilds;
//
//    private WarEntityManager warEntityManager;
//    private WarRecordEntityManager warRecordEntityManager;
//    private ClanPerksEntityManager clanPerksEntityManager;
//    private PerkModifierEntityManager perkModifierEntityManager;
//    private WarScheduleEntityManager warScheduleEntityManager;
//
//    private final WarManager warManager;
//    private final ClanPerksManager clanPerksManager;
//    private final WarTimeManager warTimeManager;
//
//    private final ConnectionSource connectionSource;
//    public static boolean ARE_WARS_ACTIVE = false;
//
//    public void configureDB() throws SQLException {
//        TableUtils.createTableIfNotExists(connectionSource, WarEntity.class);
//        TableUtils.createTableIfNotExists(connectionSource, WarRecordEntity.class);
//        TableUtils.createTableIfNotExists(connectionSource, PerkModifierEntity.class);
//        TableUtils.createTableIfNotExists(connectionSource, ClanPerksEntity.class);
//        TableUtils.createTableIfNotExists(connectionSource, WarScheduleEntity.class);
//
//        warEntityManager = new WarEntityManager(connectionSource);
//        warRecordEntityManager = new WarRecordEntityManager(connectionSource);
//        clanPerksEntityManager = new ClanPerksEntityManager(connectionSource);
//        perkModifierEntityManager = new PerkModifierEntityManager(connectionSource);
//        warScheduleEntityManager = new WarScheduleEntityManager(connectionSource);
//    }
//    public ClansExtension(Gradzix_Core plugin, ConnectionSource connectionSource, FunnyGuilds funnyGuilds) {
//        this.funnyGuilds = funnyGuilds;
//        this.plugin = plugin;
//        this.connectionSource = connectionSource;
//
//        try {
//            configureDB();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        warManager = new WarManager(warEntityManager, warRecordEntityManager, funnyGuilds, clanPerksEntityManager);
//        clanPerksManager = new ClanPerksManager(clanPerksEntityManager);
//        warTimeManager = new WarTimeManager(warScheduleEntityManager, warManager);
//
//        warTimeManager.scheduleOnEnable();
//
//    }


    public void onEnable() {

//        plugin.getCommand("configWojny").setExecutor(new WarConfigCommand(warTimeManager, warScheduleEntityManager));
//        plugin.getCommand("wypowiedzwojne").setExecutor(new DeclareWarCommand(funnyGuilds, warManager));
//        plugin.getCommand("wojny").setExecutor(new WarsCommand(funnyGuilds, warManager));
//        plugin.getCommand("ulepszeniaklanu").setExecutor(new ClanUpgradesCommand(funnyGuilds, clanPerksManager, clanPerksEntityManager));
//
//        plugin.getServer().getPluginManager().registerEvents(new GuildLoseLivesEvent(funnyGuilds), plugin);
//        plugin.getServer().getPluginManager().registerEvents(new AddWarScoreAfterKill(funnyGuilds, warManager), plugin);
//        plugin.getServer().getPluginManager().registerEvents(new GuildRemoveEvent(warManager, funnyGuilds), plugin);
//        plugin.getServer().getPluginManager().registerEvents(new PointChangeEvent(clanPerksEntityManager), plugin);

    }

}
