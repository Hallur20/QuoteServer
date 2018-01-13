/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hvn15
 */
@Entity
@Table(name = "cookie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cookie.findAll", query = "SELECT c FROM Cookie c")
    , @NamedQuery(name = "Cookie.findByPrimarykey", query = "SELECT c FROM Cookie c WHERE c.primarykey = :primarykey")
    , @NamedQuery(name = "Cookie.findByAmount", query = "SELECT c.amount FROM Cookie c")})
public class Cookie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "primarykey")
    private Integer primarykey;
    @Column(name = "amount")
    private Integer amount;

    public Cookie() {
    }

    public Cookie(Integer amount) {
        this.amount = amount;
    }

    public Integer getPrimarykey() {
        return primarykey;
    }

    public void setPrimarykey(Integer primarykey) {
        this.primarykey = primarykey;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (primarykey != null ? primarykey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cookie)) {
            return false;
        }
        Cookie other = (Cookie) object;
        if ((this.primarykey == null && other.primarykey != null) || (this.primarykey != null && !this.primarykey.equals(other.primarykey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Cookie[ primarykey=" + primarykey + " ]";
    }
    
}
