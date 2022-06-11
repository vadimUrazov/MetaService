package net.thumbtack.buscompany.dao;

import net.thumbtack.buscompany.AbstractDaoTest;
import net.thumbtack.buscompany.entity.Admin;
import net.thumbtack.buscompany.entity.Bus;
import net.thumbtack.buscompany.entity.Client;
import net.thumbtack.buscompany.exception.ServiceException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TestAdminDao extends AbstractDaoTest {

    @Test
    public void testGetBuses() throws ServiceException {
        List<Bus> buses = adminDao.getBuses();
        assertNotNull(buses);
        assertFalse(buses.isEmpty());
        assertEquals(6, buses.size());
    }

    @Test
    public void testGetClients() throws ServiceException {
        Client request = new Client("Иванов", "Иван", "Иванович", "ivanivnvrgas", "12s223", "ivanov@mail.ru", "8-916-621-32-64");
        userDao.registerUser(request);
        List<Client> clients = adminDao.getClients();
        assertNotNull(clients);
        assertFalse(clients.isEmpty());
        assertEquals(1, clients.size());

    }

    @Test
    public void testGetClientsAddAdmin() throws ServiceException {
        Admin request = new Admin("Петров", "Иван", "Петрович", "Zxcvbhjkrdyg", "ivanov@!#cfv", "Директор");
        userDao.registerUser(request);
        List<Client> clients = adminDao.getClients();
        assertNotNull(clients);
        assertTrue(clients.isEmpty());

    }

    @Test
    public void testGetClientsEmpty() throws ServiceException {
        List<Client> clients = adminDao.getClients();
        assertNotNull(clients);
        assertTrue(clients.isEmpty());
    }


}
