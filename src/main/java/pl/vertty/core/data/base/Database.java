// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.data.base;

import com.zaxxer.hikari.HikariDataSource;
import pl.vertty.core.utils.conventer.KitConventer;
import pl.vertty.core.utils.conventer.GuildPermissionConventer;
import pl.vertty.core.utils.conventer.HomeConventer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import pl.vertty.core.data.objects.user.BanUser;
import pl.vertty.core.data.objects.user.UserData;
import java.sql.SQLException;
import pl.vertty.core.manager.BanManager;
import pl.vertty.core.manager.UserManager;
import pl.vertty.core.data.config.PluginConfiguration;
import java.sql.Connection;
import java.util.logging.Logger;

public class Database
{
    private final HikariDataSource dataSource;
    private static Database database;
    private final Logger logger;
    private Connection connection;
    
    public Database(final PluginConfiguration cfg, final UserManager userManager, final BanManager banManager, final Logger logger) {
        this.logger = logger;
        Database.database = this;
        logger.info("Laczenie z baza danych...");
        (this.dataSource = new HikariDataSource()).setJdbcUrl(String.format("jdbc:mysql://%s:%s/%s", cfg.getDb_host(), cfg.getDb_port(), cfg.getDb_base()));
        this.dataSource.setUsername(cfg.getDb_user());
        this.dataSource.setPassword(cfg.getDb_pass());
        this.dataSource.addDataSourceProperty("cachePrepStmts", (Object)true);
        this.dataSource.addDataSourceProperty("prepStmtCacheSize", (Object)250);
        this.dataSource.addDataSourceProperty("prepStmtCacheSqlLimit", (Object)2048);
        this.dataSource.addDataSourceProperty("useServerPrepStmts", (Object)true);
        this.dataSource.addDataSourceProperty("rewriteBatchedStatements", (Object)true);
        this.dataSource.setConnectionTimeout(10000L);
        this.dataSource.setMaximumPoolSize(Runtime.getRuntime().availableProcessors() * 3);
        try {
            this.connection = this.dataSource.getConnection();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("Polaczono z baza danych!");
        try {
            final Statement statement = this.connection.createStatement();
            statement.executeUpdate("create table if not exists `mhCore_bans` (name varchar(36) not null, admin varchar(36) not null, reason text not null, createdTime bigint(22) not null, expireTime bigint(22) not null, primary key(name));");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `mhCore_users`(identifier VARCHAR(255) not null,lastName VARCHAR(36) not null, cobble BOOLEAN not null, messages BOOLEAN not null, notchApple int(11) not null, goldenApple int(11) not null, pearls int(11) not null, arrows int(11) not null, myCream int(11) not null, emeralds int(11) not null, spendMoney double not null, eatNotch int(11) not null, minedStone int(11) not null, kills int(11) not null, distance double not null, deaths int(11) not null, placedBlocks int(11) not null, coins double not null, pandora int(11) not null, myChest int(11) not null, homes text not null, guildPermissions text not null, kits text not null, chestMessages BOOLEAN not null, PRIMARY KEY(identifier));");
            statement.close();
        }
        catch (SQLException e) {
            logger.warning("Nie mozna utworzyc tabel!");
        }
        logger.info("Ladowanie bazy danych...");
        try {
            final ResultSet resultSet = this.connection.createStatement().executeQuery("SELECT * FROM mhCore_users");
            while (resultSet.next()) {
                final UserData user = new UserData(resultSet);
                userManager.getUserMap().put(user.getIdentifier(), user);
            }
            final ResultSet resultSetBan = this.connection.createStatement().executeQuery("SELECT * FROM mhCore_bans");
            while (resultSetBan.next()) {
                final BanUser banUser = new BanUser(resultSetBan);
                if (banUser.getExpireTime() != 0L && banUser.getExpireTime() < System.currentTimeMillis()) {
                    this.removeBan(banUser);
                }
                else {
                    banManager.getBansMap().put(banUser.getName(), banUser);
                }
            }
            resultSet.close();
            resultSetBan.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            logger.warning("Nie mozna zaladowac db!");
        }
        logger.info("Zaladowano " + userManager.getUserMap().size() + " graczy!");
        logger.info("Zaladowano " + banManager.getBansMap().size() + " banow!");
        logger.info("Zaladowano baze danych!");
    }
    
    public static Database getInstance() {
        return Database.database;
    }
    
    public void sendEmptyUpdate() {
        try {
            final PreparedStatement statement = this.connection.prepareStatement("DO 1");
            statement.executeQuery();
            statement.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void removeBan(final BanUser ban) {
        try {
            final PreparedStatement prepareStatement = this.connection.prepareStatement("DELETE FROM `mhCore_bans` WHERE `name` ='" + ban.getName() + "';");
            prepareStatement.executeUpdate();
            prepareStatement.close();
        }
        catch (SQLException e) {
            this.logger.warning("Nie mozna usunac bana!");
        }
    }
    
    public void saveBan(final BanUser ban) {
        try {
            final PreparedStatement prepareStatement = this.connection.prepareStatement("REPLACE INTO `mhCore_bans` (name, admin, reason, createdTime, expireTime) VALUES (?,?,?,?,?)");
            prepareStatement.setString(1, ban.getName());
            prepareStatement.setString(2, ban.getAdmin());
            prepareStatement.setString(3, ban.getReason());
            prepareStatement.setLong(4, ban.getCreatedTime());
            prepareStatement.setLong(5, ban.getExpireTime());
            prepareStatement.executeUpdate();
            prepareStatement.close();
        }
        catch (SQLException e) {
            this.logger.warning("Nie mozna zapisac bana!");
        }
    }
    
    public void saveUser(final UserData user) {
        try {
            final PreparedStatement preparedStatement = this.connection.prepareStatement("REPLACE INTO mhCore_users (identifier, lastName, cobble, messages, notchApple, goldenApple, pearls, arrows, myCream, emeralds, spendMoney, eatNotch, minedStone, kills, distance, deaths, placedBlocks, coins, pandora, myChest, homes, guildPermissions, kits, chestMessages) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, user.getIdentifier().toString());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setBoolean(3, user.isCobble());
            preparedStatement.setBoolean(4, user.isMessages());
            preparedStatement.setInt(5, user.getNotchApple());
            preparedStatement.setInt(6, user.getGolden());
            preparedStatement.setInt(7, user.getPearl());
            preparedStatement.setInt(8, user.getArrow());
            preparedStatement.setInt(9, user.getMyCream());
            preparedStatement.setInt(10, user.getEmerald());
            preparedStatement.setDouble(11, user.getSpendMoney());
            preparedStatement.setInt(12, user.getEatNotch());
            preparedStatement.setInt(13, user.getMinedStone());
            preparedStatement.setInt(14, user.getKills());
            preparedStatement.setDouble(15, user.getDistance());
            preparedStatement.setInt(16, user.getDeaths());
            preparedStatement.setInt(17, user.getPlacedBlocks());
            preparedStatement.setDouble(18, user.getCoins());
            preparedStatement.setInt(19, user.getPandora());
            preparedStatement.setInt(20, user.getMychest());
            preparedStatement.setString(21, HomeConventer.serialize(user.getHomes()));
            preparedStatement.setString(22, GuildPermissionConventer.serialize(user.getPermissions()));
            preparedStatement.setString(23, KitConventer.serialize(user.getKitsMap()));
            preparedStatement.setBoolean(24, user.isChestMessages());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e) {
            this.logger.warning("Nie mozna zapisac usera!");
        }
    }
    
    public void disconnect() {
        this.dataSource.close();
    }
}
