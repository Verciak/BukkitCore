package pl.vertty.core.data.objects.user;


import java.util.*;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import pl.vertty.core.data.objects.Kit;
import java.sql.SQLException;
import pl.vertty.core.utils.conventer.KitConventer;
import pl.vertty.core.utils.conventer.GuildPermissionConventer;
import pl.vertty.core.utils.conventer.HomeConventer;
import java.sql.ResultSet;
import pl.vertty.core.manager.ComparatorManager;
import com.google.common.collect.Maps;
import com.google.common.collect.Lists;
import pl.vertty.core.enums.IncognitoState;
import pl.vertty.core.enums.GuildPermission;
import pl.vertty.core.data.objects.drops.basic.Drop;

public class UserData implements GuildUser
{
    private final UUID identifier;
    private String lastName;
    private final List<Drop> disabledDrops;
    private final List<Player> tpa;
    private final Map<String, Long> kitsMap;
    private final Map<String, Location> homes;
    private final Map<GuildPermission, Boolean> guildPermissions;
    private IncognitoState incognitoState;
    private boolean cobble;
    private boolean messages;
    private boolean godMode;
    private boolean chestMessages;
    private int notchApple;
    private int pearl;
    private int goldenApple;
    private int emerald;
    private int eatNotch;
    private int minedStone;
    private int kills;
    private int deaths;
    private int placedBlocks;
    private int pandora;
    private int mychest;
    private int arrow;
    private int mycream;
    private double spendMoney;
    private double distance;
    private double coins;
    
    public UserData(final Player player) {
        this.identifier = player.getUniqueId();
        this.lastName = player.getName();
        this.disabledDrops = Lists.newArrayList();
        this.kitsMap = Maps.newLinkedHashMap();
        this.tpa = Lists.newLinkedList();
        this.homes = Maps.newLinkedHashMap();
        this.guildPermissions = Maps.newLinkedHashMap();
        for (final GuildPermission gp : GuildPermission.values()) {
            this.guildPermissions.put(gp, false);
        }
        this.incognitoState = IncognitoState.DISABLE;
        this.cobble = true;
        this.messages = true;
        this.godMode = false;
        this.notchApple = 0;
        this.pearl = 0;
        this.goldenApple = 0;
        this.arrow = 0;
        this.mycream = 0;
        this.emerald = 0;
        this.spendMoney = 0.0;
        this.eatNotch = 0;
        this.minedStone = 0;
        this.kills = 0;
        this.distance = 0.0;
        this.deaths = 0;
        this.placedBlocks = 0;
        this.coins = 0.0;
        this.pandora = 0;
        this.mychest = 0;
        this.chestMessages = true;
        ComparatorManager.addUserToTop(this);
    }
    
    public UserData(final ResultSet resultSet) throws SQLException {
        this.identifier = UUID.fromString(resultSet.getString("identifier"));
        this.lastName = resultSet.getString("lastName");
        this.disabledDrops = Lists.newArrayList();
        this.tpa = Lists.newLinkedList();
        this.incognitoState = IncognitoState.DISABLE;
        this.cobble = resultSet.getBoolean("cobble");
        this.messages = resultSet.getBoolean("messages");
        this.notchApple = resultSet.getInt("notchApple");
        this.goldenApple = resultSet.getInt("goldenApple");
        this.pearl = resultSet.getInt("pearls");
        this.arrow = resultSet.getInt("arrows");
        this.mycream = resultSet.getInt("myCream");
        this.emerald = resultSet.getInt("emeralds");
        this.spendMoney = resultSet.getDouble("spendMoney");
        this.eatNotch = resultSet.getInt("eatNotch");
        this.minedStone = resultSet.getInt("minedStone");
        this.kills = resultSet.getInt("kills");
        this.distance = resultSet.getDouble("distance");
        this.deaths = resultSet.getInt("deaths");
        this.placedBlocks = resultSet.getInt("placedBlocks");
        this.coins = resultSet.getDouble("coins");
        this.pandora = resultSet.getInt("pandora");
        this.mychest = resultSet.getInt("myChest");
        this.homes = HomeConventer.deserialize(resultSet.getString("homes"));
        this.guildPermissions = (Map<GuildPermission, Boolean>) GuildPermissionConventer.deserialize(resultSet.getString("guildPermissions"));
        this.kitsMap = (Map<String, Long>) KitConventer.deserialize(resultSet.getString("kits"));
        this.chestMessages = resultSet.getBoolean("chestMessages");
        ComparatorManager.addUserToTop(this);
    }
    
