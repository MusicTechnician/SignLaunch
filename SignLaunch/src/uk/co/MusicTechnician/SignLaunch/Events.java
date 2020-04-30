package uk.co.MusicTechnician.SignLaunch;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Events implements Listener{

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Block b = e.getClickedBlock();
		World world = b.getWorld();
		
		int x = b.getX();
		int y = b.getY();
		int z = b.getZ();
		
		//ub = Underneath Block, Built this location to be able to check 2 blocks below 
		Block ub = new Location(world, x, y - 2, z).getBlock();
		
		//checks if pressure plate + sign combo		
		if(!(e.getAction() == Action.LEFT_CLICK_BLOCK)) {
		if(b.getType() == Material.STONE_PRESSURE_PLATE ||
			b.getType() == Material.OAK_PRESSURE_PLATE ||
			b.getType() == Material.SPRUCE_PRESSURE_PLATE ||
			b.getType() == Material.BIRCH_PRESSURE_PLATE ||
			b.getType() == Material.JUNGLE_PRESSURE_PLATE ||
			b.getType() == Material.ACACIA_PRESSURE_PLATE ||
			b.getType() == Material.DARK_OAK_PRESSURE_PLATE ||
			b.getType() == Material.LIGHT_WEIGHTED_PRESSURE_PLATE ||
			b.getType() == Material.HEAVY_WEIGHTED_PRESSURE_PLATE){
			if(ub.getType() == Material.ACACIA_SIGN || 
					ub.getType() == Material.ACACIA_WALL_SIGN ||
					ub.getType() == Material.BIRCH_SIGN || 
					ub.getType() == Material.BIRCH_WALL_SIGN ||
					ub.getType() == Material.DARK_OAK_SIGN || 
					ub.getType() == Material.DARK_OAK_WALL_SIGN ||
					ub.getType() == Material.JUNGLE_SIGN|| 
					ub.getType() == Material.JUNGLE_WALL_SIGN ||
					ub.getType() == Material.OAK_SIGN || 
					ub.getType() == Material.OAK_WALL_SIGN ||
					ub.getType() == Material.SPRUCE_SIGN|| 
					ub.getType() == Material.SPRUCE_WALL_SIGN){
				Sign sign = (Sign)ub.getState();
					if(sign.getLine(0).equals("[Launch]")) {
						
						float sx = Float.parseFloat(sign.getLine(1));
						float sy = Float.parseFloat(sign.getLine(2));
						float sz = Float.parseFloat(sign.getLine(3));
						
						
					
				//Launch Player
				p.setVelocity(p.getLocation().getDirection().setX(sx).setY(sy).setZ(sz));
				p.playSound(p.getLocation(), Sound.BLOCK_END_PORTAL_FRAME_FILL, 1F, 1F);
					}
				}
			}
		}
	}
}
