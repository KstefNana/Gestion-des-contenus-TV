package com.axelor.conducteur.db.repo;

import com.axelor.conducteur.db.CentreProduction;
import com.axelor.db.JpaRepository;
import com.axelor.db.Query;

public class CentreProductionRepository extends JpaRepository<CentreProduction> {

  public CentreProductionRepository() {
    super(CentreProduction.class);
  }

  public CentreProduction findByName(String name) {
    return Query.of(CentreProduction.class)
      .filter("self.name = :name")
      .bind("name", name)
      .fetchOne();
  }
}
