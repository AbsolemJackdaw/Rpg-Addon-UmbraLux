package subaraki.umbralux.entity.minion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;

public class MinionRegistry {
	/**should only be filled server side !*/
	private static HashMap<String, List<IMinion>> playerMinions = new HashMap();

	/**returns the full list of minions under given player's name. if no such player was registered, a new empty list will be returned.*/
	public static ArrayList<IMinion> minionsForPlayer(EntityPlayer player){

		if (!MinionRegistry.playerMinions.containsKey(player.getName())) {
			MinionRegistry.playerMinions.put(player.getName(),new ArrayList<IMinion>());
		}

		return (ArrayList<IMinion>) playerMinions.get(player.getName());
	}

	/**add given minion to the list of all minions for given player*/
	public static void addMinionForPlayer(EntityPlayer player, IMinion minion){
		if(player == null)
			return;
		
		List<IMinion> list = minionsForPlayer(player);
		if (!list.contains(minion))
			list.add(minion);
	}

	/**remove given minion from the list of minions for given player. */
	public static void removeMinion(EntityPlayer player, IMinion minion){
		if(player == null)
			return;
		
		List<IMinion> list = minionsForPlayer(player);
		if (list.contains(minion))
			list.remove(minion);
	}
	
	/**Removes given player from the list of minions. called when logging out.*/
	public static void removePlayerFromList(EntityPlayer player){
		if(player == null)
			return;
		
		if(playerMinions.containsKey(player.getName())){
			playerMinions.remove(player.getName());
		}
	}
}
