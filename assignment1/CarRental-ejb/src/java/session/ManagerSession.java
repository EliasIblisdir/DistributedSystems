/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.Stateless;
import rental.Car;
import rental.CarRentalCompany;
import rental.CarType;
import rental.RentalStore;
import rental.Reservation;

/**
 *
 * @author elias
 */
@Stateless
public class ManagerSession implements ManagerSessionRemote{
    
    @Override
    public int getNumberOfReservationsForCarType(String carRentalName, String carRentalType){
        int nb =0;
        List <Car> cars = RentalStore.getRental(carRentalName).getCars();
        for (Car c: cars){
            if (c.getType().getName().equals(carRentalType)){
                nb += c.getReservations().size();
            } 
        }
        return nb;
    }

    @Override
    public List<CarType> getCarTypeForCompany(String carRentalName) {
      List <CarType> cartypes =  new ArrayList<CarType>();
      cartypes.addAll(RentalStore.getRental(carRentalName).getAllTypes());
      return cartypes;
    }
    

    @Override
     public int getNumberOfReservationsBy(String renter) {
        int nb =0;
        for (CarRentalCompany c: RentalStore.getRentals().values()){
            nb += c.getReservationsBy(renter).size();
        }
        return nb;
    }
     
     
    
    @Override
    public List<String> getBestCustomer(String carRentalName) {
        Map <String, Integer> rentals = null;
        List <Reservation> reservations = new ArrayList <Reservation> ();
        List <String> result = new ArrayList <String> ();
        
        //Construct a Map containing customers with the number of reservations they made
        for (Car c: RentalStore.getRental(carRentalName).getCars()){
            reservations = c.getReservations();
            for (Reservation r: reservations){
                String renter = r.getCarRenter();
                if (!rentals.containsKey(renter)){
                    rentals.put(renter,getNumberOfReservationsBy(renter));
                }
            }
        }
        
        //Get customer(s) with highest number of rentals
        Set <String> renters = rentals.keySet();
        Iterator it = renters.iterator();
        result.add((String) it.next());
        while (it.hasNext()){
            String renter = (String) it.next();
            int compare = Integer.compare(rentals.get(renter), rentals.get(result.get(0)));
            // If current best customer has same nb of rentals, add this customer to the list. 
            if (compare ==0){
                result.add(renter);
            }
            // If current best customer has less rentals than this customer, 
            // clear the list and add this customer
            else if (compare > 0 ){
                result.clear();
                result.add(renter);
            }
        }
        return result;
    }

}
