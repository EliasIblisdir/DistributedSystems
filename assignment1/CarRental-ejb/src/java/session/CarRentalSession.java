package session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import rental.CarRentalCompany;
import rental.CarType;
import rental.Quote;
import rental.RentalStore;
import rental.Reservation;
import rental.ReservationConstraints;
import rental.ReservationException;

@Stateful
public class CarRentalSession implements CarRentalSessionRemote {
    
    HashSet<Quote> quotes = new HashSet<Quote>();
    
    @Override
    public Set<String> getAllRentalCompanies() {
        return new HashSet<String>(RentalStore.getRentals().keySet());
    }

   @Override
   public Set<CarType> checkForAvailableCarTypes(Date start, Date end) {
        Set<CarType> result = new HashSet<CarType>();
        for (CarRentalCompany crc: RentalStore.getRentals().values()){
            result.addAll(crc.getAvailableCarTypes(start, end));
        }
        return result;
    }
   

    @Override
    public void createQuote(ReservationConstraints constraint, String guest) throws ReservationException{
        //iterate over all companies
        Collection <CarRentalCompany> allCrc = RentalStore.getRentals().values();
        Iterator it = allCrc.iterator();
        while (it.hasNext()){
            try{
                CarRentalCompany crc = (CarRentalCompany) it.next();
                //If company covers desired region, try to create a quote
                if (crc.hasRegion(constraint.getRegion())){
                    Quote quote = crc.createQuote(constraint, guest);
                    quotes.add(quote);
                }
            }catch(ReservationException ex){
                //if all companies failed, throw exception
                if (!it.hasNext()){
                    throw ex;
                }
            }
        }
        
      
    }

    @Override
    public Set<Quote> getCurrentQuotes() {
       return quotes;
    }

    /**
     *
     * @param name
     * @return
     * @throws ReservationException
     */
    @Override
    public List<Reservation> confirmQuotes(String name) throws ReservationException {
        List <Reservation> reservations = new ArrayList<Reservation>();
        List <Quote> copy = new ArrayList <Quote> (quotes);
        try {
            for (Quote q: copy){
                CarRentalCompany crc = RentalStore.getRental(q.getRentalCompany());
                reservations.add(crc.confirmQuote(q));
                }           
            }
        catch (ReservationException ex) {
            for (Reservation res: reservations){
                RentalStore.getRental(res.getRentalCompany()).cancelReservation(res);
            }
            quotes.clear();
            throw ex;
        }
        quotes.clear();
        return reservations;
    }

   
   


    

    
}
