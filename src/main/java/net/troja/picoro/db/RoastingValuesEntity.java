package net.troja.picoro.db;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "roasting_values")
public class RoastingValuesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int time;
    private Float inputTemperature;
    private Float roastTemperature;
    private Integer fan;
    private Integer heating;
    private Boolean crack;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roasting_id")
    private RoastingEntity roasting;

    public RoastingValuesEntity() {
        super();
    }

    public RoastingValuesEntity(final int time, final Float inputTemperature, final Float roastTemperature, final Integer fan,
            final Integer heating, final Boolean crack) {
        super();
        this.time = time;
        this.inputTemperature = inputTemperature;
        this.roastTemperature = roastTemperature;
        this.fan = fan;
        this.heating = heating;
        this.crack = crack;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(final int time) {
        this.time = time;
    }

    public Float getInputTemperature() {
        return inputTemperature;
    }

    public void setInputTemperature(final Float inputTemperature) {
        this.inputTemperature = inputTemperature;
    }

    public Float getRoastTemperature() {
        return roastTemperature;
    }

    public void setRoastTemperature(final Float roastTemperature) {
        this.roastTemperature = roastTemperature;
    }

    public Integer getFan() {
        return fan;
    }

    public void setFan(final Integer fan) {
        this.fan = fan;
    }

    public Integer getHeating() {
        return heating;
    }

    public void setHeating(final Integer heating) {
        this.heating = heating;
    }

    public Boolean getCrack() {
        return crack;
    }

    public void setCrack(final Boolean crack) {
        this.crack = crack;
    }

    public RoastingEntity getRoasting() {
        return roasting;
    }

    public void setRoasting(final RoastingEntity roasting) {
        this.roasting = roasting;
    }

    @Override
    public String toString() {
        return "RoastingValuesEntity [id=" + id + ", time=" + time + ", inputTemperature=" + inputTemperature + ", roastTemperature="
                + roastTemperature + ", fan=" + fan + ", heating=" + heating + ", crack=" + crack + "]";
    }
}
