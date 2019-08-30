package com.shop.nearby.nearbyshop.model;

import lombok.Data;

@Data
public class SessionAndOpenId {
    private String session_key;
    private String openid;
    private UserInfo userInfo;

    public SessionAndOpenId(String session_key, String openid,UserInfo userInfo) {
        this.session_key = session_key;
        this.openid = openid;
        this.userInfo = userInfo;
    }
}
