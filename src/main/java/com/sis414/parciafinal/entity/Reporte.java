package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/** Tabla REPORTS.DBF del VSIAF. Clave: reportName, modType. */
@Entity
@Table(name = "vsiaf_reports")
@IdClass(Reporte.Key.class)
public class Reporte {

    /** DBF: REPORTNAME */
    @Id
    @Column(length = 8)
    private String reportName;

    /** DBF: CONTENTS */
    @Column(length = 50)
    private String contents;

    /** DBF: MODTYPE */
    @Id
    @Column(length = 8)
    private String modType;

    /** DBF: NOTES */
    @Column(columnDefinition = "text")
    private String notes;

    public String getReportName() { return reportName; }
    public void setReportName(String reportName) { this.reportName = reportName; }
    public String getContents() { return contents; }
    public void setContents(String contents) { this.contents = contents; }
    public String getModType() { return modType; }
    public void setModType(String modType) { this.modType = modType; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    /** Clave compuesta (reportName, modType) */
    public static class Key implements Serializable {
        private String reportName;
        private String modType;
        public Key() {}
        public Key(String reportName, String modType) {
            this.reportName = reportName;
            this.modType = modType;
        }
        public String getReportName() { return reportName; }
        public void setReportName(String reportName) { this.reportName = reportName; }
        public String getModType() { return modType; }
        public void setModType(String modType) { this.modType = modType; }
        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Key)) return false;
            Key x = (Key) o;
            return Objects.equals(reportName, x.reportName) && Objects.equals(modType, x.modType);
        }
        @Override public int hashCode() { return Objects.hash(reportName, modType); }
    }
}
