/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import rental.CarRentalCompany;
import rental.CarType;
import rental.RentalStore;
import rental.ReservationConstraints;
import rental.ReservationException;

/**
 *
 * @author elias
 */
public class ReservationSession extends Session implements java.io.Serializable{
    
    public ReservationSession(String name){
    super(name);
        System.out.println("session.ReservationSession.<init>()");
    }
    
    
    public Set<CarType> checkForAvailableCarTypes(Date start, Date end) {
          //Dit nodig of automatisch auto's van andere CarRentalCompanies??
          CarRentalCompany crc;
          Set<CarType> result = new HashSet<CarType>();
          Iterator <String> iterator = allCrc.iterator();
          while (iterator.hasNext()){
              crc = RentalStore.getRental(iterator.next());
              result.addAll(crc.getAvailableCarTypes(start, end));
          }
          return result;
    }
    
    public void addQuoteToSession(String name,Date start,Date end,String carType,String region) throws ReservationException{
            ReservationConstraints constraints = new ReservationConstraints(start, end, carType, region);
            (RentalStore.getRental(allCrc.iterator().next())).createQuote(constraints, name);
            
            
    
    }
    
}
