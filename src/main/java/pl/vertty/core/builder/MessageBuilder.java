// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.builder;

import org.apache.commons.lang3.StringUtils;

public final class MessageBuilder
{
    private String text;
    
    public MessageBuilder setText(final String text) {
        this.text = text;
        return this;
    }
    
    public MessageBuilder addField(final String field, final String replacer) {
        this.text = StringUtils.replace(this.text, field, replacer);
        return this;
    }
    
    public String build() {
        return this.text;
    }
}
