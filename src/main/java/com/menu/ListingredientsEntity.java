package com.menu;

import javax.persistence.*;

/**
 * Created by Valdek on 05.07.2015.
 */
@Entity
@Table(name = "LISTINGREDIENTS", schema = "PUBLIC", catalog = "TEST")
public class ListingredientsEntity {
    private int id;
    private Integer did;
    private Integer pid;
    private Float pweight;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "DID")
    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    @Basic
    @Column(name = "PID")
    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Basic
    @Column(name = "PWEIGHT")
    public Float getPweight() {
        return pweight;
    }

    public void setPweight(Float pweight) {
        this.pweight = pweight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListingredientsEntity that = (ListingredientsEntity) o;

        if (id != that.id) return false;
        if (did != null ? !did.equals(that.did) : that.did != null) return false;
        if (pid != null ? !pid.equals(that.pid) : that.pid != null) return false;
        if (pweight != null ? !pweight.equals(that.pweight) : that.pweight != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (did != null ? did.hashCode() : 0);
        result = 31 * result + (pid != null ? pid.hashCode() : 0);
        result = 31 * result + (pweight != null ? pweight.hashCode() : 0);
        return result;
    }
}
