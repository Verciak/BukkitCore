package pl.vertty.core;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import pl.vertty.api.commands.Command;
import pl.vertty.api.commands.CommandManager;
import pl.vertty.core.command.*;
import pl.vertty.core.listener.inventory.InventoryClickListener;
import pl.vertty.core.listener.others.UnknownCommandListener;
import pl.vertty.core.menu.EffectMenu;
import pl.vertty.core.menu.MessageControlMenu;
import pl.vertty.core.tasks.AutoMessageTask;
import pl.vertty.core.tasks.DepositTask;
import pl.vertty.core.tasks.TeleportTask;
import pl.vertty.core.tasks.ProtectionTask;
import pl.vertty.core.tasks.CombatTask;
import pl.vertty.core.tasks.TopTask;
import pl.vertty.core.listener.player.PlayerLoginListener;
import pl.vertty.core.listener.player.PlayerMoveListener;
import pl.vertty.core.listener.player.PlayerJoinListener;
import pl.vertty.core.listener.player.PlayerInteractListener;
import pl.vertty.core.listener.player.PlayerDropItemListener;
import pl.vertty.core.listener.player.PlayerDeathListener;
import pl.vertty.core.listener.player.PlayerCommandPreprocessListener;
import pl.vertty.core.listener.player.AsyncPlayerChatListener;
import pl.vertty.core.listener.others.PlayerTeleportingListener;
import pl.vertty.core.listener.others.CraftItemListener;
import pl.vertty.core.listener.entity.EntityExplodeListener;
import pl.vertty.core.listener.entity.EntityDamageByEntityListener;
import pl.vertty.core.listener.block.BlockPlaceListener;
import pl.vertty.core.listener.block.BlockBreakListener;
import pl.vertty.core.manager.IncognitoManager;
import pl.vertty.core.data.objects.user.UserData;
import pl.vertty.core.menu.TopMenu;
import pl.vertty.core.data.base.Database;
import pl.vertty.core.manager.KitManager;
import pl.vertty.core.manager.BanManager;
import pl.vertty.core.menu.PandoreMenu;
import pl.vertty.core.manager.DropManager;
import pl.vertty.core.manager.ChatManager;
import pl.vertty.core.manager.UserManager;
import pl.vertty.core.manager.ItemManager;
import pl.vertty.core.manager.ChestManager;
import pl.vertty.core.manager.CombatManager;
import pl.vertty.core.manager.ButtonManager;
import pl.vertty.core.manager.CobblexManager;
import pl.vertty.core.manager.PandoreManager;
import pl.vertty.core.manager.TeleportManager;
import pl.vertty.core.manager.ItemShopManager;
import pl.vertty.core.manager.ProtectionManager;
import pl.vertty.core.manager.ComparatorManager;
import pl.vertty.core.data.config.PluginConfiguration;
import pl.vertty.core.data.config.SettingsConfiguration;
import pl.vertty.core.manager.PrivateMessageManager;
import pl.vertty.core.utils.ChatUtils;

public class CorePlugin extends JavaPlugin
{
    private static PluginManager pluginManager;
    private PrivateMessageManager privateMessageManager;
    private SettingsConfiguration settingsConfiguration;
    private PluginConfiguration pluginConfiguration;
    private MessageControlMenu messageControlMenu;
    private ComparatorManager comparatorManager;
    private ProtectionManager protectionManager;
//    private AdminSettingsMenu adminSettingsMenu;
    private ItemShopManager itemShopManager;
    private TeleportManager teleportManager;
//    private BlockChangeMenu blockChangeMenu;
//    private CreateCaseMenu createCaseMenu;
    private PandoreManager pandoreManager;
    private CobblexManager cobblexManager;
    private ButtonManager buttonManager;
    private CombatManager combatManager;
    private ChestManager chestManager;
    private ItemManager itemManager;
//    private DepositMenu depositMenu;
    private UserManager userManager;
    private ChatManager chatManager;
    private DropManager dropManager;
//    private CobblexMenu cobblexMenu;
    private PandoreMenu pandoreMenu;
    private BanManager banManager;
    private KitManager kitManager;
    private EffectMenu effectMenu;
//    private CraftMenu craftMenu;
//    private CaseMenu caseMenu;
    private Database database;
    private TopMenu topMenu;
//    private DropMenu dropMenu;
//    private MainMenu mainMenu;
    private static CorePlugin plugin;


