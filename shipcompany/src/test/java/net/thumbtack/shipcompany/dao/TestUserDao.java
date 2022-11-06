package net.thumbtack.shipcompany.dao;

import net.thumbtack.shipcompany.AbstractDaoTest;
import net.thumbtack.shipcompany.entity.Admin;
import net.thumbtack.shipcompany.exception.ServiceException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestUserDao extends AbstractDaoTest {

    @Test
    public void testRegisterUser() throws ServiceException {
        Admin admin = new Admin("Пётров", "Пётр", "Петрович", "petrovichpetr", "123drv23Swgdc", "Директор");
        userDao.registerUser(admin);
        assertTrue(admin.getId() > 0);
    }

    @Test
    public void testRegisterUserFail() throws ServiceException {
        Admin admin = new Admin("Петров", "Пётр", "Петрович", "petrovichpetr", "123drv23Swgdc", "Директор");
        Admin ivan = new Admin("Иванов", "Иван", "Николаевич", "petrovichpetr", "123drv23Swgdc", "Директор");

        userDao.registerUser(ivan);

        assertThrows(ServiceException.class, () -> userDao.registerUser(admin));
    }

    @Test
    public void testRegisterUserDuplicate() throws ServiceException {
        Admin petr = new Admin("Пётров", "Пётр", "Петрович", "petrovichpetr", "123drv23Swgdc", "Директор");
        Admin ivan = new Admin("Пётров", "Иван", "Петрович", "petrovichpetr", "123drv23Swgdc", "Начальник");
        userDao.registerUser(petr);
        assertThrows(ServiceException.class, () -> userDao.registerUser(ivan));
    }
}
