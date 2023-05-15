// 
// 
// 

package com.sa.web;

import java.sql.Connection;

public class ConnectionContext
{
    private static ConnectionContext instance;
    private ThreadLocal<Connection> connectionThreadLocal;
    
    static {
        ConnectionContext.instance = new ConnectionContext();
    }
    
    private ConnectionContext() {
        this.connectionThreadLocal = new ThreadLocal<Connection>();
    }
    
    public static ConnectionContext getInstance() {
        return ConnectionContext.instance;
    }
    
    public void bind(final Connection connection) {
        this.connectionThreadLocal.set(connection);
    }
    
    public Connection get() {
        return this.connectionThreadLocal.get();
    }
    
    public void remove() {
        this.connectionThreadLocal.remove();
    }
}
