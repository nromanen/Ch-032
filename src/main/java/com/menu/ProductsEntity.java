package com.menu;

import javax.persistence.*;

/**
 * Created by Valdek on 05.07.2015.
 */
@Entity
@Table(name = "PRODUCTS", schema = "PUBLIC", catalog = "TEST")
public class ProductsEntity {
    private int id;
    private String pname;
    private Float pweight;
    private Float pcost;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "PNAME")
    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    @Basic
    @Column(name = "PWEIGHT")
    public Float getPweight() {
        return pweight;
    }

    public void setPweight(Float pweight) {
        this.pweight = pweight;
    }

    @Basic
    @Column(name = "PCOST")
    public Float getPcost() {
        return pcost;
    }

    public void setPcost(Float pcost) {
        this.pcost = pcost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductsEntity that = (ProductsEntity) o;

        if (id != that.id) return false;
        if (pname != null ? !pname.equals(that.pname) : that.pname != null) return false;
        if (pweight != null ? !pweight.equals(that.pweight) : that.pweight != null) return false;
        if (pcost != null ? !pcost.equals(that.pcost) : that.pcost != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (pname != null ? pname.hashCode() : 0);
        result = 31 * result + (pweight != null ? pweight.hashCode() : 0);
        result = 31 * result + (pcost != null ? pcost.hashCode() : 0);
        return result;
    }
}