    public void onLoad() {
        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdir();
        }
        this.pluginConfiguration = new PluginConfiguration(this);
        this.settingsConfiguration = new SettingsConfiguration(this);
    }
    
    public void onEnable() {
        plugin = this;
        this.initialize();
    }
    
    public void onDisable() {
        for (final UserData user : this.userManager.getUserList()) {
            this.database.saveUser(user);
        }
        this.combatManager.getCombatSet().clear();
//        for (final Player player : Bukkit.getOnlinePlayers()) {
//            player.kickPlayer(ChatUtils.colored("&cSerwer jest restartowany..."));
//        }
        this.database.disconnect();
    }
    
    private void initialize() {
        final long startTime = System.currentTimeMillis();
        this.getLogger().info("Initializacja pluginu mhCore...");
        this.privateMessageManager = new PrivateMessageManager();
        this.itemShopManager = new ItemShopManager(this);
        this.buttonManager = new ButtonManager(this.settingsConfiguration);
        final IncognitoManager incognitoManager = new IncognitoManager(this);
        this.userManager = new UserManager();
        this.chatManager = new ChatManager();
        this.teleportManager = new TeleportManager();
        this.kitManager = new KitManager(this);
        this.comparatorManager = new ComparatorManager();
        this.protectionManager = new ProtectionManager();
        this.pandoreManager = new PandoreManager(this);
        this.cobblexManager = new CobblexManager(this);
        this.combatManager = new CombatManager();
        this.chestManager = new ChestManager(this);
        this.itemManager = new ItemManager();
        this.banManager = new BanManager();
        this.dropManager = new DropManager(this);
        this.database = new Database(this.pluginConfiguration, this.userManager, this.banManager, this.getLogger());
//        this.adminSettingsMenu = new AdminSettingsMenu(this.settingsConfiguration);
//        this.depositMenu = new DepositMenu(this);
//        this.createCaseMenu = new CreateCaseMenu(this);
//        this.blockChangeMenu = new BlockChangeMenu();
        this.topMenu = new TopMenu(this);
        this.effectMenu = new EffectMenu();
//        this.mainMenu = new MainMenu();
        new BlockBreakListener(this);
        new BlockPlaceListener(this);
        new EntityDamageByEntityListener(this);
        new EntityExplodeListener(this);
        new CraftItemListener(this);
        new PlayerTeleportingListener(this);
        new AsyncPlayerChatListener(this);
        new PlayerCommandPreprocessListener(this);
        new PlayerDeathListener(this);
        new PlayerDropItemListener(this);
        new PlayerInteractListener(this);
        new PlayerJoinListener(this);
        new PlayerMoveListener(this);
        new PlayerLoginListener(this);
        new TopTask(this);
        new CombatTask(this);
        new ProtectionTask(this);
        new TeleportTask(this);
        new DepositTask(this);
        new AutoMessageTask(this);
        registerCommand();
        registerListener();
        this.getLogger().info("Zaladowano plugin mhCore w " + (System.currentTimeMillis() - startTime) / 1000.0 + "s");
    }
    public static void registerCommand() {
        registerCommand(new ADropCommand());
        registerCommand(new AitemshopCommand());
        registerCommand(new AMychestCommand());
        registerCommand(new ApandoreCommand());
        registerCommand(new BanCommand());
        registerCommand(new BlockCommand());
        registerCommand(new BroadCastCommand());
        registerCommand(new CdiCommand());
        registerCommand(new ChatCommand());
        registerCommand(new ClearCommand());
        registerCommand(new CobblexCommand());
        registerCommand(new DeleteHomeCommand());
        registerCommand(new DepositCommand());
        registerCommand(new DropCommand());
        registerCommand(new EffectCommand());
        registerCommand(new FlightCommand());
        registerCommand(new GodModeCommand());
        registerCommand(new HealCommand());
        registerCommand(new HelpopCommand());
        registerCommand(new HomeCommand());
        registerCommand(new ItemShopCommand());
        registerCommand(new ItemShopGiveCommand());
        registerCommand(new KitCommand());
        registerCommand(new MenuCommand());
        registerCommand(new MessageControlCommand());
        registerCommand(new MoneyCommand());
        registerCommand(new MychestCommand());
        registerCommand(new OchronaCommand());
        registerCommand(new PandoraCommand());
        registerCommand(new PanelCommand());
        registerCommand(new PomocCommand());
        registerCommand(new PremiumCommand());
        registerCommand(new PremiumPlusCommand());
        registerCommand(new ReplyCommand());
        registerCommand(new SetHomeCommand());
        registerCommand(new SetSpawnCommand());
        registerCommand(new SpawnCommand());
        registerCommand(new SponsorCommand());
        registerCommand(new TeleportCommand());
        registerCommand(new TellCommand());
        registerCommand(new TopCommand());
        registerCommand(new TpacceptCommand());
        registerCommand(new TpaCommand());
        registerCommand(new TpadenyCommand());
        registerCommand(new UnbanCommand());
        registerCommand(new VanishCommand());
        registerCommand(new VipCommand());
        registerCommand(new YouTubeCommand());
    }

    public void registerListener() {
        registerListener((Plugin)this, new UnknownCommandListener());
        new InventoryClickListener(this);
    }

    public static CorePlugin getPlugin() {
        return plugin;
    }
    
    public SettingsConfiguration getSettingsConfiguration() {
        return this.settingsConfiguration;
    }
    
    public PrivateMessageManager getPrivateMessageManager() {
        return this.privateMessageManager;
    }

    public PluginConfiguration getPluginConfiguration() {
        return this.pluginConfiguration;
    }
    
