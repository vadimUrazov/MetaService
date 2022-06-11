package net.thumbtack.traincompany.dao.impl;

import net.thumbtack.traincompany.dao.repository.*;
import net.thumbtack.traincompany.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ConcurrentHashMap;

public class BaseDaoImpl {
    @Autowired
    protected TrainRepository trainRepository;

    @Autowired
    protected TripRepository tripRepository;


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
