package gestion.gestion_springboot.Service.Implementation;

import gestion.gestion_springboot.Model.Employe;
import gestion.gestion_springboot.Repository.EmpRepository;
import gestion.gestion_springboot.Service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpServiceImplementation implements EmpService {
    @Autowired
    private EmpRepository  empRepository;

    @Override
    public void Create(Employe employe) {
        empRepository.save(employe);
    }

    @Override
    public List<Employe> All() {
        return empRepository.findAll();
    }
     @Override
    public Employe Read(String Id) {
        if(empRepository.findById(Id).isPresent())
        return empRepository.findById(Id).get();
        return null;
    }

    @Override
    public void Update(Employe employe) {
         empRepository.save(employe);
    }

    @Override
    public void Delete(String Id) {
        empRepository.delete(Read(Id));
    }

    @Override
    public int Count() {
        return All().size();
    }
}
