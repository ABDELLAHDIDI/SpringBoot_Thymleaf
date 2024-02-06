package gestion.gestion_springboot.Service;

import gestion.gestion_springboot.Model.Employe;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EmpService {
    void Create(Employe employe);
    List<Employe> All();
    Employe Read(String Id);
    void Update(Employe employe );
    void Delete(String Id);
    int Count();
}
