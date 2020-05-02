package uk.co.MusicTechnician.SignLaunch;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{

	public void onEnable(){
		getServer().getConsoleSender().sendRawMessage("SignLaunch loaded");
		
		//Events Register
		 getServer().getPluginManager().registerEvents(new Events(), this);
	}
	
	public void onDisable() {
		getServer().getConsoleSender().sendRawMessage("SignLaunch Disabled");
	}
	
	


}