package fun.ksnb.multilogin.bungee.main;

import fun.ksnb.multilogin.bungee.impl.BungeePlayer;
import moe.caa.multilogin.api.internal.handle.HandleResult;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * Bungee 的事件处理程序
 */
public class GlobalListener implements Listener {
    private final MultiLoginBungee multiLoginBungee;


    public GlobalListener(MultiLoginBungee multiLoginBungee) {
        this.multiLoginBungee = multiLoginBungee;
    }

    @EventHandler
    public void onJoin(PostLoginEvent event) {
        // TODO: 2023/3/12 PostLogin???
        HandleResult result = multiLoginBungee.getMultiCoreAPI().getPlayerHandler().pushPlayerJoinGame(event.getPlayer().getUniqueId(), event.getPlayer().getName());
        if (result.getType() == HandleResult.Type.KICK) {
            if (result.getKickMessage() == null || result.getKickMessage().trim().length() == 0) {
                event.getPlayer().disconnect(new TextComponent(""));
            } else {
                event.getPlayer().disconnect(new TextComponent(result.getKickMessage()));
            }
            return;
        }
        multiLoginBungee.getMultiCoreAPI().getPlayerHandler().callPlayerJoinGame(new BungeePlayer(event.getPlayer()));
    }

    @EventHandler
    public void onQuit(PlayerDisconnectEvent event) {
        multiLoginBungee.getMultiCoreAPI().getPlayerHandler().pushPlayerQuitGame(event.getPlayer().getUniqueId(), event.getPlayer().getName());
    }

    public void register() {
        multiLoginBungee.getProxy().getPluginManager().registerListener(multiLoginBungee, this);
    }
}
