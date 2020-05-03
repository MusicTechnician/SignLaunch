package uk.co.MusicTechnician.SignLaunch;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.block.data.AnaloguePowerable;
import org.bukkit.block.data.Powerable;
import org.bukkit.block.data.type.WallSign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Events implements Listener{

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		
		if(e.getClickedBlock() == null) {
			e.setCancelled(true);
			return;
		}
		
		Player p = e.getPlayer();
		Block b = e.getClickedBlock();
		Action a = e.getAction();

		
		int x = b.getX();
		int y = b.getY();
		int z = b.getZ();
		
		//ub = Underneath Block, Built this location to be able to check 2 blocks below 
		Block ub = new Location(p.getWorld(), x, y - 2, z).getBlock();
		

		//checks if pressure plate + sign combo		
		if((a != Action.LEFT_CLICK_BLOCK && a != Action.RIGHT_CLICK_BLOCK)) {
		if(b.getBlockData() instanceof Powerable ||
			b.getBlockData() instanceof AnaloguePowerable){
			if(ub.getState() instanceof Sign || 
				ub.getBlockData() instanceof WallSign){
				Sign sign = (Sign)ub.getState();
				//checks for correct top line
					if(sign.getLine(0).equals("[Launch]")) {
						
						//grabs remaining sign lines and makes sure they're floats
						try {
						float sx = Float.parseFloat(sign.getLine(1));
						float sy = Float.parseFloat(sign.getLine(2));
						float sz = Float.parseFloat(sign.getLine(3));
						//Launch Player
						p.setVelocity(p.getLocation().getDirection().setX(sx).setY(sy).setZ(sz));
						p.playSound(p.getLocation(), Sound.BLOCK_END_PORTAL_FRAME_FILL, 1F, 1F);						
						} catch (NumberFormatException except) {
							//ignore formatting errors
						}
					}
				}
			}
		}
	}
}
