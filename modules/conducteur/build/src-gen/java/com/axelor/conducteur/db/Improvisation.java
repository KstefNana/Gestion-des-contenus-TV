package com.axelor.conducteur.db;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.Sequence;
import com.axelor.db.annotations.Widget;
import com.google.common.base.MoreObjects;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Type;

@Entity
@Table(
  name = "CONDUCTEUR_IMPROVISATION",
  indexes = {
    @Index(
      columnList = "name"
    ),
    @Index(
      columnList = "programme"
    ),
    @Index(
      columnList = "conducteur"
    ),
    @Index(
      columnList = "motif"
    )
  }
)
public class Improvisation extends AuditableModel {

  @Id
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "CONDUCTEUR_IMPROVISATION_SEQ"
  )
  @SequenceGenerator(
    name = "CONDUCTEUR_IMPROVISATION_SEQ",
    sequenceName = "CONDUCTEUR_IMPROVISATION_SEQ",
    allocationSize = 1
  )
  private Long id;

  @Widget(
    title = "ID",
    readonly = true
  )
  @Sequence("conducteur.improvisation.seq")
  private String name;

  @Widget(
    title = "Date d'enregistrement"
  )
  @NotNull
  private LocalDate dateImprovisation;

  @NotNull
  @OneToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Programme programme;

  @NotNull
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private ConducteurAntenne conducteur;

  @NotNull
  private LocalTime heureDebut;

  @NotNull
  private LocalTime heureFin;

  @NotNull
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private MotifImprovisation motif;

  @Widget(
    title = "Attributes"
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Type(
    type = "json"
  )
  private String attrs;

  public Improvisation() {
  }

  public Improvisation(String name) {
    this.name = name;
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getDateImprovisation() {
    return dateImprovisation;
  }

  public void setDateImprovisation(LocalDate dateImprovisation) {
    this.dateImprovisation = dateImprovisation;
  }

  public Programme getProgramme() {
    return programme;
  }

  public void setProgramme(Programme programme) {
    this.programme = programme;
  }

  public ConducteurAntenne getConducteur() {
    return conducteur;
  }

  public void setConducteur(ConducteurAntenne conducteur) {
    this.conducteur = conducteur;
  }

  public LocalTime getHeureDebut() {
    return heureDebut;
  }

  public void setHeureDebut(LocalTime heureDebut) {
    this.heureDebut = heureDebut;
  }

  public LocalTime getHeureFin() {
    return heureFin;
  }

  public void setHeureFin(LocalTime heureFin) {
    this.heureFin = heureFin;
  }

  public MotifImprovisation getMotif() {
    return motif;
  }

  public void setMotif(MotifImprovisation motif) {
    this.motif = motif;
  }

  public String getAttrs() {
    return attrs;
  }

  public void setAttrs(String attrs) {
    this.attrs = attrs;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) return false;
    if (this == obj) return true;
    if (!(obj instanceof Improvisation)) return false;

    final Improvisation other = (Improvisation) obj;
    if (this.getId() != null || other.getId() != null) {
      return Objects.equals(this.getId(), other.getId());
    }

    return false;
  }

  @Override
  public int hashCode() {
    return 31;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
     .add("id", getId())
      .add("name", getName())
      .add("dateImprovisation", getDateImprovisation())
      .add("heureDebut", getHeureDebut())
      .add("heureFin", getHeureFin())
      .omitNullValues()
      .toString();
  }
}
