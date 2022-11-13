package net.thumbtack.traincompany.dao.impl;

import lombok.NoArgsConstructor;
import net.thumbtack.traincompany.dao.DebugDao;
import net.thumbtack.traincompany.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository("SQLDebugDao")
@NoArgsConstructor
public class DebugDaoImpl extends BaseDaoImpl implements DebugDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(DebugDaoImpl.class);
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional
    public void clear() throws ServiceException {
        LOGGER.debug("DAO delete data{ }");
        entityManager.createNativeQuery("DELETE FROM admin").executeUpdate();
        entityManager.createNativeQuery("DELETE FROM client").executeUpdate();
        entityManager.createNativeQuery("DELETE FROM \"user\"").executeUpdate();
        entityManager.createNativeQuery("DELETE FROM place").executeUpdate();
        entityManager.createNativeQuery("DELETE FROM cargo").executeUpdate();
        entityManager.createNativeQuery("DELETE FROM passenger").executeUpdate();
        entityManager.createNativeQuery("DELETE FROM day_trip").executeUpdate();
        entityManager.createNativeQuery("DELETE FROM trip").executeUpdate();
        entityManager.createNativeQuery("DELETE FROM \"order\"").executeUpdate();


    }

}

