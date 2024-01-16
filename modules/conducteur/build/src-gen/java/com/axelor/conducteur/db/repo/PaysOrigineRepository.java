package com.axelor.conducteur.db.repo;

import com.axelor.conducteur.db.PaysOrigine;
import com.axelor.db.JpaRepository;
import com.axelor.db.Query;

public class PaysOrigineRepository extends JpaRepository<PaysOrigine> {

  public PaysOrigineRepository() {
    super(PaysOrigine.class);
  }

  public PaysOrigine findByCode(String code) {
    return Query.of(PaysOrigine.class)
      .filter("self.code = :code")
      .bind("code", code)
      .fetchOne();
  }

  public PaysOrigine findByName(String name) {
    return Query.of(PaysOrigine.class)
      .filter("self.name = :name")
      .bind("name", name)
      .fetchOne();
  }
}
