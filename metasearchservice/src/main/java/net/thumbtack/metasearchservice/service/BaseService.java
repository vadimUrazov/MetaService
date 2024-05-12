package net.thumbtack.metasearchservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class BaseService {
    @Autowired
    protected CacheManager cacheManager;
}
