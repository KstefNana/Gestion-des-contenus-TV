package com.axelor.conducteur.db;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.EqualsInclude;
import com.axelor.db.annotations.Widget;
import com.google.common.base.MoreObjects;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Type;

@Entity
@Cacheable
@Table(
  name = "CONDUCTEUR_MOTIF_IMPROVISATION"
)
public class MotifImprovisation extends AuditableModel {

  @Id
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "CONDUCTEUR_MOTIF_IMPROVISATION_SEQ"
  )
  @SequenceGenerator(
    name = "CONDUCTEUR_MOTIF_IMPROVISATION_SEQ",
    sequenceName = "CONDUCTEUR_MOTIF_IMPROVISATION_SEQ",
    allocationSize = 1
  )
  private Long id;

  @EqualsInclude
  @Widget(
    title = "motif"
  )
  @NotNull
  @Column(
    unique = true
  )
  private String name;

  @Size(
    max = 255
  )
  private String description;

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

  public MotifImprovisation() {
  }

  public MotifImprovisation(String name) {
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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
    if (!(obj instanceof MotifImprovisation)) return false;

    final MotifImprovisation other = (MotifImprovisation) obj;
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
      .add("description", getDescription())
      .omitNullValues()
      .toString();
  }
}
