
package com.uygulama.iliski.DAO;

import com.uygulama.iliski.model.oneToOne.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

@Repository
public class DAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    //Data bul
    public Object getObject(Class whichClass, Serializable id){

        return getCurrentSession().get(whichClass,id);
    }

    //Data sil
    public boolean removeObject(Object removeObject){

        try {
            getCurrentSession().remove(removeObject);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    //Data ekle
    public boolean saveOrUpdateObject(Object saveObject){
        try {
            getCurrentSession().save(saveObject);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    //Customer Listesini getir
    public List<Customer> loadCustomerList(){

        CriteriaBuilder criteriaBuilder=getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery=criteriaBuilder.createQuery(Customer.class);
        Root<Customer> root = criteriaQuery.from(Customer.class);

        criteriaQuery.select(root);

        Query<Customer> query=getCurrentSession().createQuery(criteriaQuery);

        return query.getResultList();
    }
}