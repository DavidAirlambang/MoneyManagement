package com.tugasbesar.tugasbesar.utility;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HiberUtility {

    public static SessionFactory getSessionFactory() {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        return sf;
    }
}
