package ru.brenlike.custombossapi.util;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.brenlike.custombossapi.CustomBossApi;

public class ErrorMessages {
    public static final ErrorMessages ONLY_IN_GAME = new ErrorMessages("command.error.only_in_game");
    public static final ErrorMessages UNKNOWN_ARGUMENT = new ErrorMessages("command.error.unknown_argument");
    public static final ErrorMessages LITTLE_ARGS = new ErrorMessages("command.error.little_args");
    public static final ErrorMessages PERMISSION_ERROR = new ErrorMessages("command.error.permission");

    private final String path;

    private ErrorMessages(String translationPath) {
        this.path = translationPath;
    }

    /**
     * Sends system message to player if it instanceof {@link Player}
     * @param sender console sender or player sender
     */
    public void send(CommandSender sender) {
        // Комментарий от разработчика:
        //   Этот класс из моего проекта, я немного его переделал.
        //   Тут изначально было воспроизведение звуков, но я убрал это.
        //   Зачем оно?

        if (sender instanceof Player) {
            send((Player) sender);
            return;
        }
        sender.sendMessage(CustomBossApi.m_7011_().getMessage(path));
    }

    /**
     * Sends system message to player
     * @param player who will see system message
     */
    public void send(Player player) {
        player.sendMessage(CustomBossApi.m_7011_().getMessage(path));
    }
}
