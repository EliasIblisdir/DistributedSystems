package rental;

import java.rmi.Remote;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface ICarRentalCompany extends Remote{

	
	
	public String getName()throws java.rmi.RemoteException;
	public List<String> getRegions() throws java.rmi.RemoteException;
	public Collection<CarType> getAllCarTypes()throws java.rmi.RemoteException;
	public CarType getCarType(String carTypeName)throws java.rmi.RemoteException;
	public boolean isAvailable(String carTypeName, Date start, Date end)throws java.rmi.RemoteException;
	public Set<CarType> getAvailableCarTypes(Date start, Date end)throws java.rmi.RemoteException;
	public Quote createQuote(ReservationConstraints constraints, String client)throws java.rmi.RemoteException, ReservationException;
	public Reservation confirmQuote(Quote quote) throws java.rmi.RemoteException, ReservationException;
	public void cancelReservation(Reservation res)throws java.rmi.RemoteException;
	public List<Reservation> getReservationsByRenter(String ClientName)throws java.rmi.RemoteException;
	public int getNumberOfReservationsForCarType(String carType)throws java.rmi.RemoteException;
}
