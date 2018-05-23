package net.gr2en.icsystem.repository;

import net.gr2en.icsystem.model.Software;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoftwareRepository extends JpaRepository<Software, Integer> {

}
