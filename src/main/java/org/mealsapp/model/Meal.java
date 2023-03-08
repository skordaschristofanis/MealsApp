package org.mealsapp.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

import java.io.Serializable;

@Entity
@Table(name = "MEAL")
@NamedQueries({
        @NamedQuery(name = "Meal.findAll", query = "SELECT m FROM Meal m"),
        @NamedQuery(name = "Meal.findByIdmeal", query = "SELECT m FROM Meal m WHERE m.idmeal = :idmeal"),
        @NamedQuery(name = "Meal.findByStrmeal", query = "SELECT m FROM Meal m WHERE m.strmeal = :strmeal"),
        @NamedQuery(name = "Meal.findByStrcategory", query = "SELECT m FROM Meal m WHERE m.strcategory = :strcategory"),
        @NamedQuery(name = "Meal.findByStrarea", query = "SELECT m FROM Meal m WHERE m.strarea = :strarea"),
        @NamedQuery(name = "Meal.findByStrinstructions", query = "SELECT m FROM Meal m WHERE m.strinstructions = :strinstructions"),
        @NamedQuery(name = "Meal.findByStatus", query = "SELECT m FROM Meal m WHERE m.status = :status")})
public class Meal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDMEAL")
    private Integer idmeal;
    @Column(name = "STRMEAL")
    private String strmeal;
    @Column(name = "STRCATEGORY")
    private String strcategory;
    @Column(name = "STRAREA")
    private String strarea;
    @Column(name = "STRINSTRUCTIONS")
    private String strinstructions;
    @Column(name = "STATUS")
    private Integer status;

    public Meal() {
        // Constructor
    }

    public Meal(Integer idmeal) {
        this.idmeal = idmeal;
    }

    public Integer getIdmeal() {
        return idmeal;
    }

    public void setIdmeal(Integer idmeal) {
        this.idmeal = idmeal;
    }

    public String getStrmeal() {
        return strmeal;
    }

    public void setStrmeal(String strmeal) {
        this.strmeal = strmeal;
    }

    public String getStrcategory() {
        return strcategory;
    }

    public void setStrcategory(String strcategory) {
        this.strcategory = strcategory;
    }

    public String getStrarea() {
        return strarea;
    }

    public void setStrarea(String strarea) {
        this.strarea = strarea;
    }

    public String getStrinstructions() {
        return strinstructions;
    }

    public void setStrinstructions(String strinstructions) {
        this.strinstructions = strinstructions;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmeal != null ? idmeal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Meal)) {
            return false;
        }
        Meal other = (Meal) object;
        if ((this.idmeal == null && other.idmeal != null) || (this.idmeal != null && !this.idmeal.equals(other.idmeal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() { return "org.mealsapp.model.Meal[ idmeal=" + idmeal + " ]"; }
}
