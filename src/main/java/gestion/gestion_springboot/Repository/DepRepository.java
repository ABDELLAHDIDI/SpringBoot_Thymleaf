package gestion.gestion_springboot.Repository;

import gestion.gestion_springboot.Model.Departement;
import gestion.gestion_springboot.Model.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepRepository extends JpaRepository<Departement,String > {
    @Query("SELECT e FROM Employe e WHERE e.Mon_departement.Id_Dept = :departementId")
    List<Employe> findEmployesByDepartementId(@Param("departementId") String departementId);
}
