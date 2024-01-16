package com.axelor.conducteur.db;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.EqualsInclude;
import com.axelor.db.annotations.Widget;
import com.axelor.meta.db.MetaFile;
import com.google.common.base.MoreObjects;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Type;

@Entity
@Table(
  name = "CONDUCTEUR_PROGRAMME",
  indexes = {
    @Index(
      columnList = "programme_parent"
    ),
    @Index(
      columnList = "genre"
    ),
    @Index(
      columnList = "type"
    ),
    @Index(
      columnList = "langue"
    ),
    @Index(
      columnList = "centre"
    ),
    @Index(
      columnList = "image"
    )
  }
)
public class Programme extends AuditableModel {

  @Id
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "CONDUCTEUR_PROGRAMME_SEQ"
  )
  @SequenceGenerator(
    name = "CONDUCTEUR_PROGRAMME_SEQ",
    sequenceName = "CONDUCTEUR_PROGRAMME_SEQ",
    allocationSize = 1
  )
  private Long id;

  @EqualsInclude
  @NotNull
  @Size(
    min = 2
  )
  @Column(
    unique = true
  )
  private String name;

  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Programme programmeParent;

  @NotNull
  private LocalDate dateEnregistrement;

  @NotNull
  private LocalTime duree;

  @NotNull
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Genre genre;

  @NotNull
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private TypeProgramme type;

  @NotNull
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Langue langue;

  @NotNull
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private CentreProduction centre;

  @OneToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private MetaFile image;

  @Widget(
    title = "A propos"
  )
  @Lob
  @Basic(
    fetch = FetchType.LAZY
  )
  @Type(
    type = "text"
  )
  private String description;

  @Widget(
    selection = "programme.statut.selection"
  )
  @NotNull
  private String statut;

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

  public Programme() {
  }

  public Programme(String name) {
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

  public Programme getProgrammeParent() {
    return programmeParent;
  }

  public void setProgrammeParent(Programme programmeParent) {
    this.programmeParent = programmeParent;
  }

  public LocalDate getDateEnregistrement() {
    return dateEnregistrement;
  }

  public void setDateEnregistrement(LocalDate dateEnregistrement) {
    this.dateEnregistrement = dateEnregistrement;
  }

  public LocalTime getDuree() {
    return duree;
  }

  public void setDuree(LocalTime duree) {
    this.duree = duree;
  }

  public Genre getGenre() {
    return genre;
  }

  public void setGenre(Genre genre) {
    this.genre = genre;
  }

  public TypeProgramme getType() {
    return type;
  }

  public void setType(TypeProgramme type) {
    this.type = type;
  }

  public Langue getLangue() {
    return langue;
  }

  public void setLangue(Langue langue) {
    this.langue = langue;
  }

  public CentreProduction getCentre() {
    return centre;
  }

  public void setCentre(CentreProduction centre) {
    this.centre = centre;
  }

  public MetaFile getImage() {
    return image;
  }

  public void setImage(MetaFile image) {
    this.image = image;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getStatut() {
    return statut;
  }

  public void setStatut(String statut) {
    this.statut = statut;
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
    if (!(obj instanceof Programme)) return false;

    final Programme other = (Programme) obj;
    if (this.getId() != null || other.getId() != null) {
      return Objects.equals(this.getId(), other.getId());
    }

    return Objects.equals(getName(), other.getName())
      && (getName() != null);
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
      .add("dateEnregistrement", getDateEnregistrement())
      .add("duree", getDuree())
      .add("statut", getStatut())
      .omitNullValues()
      .toString();
  }
}
