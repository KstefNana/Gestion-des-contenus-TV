package com.axelor.conducteur.db.repo;

import com.axelor.conducteur.db.DetailsConducteur;
import com.axelor.db.JpaRepository;

public class DetailsConducteurRepository extends JpaRepository<DetailsConducteur> {

  public DetailsConducteurRepository() {
    super(DetailsConducteur.class);
  }
}
