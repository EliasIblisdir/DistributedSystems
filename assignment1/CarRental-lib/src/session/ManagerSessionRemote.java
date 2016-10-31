/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.util.List;
import javax.ejb.Remote;
import rental.CarType;

/**
 *
 * @author elias
 */
@Remote
public interface ManagerSessionRemote {

    int getNumberOfReservationsForCarType(String carRentalName, String carType);
    
    
    List <CarType> getCarTypeForCompany (String carRentalName);
    
    List <String> getBestCustomer(String carRentalName);
    
    int getNumberOfReservationsBy(String renter);
    
    

    
}
