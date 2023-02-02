package moe.caa.multilogin.core.command;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 储存权限的地方
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Permissions {
    public static final String COMMAND_TAB_COMPLETE = "command.multilogin.tab.complete";
    public static final String COMMAND_MULTI_LOGIN_RELOAD = "command.multilogin.reload";
    public static final String COMMAND_MULTI_LOGIN_CONFIRM = "command.multilogin.confirm";
    public static final String COMMAND_MULTI_LOGIN_ERASE_USERNAME = "command.multilogin.eraseusername";
    public static final String COMMAND_MULTI_LOGIN_WHITELIST_ADD = "command.multilogin.whitelist.add";
    public static final String COMMAND_MULTI_LOGIN_WHITELIST_REMOVE = "command.multilogin.whitelist.remove";
    public static final String COMMAND_MULTI_LOGIN_WHITELIST_PERMANENT_ADD = "command.multilogin.whitelist.permanent.add";
    public static final String COMMAND_MULTI_LOGIN_WHITELIST_PERMANENT_REMOVE = "command.multilogin.whitelist.permanent.remove";
    public static final String COMMAND_MULTILOGIN_RENAME_ONESELF = "command.multilogin.rename.oneself";
    public static final String COMMAND_MULTILOGIN_RENAME_OTHER = "command.multilogin.rename.other";
    public static final String COMMAND_MULTI_LOGIN_ERASE_ALL_USERNAME = "command.multilogin.eraseallusername";
    public static final String COMMAND_MULTI_LOGIN_CURRENT_ONESELF = "command.multilogin.current.oneself";
    public static final String COMMAND_MULTI_LOGIN_CURRENT_OTHER = "command.multilogin.current.other";
    public static final String COMMAND_MULTI_LOGIN_PROFILE_CREATE = "command.multilogin.profile.create";
    public static final String COMMAND_MULTI_LOGIN_PROFILE_SET_ONESELF = "command.multilogin.profile.set.oneself";
    public static final String COMMAND_MULTI_LOGIN_PROFILE_SET_OTHER = "command.multilogin.profile.set.other";
    public static final String COMMAND_MULTI_LOGIN_PROFILE_SET_TEMP_ONESELF = "command.multilogin.profile.set.temp.oneself";
    public static final String COMMAND_MULTI_LOGIN_PROFILE_SET_TEMP_OTHER = "command.multilogin.profile.set.temp.other";
    public static final String COMMAND_MULTI_LOGIN_PROFILE_REMOVE = "command.multilogin.profile.remove";
}