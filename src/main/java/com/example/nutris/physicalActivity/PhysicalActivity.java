package com.example.nutris.physicalActivity;

import com.example.nutris.user.CustomUser;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="activity")
public class PhysicalActivity {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Column(columnDefinition = "date")
    private Date date;
    @NotNull
    private Integer duration;

    @Column(columnDefinition = "float default 0.0")
    private Float caloriesBurnt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private CustomUser user;

    public PhysicalActivity() {

    }

    public PhysicalActivity(String name, Date date, Integer duration, Float caloriesBurnt, CustomUser user) {
        this.name = name;
        this.date = date;
        this.duration = duration;
        this.caloriesBurnt = caloriesBurnt;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Float getCaloriesBurnt() {
        return caloriesBurnt;
    }

    public void setCaloriesBurnt(Float caloriesBurnt) {
        this.caloriesBurnt = caloriesBurnt;
    }

    public CustomUser getUser() {
        return user;
    }

    public void setUser(CustomUser user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }
}
