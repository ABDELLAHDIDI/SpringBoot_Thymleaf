package gestion.gestion_springboot.Repository;

import gestion.gestion_springboot.Model.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpRepository extends JpaRepository<Employe ,String > {
}
