package com.gbm.challenge.taxitracking.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * TaxiUser entity.
 *
 * @author danizz
 */
@Entity
@Table(name = "taxi_user")
@Getter
@Setter
public class TaxiUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_taxi_user")
    private Integer idTaxiUser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "first_name")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "last_name")
    private String lastName;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "phone")
    private String phone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "password")
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTaxiUser", fetch = FetchType.LAZY)
    private List<Travel> travelList;

    public TaxiUser() {
    }

    public TaxiUser(Integer idTaxiUser) {
        this.idTaxiUser = idTaxiUser;
    }

    public TaxiUser(Integer idTaxiUser, String firstName, String lastName, String phone, String password) {
        this.idTaxiUser = idTaxiUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTaxiUser != null ? idTaxiUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TaxiUser)) {
            return false;
        }
        TaxiUser other = (TaxiUser) object;
        if ((this.idTaxiUser == null && other.idTaxiUser != null) || (this.idTaxiUser != null && !this.idTaxiUser.equals(other.idTaxiUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gbm.challenge.taxitracking.entity.TaxiUser[ idTaxiUser=" + idTaxiUser + " ]";
    }

}
