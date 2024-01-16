package com.axelor.conducteur.db.repo;

import com.axelor.conducteur.db.Langue;
import com.axelor.db.JpaRepository;
import com.axelor.db.Query;

public class LangueRepository extends JpaRepository<Langue> {

  public LangueRepository() {
    super(Langue.class);
  }

  public Langue findByName(String name) {
    return Query.of(Langue.class)
      .filter("self.name = :name")
      .bind("name", name)
      .fetchOne();
  }
}
