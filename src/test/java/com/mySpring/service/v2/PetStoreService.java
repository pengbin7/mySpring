package com.mySpring.service.v2;

import com.mySpring.dao.v2.AcountDao;
import com.mySpring.dao.v2.ItemDao;

public class PetStoreService {

	private AcountDao acountDao;
	
	private ItemDao itemDao;

	private String owner;

	private  int version;

	public AcountDao getAcountDao(){
		return acountDao;
	}
	
	public ItemDao getItemDao(){
		return itemDao;
	}
	
	public void setAcountDao(AcountDao acountDao){
		this.acountDao = acountDao;
	}
	
	public void setItemDao(ItemDao itemDao){
		this.itemDao = itemDao;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}
