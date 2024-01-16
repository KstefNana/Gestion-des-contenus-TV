package com.axelor.conducteur.db.repo;

import com.axelor.conducteur.db.Structure;
import com.axelor.db.JpaRepository;
import com.axelor.db.Query;

public class StructureRepository extends JpaRepository<Structure> {

  public StructureRepository() {
    super(Structure.class);
  }

  public Structure findByName(String name) {
    return Query.of(Structure.class)
      .filter("self.name = :name")
      .bind("name", name)
      .fetchOne();
  }
}
