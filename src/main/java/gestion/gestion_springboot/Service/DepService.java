package gestion.gestion_springboot.Service;

import gestion.gestion_springboot.Model.Departement;
import gestion.gestion_springboot.Model.Employe;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepService {
    void Create(Departement departement);
    List<Departement> All();
    List<Employe> GetEmps(String Id_Dep);
    Departement GetMaxEmp();
    Departement Read(String Id);
    void Update(Departement departement );
    void Delete(String Id);
    int Count();
}