    @Override
    public UUID getIdentifier() {
        return this.identifier;
    }
    
    @Override
    public String getLastName() {
        return this.lastName;
    }
    
    @Override
    public void setLastName(final String newName) {
        this.lastName = newName;
    }
    
    @Override
    public void resetPermissions() {
        this.guildPermissions.clear();
    }
    
    @Override
    public void togglePermission(final GuildPermission guildPermission) {
        this.guildPermissions.computeIfPresent(guildPermission, (a, b) -> !b);
    }
    
    @Override
    public boolean hasPermission(final GuildPermission guildPermission) {
        return this.guildPermissions.get(guildPermission);
    }
    
    @Override
    public Map<GuildPermission, Boolean> getPermissions() {
        return this.guildPermissions;
    }
    
    @Override
    public boolean isGodMode() {
        return this.godMode;
    }
    
    public List<Player> getTpa() {
        return this.tpa;
    }
    
    @Override
    public void setGodMode(final boolean godMode) {
        this.godMode = godMode;
    }
    
    @Override
    public List<Drop> getDisabledDrops() {
        return this.disabledDrops;
    }
    
    @Override
    public boolean isDisabled(final Drop drop) {
        return this.disabledDrops.contains(drop);
    }
    
    @Override
    public boolean isCobble() {
        return this.cobble;
    }
    
    public boolean isChestMessages() {
        return this.chestMessages;
    }
    
    public void setChestMessages(final boolean chestMessages) {
        this.chestMessages = chestMessages;
    }
    
    @Override
    public void setCobble(final boolean b) {
        this.cobble = b;
    }
    
    @Override
    public boolean isMessages() {
        return this.messages;
    }
    
    @Override
    public void setMessages(final boolean b) {
        this.messages = b;
    }
    
    @Override
    public int getNotchApple() {
        return this.notchApple;
    }
    
    @Override
    public void setNotchApple(final int toSet) {
        this.notchApple = toSet;
    }
    
    @Override
    public void addNotchApple(final int toAdd) {
        this.notchApple += toAdd;
    }
    
    @Override
    public void removeNotchApple(final int toRemove) {
        this.notchApple -= toRemove;
    }
    
    @Override
    public int getPearl() {
        return this.pearl;
    }
    
    @Override
    public void setPearl(final int toSet) {
        this.pearl = toSet;
    }
    
    @Override
    public void addPearl(final int toAdd) {
        this.pearl += toAdd;
    }
    
    @Override
    public void removePearl(final int toRemove) {
        this.pearl -= toRemove;
    }
    
    @Override
    public int getGolden() {
        return this.goldenApple;
    }
    
    @Override
    public void setGolden(final int toSet) {
        this.goldenApple = toSet;
    }
    
    @Override
    public void addGolden(final int toAdd) {
        this.goldenApple += toAdd;
    }
    
    @Override
    public void removeGolden(final int toRemove) {
        this.goldenApple -= toRemove;
    }
    
    @Override
    public int getArrow() {
        return this.arrow;
    }
    
    @Override
    public void setArrow(final int toSet) {
        this.arrow = toSet;
    }
    
    @Override
    public void addArrow(final int toAdd) {
        this.arrow += toAdd;
    }
    
    @Override
    public void removeArrow(final int toRemove) {
        this.arrow -= toRemove;
    }
    
    @Override
    public int getMyCream() {
        return this.mycream;
    }
    
    @Override
    public void setMyCream(final int toSet) {
        this.mycream = toSet;
    }
    
    @Override
    public void addMyCream(final int toAdd) {
        this.mycream += toAdd;
    }
    
    @Override
    public void removeMyCream(final int toRemove) {
        this.mycream -= toRemove;
    }
    
    @Override
    public int getEmerald() {
        return this.emerald;
    }
    
    @Override
    public void setEmerald(final int toSet) {
        this.emerald = toSet;
    }
    
    @Override
    public void addEmerald(final int toAdd) {
        this.emerald += toAdd;
    }
    
    @Override
    public void removeEmerald(final int toRemove) {
        this.emerald -= toRemove;
    }
    
    @Override
    public double getSpendMoney() {
        return this.spendMoney;
    }
    
