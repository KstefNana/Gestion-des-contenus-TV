package com.axelor.conducteur.db.repo;

import com.axelor.conducteur.db.ConducteurAntenne;
import com.axelor.db.JpaRepository;
import com.axelor.db.Query;

public class ConducteurAntenneRepository extends JpaRepository<ConducteurAntenne> {

  public ConducteurAntenneRepository() {
    super(ConducteurAntenne.class);
  }

  public ConducteurAntenne findByName(String name) {
    return Query.of(ConducteurAntenne.class)
      .filter("self.name = :name")
      .bind("name", name)
      .fetchOne();
  }
}
