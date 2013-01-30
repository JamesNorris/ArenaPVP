package com.github;

public enum Setting {
    SPECIFIC_CLAN_CREATION_PERMS("SpecificClanCreationPerms");
    
    private final String value;
    public Object object;
    
    Setting(String value) {
        this.value = value;
    }
    
    static {
        for (Setting setting : values())
            setting.object = Main.instance.getConfig().get(setting.value);
    }
}
