package net.thumbtack.shipcompany.service;

import net.thumbtack.shipcompany.dao.DebugDao;
import net.thumbtack.shipcompany.exception.ServiceException;
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
