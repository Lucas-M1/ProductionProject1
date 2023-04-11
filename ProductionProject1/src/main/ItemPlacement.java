package main;

import items.DoorItem;
import items.KeyItem;

public class ItemPlacement {
	
	Frame f;
	
	public ItemPlacement (Frame f) {
		this.f = f;
	}
	
	public void itemLocationSet() {
		f.itm[0] = new KeyItem();
		f.itm[0].envX = 23 * f.realTileSize;
		f.itm[0].envY = 7 * f.realTileSize;
		
		f.itm[1] = new KeyItem();
		f.itm[1].envX = 23 * f.realTileSize;
		f.itm[1].envY = 40 * f.realTileSize;
		
		f.itm[2] = new KeyItem();
		f.itm[2].envX =  44 * f.realTileSize;
		f.itm[2].envY =  26 * f.realTileSize;
		
		f.itm[3] = new KeyItem();
		f.itm[3].envX =  47 * f.realTileSize;
		f.itm[3].envY =  42 * f.realTileSize;
		
		f.itm[4] = new DoorItem();
		f.itm[4].envX = 11 * f.realTileSize;
		f.itm[4].envY = 29 * f.realTileSize;
	}

}


