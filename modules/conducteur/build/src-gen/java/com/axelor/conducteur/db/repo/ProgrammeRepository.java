package com.axelor.conducteur.db.repo;

import com.axelor.conducteur.db.Programme;
import com.axelor.db.JpaRepository;
import com.axelor.db.Query;

public class ProgrammeRepository extends JpaRepository<Programme> {

  public ProgrammeRepository() {
    super(Programme.class);
  }

  public Programme findByName(String name) {
    return Query.of(Programme.class)
      .filter("self.name = :name")
      .bind("name", name)
      .fetchOne();
  }
}
