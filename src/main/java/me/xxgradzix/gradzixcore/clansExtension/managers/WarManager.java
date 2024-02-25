package me.xxgradzix.gradzixcore.clansExtension.managers;

public class WarManager {

//    private final WarEntityManager warEntityManager;
//    private final WarRecordEntityManager warRecordEntityManager;
//    private final ClanPerksEntityManager clanPerksEntityManager;
//    private final FunnyGuilds funnyGuilds;
//
//    public WarManager(WarEntityManager warEntityManager, WarRecordEntityManager warRecordEntityManager, FunnyGuilds funnyGuilds, ClanPerksEntityManager clanPerksEntityManager) {
//        this.warEntityManager = warEntityManager;
//        this.warRecordEntityManager = warRecordEntityManager;
//        this.funnyGuilds = funnyGuilds;
//        this.clanPerksEntityManager = clanPerksEntityManager;
//    }
//    public void declareWar(Guild invaderGuild, Guild invadedGuild) throws YouAlreadyHaveMaxAmountOfWarsException, CantDeclareWarDuringWarTimeException, YouAlreadyHaveWarWithThisGuildException {
//
//        if (ClansExtension.ARE_WARS_ACTIVE) {
//            throw new CantDeclareWarDuringWarTimeException("You already have war");
//        }
//
//        UUID invaderGuildId = invaderGuild.getUUID();
//        UUID invadedGuildId = invadedGuild.getUUID();
//
//        /*** WOJNY ZAPLANOWANE WYPOWIEDZIANE PRZEZ ATAKUJACEGO */
//        Set<WarEntity> nonEndedInvaderGuildWarEntities = warEntityManager.getInvaderWarsByGuildId(invaderGuildId, false, false);
//
//        int maxInvaderWars = 1;
//
//        {
//            ClanPerksEntity invaderClanPerksEntity = clanPerksEntityManager.getClanPerksEntityByID(invaderGuildId);
//            int invaderWarAmountPerkLevel = invaderClanPerksEntity.getClanPerkLevel(ClanPerk.WAR_AMOUNT);
//            if(invaderWarAmountPerkLevel > 0) maxInvaderWars = (int) PerkModifierEntityManager.getPerkModifierEntityByID(ClanPerk.WAR_AMOUNT).getPerkModifierPerLevel(invaderWarAmountPerkLevel);
//        }
//
//
//        if(nonEndedInvaderGuildWarEntities.size() >= maxInvaderWars) {
//            throw new YouAlreadyHaveMaxAmountOfWarsException("You already have max amount of wars");
//        }
//
//        Set<WarEntity> commonWars = warEntityManager.getWarByGuildIds(invaderGuildId, invadedGuildId, false, false);
//        if(!commonWars.isEmpty()) {
//            throw new YouAlreadyHaveWarWithThisGuildException("You already have war with this guild");
//        }
//
//        LocalDate now = LocalDate.now();
//        LocalDateTime warStart = now.with(TemporalAdjusters.next(DayOfWeek.FRIDAY)).atTime(12, 00);
//        LocalDateTime warEnd = now.with(TemporalAdjusters.next(DayOfWeek.SUNDAY)).atTime(18, 00);
//
//        WarEntity warEntity = new WarEntity(
//                invaderGuild.getUUID(),
//                invadedGuild.getUUID(),
//                0,
//                0,
//                warStart,
//                warEnd
//        );
//
//        warEntityManager.createWar(warEntity);
//    }
//
////    public void startWars() {
////
////        List<WarEntity> warEntities = warEntityManager.getAllWars();
////
////        warEntities.forEach((war) -> {
////
////            UUID invaderGuildId = war.getInvaderGuildId();
////            UUID invadedGuildId = war.getInvadedGuildId();
////
////            Option<Guild> invaderGuildOption = funnyGuilds.getGuildManager().findByUuid(invaderGuildId);
////            Option<Guild> invadedGuildOption = funnyGuilds.getGuildManager().findByUuid(invadedGuildId);
////
////            if(invaderGuildOption.isPresent() && invadedGuildOption.isPresent()) {
////                Guild invaderGuild = invaderGuildOption.get();
////                Guild invadedGuild = invadedGuildOption.get();
////
////                {
////                    Set<Guild> enemies = invaderGuild.getEnemies();
////                    enemies.add(invadedGuild);
////                    invaderGuild.setEnemies(enemies);
////                }
////                {
////                    Set<Guild> enemies = invadedGuild.getEnemies();
////                    enemies.add(invaderGuild);
////                    invadedGuild.setEnemies(enemies);
////                }
////
////                invaderGuild.getOnlineMembers().forEach(onlinePlayer -> onlinePlayer.sendMessage(Messages.YOUR_WAR_WITH_CLAN_XXXX_HAS_STARTED(invadedGuild.getTag())));
////                invadedGuild.getOnlineMembers().forEach(onlinePlayer -> onlinePlayer.sendMessage(Messages.YOUR_WAR_WITH_CLAN_XXXX_HAS_STARTED(invaderGuild.getTag())));
////            }
////            war.setActive(true);
////            warEntityManager.createOrUpdateWar(war);
////        });
////    }
//    public void endWars() {
//        List<WarEntity> warEntities = warEntityManager.getAllWars();
//
//        warEntities.forEach(war -> {
//            endWar(war);
//        });
//    }
//
//    public Set<WarEntity> getNonEndedGuildWars(UUID clanId) {
//
//        Set<WarEntity> activeWars = warEntityManager.getWarsByGuildId(clanId, true, false);
//        Set<WarEntity> scheduledWars = warEntityManager.getWarsByGuildId(clanId, false, false);
//
//        activeWars.addAll(scheduledWars);
//        return activeWars;
//    }
//    public List<WarRecordEntity> getAllEndedWarsByGuildId(UUID id) {
//        return warRecordEntityManager.getWarRecordsByGuildId(id);
//    }
//
//    public void addPointForGuild(WarEntity warEntity, UUID guildId) {
//
//        if(guildId.equals(warEntity.getInvadedGuildId())) {
//            int previousPoints = warEntity.getInvadedScore();
//            previousPoints++;
//            warEntity.setInvadedScore(previousPoints);
//        }
//        if(guildId.equals(warEntity.getInvaderGuildId())) {
//            int previousPoints = warEntity.getInvaderScore();
//            previousPoints++;
//            warEntity.setInvaderScore(previousPoints);
//        }
//        warEntityManager.createOrUpdateWar(warEntity);
//    }
//
//    @Nullable
//    public UUID getWinnerGuildUUID(WarEntity warEntity) {
//
//        int invaderKills = warEntity.getInvaderScore();
//        int invadedKills = warEntity.getInvadedScore();
//
//        if(invaderKills == invadedKills) return null; // draw
//
//        if(invaderKills > invadedKills) return warEntity.getInvaderGuildId();
//
//        if(invaderKills < invadedKills) return warEntity.getInvadedGuildId();
//
//        return null;
//    }
//    @Nullable
//    public UUID getLooserGuildUUID(WarEntity warEntity) {
//
//        int invaderKills = warEntity.getInvaderScore();
//        int invadedKills = warEntity.getInvadedScore();
//
//        if(invaderKills == invadedKills) return null; // draw
//
//
//        if(invaderKills < invadedKills) return warEntity.getInvaderGuildId();
//
//        if(invaderKills > invadedKills) return warEntity.getInvadedGuildId();
//
//        return null;
//    }
//
//    public Optional<WarEntity> getActiveWarOfGuilds(UUID guild1Id, UUID guild2Id) {
//
//
//
//
//        Set<WarEntity> warEntityByGuildIds = warEntityManager.getWarByGuildIds(guild1Id, guild2Id, true, false);
//
//        if(!warEntityByGuildIds.isEmpty()) return warEntityByGuildIds.stream().findFirst();
//
//        return Optional.empty();
//    }
//
//
//    public boolean canCollectReward(WarRecordEntity warRecordEntity) {
//        return !warRecordEntity.isRewardCollected();
//    }
//
//    public void endWar(WarEntity warEntity) {
//
//        UUID looserGuildUUID = getLooserGuildUUID(warEntity);
//
//        String invaderTag = "ERROR";
//        String invadedTag = "ERROR";
//
//        GuildManager guildManager = funnyGuilds.getGuildManager();
//
//        Option<Guild> invaderOptionalGuild = guildManager.findByUuid(warEntity.getInvaderGuildId());
//        if(invaderOptionalGuild.isPresent()) invaderTag = invaderOptionalGuild.get().getTag();
//
//        Option<Guild> invadedOptionalGuild = guildManager.findByUuid(warEntity.getInvadedGuildId());
//        if(invadedOptionalGuild.isPresent()) invadedTag = invadedOptionalGuild.get().getTag();
//
//        if(invaderOptionalGuild.isPresent() && invadedOptionalGuild.isPresent()) {
//            Guild invaderGuild = invaderOptionalGuild.get();
//            Guild invadedGuild = invadedOptionalGuild.get();
//
//            {
//                Set<Guild> enemies = invaderGuild.getEnemies();
//                enemies.remove(invadedGuild);
//                invaderGuild.setEnemies(enemies);
//            }
//            {
//                Set<Guild> enemies = invadedGuild.getEnemies();
//                enemies.remove(invaderGuild);
//                invadedGuild.setEnemies(enemies);
//            }
//
//            invaderGuild.getOnlineMembers().forEach(onlinePlayer -> onlinePlayer.sendMessage(Messages.YOUR_WAR_WITH_CLAN_XXXX_HAS_ENDED(invadedGuild.getTag())));
//            invadedGuild.getOnlineMembers().forEach(onlinePlayer -> onlinePlayer.sendMessage(Messages.YOUR_WAR_WITH_CLAN_XXXX_HAS_ENDED(invaderGuild.getTag())));
//        }
//
//        WarRecordEntity invaderRecord = new WarRecordEntity(
//                warEntity.getInvaderGuildId(),
//                invaderTag,
//                invadedTag,
//                warEntity.getInvaderScore(),
//                warEntity.getInvadedScore(),
//                warEntity.getWarStart(),
//                warEntity.getWarEnd());
//
//        WarRecordEntity invadedRecord = new WarRecordEntity(warEntity.getInvadedGuildId(),
//                invadedTag,
//                invaderTag,
//                warEntity.getInvadedScore(),
//                warEntity.getInvaderScore(),
//                warEntity.getWarStart(),
//                warEntity.getWarEnd());
//
//        warRecordEntityManager.createWarRecordEntity(invaderRecord);
//        warRecordEntityManager.createWarRecordEntity(invadedRecord);
//
//        if(looserGuildUUID != null) {
//
//            Option<Guild> byUuid = guildManager.findByUuid(looserGuildUUID);
//
//            if(byUuid.isPresent()){
//                Guild guild = byUuid.get();
//                int oldLives = guild.getLives();
//                oldLives--;
//                guild.setLives(oldLives);
//                Bukkit.getServer().getPluginManager().callEvent(new GuildLivesChangeEvent(FunnyEvent.EventCause.COMBAT, null, guild, oldLives));
//            }
//        }
//        warEntity.setActive(false);
//        warEntity.setFinished(true);
//
//        warEntityManager.deleteWarById(warEntity.getId());
//    }
//
//
//    public void collectReward(Player player, WarRecordEntity warRecord) {
//        warRecord.setRewardCollected(true);
//        warRecordEntityManager.createOrUpdateWarRecordEntity(warRecord);
//
//        player.getInventory().addItem(new ItemStack(Material.BARRIER));
//    }
}
