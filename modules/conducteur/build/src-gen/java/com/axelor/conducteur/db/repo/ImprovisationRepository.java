package com.axelor.conducteur.db.repo;

import com.axelor.conducteur.db.Improvisation;
import com.axelor.db.JpaRepository;
import com.axelor.db.Query;

public class ImprovisationRepository extends JpaRepository<Improvisation> {

  public ImprovisationRepository() {
    super(Improvisation.class);
  }

  public Improvisation findByName(String name) {
    return Query.of(Improvisation.class)
      .filter("self.name = :name")
      .bind("name", name)
      .fetchOne();
  }
}
