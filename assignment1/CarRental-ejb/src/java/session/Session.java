/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.ejb.Stateful;
import rental.CarType;
import rental.Quote;
import rental.RentalStore;
import rental.ReservationConstraints;
import rental.ReservationException;


/**
 *
 * @author elias
 */
@Stateful
public class Session {

    public Session() {
    }
    
    public Session (String name){
        owner = name;
      
    }
    
    private String owner;
    private Session session;
    HashSet <String> allCrc;

    
//    @Override
    public void confirmQuotes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public ReservationSession getNewReservationSession(String name) {
        session = new ReservationSession(name);
        return (ReservationSession) session;
    }

//    @Override
    public ManagerSession getNewManagerSession(String name, String carRentalName) {
        session = new ManagerSession(name, carRentalName);
        return (ManagerSession) session;
    }


//     @Override
    public Set<String> getAllRentalCompanies() {
        allCrc = new HashSet<String>(RentalStore.getRentals().keySet());
        return allCrc;
    }

//    @Override
    public void createQuote(ReservationConstraints constraint) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public Set<Quote> getCurrentQuotes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public Set<CarType> checkForAvailableCarTypes(Object session, Date start, Date end) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public void addQuoteToSession(Object session, String name, Date start, Date end, String carType, String region) throws ReservationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

}
