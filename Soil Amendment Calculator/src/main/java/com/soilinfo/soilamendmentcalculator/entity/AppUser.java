package com.soilinfo.soilamendmentcalculator.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "app-users")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ids")
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "passwords")
    private String password;

    @Column(name = "soil_reports")
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<SoilReport> soilReports;

    public AppUser(){}

    public AppUser(String username, String password){
        this.username = username;
        this.password = password;
    }
    public AppUser(Long id, String username, String password, List<SoilReport> soilReports) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.soilReports = soilReports;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<SoilReport> getSoilReports() {
        return soilReports;
    }

    public void setSoilReports(List<SoilReport> soilReports) {
        this.soilReports = soilReports;
    }
}
