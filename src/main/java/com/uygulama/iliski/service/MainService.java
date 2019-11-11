
package com.uygulama.iliski.service;

import com.uygulama.iliski.DAO.DAO;
import com.uygulama.iliski.model.oneToOne.Address;
import com.uygulama.iliski.model.oneToOne.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class MainService {

    @Autowired
    private DAO dao;

    //Customer sil
    @Transactional
    public Boolean removeCustomer(Integer customerNo){
        Customer customer = (Customer) dao.getObject(Customer.class,customerNo);
        return dao.removeObject(customer);
    }

    //Customer ekle/güncelle
    @Transactional
    public Boolean saveOrUpdateCustomer(Integer customerNo, String firstName, String lastName, Date birthOfDate, String city, String district, String street, Integer postCode){
        Customer customer = (Customer) dao.getObject(Customer.class, customerNo);
        if (customer==null){
            customer = new Customer();
            customer.setCustomerNo(customerNo);
        }

        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setBirthOfDate(birthOfDate);

        Address address = new Address();
        address.setCity(city);
        address.setDistrict(district);
        address.setStreet(street);
        address.setPostCode(postCode);

        customer.setAddress(address);

        return dao.saveOrUpdateObject(customer);
    }

    //Customer listesini getir
    public List<Customer> loadCustomerList(){
        List<Customer> customerList = dao.loadCustomerList();
        return customerList;
    }
}