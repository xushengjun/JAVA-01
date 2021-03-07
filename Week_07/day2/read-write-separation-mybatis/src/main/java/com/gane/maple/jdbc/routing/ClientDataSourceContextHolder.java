package com.gane.maple.jdbc.routing;

import java.util.Objects;

/**
 * @author maple
 * @date 2021/3/3
 */
public class ClientDataSourceContextHolder {

    private static final ThreadLocal<ClientDataSource> CONTEXT = new ThreadLocal<>();

    public static void set(ClientDataSource clientDataSource) {
        CONTEXT.set(Objects.requireNonNull(clientDataSource, "clientDatabase cannot be null"));
    }

    public static ClientDataSource getClientDatabase() {
        return CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }
}
