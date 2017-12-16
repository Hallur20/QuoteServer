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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hvn15
 */
@Entity
@Table(name = "quoting")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Quoting.findAll", query = "SELECT q FROM Quoting q")
    , @NamedQuery(name = "Quoting.findByQuote", query = "SELECT q FROM Quoting q WHERE q.quote = :quote")
    , @NamedQuery(name = "Quoting.findByNum", query = "SELECT q FROM Quoting q WHERE q.num = :num")})
public class Quoting implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 100)
    @Column(name = "quote")
    private String quote;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "num")
    private Integer num;

    public Quoting() {
    }

    public Quoting(Integer num) {
        this.num = num;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (num != null ? num.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Quoting)) {
            return false;
        }
        Quoting other = (Quoting) object;
        if ((this.num == null && other.num != null) || (this.num != null && !this.num.equals(other.num))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.quoteserver.Quoting[ num=" + num + " ]";
    }
    
}
