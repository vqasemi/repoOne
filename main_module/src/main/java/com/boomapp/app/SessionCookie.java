package com.boomapp.app;

/**
 * @author mohsen
 * @since 11/6/2015.
 */
public class SessionCookie {

    static private SessionCookie sessionCookie;
    private String session;

    private SessionCookie(){}

    static public SessionCookie getInstance() {
        if(sessionCookie == null){
            sessionCookie = new SessionCookie();
        }
        return sessionCookie;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}
