package com.soilinfo.soilamendmentcalculator.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "soil_reports")
public class SoilReport {

    @Id
    @Column(name = "ids")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "users")
    private AppUser user;

    @Column(name = "locations")
    private String location;

    @Column(name = "dates")
    private LocalDateTime date;

    @Column(name = "ph_levels")
    private double soilPh;

    @Column(name = "phosphorus_levels")
    private int phosphorus;

    @Column(name = "potassium_levels")
    private int potassium;

    @Column(name = "calcium_levels")
    private int calcium;

    @Column(name = "magnesium_levels")
    private int magnesium;

    @Column(name = "zinc_levels")
    private double zinc;

    public SoilReport() {}

    public SoilReport(AppUser user, String location, LocalDateTime date, double soilPh,
                      int phosphorus, int potassium, int calcium, int magnesium,
                      double zinc) {
        this.user = user;
        this.location = location;
        this.date = date;
        this.soilPh = soilPh;
        this.phosphorus = phosphorus;
        this.potassium = potassium;
        this.calcium = calcium;
        this.magnesium = magnesium;
        this.zinc = zinc;
    }

    public SoilReport(Long id, AppUser user, String location, LocalDateTime date, double soilPh,
                      int phosphorus, int potassium, int calcium, int magnesium,
                      double zinc) {
        this.id = id;
        this.user = user;
        this.location = location;
        this.date = date;
        this.soilPh = soilPh;
        this.phosphorus = phosphorus;
        this.potassium = potassium;
        this.calcium = calcium;
        this.magnesium = magnesium;
        this.zinc = zinc;
    }

    public SoilReport(SoilReportBuilder soilReportBuilder){
        this.user = soilReportBuilder.user;
        this.location = soilReportBuilder.location;
        this.date = soilReportBuilder.date;
        this.soilPh = soilReportBuilder.soilPh;
        this.phosphorus = soilReportBuilder.phosphorus;;
        this.potassium = soilReportBuilder.potassium;
        this.calcium = soilReportBuilder.calcium;
        this.magnesium = soilReportBuilder.magnesium;
        this.zinc = soilReportBuilder.zinc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getSoilPh() {
        return soilPh;
    }

    public void setSoilPh(double soilPh) {
        this.soilPh = soilPh;
    }

    public int getPhosphorus() {
        return phosphorus;
    }

    public void setPhosphorus(int phosphorus) {
        this.phosphorus = phosphorus;
    }

    public int getPotassium() {
        return potassium;
    }

    public void setPotassium(int potassium) {
        this.potassium = potassium;
    }

    public int getCalcium() {
        return calcium;
    }

    public void setCalcium(int calcium) {
        this.calcium = calcium;
    }

    public int getMagnesium() {
        return magnesium;
    }

    public void setMagnesium(int magnesium) {
        this.magnesium = magnesium;
    }

    public double getZinc() {
        return zinc;
    }

    public void setZinc(double zinc) {
        this.zinc = zinc;
    }

    public static class SoilReportBuilder{

        private long id;

        private AppUser user;

        private String location;

        private LocalDateTime date;

        private double soilPh;

        private int phosphorus;

        private int potassium;

        private int calcium;

        private int magnesium;

        private double zinc;

        public SoilReportBuilder user(AppUser user){
            this.user = user;
            return this;
        }

        public SoilReportBuilder location(String location){
            this.location = location;
            return this;
        }

        public SoilReportBuilder date(){
            this.date = LocalDateTime.now();
            return this;
        }

        public SoilReportBuilder soilPh(double soilPh){
            this.soilPh = soilPh;
            return this;
        }

        public SoilReportBuilder phosphorus(int phosphorus){
            this.phosphorus = phosphorus;
            return this;
        }

        public SoilReportBuilder potassium(int potassium){
            this.potassium = potassium;
            return this;
        }

        public SoilReportBuilder calcium(int calcium) {
            this.calcium = calcium;
            return this;
        }

        public SoilReportBuilder magnesium(int magnesium){
            this.magnesium = magnesium;
            return this;
        }

        public SoilReportBuilder zinc(double zinc){
            this.zinc = zinc;
            return this;
        }

        public SoilReport build(){
            return new SoilReport(this);
        }

    }


}
