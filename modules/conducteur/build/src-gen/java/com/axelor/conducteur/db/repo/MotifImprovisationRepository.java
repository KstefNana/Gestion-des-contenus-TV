package com.axelor.conducteur.db.repo;

import com.axelor.conducteur.db.MotifImprovisation;
import com.axelor.db.JpaRepository;
import com.axelor.db.Query;

public class MotifImprovisationRepository extends JpaRepository<MotifImprovisation> {

  public MotifImprovisationRepository() {
    super(MotifImprovisation.class);
  }

  public MotifImprovisation findByName(String name) {
    return Query.of(MotifImprovisation.class)
      .filter("self.name = :name")
      .bind("name", name)
      .fetchOne();
  }
}
