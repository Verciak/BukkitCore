package pl.vertty.core.utils;


import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collection;

public final class PacketUtils
{
    private static Reflection.MethodInvoker entityHandleMethod;
    private static Reflection.MethodInvoker sendPacket;
    private static Reflection.FieldAccessor<Object> networkManager;
    private static Reflection.FieldAccessor<Object> playerConnection;
    private static Reflection.FieldAccessor<Integer> ping;
    
    public static void sendPacket(final Player player, final Object... objects) {
        final Object handle = getHandle(player);
        for (final Object o : objects) {
            PacketUtils.sendPacket.invoke(PacketUtils.playerConnection.get(handle), o);
        }
    }
    
    public static int getPing(final Player p) {
        return PacketUtils.ping.get(PacketUtils.entityHandleMethod.invoke(p, new Object[0]));
    }
    
    public static Object getHandle(final Player p) {
        if (PacketUtils.entityHandleMethod == null) {
            throw new IllegalArgumentException("HandleMethod can not be null!");
        }
        return PacketUtils.entityHandleMethod.invoke(p, new Object[0]);
    }
    
    static {
        PacketUtils.entityHandleMethod = null;
        PacketUtils.sendPacket = null;
        PacketUtils.networkManager = null;
        PacketUtils.playerConnection = null;
        PacketUtils.ping = null;
        PacketUtils.entityHandleMethod = Reflection.getMethod(Reflection.getCraftBukkitClass("entity.CraftEntity"), "getHandle", (Class<?>[])new Class[0]);
        PacketUtils.sendPacket = Reflection.getMethod(Reflection.getMinecraftClass("PlayerConnection"), "sendPacket", Reflection.getMinecraftClass("Packet"));
        PacketUtils.playerConnection = Reflection.getSimpleField(Reflection.getMinecraftClass("EntityPlayer"), "playerConnection");
        PacketUtils.networkManager = Reflection.getSimpleField(Reflection.getMinecraftClass("PlayerConnection"), "networkManager");
        PacketUtils.ping = Reflection.getField(Reflection.getMinecraftClass("EntityPlayer"), "ping", Integer.TYPE);
    }
    
    private static final class PACKET1S
    {
        private static Class<?> packetClass;
        private static Class<?> packetClassPlayer;
        private static Reflection.ConstructorInvoker con;
        private static Reflection.ConstructorInvoker conPlayer;
        private static Reflection.FieldAccessor<String> a;
        private static Reflection.FieldAccessor<String> b;
        private static Reflection.FieldAccessor<String> c;
        private static Reflection.FieldAccessor<String> d;
        private static Reflection.FieldAccessor<Collection> e;
        private static Reflection.FieldAccessor<Integer> f;
        private static Reflection.FieldAccessor<Integer> g;
        
        public static Object createTeamPacket(final String name, final String display, final String prefix, final String suffix, final int flag, final String... members) {
            final Object packet = PACKET1S.con.invoke(new Object[0]);
            PACKET1S.a.set(packet, (name.length() > 16) ? name.substring(0, 16) : name);
            PACKET1S.f.set(packet, flag);
            if (flag == 0 || flag == 2) {
                PACKET1S.b.set(packet, (display == null) ? "" : display.substring(0, Math.min(display.length(), 16)));
                PACKET1S.c.set(packet, (prefix == null) ? "" : prefix.substring(0, Math.min(prefix.length(), 16)));
                PACKET1S.d.set(packet, (suffix == null) ? "" : suffix.substring(0, Math.min(suffix.length(), 16)));
                PACKET1S.g.set(packet, 0);
                if (flag == 0) {
                    PACKET1S.e.set(packet, Arrays.asList(members));
                }
            }
            else if (flag == 3 || flag == 4) {
                PACKET1S.g.set(packet, 0);
                PACKET1S.e.set(packet, Arrays.asList(members));
            }
            return packet;
        }
        
        public static Object createPlayerPacket(final String name, final boolean visible, final int ping) {
            return PACKET1S.conPlayer.invoke(name, visible, ping);
        }
        
        static {
            PACKET1S.packetClass = Reflection.getMinecraftClass("PacketPlayOutScoreboardTeam");
            PACKET1S.packetClassPlayer = Reflection.getMinecraftClass("PacketPlayOutPlayerInfo");
            PACKET1S.con = Reflection.getConstructor(PACKET1S.packetClass, (Class<?>[])new Class[0]);
            PACKET1S.conPlayer = Reflection.getConstructor(PACKET1S.packetClassPlayer, String.class, Boolean.TYPE, Integer.TYPE);
            PACKET1S.a = Reflection.getField(PACKET1S.packetClass, "a", String.class);
            PACKET1S.b = Reflection.getField(PACKET1S.packetClass, "b", String.class);
            PACKET1S.c = Reflection.getField(PACKET1S.packetClass, "c", String.class);
            PACKET1S.d = Reflection.getField(PACKET1S.packetClass, "d", String.class);
            PACKET1S.e = (Reflection.FieldAccessor<Collection>)Reflection.getField(PACKET1S.packetClass, "e", Collection.class);
            PACKET1S.f = Reflection.getField(PACKET1S.packetClass, "f", Integer.TYPE);
            PACKET1S.g = Reflection.getField(PACKET1S.packetClass, "g", Integer.TYPE);
        }
    }
}
