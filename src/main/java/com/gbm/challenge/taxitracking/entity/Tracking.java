package com.gbm.challenge.taxitracking.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Tracking entity.
 *
 * @author danizz
 */
@Entity
@Table(name = "tracking")
@Getter
@Setter
public class Tracking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tracking")
    private Integer idTracking;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lat")
    private float lat;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lng")
    private float lng;
    @Basic(optional = false)
    @NotNull
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @JoinColumn(name = "id_travel", referencedColumnName = "id_travel")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Travel idTravel;

    public Tracking() {
    }

    public Tracking(Integer idTracking) {
        this.idTracking = idTracking;
    }

    public Tracking(Integer idTracking, float lat, float lng, Date time) {
        this.idTracking = idTracking;
        this.lat = lat;
        this.lng = lng;
        this.time = time;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTracking != null ? idTracking.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tracking)) {
            return false;
        }
        Tracking other = (Tracking) object;
        if ((this.idTracking == null && other.idTracking != null) || (this.idTracking != null && !this.idTracking.equals(other.idTracking))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gbm.challenge.taxitracking.entity.Tracking[ idTracking=" + idTracking + " ]";
    }

}
