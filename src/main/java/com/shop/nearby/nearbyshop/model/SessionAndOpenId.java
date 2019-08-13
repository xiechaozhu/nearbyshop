package com.shop.nearby.nearbyshop.model;

public class SessionAndOpenId {
    private String session_key;
    private String openid;

    public SessionAndOpenId(String session_key, String openid) {
        this.session_key = session_key;
        this.openid = openid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }
}
