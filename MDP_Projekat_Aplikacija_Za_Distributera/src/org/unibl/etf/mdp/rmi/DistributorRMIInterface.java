package org.unibl.etf.mdp.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import org.unibl.etf.mdp.workwithdistributors.rmi.RawMaterialAmount;

public interface DistributorRMIInterface extends Remote{

	public void addOrder(String name, List<RawMaterialAmount> list) throws RemoteException;
}
