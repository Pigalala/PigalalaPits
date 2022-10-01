package me.pigalala.pitminigame.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import co.aikar.commands.annotation.CommandCompletion;
import me.pigalala.pitminigame.PigStops;
import me.pigalala.pitminigame.PitGame;
import me.pigalala.pitminigame.PitType;
import me.pigalala.pitminigame.pit.PitCOOKIE;
import me.pigalala.pitminigame.pit.PitSTANDARD;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@CommandAlias("pigstop|pit")
public class CommandPit extends BaseCommand {

    @Default
    public static void practisePit(Player player) {
        if(PigStops.getPlugin().getDefaultPitGame() == PitGame.STANDARD) {
            new PitSTANDARD(player, PitType.FAKE);
            return;
        }
        if(PigStops.getPlugin().getDefaultPitGame() == PitGame.COOKIE) {
            new PitCOOKIE(player, PitType.FAKE);
            return;
        }
    }

    @Subcommand("setgame")
    @CommandCompletion("@pits")
    @CommandPermission("pigstop.admin")
    public static void setNewGame(Player player, PitGame game) {
        PigStops.getPlugin().setDefaultPitGame(game);
        player.sendMessage("§aSet new pit game to " + game.name());
    }

    @Subcommand("setpitblock")
    @CommandCompletion("@blocks")
    @CommandPermission("pigstop.admin")
    public static void setPitBlock(Player player, String blocke) {
        Material block;
        try {
            block = Material.valueOf(blocke.toUpperCase());
        } catch (IllegalArgumentException e) {
            player.sendMessage("§cThat is not a valid block");
            return;
        }

        PigStops.getPlugin().setPitBlock(block);
        player.sendMessage("§aSuccessfully set pit block to " + block.toString().toLowerCase());
    }
}