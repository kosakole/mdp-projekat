package org.unibl.etf.mdp.workwithdistributors.service;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import org.unibl.etf.mdp.workwithdistributors.model.Server;
import org.unibl.etf.mdp.workwithdistributors.rmi.Distributor;
import org.unibl.etf.mdp.workwithdistributors.rmi.RawMaterial;
import org.unibl.etf.mdp.workwithdistributors.rmi.RawMaterialAmount;

public class ServerService {

	private Server server;

	public ServerService() {
		server = new Server();
	}
	
	public List<Distributor> getDistributors(){
		return server.getDistributors();
	}
	
	public Distributor[] getDistributorsArray() {
		if(getDistributors().size() < 1)
			return null;
		Distributor[] ret = new Distributor[getDistributors().size()];
		List<Distributor> list = getDistributors();
		for(int i = 0; i < getDistributors().size(); i++) {
			ret[i] = list.get(i);
		}
		return ret;
	}
	
	public List<RawMaterial> getRawMaterila(Distributor dis){
		return server.getRawMaterials(dis);
	}
	
	public RawMaterial[] getRawMaterialArray(Distributor dis) {
		List<RawMaterial> list = getRawMaterila(dis);
		RawMaterial[] ret = new RawMaterial[list.size()];
		for(int i = 0; i < list.size(); i++) {
			ret[i] = list.get(i);
		}
		return ret;
	}
	
	public void sendOrder(Distributor dis, List<RawMaterialAmount> list) throws MalformedURLException, RemoteException, NotBoundException {
		server.sendOrder(dis, list);
	}
}
