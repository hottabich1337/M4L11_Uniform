package org.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "region", schema = "public", catalog = "MrSQL")
public class RegionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "region_id")
    private short regionId;
    @Basic
    @Column(name = "region_description")
    private Object regionDescription;

    public short getRegionId() {
        return regionId;
    }

    public void setRegionId(short regionId) {
        this.regionId = regionId;
    }

    public Object getRegionDescription() {
        return regionDescription;
    }

    public void setRegionDescription(Object regionDescription) {
        this.regionDescription = regionDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegionEntity that = (RegionEntity) o;

        if (regionId != that.regionId) return false;
        if (regionDescription != null ? !regionDescription.equals(that.regionDescription) : that.regionDescription != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) regionId;
        result = 31 * result + (regionDescription != null ? regionDescription.hashCode() : 0);
        return result;
    }
}
