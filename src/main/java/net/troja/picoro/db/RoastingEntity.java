package net.troja.picoro.db;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roastings")
public class RoastingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String origin;
    private String beanName;
    private LocalDateTime created;
    @ManyToOne
    private RoastProfileEntity roastProfile;
    private Integer duration;
    private Float burnIn;
    private String comment;
    @OneToMany(mappedBy = "roasting", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoastingValuesEntity> values = new ArrayList<>();

    public RoastingEntity() {
        super();
    }

    public RoastingEntity(final String origin, final String beanName, final LocalDateTime created, final Integer duration, final Float burnIn,
            final String comment) {
        super();
        this.origin = origin;
        this.beanName = beanName;
        this.created = created;
        this.duration = duration;
        this.burnIn = burnIn;
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(final String origin) {
        this.origin = origin;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(final String beanName) {
        this.beanName = beanName;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(final LocalDateTime created) {
        this.created = created;
    }

    public RoastProfileEntity getRoastProfile() {
        return roastProfile;
    }

    public void setRoastProfile(final RoastProfileEntity roastProfile) {
        this.roastProfile = roastProfile;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(final Integer duration) {
        this.duration = duration;
    }

    public Float getBurnIn() {
        return burnIn;
    }

    public void setBurnIn(final Float burnIn) {
        this.burnIn = burnIn;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    public List<RoastingValuesEntity> getValues() {
        return values;
    }

    public void setValues(final List<RoastingValuesEntity> values) {
        this.values = values;
    }

    public void addValue(final RoastingValuesEntity value) {
        values.add(value);
    }

    @Override
    public String toString() {
        return "RoastingEntity [id=" + id + ", origin=" + origin + ", beanName=" + beanName + ", created=" + created + ", roastProfile="
                + roastProfile + ", duration=" + duration + ", burnIn=" + burnIn + ", comment=" + comment + ", values=" + values + "]";
    }
}
