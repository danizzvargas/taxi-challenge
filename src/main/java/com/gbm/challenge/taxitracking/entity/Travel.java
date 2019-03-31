package com.gbm.challenge.taxitracking.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Travel entity.
 *
 * @author danizz
 */
@Entity
@Table(name = "travel")
@Getter
@Setter
public class Travel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_travel")
    private Integer idTravel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_travel")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTravel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "end_travel")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTravel;
    @JoinColumn(name = "id_taxi_user", referencedColumnName = "id_taxi_user")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TaxiUser idTaxiUser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTravel", fetch = FetchType.LAZY)
    private List<Tracking> trackingList;

    public Travel() {
    }

    public Travel(Integer idTravel) {
        this.idTravel = idTravel;
    }

    public Travel(Integer idTravel, Date startTravel, Date endTravel) {
        this.idTravel = idTravel;
        this.startTravel = startTravel;
        this.endTravel = endTravel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTravel != null ? idTravel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Travel)) {
            return false;
        }
        Travel other = (Travel) object;
        if ((this.idTravel == null && other.idTravel != null) || (this.idTravel != null && !this.idTravel.equals(other.idTravel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gbm.challenge.taxitracking.entity.Travel[ idTravel=" + idTravel + " ]";
    }

}
