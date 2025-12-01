package com.geertjankuip.mijnwoonplaats.domainobjects;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="endpointstest")
public class EndpointsTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="created", insertable = false, updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private java.time.LocalDateTime created;

    private Boolean woonplaatsendpoint;
    private Boolean woonplaatsdetailendpoint;
    private Boolean buurtendpoint;
    private Boolean postcodeendpoint;

    public EndpointsTest(Boolean woonplaatsendpoint, Boolean woonplaatsdetailendpoint,
                         Boolean buurtendpoint, Boolean postcodeendpoint) {

        this.woonplaatsendpoint = woonplaatsendpoint;
        this.woonplaatsdetailendpoint = woonplaatsdetailendpoint;
        this.buurtendpoint = buurtendpoint;
        this.postcodeendpoint = postcodeendpoint;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Boolean getWoonplaatsendpoint() {
        return woonplaatsendpoint;
    }

    public void setWoonplaatsendpoint(Boolean woonplaatsendpoint) {
        this.woonplaatsendpoint = woonplaatsendpoint;
    }

    public Boolean getWoonplaatsdetailendpoint() {
        return woonplaatsdetailendpoint;
    }

    public void setWoonplaatsdetailendpoint(Boolean woonplaatsdetailendpoint) {
        this.woonplaatsdetailendpoint = woonplaatsdetailendpoint;
    }

    public Boolean getBuurtendpoint() {
        return buurtendpoint;
    }

    public void setBuurtendpoint(Boolean buurtendpoint) {
        this.buurtendpoint = buurtendpoint;
    }

    public Boolean getPostcodeendpoint() {
        return postcodeendpoint;
    }

    public void setPostcodeendpoint(Boolean postcodeendpoint) {
        this.postcodeendpoint = postcodeendpoint;
    }
}
