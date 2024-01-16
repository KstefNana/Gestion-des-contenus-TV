package com.axelor.conducteur.db;

import com.axelor.db.ValueEnum;
import com.axelor.db.annotations.Widget;
import java.util.Objects;

public enum EtatValidation implements ValueEnum<String> {
  @Widget(
    title = "Brouillon"
  )
  BROUILLON("brouillon"),

  @Widget(
    title = "En étude"
  )
  ETUDE("etude"),

  @Widget(
    title = "Validé"
  )
  VALIDE("valide"),

  @Widget(
    title = "Annulé"
  )
  ANNULE("annule");

  private final String value;

  private EtatValidation(String value) {
    this.value = Objects.requireNonNull(value);
  }

  @Override
  public String getValue() {
    return value;
  }
}
