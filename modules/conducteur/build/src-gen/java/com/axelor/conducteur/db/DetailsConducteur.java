package com.axelor.conducteur.db;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.VirtualColumn;
import com.axelor.db.annotations.Widget;
import com.google.common.base.MoreObjects;
import java.time.LocalTime;
import java.util.Objects;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(
  name = "CONDUCTEUR_DETAILS_CONDUCTEUR",
  indexes = {
    @Index(
      columnList = "programme"
    ),
    @Index(
      columnList = "conducteur"
    )
  }
)
public class DetailsConducteur extends AuditableModel {

  @Id
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "CONDUCTEUR_DETAILS_CONDUCTEUR_SEQ"
  )
  @SequenceGenerator(
    name = "CONDUCTEUR_DETAILS_CONDUCTEUR_SEQ",
    sequenceName = "CONDUCTEUR_DETAILS_CONDUCTEUR_SEQ",
    allocationSize = 1
  )
  private Long id;

  @NotNull
  @ManyToOne(
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
  private LocalTime heureDebutPrevue;

  @VirtualColumn
  @Access(AccessType.PROPERTY)
  private LocalTime heureFinPrevue;

  @VirtualColumn
  @Access(AccessType.PROPERTY)
  private LocalTime duree;

  @VirtualColumn
  @Access(AccessType.PROPERTY)
  private String producteur;

  @VirtualColumn
  @Access(AccessType.PROPERTY)
  private String genre;

  @VirtualColumn
  @Access(AccessType.PROPERTY)
  private String statut;

  @VirtualColumn
  @Access(AccessType.PROPERTY)
  private String langue;

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

  public DetailsConducteur() {
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
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

  public LocalTime getHeureDebutPrevue() {
    return heureDebutPrevue;
  }

  public void setHeureDebutPrevue(LocalTime heureDebutPrevue) {
    this.heureDebutPrevue = heureDebutPrevue;
  }

  public LocalTime getHeureFinPrevue() {
    try {
      heureFinPrevue = computeHeureFinPrevue();
    } catch (NullPointerException e) {
      Logger logger = LoggerFactory.getLogger(getClass());
      logger.error("NPE in function field: getHeureFinPrevue()", e);
    }
    return heureFinPrevue;
  }

  protected LocalTime computeHeureFinPrevue() {
    return this.getHeureDebutPrevue().plusHours(programme.getDuree().getHour()).plusMinutes(programme.getDuree().getMinute());
  }

  public void setHeureFinPrevue(LocalTime heureFinPrevue) {
    this.heureFinPrevue = heureFinPrevue;
  }

  public LocalTime getDuree() {
    try {
      duree = computeDuree();
    } catch (NullPointerException e) {
      Logger logger = LoggerFactory.getLogger(getClass());
      logger.error("NPE in function field: getDuree()", e);
    }
    return duree;
  }

  protected LocalTime computeDuree() {
    return programme.getDuree();
  }

  public void setDuree(LocalTime duree) {
    this.duree = duree;
  }

  public String getProducteur() {
    try {
      producteur = computeProducteur();
    } catch (NullPointerException e) {
      Logger logger = LoggerFactory.getLogger(getClass());
      logger.error("NPE in function field: getProducteur()", e);
    }
    return producteur;
  }

  protected String computeProducteur() {
    return programme.getCentre().getName();
  }

  public void setProducteur(String producteur) {
    this.producteur = producteur;
  }

  public String getGenre() {
    try {
      genre = computeGenre();
    } catch (NullPointerException e) {
      Logger logger = LoggerFactory.getLogger(getClass());
      logger.error("NPE in function field: getGenre()", e);
    }
    return genre;
  }

  protected String computeGenre() {
    return programme.getGenre().getName();
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public String getStatut() {
    try {
      statut = computeStatut();
    } catch (NullPointerException e) {
      Logger logger = LoggerFactory.getLogger(getClass());
      logger.error("NPE in function field: getStatut()", e);
    }
    return statut;
  }

  protected String computeStatut() {
    return programme.getStatut();
  }

  public void setStatut(String statut) {
    this.statut = statut;
  }

  public String getLangue() {
    try {
      langue = computeLangue();
    } catch (NullPointerException e) {
      Logger logger = LoggerFactory.getLogger(getClass());
      logger.error("NPE in function field: getLangue()", e);
    }
    return langue;
  }

  protected String computeLangue() {
    return programme.getLangue().getName();
  }

  public void setLangue(String langue) {
    this.langue = langue;
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
    if (!(obj instanceof DetailsConducteur)) return false;

    final DetailsConducteur other = (DetailsConducteur) obj;
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
      .add("heureDebutPrevue", getHeureDebutPrevue())
      .omitNullValues()
      .toString();
  }
}
