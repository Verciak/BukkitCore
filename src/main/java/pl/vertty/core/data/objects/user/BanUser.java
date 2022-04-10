// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.data.objects.user;

import java.sql.SQLException;
import java.sql.ResultSet;

public class BanUser
{
    private String name;
    private String admin;
    private String reason;
    private Long createdTime;
    private Long expireTime;
    
    public BanUser(final String name, final String admin, final String reason, final Long expireTime) {
        this.name = name;
        this.admin = admin;
        this.reason = reason;
        this.createdTime = System.currentTimeMillis();
        this.expireTime = expireTime;
    }
    
    public BanUser(final ResultSet resultSet) throws SQLException {
        this.name = resultSet.getString("name");
        this.admin = resultSet.getString("admin");
        this.reason = resultSet.getString("reason");
        this.createdTime = resultSet.getLong("createdTime");
        this.expireTime = resultSet.getLong("expireTime");
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getAdmin() {
        return this.admin;
    }
    
    public String getReason() {
        return this.reason;
    }
    
    public Long getCreatedTime() {
        return this.createdTime;
    }
    
    public Long getExpireTime() {
        return this.expireTime;
    }
}
