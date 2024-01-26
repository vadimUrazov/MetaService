package net.thumbtack.buscompany.service;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.TimeZone;
import net.thumbtack.buscompany.dao.AdminDao;
import net.thumbtack.buscompany.dao.ClientDao;
import net.thumbtack.buscompany.dao.OrderDao;
import net.thumbtack.buscompany.dao.TripDao;
import net.thumbtack.buscompany.dao.UserDao;
import net.thumbtack.buscompany.exception.ErrorCode;
import net.thumbtack.buscompany.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ServiceBase {

  @Autowired
  @Qualifier("DaoUser")
  protected UserDao userDao;
  @Autowired
  @Qualifier("DaoAdmin")
  protected AdminDao adminDao;
  @Autowired
  @Qualifier("DaoClient")
  protected ClientDao clientDao;
  @Autowired
  @Qualifier("DaoTrip")
  protected TripDao tripDao;


  @Autowired
  @Qualifier("DaoOrder")
  protected OrderDao orderDao;

  public ServiceBase() {
  }


  protected LocalDate convertDateFromGMT(LocalDate date) {
    return date.minusDays(1);
  }

  protected LocalTime convertGMT(LocalTime time) {
    SimpleDateFormat timeGmt = new SimpleDateFormat("HH:mm");
    timeGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
    var gmtTime = timeGmt.format(new Date(0, 0, 0, time.getHour(), time.getMinute()));

    return LocalTime.parse(gmtTime);
  }


  protected void checkPassenger(String firstName, String lastName, long passport)
      throws ServiceException {
    if (StringUtils.isBlank(firstName)) {
      throw new ServiceException(ErrorCode.INCORRECT_FIRSTNAME);
    } else if (StringUtils.isBlank(lastName)) {
      throw new ServiceException(ErrorCode.INCORRECT_LASTNAME);
    } else if (passport <= 0) {
      throw new ServiceException(ErrorCode.INCORRECT_PASSPORT);
    }

  }


}
