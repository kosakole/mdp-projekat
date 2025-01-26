package org.unibl.etf.mdp.workwithdistributors.model;

import java.io.File;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.logging.Logger;

import org.unibl.etf.mdp.rmi.DistributorRMIInterface;
import org.unibl.etf.mdp.source.Property;
import org.unibl.etf.mdp.workwithdistributors.rmi.Distributor;
import org.unibl.etf.mdp.workwithdistributors.rmi.RawMaterial;
import org.unibl.etf.mdp.workwithdistributors.rmi.RawMaterialAmount;
import org.unibl.etf.mdp.workwithdistributors.rmi.ServerRMIInterface;
import org.unibl.etf.mdp.workwithdistributors.rmi.imp.ServerRMI;

public class Server {

	private ServerRMI server;

	private static final String ServerRMI;
	public static final String PATH;
	private static final String SERVER_NAME;
	private static final String SERVER_HOST;
	private static final int RMI_PORT;
	private static final String CLIENT_POLICY;
	private static final String SERVER_POLICY;
	private static final String RMI_HOST;
	private static Property property;
	private static Logger logger;
	
	static {
		property = Property.getProperty();
		RMI_HOST = property.getRMI_HOST();
		RMI_PORT = property.getRMI_PORT();
		SERVER_HOST = property.getSERVER_HOST();
		PATH = property.getRESOURSE_PATH();
		CLIENT_POLICY = property.getCLIENT_POLICY();
		SERVER_POLICY = property.getSERVER_POLICY();
		SERVER_NAME = property.getSERVER_NAME();
		ServerRMI = property.getServerRMI();
		logger = property.getLogger();
	}
	public Server() {
		System.setProperty("java.security.policy", PATH + File.separator + SERVER_POLICY);
		System.setProperty("java.security.policy", PATH + File.separator + CLIENT_POLICY);
		if(System.getSecurityManager() == null)
			System.setSecurityManager(new SecurityManager());
		System.setProperty("java.rmi.server.hostname",SERVER_HOST);
		
		try {
			server = new ServerRMI();
			ServerRMIInterface stub = (ServerRMIInterface) UnicastRemoteObject.exportObject(server, 0);
			LocateRegistry.createRegistry(RMI_PORT);
//			Naming.rebind("//127.0.0.1:1099/"+ServerRMI, stub);
			Naming.rebind(String.format("//%s:%d/%s", RMI_HOST, RMI_PORT, ServerRMI), stub);
		} catch (RemoteException e) {
			logger.severe(e.getMessage());
		} catch (MalformedURLException e) {
			logger.severe(e.getMessage());
		} 
	}
	
	public  List<Distributor> getDistributors(){
		return server.getDistributors();
	}
	
	public List<RawMaterial> getRawMaterials(Distributor dis){
		return server.getRawMaterial(dis);
	}
	
	public void sendOrder(Distributor dis, List<RawMaterialAmount> list) throws MalformedURLException, RemoteException, NotBoundException {
//		LocateRegistry.getRegistry(1098);
//		DistributorRMIInterface serverDis = (DistributorRMIInterface) Naming.lookup("//127.0.0.1/" + dis.getName());
		DistributorRMIInterface serverDis = (DistributorRMIInterface) Naming.lookup(String.format("//%s/%s", RMI_HOST, dis.getName()));
		serverDis.addOrder(SERVER_NAME, list);
	}
}
