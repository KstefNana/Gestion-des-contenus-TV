package com.axelor.conducteur.db;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.EqualsInclude;
import com.axelor.db.annotations.Widget;
import com.google.common.base.MoreObjects;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import javax.validation.constraints.Size;
import org.hibernate.annotations.Type;

@Entity
@Cacheable
@Table(
  name = "CONDUCTEUR_CENTRE_PRODUCTION",
  indexes = @Index(
    columnList = "pays"
  )
)
public class CentreProduction extends AuditableModel {

  @Id
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "CONDUCTEUR_CENTRE_PRODUCTION_SEQ"
  )
  @SequenceGenerator(
    name = "CONDUCTEUR_CENTRE_PRODUCTION_SEQ",
    sequenceName = "CONDUCTEUR_CENTRE_PRODUCTION_SEQ",
    allocationSize = 1
  )
  private Long id;

  @EqualsInclude
  @Widget(
    title = "centre"
  )
  @NotNull
  @Column(
    unique = true
  )
  private String name;

  @NotNull
  private LocalDate dateCreation;

  @Size(
    max = 255
  )
  private String description;

  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private PaysOrigine pays;

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

  public CentreProduction() {
  }

  public CentreProduction(String name) {
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

  public LocalDate getDateCreation() {
    return dateCreation;
  }

  public void setDateCreation(LocalDate dateCreation) {
    this.dateCreation = dateCreation;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public PaysOrigine getPays() {
    return pays;
  }

  public void setPays(PaysOrigine pays) {
    this.pays = pays;
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
    if (!(obj instanceof CentreProduction)) return false;

    final CentreProduction other = (CentreProduction) obj;
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
      .add("dateCreation", getDateCreation())
      .add("description", getDescription())
      .omitNullValues()
      .toString();
  }
}
