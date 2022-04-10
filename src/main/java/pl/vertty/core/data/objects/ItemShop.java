// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.data.objects;

import java.util.List;

public class ItemShop
{
    private final String name;
    private final List<String> chatMessage;
    private final List<String> commands;
    
    public ItemShop(final String name, final List<String> chatMessage, final List<String> commands) {
        this.name = name;
        this.chatMessage = chatMessage;
        this.commands = commands;
    }
    
    public String getName() {
        return this.name;
    }
    
    public List<String> getChatMessage() {
        return this.chatMessage;
    }
    
    public List<String> getCommands() {
        return this.commands;
    }
}
