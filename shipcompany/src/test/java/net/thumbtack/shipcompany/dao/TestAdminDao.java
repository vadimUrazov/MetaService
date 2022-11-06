package net.thumbtack.shipcompany.dao;

import net.thumbtack.shipcompany.AbstractDaoTest;
import net.thumbtack.shipcompany.entity.Admin;
import net.thumbtack.shipcompany.entity.Client;
import net.thumbtack.shipcompany.entity.Ship;
import net.thumbtack.shipcompany.exception.ServiceException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TestAdminDao extends AbstractDaoTest {

    @Test
    public void testGetBuses() throws ServiceException {
        List<Ship> ships = adminDao.getShips();
        assertNotNull(ships);
        assertFalse(ships.isEmpty());
        assertEquals(6, ships.size());
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
