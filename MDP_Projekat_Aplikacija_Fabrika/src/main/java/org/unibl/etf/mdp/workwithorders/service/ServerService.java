package org.unibl.etf.mdp.workwithorders.service;

import org.unibl.etf.mdp.model.UserFactory;
import org.unibl.etf.mdp.source.UserFactoryData;
import org.unibl.etf.mdp.source.UserFactoryDataJSON;
import org.unibl.etf.mdp.workwithorders.sources.OrderData;

public class ServerService {

	private UserFactoryData dataUserFactory;
	private OrderData dataOrder;
	
	public ServerService() {
		dataUserFactory = new UserFactoryDataJSON();
		dataOrder = new OrderData();
	}
	
	public boolean checkUserFactory(String s){
		return dataUserFactory.contain(new UserFactory(s));
	}
	
	public void storeOrder(String order) {
		dataOrder.store(order);
	}
}
