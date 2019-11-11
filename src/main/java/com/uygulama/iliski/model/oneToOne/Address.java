package com.uygulama.iliski.model.oneToOne;

import javax.persistence.*;

//File--> Project Structure --> Facets --> click on + --> Select JPA : JPA projesıne donusturduk. Hata oldugunda bizi uyaracak
//Verilecek OneToOne ornegınde bir Kullanıcının bir adresı olabılır ve bir adresi sadece bir kullanıcı kullanabılır
@Entity
@Table(name = "Address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer addressId;

    @Column(name = "city")
    private String city;

    @Column(name = "district")
    private String district;

    @Column(name = "street")
    private String street;

    @Column(name = "post_code")
    private Integer postCode;

    @OneToOne(mappedBy = "address")
    //mappedBy : iki entity arasındaki iliskinin sahıbını belırtır. Bu sayede Address table'ında bu column bulunmaz
    private Customer customer; //Address table'ında da customer columnu eklendı. Address'ten customer'a ulasılabılır. City= x olan customer'ları getırebılırız

    public Address() {
    }

    public Address(String city, String district, String street, Integer postCode) {
        this.city = city;
        this.district = district;
        this.street = street;
        this.postCode = postCode;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getPostCode() {
        return postCode;
    }

    public void setPostCode(Integer postCode) {
        this.postCode = postCode;
    }

    @Override
    public String toString() {
        return "Address : " +
                "\nAddress Id = " + addressId +
                "\ncity = " + city  +
                "\ndistrict = " + district  +
                "\nstreet = " + street +
                "\npostCode = " + postCode;
    }
}
