/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

/**
 *
 * @author elias
 */
public class ManagerSession extends Session{
    
    public ManagerSession(String name,String carRentalName ){
        super(name);
        carRentalCompany = carRentalName;
        
    }
    private final String carRentalCompany;
}
