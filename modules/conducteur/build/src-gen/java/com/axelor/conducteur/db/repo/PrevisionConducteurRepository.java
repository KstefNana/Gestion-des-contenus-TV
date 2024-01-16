package com.axelor.conducteur.db.repo;

import com.axelor.conducteur.db.PrevisionConducteur;
import com.axelor.db.JpaRepository;

public class PrevisionConducteurRepository extends JpaRepository<PrevisionConducteur> {

  public PrevisionConducteurRepository() {
    super(PrevisionConducteur.class);
  }
}
