package net.thumbtack.buscompany.dao;

import net.thumbtack.buscompany.AbstractDaoTest;
import net.thumbtack.buscompany.entity.Admin;
import net.thumbtack.buscompany.exception.ServiceException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TestAccountDao extends AbstractDaoTest {


    @Test
    public void testDeleteUser() throws ServiceException {
        Admin ivan = new Admin("Иванов", "Иван", "Николаевич", "ivanovpetrnick", "123drv23Swgdc", "Директор");
        Admin petr = new Admin("Иванов", "Пётр", "Николаевич", "petrivannickol", "164!fh8gytr#h", "Директор");

        userDao.registerUser(ivan);

        userDao.registerUser(petr);


        accountDao.deleteUser(ivan.getId());

        assertNull(userDao.getUserById(ivan.getId()));


    }

    @Test
    public void testDeleteUserFail() {
        assertThrows(ServiceException.class, () -> accountDao.deleteUser(4075));

    }

    @Test
    public void testDeleteAdminFail() throws ServiceException {
        Admin ivan = new Admin("Иванов", "Иван", "Николаевич", "ivanovpetrnick", "123drv23Swgdc", "Директор");
        userDao.registerUser(ivan);
        assertThrows(ServiceException.class, () -> accountDao.deleteUser(ivan.getId()));

    }


}
