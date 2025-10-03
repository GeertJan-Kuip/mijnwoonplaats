package com.geertjankuip.mijnwoonplaats.domainobjects;

import jakarta.persistence.*;

@Entity
@Table(name="fetchattempts")
public class FetchAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="created", insertable = false, updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private java.time.LocalDateTime created;

    private boolean success;
    private String endpoint;

    public FetchAttempt(boolean success, String endpoint) {
        this.success = success;
        this.endpoint = endpoint;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}
