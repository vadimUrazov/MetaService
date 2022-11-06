package net.thumbtack.buscompany.config;

import net.thumbtack.buscompany.dao.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class SqlDaoConfig {

    private final UserDao userDao;
    private final DebugDao debugDao;
    private final AdminDao adminDao;
    private final ClientDao clientDao;
    private final AccountDao accountDao;
    private final TripDao tripDao;
    private final OrderDao orderDao;

    public SqlDaoConfig(@Qualifier("SQLUserDao") UserDao userDao, @Qualifier("SQLDebugDao") DebugDao debugDao, @Qualifier("SQLAdminDao") AdminDao adminDao,
                        @Qualifier("SQLClientDao") ClientDao clientDao,
                        @Qualifier("SQLAccountDao") AccountDao accountDao, @Qualifier("SQLTripDao") TripDao tripDao, @Qualifier("SQLOrderDao") OrderDao orderDao) {
        this.userDao = userDao;
        this.debugDao = debugDao;
        this.adminDao = adminDao;
        this.clientDao = clientDao;
        this.accountDao = accountDao;
        this.tripDao = tripDao;
        this.orderDao = orderDao;
    }

    @Bean("DaoAccount")
    public AccountDao getAccountDao() {
        return accountDao;
    }

    @Bean("DaoUser")
    public UserDao getUserDao() {
        return userDao;
    }

    @Bean("DaoDebug")
    public DebugDao getDebugDao() {
        return debugDao;
    }

    @Bean("DaoAdmin")
    public AdminDao getAdminDao() {
        return adminDao;
    }

    @Bean("DaoClient")
    public ClientDao getClientDao() {
        return clientDao;
    }

    @Bean("DaoTrip")
    public TripDao getTripDao() {
        return tripDao;
    }

    @Bean("DaoOrder")
    public OrderDao getOrderDao() {
        return orderDao;
    }
}
