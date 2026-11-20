package com.sis414.parciafinal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "reporte")
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 8)
    private String reportName;

    @Column(length = 50)
    private String contents;

    @Column(length = 8)
    private String modType;

    @Column(columnDefinition = "text")
    private String notes;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getReportName() { return reportName; }
    public void setReportName(String reportName) { this.reportName = reportName; }

    public String getContents() { return contents; }
    public void setContents(String contents) { this.contents = contents; }

    public String getModType() { return modType; }
    public void setModType(String modType) { this.modType = modType; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
