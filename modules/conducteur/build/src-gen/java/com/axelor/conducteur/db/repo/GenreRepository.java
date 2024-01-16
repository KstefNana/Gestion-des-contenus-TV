package com.axelor.conducteur.db.repo;

import com.axelor.conducteur.db.Genre;
import com.axelor.db.JpaRepository;
import com.axelor.db.Query;

public class GenreRepository extends JpaRepository<Genre> {

  public GenreRepository() {
    super(Genre.class);
  }

  public Genre findByName(String name) {
    return Query.of(Genre.class)
      .filter("self.name = :name")
      .bind("name", name)
      .fetchOne();
  }
}
