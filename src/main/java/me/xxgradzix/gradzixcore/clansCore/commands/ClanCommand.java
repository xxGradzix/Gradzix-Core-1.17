package me.xxgradzix.gradzixcore.clansCore.commands;

import me.xxgradzix.gradzixcore.clansCore.data.database.entities.ClanEntity;
import me.xxgradzix.gradzixcore.clansCore.data.database.entities.UserEntity;
import me.xxgradzix.gradzixcore.clansCore.exceptions.*;
import me.xxgradzix.gradzixcore.clansCore.managers.ClanManager;
import me.xxgradzix.gradzixcore.clansCore.managers.UserManager;
import me.xxgradzix.gradzixcore.clansCore.messages.Messages;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ClanCommand implements CommandExecutor, TabCompleter {
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        ArrayList<String> completions = new ArrayList<>();
        if(args.length == 1) {
            for(ClanSubCommand subCommand : ClanSubCommand.values()) {
                completions.add(subCommand.name().toLowerCase());
            }
        }
        if(args.length == 2) {
            if(args[0].equalsIgnoreCase(ClanSubCommand.INFO.name())) {
                for(ClanEntity clan : ClanManager.getAllClans()) {
                    completions.add(clan.getTag());
                }
            }
            if(args[0].equalsIgnoreCase(ClanSubCommand.ZAPROS.name())) {
                for(Player player : Bukkit.getOnlinePlayers()) {
                    if(player.equals(sender)) continue;
                    completions.add(player.getName());
                }
            }
            if(args[0].equalsIgnoreCase(ClanSubCommand.DOLACZ.name())) {
                for(ClanEntity clan : ClanManager.getAllClans()) {
                    completions.add(clan.getTag());
                }
            }
            if(args[0].equalsIgnoreCase(ClanSubCommand.WYRZUC.name())) {
                if(!(sender instanceof Player)) return completions;
                Optional<ClanEntity> clanEntityByLeader = ClanManager.getClanEntityByLeader(UserManager.getOrCreateUserEntity((Player) sender));
                if(clanEntityByLeader.isPresent()) {
                    for(UUID userEntityUUID : clanEntityByLeader.get().getMembersUUIDs()) {
                        Player player = Bukkit.getPlayer(userEntityUUID);
                        if(player == null) continue;
                        completions.add(player.getName());
                    }
                }
            }
        }
        return completions;

    }

    private enum ClanSubCommand {
        INFO,
        ZALOZ,
        USUN,
        ZAPROS,
        DOLACZ,
        WYRZUC,
        OPUSC,
        PVP,
        USTAWLIDERA,
        ZASTEPCA
    }
    private static void sendAllUsages(Player player) {
        player.sendMessage(ChatColor.DARK_GRAY + "------» " + ChatColor.DARK_GREEN + "Komendy Gildyjne" + ChatColor.DARK_GRAY + " «-------");
        player.sendMessage(Messages.CLAN_INFO_COMMAND_USAGE);
        player.sendMessage(Messages.CREATE_CLAN_COMMAND_USAGE);
        player.sendMessage(Messages.DELETE_CLAN_COMMAND_USAGE);
        player.sendMessage(Messages.INVITE_COMMAND_USAGE);
        player.sendMessage(Messages.JOIN_COMMAND_USAGE);
        player.sendMessage(Messages.KICK_COMMAND_USAGE);
        player.sendMessage(Messages.LEAVE_COMMAND_USAGE);
        player.sendMessage(Messages.PVP_COMMAND_USAGE);
        player.sendMessage(Messages.LEADER_COMMAND_USAGE);
        player.sendMessage(Messages.DEPUTY_COMMAND_USAGE);

    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        if(args.length < 1) {
            sendAllUsages(player);
            return false;
        }
        ClanSubCommand subCommand;
        try {
            subCommand = ClanSubCommand.valueOf(args[0].toUpperCase());
        } catch (IllegalArgumentException exception) {
            sendAllUsages(player);
            return false;
        }
        switch (subCommand) {
            case INFO: {
                if(args.length != 2) {
                    player.sendMessage(Messages.CLAN_INFO_COMMAND_USAGE);
                    return false;
                }
                String tag = args[1];
                Optional<ClanEntity> clan = ClanManager.getClanEntityByTag(tag);
                if(!clan.isPresent()) {
                    player.sendMessage(Messages.CLAN_WITH_THIS_TAG_DOES_NOT_EXISTS);
                    return false;
                }
                ClanEntity targetClan = clan.get();
                player.sendMessage(Messages.CLAN_INFO(targetClan));
            }
            return true;
            case ZALOZ:
            {
                if (args.length != 3) {
                    player.sendMessage("Użycie: /klan zaloz <nazwa> <tag>");
                    return false;
                }
                String clanTag = args[1];
                String clanName = args[2];
                if (!assertTag(clanTag)) {
                    player.sendMessage("Tag klanu musi zawierać od 3 do 5 znaków i składać się z liter i cyfr oraz nie może zawierać spacji");
                    return false;
                }
                if (!assertName(clanName)) {
                player.sendMessage("Nazwa klanu musi zawierać od 3 do 20 znaków i składać się z liter i cyfr oraz nie może zawierać spacji");
                return false;
                }
                try {
                    ClanManager.createClan(clanName, clanTag, player);
                } catch (ClanWithThisTagAlreadyExists clanWithThisTagAlreadyExists) {
                    player.sendMessage(Messages.CLAN_WITH_THIS_TAG_ALREADY_EXISTS);
                    return false;
                } catch (ThisUserAlreadyBelongsToAnotherClan e) {
                    player.sendMessage(Messages.YOU_BELONG_TO_ANOTHER_CLAN);
                    return false;
                } catch (ThisUserAlreadyIsALeaderOfAnotherClan e) {
                    player.sendMessage(Messages.YOU_BELONG_TO_ANOTHER_CLAN);
                    return false;
                } catch (ClanWithThisNameAlreadyExists e) {
                    player.sendMessage(Messages.CLAN_WITH_THIS_NAME_ALREADY_EXISTS);
                    return false;
                }
                player.sendMessage(Messages.CREATED_GUILD_MESSAGE_LEADER(clanName, clanTag));
            }
            return true;

            case USUN:
            {
                if (args.length != 1) {
                    player.sendMessage(Messages.DELETE_CLAN_COMMAND_USAGE);
                    return false;
                }
                try {
                    Optional<ClanEntity> clanEntityOptional = ClanManager.getClanEntityOfMember(UserManager.getOrCreateUserEntity(player));
                    if (!clanEntityOptional.isPresent()) {
                        player.sendMessage(Messages.YOU_DONT_BELONG_TO_ANY_CLAN);
                        return false;
                    }
                    ClanManager.removeClanOfPlayerByPlayer(player);
                } catch (ThisUserIsNotALeader e) {
                    player.sendMessage(Messages.ONLY_LEADER_CANT_DELETE_CLAN);
                    return false;
                }
                player.sendMessage(Messages.DELETE_CLAN_SUCCESSFUL);
            }
            return true;

            case ZAPROS:
            {
                if(args.length != 2) {
                    sender.sendMessage(Messages.INVITE_COMMAND_USAGE);
                    return false;
                }

                UserEntity userEntity = UserManager.getOrCreateUserEntity(player);

                Optional<ClanEntity> clanEntityByClanMember = ClanManager.getClanEntityByLeader(userEntity);

                if(!clanEntityByClanMember.isPresent()) {
                    player.sendMessage(Messages.YOU_DONT_HAVE_PERMISSION_TO_DO_THIS);
                    return false;
                }

                String nick = args[1];
                Player invited;
                try {
                    invited = Bukkit.getPlayer(nick);
                } catch (Exception e) {
                    player.sendMessage(Messages.PLAYER_IS_NOT_ONLINE(nick));
                    return false;
                }
                if(invited == null) {
                    player.sendMessage(Messages.PLAYER_IS_NOT_ONLINE(nick));
                    return false;
                }
                ClanManager.addClanInvitation(invited, clanEntityByClanMember.get().getUuid());
            }
            return true;

            case DOLACZ:
            {
                if(args.length != 2) {
                    sender.sendMessage(Messages.JOIN_COMMAND_USAGE);
                    return false;
                }
                String tag = args[1];
                Optional<ClanEntity> clan = ClanManager.getClanEntityByTag(tag);
                if(!clan.isPresent()) {
                    player.sendMessage(Messages.CLAN_WITH_THIS_TAG_DOES_NOT_EXISTS);
                    return false;
                }
                if(!ClanManager.isPlayerInvitedToClan(player, clan.get().getUuid())) {
                    player.sendMessage(Messages.YOU_WERE_NOT_INVITED_TO_THIS_CLAN);
                    return false;
                }
                try {
                    ClanManager.addMemberToClan(clan.get().getUuid(), player);
                } catch (ThisUserAlreadyBelongsToAnotherClan e) {
                    player.sendMessage(Messages.YOU_BELONG_TO_ANOTHER_CLAN);
                    return false;
                } catch (ClanWithThisUUIDDoesNotExists e) {
                    player.sendMessage(Messages.CLAN_WITH_THIS_TAG_DOES_NOT_EXISTS);
                    return false;
                } catch (ThisUserAlreadyBelongsToThisClan e) {
                    player.sendMessage(Messages.YOU_BELONG_TO_THIS_CLAN);
                }
            }
            return true;
            case WYRZUC:
            {
                if(args.length != 2) {
                    sender.sendMessage(Messages.KICK_COMMAND_USAGE);
                    return false;
                }
                Optional<ClanEntity> leaderClan = ClanManager.getClanEntityByLeader(UserManager.getOrCreateUserEntity(player));
                if(!leaderClan.isPresent()) {
                    player.sendMessage(Messages.YOU_DONT_HAVE_PERMISSION_TO_DO_THIS);
                    return false;
                }
                String nick = args[1];
                Optional<UserEntity> userEntityByNick = UserManager.getUserEntityByNick(nick);
                if(!userEntityByNick.isPresent()) {
                    player.sendMessage(Messages.PLAYER_DOES_NOT_EXISTS(nick));
                    return false;
                }
                UserEntity userEntity = UserManager.getOrCreateUserEntity(player);
                boolean isLeader = ClanManager.getClanEntityByLeader(userEntity).isPresent();
                if(isLeader) {
                    player.sendMessage(Messages.YOU_CANT_LEAVE_CLAN_BECAUSE_YOU_ARE_LEADER);
                    return false;
                }
                try {
                    ClanManager.removeMemberFromClan(leaderClan.get().getUuid(), Bukkit.getPlayer(nick));
                    player.sendMessage(Messages.YOU_KICKED_PLAYER(nick));
                } catch (ThisClanDoesNotExists | ThisUserDoesNotBelongToThisClan e) {
                    player.sendMessage(Messages.PLAYER_DOES_BELONG_TO_YOUR_CLAN(nick));
                    return false;
                }
            }
            return true;
            case OPUSC:
            {
                if(args.length != 1) {
                    sender.sendMessage(Messages.LEAVE_COMMAND_USAGE);
                    return false;
                }
                UserEntity userEntity = UserManager.getOrCreateUserEntity(player);
                Optional<ClanEntity> clanEntityOfMember = ClanManager.getClanEntityOfMember(userEntity);
                if(!clanEntityOfMember.isPresent()) {
                    player.sendMessage(Messages.YOU_DONT_BELONG_TO_ANY_CLAN);
                    return false;
                }
                boolean isLeader = ClanManager.getClanEntityByLeader(userEntity).isPresent();
                if(isLeader) {
                    player.sendMessage(Messages.YOU_CANT_LEAVE_CLAN_BECAUSE_YOU_ARE_LEADER);
                    return false;
                }
                ClanManager.removeMemberFromClan(player);
            }
            return true;
            case PVP:
            {
                if(args.length != 1) {
                    sender.sendMessage(Messages.PVP_COMMAND_USAGE);
                    return false;
                }
                UserEntity userEntity = UserManager.getOrCreateUserEntity(player);
                Optional<ClanEntity> clanEntityOfMember = ClanManager.getClanEntityOfMember(userEntity);
                if(!clanEntityOfMember.isPresent()) {
                    player.sendMessage(Messages.YOU_DONT_BELONG_TO_ANY_CLAN);
                    return false;
                }
                if(!ClanManager.getClanEntityByLeader(userEntity).isPresent()) {
                    player.sendMessage(Messages.ONLY_LEADER_CAN_CHANGE_PVP);
                    return false;
                }
                ClanManager.changePvpStatus(ClanManager.getClanEntityByLeader(userEntity).get());
            }
            return true;
            case USTAWLIDERA:
            {
                if(args.length != 2) {
                    sender.sendMessage(Messages.LEADER_COMMAND_USAGE);
                    return false;
                }

                UserEntity userEntity = UserManager.getOrCreateUserEntity(player);
                Optional<ClanEntity> clanEntityOfMember = ClanManager.getClanEntityOfMember(userEntity);
                if(!clanEntityOfMember.isPresent()) {
                    player.sendMessage(Messages.YOU_DONT_BELONG_TO_ANY_CLAN);
                    return false;
                }
                if(!ClanManager.getClanEntityByLeader(userEntity).isPresent()) {
                    player.sendMessage(Messages.ONLY_LEADER_CAN_SET_LEADER);
                    return false;
                }
                String nick = args[1];

                Optional<UserEntity> userEntityByNick = UserManager.getUserEntityByNick(nick);
                if(!userEntityByNick.isPresent()) {
                    player.sendMessage(Messages.PLAYER_DOES_NOT_EXISTS(nick));
                    return false;
                }
                ClanManager.getClanEntityOfMember(userEntityByNick.get()).ifPresent(clanEntity -> {
                    if(clanEntity.getLeader().equals(userEntityByNick.get())) {
                        player.sendMessage(Messages.PLAYER_DOES_BELONG_TO_YOUR_CLAN(nick));
                        return;
                    }

                    try {
                        ClanManager.setLeaderOfClan(clanEntity.getUuid(), userEntityByNick.get());
                    } catch (ThisClanDoesNotExists e) {
                        player.sendMessage(Messages.CLAN_WITH_THIS_TAG_DOES_NOT_EXISTS);
                    } catch (ThisUserDoesNotBelongToThisClan e) {
                        player.sendMessage(Messages.PLAYER_DOES_BELONG_TO_YOUR_CLAN(nick));
                    }
                    player.sendMessage(Messages.SUCCESFULLU_GRANTED_LEADER(nick));
                });
                player.sendMessage(Messages.THIS_PLAYER_DOES_NOT_BELONG_TO_THIS_CLAN);

            }
            return true;
            case ZASTEPCA:
            {
                if(args.length != 2) {
                    sender.sendMessage(Messages.DEPUTY_COMMAND_USAGE);
                    return false;
                }

                UserEntity userEntity = UserManager.getOrCreateUserEntity(player);
                Optional<ClanEntity> clanEntityOfMember = ClanManager.getClanEntityOfMember(userEntity);
                if(!clanEntityOfMember.isPresent()) {
                    player.sendMessage(Messages.YOU_DONT_BELONG_TO_ANY_CLAN);
                    return false;
                }
                if(!ClanManager.getClanEntityByLeader(userEntity).isPresent()) {
                    player.sendMessage(Messages.ONLY_LEADER_CAN_SET_DEPUTY);
                    return false;
                }
                String nick = args[1];

                Optional<UserEntity> userEntityByNick = UserManager.getUserEntityByNick(nick);
                if(!userEntityByNick.isPresent()) {
                    player.sendMessage(Messages.PLAYER_DOES_NOT_EXISTS(nick));
                    return false;
                }
                ClanManager.getClanEntityOfMember(userEntityByNick.get()).ifPresent(clanEntity -> {
                    if(clanEntity.getLeader().equals(userEntityByNick.get())) {
                        player.sendMessage(Messages.PLAYER_DOES_BELONG_TO_YOUR_CLAN(nick));
                        return;
                    }

                    try {
                        ClanManager.setDeputyOfClan(clanEntity.getUuid(), userEntityByNick.get());
                    } catch (ThisClanDoesNotExists e) {
                        player.sendMessage(Messages.CLAN_WITH_THIS_TAG_DOES_NOT_EXISTS);
                    } catch (ThisUserDoesNotBelongToThisClan e) {
                        player.sendMessage(Messages.PLAYER_DOES_BELONG_TO_YOUR_CLAN(nick));
                    }
                    player.sendMessage(Messages.SUCCESFULLU_GRANTED_LEADER(nick));
                });
                player.sendMessage(Messages.THIS_PLAYER_DOES_NOT_BELONG_TO_THIS_CLAN);

            }
            return true;
        }
        return false;
    }
    private boolean assertName(String name) {
        if(name.length() > 20 || name.length() < 3) return false;
        if(!name.matches("^[a-zA-Z0-9]*$")) return false;
        if(name.contains(" ")) return false;
        return true;
    }
    private boolean assertTag(String name) {
        if(name.length() > 5 || name.length() < 3) return false;
        if(!name.matches("^[a-zA-Z0-9]*$")) return false;
        if(name.contains(" ")) return false;
        return true;
    }
}
