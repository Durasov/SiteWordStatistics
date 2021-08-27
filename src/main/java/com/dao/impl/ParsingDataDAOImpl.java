package com.dao.impl;

import com.dao.ParsingDataDAO;
import com.model.ParsingData;
import com.util.HibernateUtil;
import org.hibernate.Session;

public class ParsingDataDAOImpl implements ParsingDataDAO {

    public void save(ParsingData parsingData){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(parsingData);
        session.getTransaction().commit();
        session.close();
    }

    //Этот метод не используется, но на всякий случай реализовал
    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        ParsingData parsingData = session.getSession().load(ParsingData.class, id);
        session.delete(parsingData);
        session.getTransaction().commit();
        session.close();
    }

}