    @Override
    public void setSpendMoney(final double toSet) {
        this.spendMoney = toSet;
    }
    
    @Override
    public void addSpendMoney(final double toAdd) {
        this.spendMoney += toAdd;
    }
    
    @Override
    public int getEatNotch() {
        return this.eatNotch;
    }
    
    @Override
    public void setEatNotch(final int toSet) {
        this.eatNotch = toSet;
    }
    
    @Override
    public void addEatNotch(final int toAdd) {
        this.eatNotch += toAdd;
    }
    
    @Override
    public int getMinedStone() {
        return this.minedStone;
    }
    
    @Override
    public void setMinedStone(final int toSet) {
        this.minedStone += toSet;
    }
    
    @Override
    public void addMinedStone(final int toAdd) {
        this.minedStone += toAdd;
    }
    
    @Override
    public int getKills() {
        return this.kills;
    }
    
    @Override
    public void setKills(final int toSet) {
        this.kills = toSet;
    }
    
    @Override
    public void addKill(final int toAdd) {
        this.kills += toAdd;
    }
    
    @Override
    public double getDistance() {
        return this.distance;
    }
    
    @Override
    public void setDistance(final double toSet) {
        this.distance = toSet;
    }
    
    @Override
    public void addDistance(final double toAdd) {
        this.distance += toAdd;
    }
    
    @Override
    public int getDeaths() {
        return this.deaths;
    }
    
    @Override
    public void setDeaths(final int toSet) {
        this.deaths = toSet;
    }
    
    @Override
    public void addDeath(final int toAdd) {
        this.deaths += toAdd;
    }
    
    @Override
    public int getPlacedBlocks() {
        return this.placedBlocks;
    }
    
    @Override
    public void setPlacedBlocks(final int toSet) {
        this.placedBlocks = toSet;
    }
    
    @Override
    public void addPlacedBlock(final int toAdd) {
        this.placedBlocks += toAdd;
    }
    
    @Override
    public double getCoins() {
        return this.coins;
    }
    
    @Override
    public void setCoins(final double toSet) {
        this.coins = toSet;
    }
    
    @Override
    public void addCoins(final double toAdd) {
        this.coins += toAdd;
    }
    
    @Override
    public int getPandora() {
        return this.pandora;
    }
    
    @Override
    public void setPandora(final int toSet) {
        this.pandora = toSet;
    }
    
    @Override
    public void addPandora(final int toAdd) {
        this.pandora += toAdd;
    }
    
    @Override
    public int getMychest() {
        return this.mychest;
    }
    
    @Override
    public void setMychest(final int toSet) {
        this.mychest = toSet;
    }
    
    @Override
    public void addMychest(final int toAdd) {
        this.mychest += toAdd;
    }
    
    @Override
    public void addKit(final Kit kit, final long delay) {
        this.kitsMap.put(kit.getName(), delay);
    }
    
    @Override
    public long getKit(final Kit kit) {
        return this.kitsMap.get(kit.getName());
    }
    
    @Override
    public boolean canTake(final Kit kit) {
        if (this.kitsMap.containsKey(kit.getName()) && this.getKit(kit) > System.currentTimeMillis()) {
            return false;
        }
        this.kitsMap.remove(kit.getName());
        return true;
    }
    
    @Override
    public Map<String, Long> getKitsMap() {
        return this.kitsMap;
    }
    
    public Map<String, Location> getHomes() {
        return this.homes;
    }
    
    public Location getHome(final String home) {
        if (this.homes.containsKey(home.toLowerCase())) {
            return this.homes.get(home.toLowerCase());
        }
        return null;
    }
    
    public List<String> getHomesList() {
        return new ArrayList<String>(this.homes.keySet());
    }
    
    public void addHome(final String home, final Location location) {
        this.homes.put(home.toLowerCase(), location);
    }
    
    public void deleteHome(final String home) {
        this.homes.remove(home.toLowerCase());
    }
    
    public void toggleIncognito() {
        if (this.incognitoState == IncognitoState.ENABLE) {
            this.incognitoState = IncognitoState.DISABLE;
        }
        else if (this.incognitoState == IncognitoState.DISABLE) {
            this.incognitoState = IncognitoState.ENABLE;
        }
    }
    
    public boolean isIncognito() {
        return this.incognitoState == IncognitoState.ENABLE;
    }
    
    public Player getPlayer() {
        return Bukkit.getPlayer(this.identifier);
    }
}
