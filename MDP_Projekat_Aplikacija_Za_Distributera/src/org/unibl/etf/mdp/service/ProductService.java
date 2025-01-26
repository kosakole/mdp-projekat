package org.unibl.etf.mdp.service;

import java.io.File;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.unibl.etf.mdp.rmi.DistributorRMIInterface;
import org.unibl.etf.mdp.rmi.imp.DistributorRMI;
import org.unibl.etf.mdp.source.Property;
import org.unibl.etf.mdp.workwithdistributors.rmi.Distributor;
import org.unibl.etf.mdp.workwithdistributors.rmi.RawMaterial;
import org.unibl.etf.mdp.workwithdistributors.rmi.RawMaterialAmount;
import org.unibl.etf.mdp.workwithdistributors.rmi.ServerRMIInterface;


public class ProductService {

	private static final String ServerRMI;
	private static final String MESSAGE_CONNECTES = "Successfull connection";
	private static final String RMI_HOST;
	private static final int RMI_PORT;
	private static final String SERVER_HOST;
	private static final String CLIENT_POLICY;
	private static final String SERVER_POLICY;
	private static final String PATH;
	private static Logger logger;
	private static Property property = Property.getProperty();
	
	private ServerRMIInterface server;
	private Distributor distributor;
	private DistributorRMI distrbutorRMI;
	
	static {
		CLIENT_POLICY = property.getCLIENT_POLICY();
		SERVER_POLICY = property.getSERVER_POLICY();
		PATH = property.getRESOURSE_PATH();
		RMI_HOST = property.getRMI_HOST();
		RMI_PORT = property.getRMI_PORT();
		SERVER_HOST = property.getSERVER_HOST();
		ServerRMI= property.getServerRMI();
		logger = property.getLogger();
	}
	
	public ProductService(Distributor dis) {
		this.distributor = dis;
		System.setProperty("java.security.policy", PATH + File.separator + CLIENT_POLICY);
		System.setProperty("java.security.policy", PATH + File.separator + SERVER_POLICY);
		if(System.getSecurityManager() == null)
			System.setSecurityManager(new SecurityManager());
		System.setProperty("java.rmi.server.hostname",SERVER_HOST);
		try {
			server = (ServerRMIInterface) Naming.lookup(String.format("//%s/%s", RMI_HOST, ServerRMI));
			distrbutorRMI = new DistributorRMI();
			DistributorRMIInterface stub2 = (DistributorRMIInterface) UnicastRemoteObject.exportObject(distrbutorRMI, 0);
			Naming.rebind(String.format("//%s:%d/%s", RMI_HOST, RMI_PORT, distributor.getName()), stub2);
			logger.info(MESSAGE_CONNECTES);
		} catch (RemoteException e) {
			logger.severe(e.getMessage());
		} catch (NotBoundException e) {
			logger.severe(e.getMessage());
		} catch (MalformedURLException e) {
			logger.severe(e.getMessage());
		}
	}
	
	public void generateProduct(RawMaterial rawM) throws RemoteException {
		server.generateProduct(distributor, rawM);
	}
	
	public List<String> getOrders(){
		List<String> ret = new ArrayList<>();
		if(distrbutorRMI == null || distrbutorRMI.getMapOrders() == null) {
			return ret;
		}
		for(String s : distrbutorRMI.getMapOrders().keySet()) {
			ret.add(s);
		}
		return ret;
	}
	

	public String[] getOrdersArray() {
		List<String> list = getOrders();
		String[] ret = new String[list.size()];
		for(int i = 0; i < list.size(); i++) {
			ret[i] = list.get(i);
		}
		return ret;
	}
	
	public List<RawMaterialAmount> getOrder(String factory){
		return distrbutorRMI.getOrder(factory);
	}
	
	public String[][] getOrderMat(String factoty){
		List<RawMaterialAmount> list = getOrder(factoty);
		String[][] ret = new String[list.size()][];
		for(int i = 0; i < list.size(); i++) {
			ret[i] = getArray(list.get(i));
		}
		return ret;
	}
	
	private String[] getArray(RawMaterialAmount rma) {
		String[] ret = new String[2];
		ret[0] = rma.getRawMaterial().getName();
		ret[1] = rma.getAmount() + "";
		return ret;
	}

}
