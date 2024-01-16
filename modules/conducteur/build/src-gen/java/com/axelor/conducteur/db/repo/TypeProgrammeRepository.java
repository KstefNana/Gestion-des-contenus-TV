package com.axelor.conducteur.db.repo;

import com.axelor.conducteur.db.TypeProgramme;
import com.axelor.db.JpaRepository;
import com.axelor.db.Query;

public class TypeProgrammeRepository extends JpaRepository<TypeProgramme> {

  public TypeProgrammeRepository() {
    super(TypeProgramme.class);
  }

  public TypeProgramme findByName(String name) {
    return Query.of(TypeProgramme.class)
      .filter("self.name = :name")
      .bind("name", name)
      .fetchOne();
  }
}
