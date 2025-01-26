package org.unibl.etf.mdp.workwithdistributors.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerRMIInterface extends Remote{

	public void generateProduct(Distributor dis, RawMaterial product) throws RemoteException;
}
