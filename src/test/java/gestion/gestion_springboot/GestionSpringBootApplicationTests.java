package gestion.gestion_springboot;

import gestion.gestion_springboot.Model.Departement;
import gestion.gestion_springboot.Model.Employe;
import gestion.gestion_springboot.Repository.EmpRepository;
import gestion.gestion_springboot.Service.Implementation.DepServiceImplementation;
import gestion.gestion_springboot.Service.Implementation.EmpServiceImplementation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class GestionSpringBootApplicationTests {
    @Autowired
    EmpServiceImplementation empServiceImplementation;
    @Autowired
    DepServiceImplementation depServiceImplementation;

    @Test
    void create() {
//        Employe emp = new Employe();
//        emp.setId_Emp("az");
//        emp.setNom_Emp("azerty");
//        emp.setSalaire(23.4509F);
//
//        empServiceImplementation.Create(emp);
        Departement dep = new Departement();
        dep.setId_Dept("INDUS");
        dep.setNom_Dept("Departement industriel");
depServiceImplementation.Create(dep);


    }
    @Test
    void all() {

//        List<Employe> L=empServiceImplementation.All();
//
//        for (Employe emp :  L) {
//            System.out.println(emp);
//        }
        List<Departement> L=depServiceImplementation.All();

        for (Departement dep :  L) {
            System.out.println(dep);
        }

    }

}
