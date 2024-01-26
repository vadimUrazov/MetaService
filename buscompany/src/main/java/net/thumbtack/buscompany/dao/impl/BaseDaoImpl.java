package net.thumbtack.buscompany.dao.impl;

import net.thumbtack.buscompany.dao.repository.AdminRepository;
import net.thumbtack.buscompany.dao.repository.BusRepository;
import net.thumbtack.buscompany.dao.repository.CargoRepository;
import net.thumbtack.buscompany.dao.repository.ClientRepository;
import net.thumbtack.buscompany.dao.repository.DayTripRepository;
import net.thumbtack.buscompany.dao.repository.OrderRepository;
import net.thumbtack.buscompany.dao.repository.PassengerRepository;
import net.thumbtack.buscompany.dao.repository.PlaceRepository;
import net.thumbtack.buscompany.dao.repository.TripRepository;
import net.thumbtack.buscompany.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDaoImpl {

  @Autowired
  protected BusRepository busRepository;

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
