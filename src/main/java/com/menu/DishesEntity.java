package com.menu;

import javax.persistence.*;

/**
 * Created by Valdek on 05.07.2015.
 */
@Entity
@Table(name = "DISHES", schema = "PUBLIC", catalog = "TEST")
public class DishesEntity {
    private int id;
    private String dname;
    private Integer dcategory;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "DNAME")
    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    @Basic
    @Column(name = "DCATEGORY")
    public Integer getDcategory() {
        return dcategory;
    }

    public void setDcategory(Integer dcategory) {
        this.dcategory = dcategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DishesEntity that = (DishesEntity) o;

        if (id != that.id) return false;
        if (dname != null ? !dname.equals(that.dname) : that.dname != null) return false;
        if (dcategory != null ? !dcategory.equals(that.dcategory) : that.dcategory != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (dname != null ? dname.hashCode() : 0);
        result = 31 * result + (dcategory != null ? dcategory.hashCode() : 0);
        return result;
    }
}
