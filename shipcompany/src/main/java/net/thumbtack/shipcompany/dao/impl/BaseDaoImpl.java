package net.thumbtack.shipcompany.dao.impl;

import net.thumbtack.shipcompany.dao.repository.*;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDaoImpl {

    @Autowired
    protected ShipRepository shipRepository;

    @Autowired
    protected TripRepository tripRepository;

    @Autowired
    protected CargoRepository cargoRepository;
    @Autowired
    protected ClientRepository clientRepository;

    @Autowired
    protected DayTripRepository dayTripRepository;

    @Autowired
    protected PlaceRepository placeRepository;

    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected AdminRepository adminRepository;

    @Autowired
    protected OrderRepository orderRepository;

    @Autowired
    protected PassengerRepository passengerRepository;


    public BaseDaoImpl() {
    }
}
