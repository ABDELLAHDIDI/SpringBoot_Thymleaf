package gestion.gestion_springboot.Service.Implementation;

import gestion.gestion_springboot.Model.Departement;
import gestion.gestion_springboot.Model.Employe;
import gestion.gestion_springboot.Repository.DepRepository;
import gestion.gestion_springboot.Service.DepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepServiceImplementation implements DepService {
    @Autowired
    private DepRepository depRepository;

    @Override
    public void Create(Departement departement) {
        depRepository.save(departement);
    }

    @Override
    public List<Departement> All() {
        return depRepository.findAll();
    }

    @Override
    public List<Employe> GetEmps(String Id_Dep) {

        return depRepository.findEmployesByDepartementId(Id_Dep);
    }

    @Override
    public Departement GetMaxEmp() {
        Departement dep = null ;


        List<Departement>  l = All();

        if(!l.isEmpty())
        {
            int max =GetEmps(l.get(0).getId_Dept()).size();
            dep = l.get(0);

            for(int i=1;i<All().size();i++){
                List<Employe> L = GetEmps(l.get(i).getId_Dept());

                if(max<L.size()) {
                    max= L.size();
                    dep=l.get(i);
                }
            }
        }

        return dep;
    }

    @Override
    public Departement Read(String Id) {
        if(depRepository.findById(Id).isPresent())
        return depRepository.findById(Id).get();
        return null;
    }

    @Override
    public void Update(Departement departement) {
depRepository.save(departement);
    }

    @Override
    public void Delete(String Id) {
depRepository.delete(Read(Id));
    }

    @Override
    public int Count() {
        return All().size();
    }
}
