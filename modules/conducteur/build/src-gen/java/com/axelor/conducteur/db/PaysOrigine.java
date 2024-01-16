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
  name = "CONDUCTEUR_PAYS_ORIGINE"
)
public class PaysOrigine extends AuditableModel {

  @Id
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "CONDUCTEUR_PAYS_ORIGINE_SEQ"
  )
  @SequenceGenerator(
    name = "CONDUCTEUR_PAYS_ORIGINE_SEQ",
    sequenceName = "CONDUCTEUR_PAYS_ORIGINE_SEQ",
    allocationSize = 1
  )
  private Long id;

  @EqualsInclude
  @Widget(
    title = "pays"
  )
  @NotNull
  @Column(
    unique = true
  )
  private String name;

  @EqualsInclude
  @NotNull
  @Size(
    max = 3
  )
  @Column(
    unique = true
  )
  private String code;

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

  public PaysOrigine() {
  }

  public PaysOrigine(String name, String code) {
    this.name = name;
    this.code = code;
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

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
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
    if (!(obj instanceof PaysOrigine)) return false;

    final PaysOrigine other = (PaysOrigine) obj;
    if (this.getId() != null || other.getId() != null) {
      return Objects.equals(this.getId(), other.getId());
    }

    return Objects.equals(getName(), other.getName())
      && Objects.equals(getCode(), other.getCode())
      && (getName() != null
        || getCode() != null);
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
      .add("code", getCode())
      .omitNullValues()
      .toString();
  }
}
