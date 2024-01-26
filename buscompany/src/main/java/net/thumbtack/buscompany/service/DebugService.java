package net.thumbtack.buscompany.service;

import net.thumbtack.buscompany.dao.DebugDao;
import net.thumbtack.buscompany.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DebugService {

  private final DebugDao debugDao;

  @Autowired
  public DebugService(@Qualifier("DaoDebug") DebugDao debugDao) {
    this.debugDao = debugDao;
  }

  public void clear() throws ServiceException {
    debugDao.clear();
  }

}
