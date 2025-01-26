package org.unibl.etf.mdp.rmi.imp;



import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;

import org.unibl.etf.mdp.rmi.DistributorRMIInterface;
import org.unibl.etf.mdp.source.Property;
import org.unibl.etf.mdp.workwithdistributors.rmi.RawMaterialAmount;

public class DistributorRMI implements DistributorRMIInterface{
	
	private static Property property;
	public static final String PATH;
	private HashMap<String, List<RawMaterialAmount>> mapOrders;
	
	static {
		property = Property.getProperty();
		PATH = property.getRESOURSE_PATH();
	}
	public DistributorRMI() {
		super();
		mapOrders = new HashMap<String, List<RawMaterialAmount>>();
	}


	@Override
	public void addOrder(String name, List<RawMaterialAmount> list) throws RemoteException {
		mapOrders.put(name, list);
	}


	public HashMap<String, List<RawMaterialAmount>> getMapOrders() {
		return mapOrders;
	}


	public void setMapOrders(HashMap<String, List<RawMaterialAmount>> mapOrders) {
		this.mapOrders = mapOrders;
	}
	
	public List<RawMaterialAmount> getOrder(String factory){
		return mapOrders.get(factory);
	}

}