//    public DepositMenu getDepositMenu() {
//        return this.depositMenu;
//    }
//
//    public MessageControlMenu getMessageControlMenu() {
//        return this.messageControlMenu;
//    }
    
    public ComparatorManager getComparatorManager() {
        return this.comparatorManager;
    }
    
    public ProtectionManager getProtectionManager() {
        return this.protectionManager;
    }
    

//    public AdminSettingsMenu getAdminSettingsMenu() {
//        return this.adminSettingsMenu;
//    }
    
    public TeleportManager getTeleportManager() {
        return this.teleportManager;
    }

    public ChatManager getChatManager() {
        return this.chatManager;
    }
    
    public ItemShopManager getItemShopManager() {
        return this.itemShopManager;
    }
    
//    public BlockChangeMenu getBlockChangeMenu() {
//        return this.blockChangeMenu;
//    }
    
    public CombatManager getCombatManager() {
        return this.combatManager;
    }
    
    public PandoreManager getPandoreManager() {
        return this.pandoreManager;
    }
    
//    public CobblexManager getCobblexManager() {
//        return this.cobblexManager;
//    }
    
//    public CreateCaseMenu getCreateCaseMenu() {
//        return this.createCaseMenu;
//    }
    
    public ChestManager getChestManager() {
        return this.chestManager;
    }
    
    public PandoreMenu getPandoreMenu() {
        return this.pandoreMenu;
    }
    
//    public CobblexMenu getCobblexMenu() {
//        return this.cobblexMenu;
//    }
    
    public UserManager getUserManager() {
        return this.userManager;
    }
    
    public ButtonManager getButtonManager() {
        return this.buttonManager;
    }
    
    public ItemManager getItemManager() {
        return this.itemManager;
    }
    
    public DropManager getDropManager() {
        return this.dropManager;
    }
    
    public BanManager getBanManager() {
        return this.banManager;
    }
    
    public KitManager getKitManager() {
        return this.kitManager;
    }
    
//    public EffectMenu getEffectMenu() {
//        return this.effectMenu;
//    }
//
//    public CraftMenu getCraftMenu() {
//        return this.craftMenu;
//    }
//
//    public DropMenu getDropMenu() {
//        return this.dropMenu;
//    }
//
//    public MainMenu getMainMenu() {
//        return this.mainMenu;
//    }
//
//    public CaseMenu getCaseMenu() {
//        return this.caseMenu;
//    }
    
    public TopMenu getTopMenu() {
        return this.topMenu;
    }


    public static void registerCommand(Command command) {
        CommandManager.register((Command)command);
    }

    public static void registerListener(Plugin plugin, Listener... listeners) {
        if (pluginManager == null)
            pluginManager = Bukkit.getPluginManager();
        for (Listener listener : listeners)
            pluginManager.registerEvents(listener, plugin);
    }

}
