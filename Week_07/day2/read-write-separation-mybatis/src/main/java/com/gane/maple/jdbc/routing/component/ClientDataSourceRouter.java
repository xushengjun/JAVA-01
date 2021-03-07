package com.gane.maple.jdbc.routing.component;

import com.gane.maple.jdbc.routing.ClientDataSource;
import com.gane.maple.jdbc.routing.ClientDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author maple
 * @date 2021/3/3
 */
@Slf4j
public class ClientDataSourceRouter extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        ClientDataSource clientDataSource = ClientDataSourceContextHolder.getClientDatabase();
        if (clientDataSource == null) {
            log.debug("null client database, use default {}", ClientDataSource.MASTER);
            clientDataSource = ClientDataSource.MASTER;
        }
        log.trace("use {} as database", clientDataSource);
        return clientDataSource;
    }
}
