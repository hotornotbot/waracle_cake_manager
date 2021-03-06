package com.waracle.cakemanager.storage;

import com.waracle.cakemanager.entity.Cake;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CakeRepository extends CrudRepository<Cake, Long> {
}
