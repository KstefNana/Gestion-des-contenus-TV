package com.axelor.conducteur.db;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.Sequence;
import com.axelor.db.annotations.Track;
import com.axelor.db.annotations.TrackEvent;
import com.axelor.db.annotations.TrackField;
import com.axelor.db.annotations.TrackMessage;
import com.axelor.db.annotations.Widget;
import com.google.common.base.MoreObjects;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Type;

@Entity
@Table(
  name = "CONDUCTEUR_CONDUCTEUR_ANTENNE",
  indexes = {
    @Index(
      columnList = "structure"
    ),
    @Index(
      columnList = "name"
    )
  }
)
@Track(
  fields = {
    @TrackField(
      name = "name"
    ),
    @TrackField(
      name = "dateEnregistrement",
      on = TrackEvent.CREATE
    ),
    @TrackField(
      name = "confirmed",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "dateEnregistrement",
      condition = "confirmed",
      on = TrackEvent.UPDATE
    )
  },
  messages = {
    @TrackMessage(
      message = "Order created",
      condition = "true",
      on = TrackEvent.CREATE
    ),
    @TrackMessage(
      message = "Conducteur confirmé",
      condition = "confirmed",
      on = TrackEvent.UPDATE
    ),
    @TrackMessage(
      message = "Brouillon",
      condition = "status == EtatValidation.BROUILLON",
      tag = "important"
    ),
    @TrackMessage(
      message = "Confirmé",
      condition = "confirmed",
      tag = "success"
    )
  }
)
public class ConducteurAntenne extends AuditableModel {

  @Id
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "CONDUCTEUR_CONDUCTEUR_ANTENNE_SEQ"
  )
  @SequenceGenerator(
    name = "CONDUCTEUR_CONDUCTEUR_ANTENNE_SEQ",
    sequenceName = "CONDUCTEUR_CONDUCTEUR_ANTENNE_SEQ",
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
  private Structure structure;

  @Widget(
    title = "ID",
    readonly = true
  )
  @Sequence("conducteur.antenne.seq")
  private String name;

  @NotNull
  @OneToMany(
    fetch = FetchType.LAZY,
    mappedBy = "conducteur",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  @OrderBy("duree")
  private List<DetailsConducteur> details;

  @OneToMany(
    fetch = FetchType.LAZY,
    mappedBy = "conducteur",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  private List<PrevisionConducteur> prevision;

  @NotNull
  private LocalDate dateEnregistrement;

  private Integer sequence = 0;

  private Boolean confirmed = Boolean.FALSE;

  private LocalDate dateExecution;

  @Basic
  @Type(
    type = "com.axelor.db.hibernate.type.ValueEnumType"
  )
  private EtatValidation etat;

  @Lob
  @Basic(
    fetch = FetchType.LAZY
  )
  @Type(
    type = "text"
  )
  private String observation;

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

  public ConducteurAntenne() {
  }

  public ConducteurAntenne(String name) {
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

  public Structure getStructure() {
    return structure;
  }

  public void setStructure(Structure structure) {
    this.structure = structure;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<DetailsConducteur> getDetails() {
    return details;
  }

  public void setDetails(List<DetailsConducteur> details) {
    this.details = details;
  }

  /**
   * Add the given {@link DetailsConducteur} item to the {@code details} collection.
   *
   * <p>
   * It sets {@code item.conducteur = this} to ensure the proper relationship.
   * </p>
   *
   * @param item the item to add
   */
  public void addDetail(DetailsConducteur item) {
    if (getDetails() == null) {
      setDetails(new ArrayList<>());
    }
    getDetails().add(item);
    item.setConducteur(this);
  }

  /**
   * Remove the given {@link DetailsConducteur} item from the {@code details} collection.
   *
   * @param item the item to remove
   */
  public void removeDetail(DetailsConducteur item) {
    if (getDetails() == null) {
      return;
    }
    getDetails().remove(item);
  }

  /**
   * Clear the {@code details} collection.
   */
  public void clearDetails() {
    if (getDetails() != null) {
      getDetails().clear();
    }
  }

  public List<PrevisionConducteur> getPrevision() {
    return prevision;
  }

  public void setPrevision(List<PrevisionConducteur> prevision) {
    this.prevision = prevision;
  }

  /**
   * Add the given {@link PrevisionConducteur} item to the {@code prevision} collection.
   *
   * <p>
   * It sets {@code item.conducteur = this} to ensure the proper relationship.
   * </p>
   *
   * @param item the item to add
   */
  public void addPrevision(PrevisionConducteur item) {
    if (getPrevision() == null) {
      setPrevision(new ArrayList<>());
    }
    getPrevision().add(item);
    item.setConducteur(this);
  }

  /**
   * Remove the given {@link PrevisionConducteur} item from the {@code prevision} collection.
   *
   * @param item the item to remove
   */
  public void removePrevision(PrevisionConducteur item) {
    if (getPrevision() == null) {
      return;
    }
    getPrevision().remove(item);
  }

  /**
   * Clear the {@code prevision} collection.
   */
  public void clearPrevision() {
    if (getPrevision() != null) {
      getPrevision().clear();
    }
  }

  public LocalDate getDateEnregistrement() {
    return dateEnregistrement;
  }

  public void setDateEnregistrement(LocalDate dateEnregistrement) {
    this.dateEnregistrement = dateEnregistrement;
  }

  public Integer getSequence() {
    return sequence == null ? 0 : sequence;
  }

  public void setSequence(Integer sequence) {
    this.sequence = sequence;
  }

  public Boolean getConfirmed() {
    return confirmed == null ? Boolean.FALSE : confirmed;
  }

  public void setConfirmed(Boolean confirmed) {
    this.confirmed = confirmed;
  }

  public LocalDate getDateExecution() {
    return dateExecution;
  }

  public void setDateExecution(LocalDate dateExecution) {
    this.dateExecution = dateExecution;
  }

  public EtatValidation getEtat() {
    return etat;
  }

  public void setEtat(EtatValidation etat) {
    this.etat = etat;
  }

  public String getObservation() {
    return observation;
  }

  public void setObservation(String observation) {
    this.observation = observation;
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
    if (!(obj instanceof ConducteurAntenne)) return false;

    final ConducteurAntenne other = (ConducteurAntenne) obj;
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
      .add("dateEnregistrement", getDateEnregistrement())
      .add("sequence", getSequence())
      .add("confirmed", getConfirmed())
      .add("dateExecution", getDateExecution())
      .omitNullValues()
      .toString();
  }
}
