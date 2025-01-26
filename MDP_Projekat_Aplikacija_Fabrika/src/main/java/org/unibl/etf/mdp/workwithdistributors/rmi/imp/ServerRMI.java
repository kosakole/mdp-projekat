package org.unibl.etf.mdp.workwithdistributors.rmi.imp;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.unibl.etf.mdp.rmi.DistributorRMIInterface;
import org.unibl.etf.mdp.source.Property;
import org.unibl.etf.mdp.workwithdistributors.rmi.Distributor;
import org.unibl.etf.mdp.workwithdistributors.rmi.RawMaterial;
import org.unibl.etf.mdp.workwithdistributors.rmi.RawMaterialAmount;
import org.unibl.etf.mdp.workwithdistributors.rmi.ServerRMIInterface;

public class ServerRMI implements ServerRMIInterface{

	private static final String RMI_HOST;
	private static final String SERVER_NAME;
	private static HashMap<Distributor, List<RawMaterial>> mapDistributors;
	private static Property property;
	
	static {
		property = Property.getProperty();
		SERVER_NAME = property.getSERVER_NAME();
		RMI_HOST = property.getRMI_HOST();
	}
	
	public ServerRMI() {
		mapDistributors = new HashMap<>();
	}

	public  HashMap<Distributor, List<RawMaterial>> getMapDistributors() {
		return mapDistributors;
	}

	public  List<Distributor> getDistributors(){
		List<Distributor> distributors = new ArrayList<>();
		for(Distributor s: mapDistributors.keySet())
			distributors.add(s);
		return distributors;
	}
	
	public List<RawMaterial> getRawMaterial(Distributor dis){
		return mapDistributors.get(dis);
	}
	
	@Override
	public void generateProduct(Distributor dis, RawMaterial product) throws RemoteException {
		if(mapDistributors.containsKey(dis)) {
			mapDistributors.get(dis).add(product);
		} else {
			mapDistributors.put(dis, new ArrayList<>());
			mapDistributors.get(dis).add(product);
		}
	}
	
	public void createOrder(Distributor dis, List<RawMaterialAmount> products) throws MalformedURLException, RemoteException, NotBoundException {
//		DistributorRMIInterface distributorRMI = (DistributorRMIInterface) Naming.lookup("//127.0.0.1/"+dis.getName());
		DistributorRMIInterface distributorRMI = (DistributorRMIInterface) Naming.lookup(String.format("//%s/%s", RMI_HOST, dis.getName()));
		distributorRMI.addOrder(SERVER_NAME, products);
	}
}
