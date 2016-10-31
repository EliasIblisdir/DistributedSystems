package session;

import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.ejb.Remote;
import rental.*;



@Remote
public interface CarRentalSessionRemote {
    

    Set<String> getAllRentalCompanies();
    
    void createQuote(ReservationConstraints constraint, String guest) throws ReservationException;
    
    Set<Quote> getCurrentQuotes();
    
    List<Reservation> confirmQuotes(String name) throws ReservationException;
    
    //Stond als void, maar carTypes moeten toch worden teruggegeven? 
    Set<CarType> checkForAvailableCarTypes(Date start, Date end);
    
    
    
}
