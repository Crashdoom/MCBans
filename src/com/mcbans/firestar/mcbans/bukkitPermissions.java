package com.mcbans.firestar.mcbans;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
@SuppressWarnings("unused")
public class bukkitPermissions{
	private static PermissionHandler permissionHandler = null;
	private bukkitInterface MCBans;
	
	private Settings Config;
	public bukkitPermissions( Settings cf, bukkitInterface p ){
		MCBans = p;
		Config=cf;
	}
	public void setupPermissions() {
		Plugin permissionsPlugin = MCBans.pluginInterface("Permissions");
		if (permissionHandler == null) {
			if (permissionsPlugin != null) {
				permissionHandler = ((Permissions) permissionsPlugin).getHandler();
				MCBans.log.write("Permissions plugin found!");
			}else{
				MCBans.log.write("Using bukkit permissions!");
			}
		}
	}
	public boolean isAllow( String WorldName, String PlayerName, String PermissionNode ){
		Player target = MCBans.getServer().getPlayer(PlayerName);
		if( target!=null ){
			 return isAllow( target, PermissionNode );
		}
		return false;
	}
	public boolean isAllow( Player Player, String PermissionNode ){
		if( permissionHandler != null ){
			if( permissionHandler.has( Player, "mcbans."+PermissionNode ) ){
				return true;
			}
		}else if( Player.hasPermission( "mcbans."+PermissionNode ) ){
			return true;
		}
		return false;
	}
	public boolean inGroup( String WorldName, String PlayerName, String GroupName ){
		if( permissionHandler.inGroup( WorldName, PlayerName, GroupName ) ){
			return true;
		}
		return false;
	}
}