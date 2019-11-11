package com.uygulama.iliski.model.oneToOne;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @Column(name = "customer_no")
    private Integer customerNo;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Temporal(TemporalType.DATE)
    @Column
    private Date birthOfDate;

    //Tabloları iliskılendırıyoruz
    // cascadeType : Bir entity'nin diğer entity uzerındekı ıslevlerı, yetkileri. Basamaklama
    /* CascadeType.PERSIST : Customer'a Address verisi de girince, Ayriyetten Address'i persist etmeye gerek kalmadan,
       sadece Customer aracılıgıyla Address verisini de ekler(persist eder)(save). cascade olmadan. Address'i de persist (save) etmek gerekır.
       CascadeType.REMOVE : Bu Customer silindiğinde, ilgili adresler de silinir. OneToMany ılıskılerde sorun olabılır (Danısman silinirse tüm ogrencıler sılınebılır)
       CascadeType.MERGE : Güncelleme yapılabılsın Customer uzerınden Address'e*/

    //JPA ve Hibernate’in ilişkileri eager veya lazy yükleme özelliği vardır. Ancak Hibernate’in proxy yaklaşımından dolayı
    // opsiyonel 1:1 ilişkilerde lazy yükleme yapmak mümkün olmayabilir.

    //@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}) //Üç yetkıyı bırden verdık. Eklendıgınde eklenir, silindiğinde silinir, güncellendıgınde guncellenır.
    @OneToOne(cascade = CascadeType.ALL) //Tüm izinleri verdik
    @JoinColumn(name = "fk_address")
    private Address address; //Kullanıcının adresı

    public Customer() {
    }

    public Customer(String firstName, String lastName, Date birthOfDate, Integer customerNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthOfDate = birthOfDate;
        this.customerNo = customerNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthOfDate() {
        return birthOfDate;
    }

    public void setBirthOfDate(Date birthOfDate) {
        this.birthOfDate = birthOfDate;
    }

    public Integer getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(Integer customerNo) {
        this.customerNo = customerNo;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return "Customer : " +
                "\ncustomer no : " + customerNo +
                "\nfirstName = " + firstName +
                "\nlastName = " + lastName +
                "\nbirthOfDate = " + birthOfDate +
                "\naddress = " + address ;
    }
}
