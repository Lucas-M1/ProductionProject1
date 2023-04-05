package main;

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
	}

}


