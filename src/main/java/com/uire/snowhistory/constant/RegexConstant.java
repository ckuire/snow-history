package com.uire.snowhistory.constant;

/**
 * @author uire
 * @date 2021/11/05 11:39
 **/
public class RegexConstant {

    public static final String REGEX_IMAGE = "\\[CQ:image,.*?\\]";
    public static final String REGEX_AT = "\\[CQ:at,qq=(.*?)\\]";
    public static final String REGEX_VOICE = "\\[CQ:record,.*?\\]";
    // 音乐分享数据匹配，可能不正确
    public static final String REGEX_MUSIC = "\"meta\": \\{[\\s\\S]*\"music\"";
    // 随便找了个东西分享，标识是news，就先用这个代替链接分享吧
    public static final String REGEX_SHARE = "\"meta\": \\{[\\s\\S]*\"news\"";
}
