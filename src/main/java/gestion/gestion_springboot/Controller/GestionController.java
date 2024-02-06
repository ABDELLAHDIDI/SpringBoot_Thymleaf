package gestion.gestion_springboot.Controller;

import gestion.gestion_springboot.Model.Departement;
import gestion.gestion_springboot.Model.Employe;
import gestion.gestion_springboot.Service.Implementation.DepServiceImplementation;
import gestion.gestion_springboot.Service.Implementation.EmpServiceImplementation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class GestionController {
    String context ;
    void setContext(){
        context   =
                ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                        .getRequest().getRequestURI();
    }

        @Autowired
    EmpServiceImplementation empServiceImplementation;
    @Autowired
    DepServiceImplementation depServiceImplementation;



    @RequestMapping("/Departements")
    String GetAllDep(Model m){
       setContext();
//        System.out.println("  HttpServletRequest request :"+request.getRequestURI().split("/")[1]);
        List<Departement> L = depServiceImplementation.All();
        L.forEach(dep -> {
            dep.setMesEmployes(depServiceImplementation.GetEmps(dep.getId_Dept()));
        });
        m.addAttribute("departements",depServiceImplementation.All());

        List<Float> masse = new ArrayList<>();

        for(int i = 0 ; i< L.size() ; i ++) {
            Float salaire = 0F;
            List<Employe> l = depServiceImplementation.GetEmps(L.get(i).getId_Dept());
            for (int j = 0; j < l.size(); j++) {
                salaire = +salaire + l.get(j).getSalaire();
            }
            masse.add(salaire);
        }
        m.addAttribute("m",masse);


        return "Departements/Departements";
    }
    @RequestMapping("/updatedep/{code}")
    public String updateDepartement(Model m, @PathVariable String code) {
        m.addAttribute("dep", depServiceImplementation.Read(code));
        m.addAttribute("context",  context);
        return "Departements/UpdateDepartement";
    }
    @PostMapping("/updatedep/{code}")
    public String updateDept(Model m, @PathVariable String code,@RequestParam("nom_Dept") String nom_Dept) {
        Departement dep = depServiceImplementation.Read(code);
        dep.setNom_Dept(nom_Dept);
        depServiceImplementation.Update(dep);
        return "redirect:/"+context.split("/")[1];
    }
    @GetMapping("/adddep")
    public String adddep(Model m){

        m.addAttribute("Add_dept",new Departement());
        m.addAttribute("context",  context);
        return  "Departements/AddDepartement";
    }
    @PostMapping("/adddep")
    public String adddepartement(Model m, @RequestParam("id_Dept") String code,@RequestParam("nom_Dept") String nom_Dept) {

        if(depServiceImplementation.Read(code) != null ){
            m.addAttribute("Add_dept",depServiceImplementation.Read(code));
            m.addAttribute("context",  context);
            return  "Departements/AddDepartement";
        }
        Departement dep =new Departement();
        dep.setId_Dept(code);
        dep.setNom_Dept(nom_Dept);
        depServiceImplementation.Create(dep);
        return "redirect:/Departements";
    }
    @GetMapping("/deletedep/{code}")
    public String deletedep(Model m,@PathVariable String code){

        m.addAttribute("Delete_dept",depServiceImplementation.Read(code));
        m.addAttribute("context",  context);
        return  "Departements/DeleteDepartement";
    }
    @PostMapping("/deletedep")
    public String deletedepartement(Model m, @RequestParam("id_Dept") String code) {

        Departement d = new Departement();
        d.setId_Dept("Vide");
        List<Employe> list =depServiceImplementation.GetEmps(code);
        list.forEach(emp -> {
            emp.setMondepartement(d);
            empServiceImplementation.Update(emp);
        });

       depServiceImplementation.Delete(code);
        return "redirect:/"+context.split("/")[1];
    }


    @RequestMapping("/Employes")
    String GetAllEmp(Model m){
        setContext();
        m.addAttribute("employes",empServiceImplementation.All());
        return "Employes/Employes";
    }

    @GetMapping("/addemp")
    public String addemp(Model m){

        m.addAttribute("Add_emp",new Employe());
        m.addAttribute("allDepartements" , depServiceImplementation.All());
        m.addAttribute("context",  context);
        return  "Employes/AddEmploye";
    }
    @PostMapping("/addemp")
    public String addemploye(Model m, @RequestParam("id_Emp") String code,@RequestParam("nom_Emp") String nom,
                             @RequestParam("salaire") Float sal, @RequestParam("age_Emp") int age,
                             @RequestParam("mon_departement") Departement dep ) {

        if(empServiceImplementation.Read(code) != null ){
            m.addAttribute("Add_emp",empServiceImplementation.Read(code));
            m.addAttribute("allDepartements" , depServiceImplementation.All());
            m.addAttribute("context",  context);
            return  "Employes/AddEmploye";
        }
        Employe emp =new Employe();
        emp.setId_Emp(code);
        emp.setNom_Emp(nom);
        emp.setSalaire(sal);
        emp.setAge_Emp(age);
        emp.setMondepartement(dep);
        empServiceImplementation.Create(emp);
        return "redirect:"+context;
    }
    @RequestMapping("/updatemp/{code}")
    public String updatemp(Model m, @PathVariable String code) {

        m.addAttribute("emp", empServiceImplementation.Read(code));
        m.addAttribute("allDepartements" , depServiceImplementation.All());
        m.addAttribute("context",  context);

        return "Employes/UpdateEmploye";
    }
    @PostMapping("/updatemp/{code}")
    public String updatemploye(Model m, @PathVariable String code,@RequestParam("nom_Emp") String nom,
                               @RequestParam("salaire") Float sal, @RequestParam("age_Emp") int age,
                               @RequestParam("mon_departement") Departement dep ) {
        System.out.println("updatemploye");
        Employe emp = empServiceImplementation.Read(code);
       emp.setNomEmp(nom);
       emp.setSalaire(sal);
       emp.setAge_Emp(age);
       emp.setMondepartement(dep);
        empServiceImplementation.Update(emp);
        return "redirect:"+context;
    }
    @GetMapping("/deletemp/{mix}")
    public String deletemp(Model m,@PathVariable String mix){

        String code = mix.split("%")[0];
        String dep = mix.split("%")[1];
        Departement d = new Departement();
        d.setId_Dept(dep);
Employe emp = empServiceImplementation.Read(code);
emp.setMondepartement(d);
        m.addAttribute("context",  context);
        m.addAttribute("Delete_emp",emp);
        return  "Employes/DeleteEmploye";
    }
    @PostMapping("/deletemp")
    public String deletemploye(Model m, @RequestParam("id_Emp") String code,
                               @RequestParam("mon_departement.id_Dept") String dep ) {

      if(Objects.equals(dep, "Vide")){
          empServiceImplementation.Delete(code);
      }
      else {
          Employe emp = empServiceImplementation.Read(code);
          Departement dept = new Departement();
          dept.setId_Dept("Vide");
          emp.setMon_departement(dept);
          empServiceImplementation.Update(emp);
        }
        return "redirect:"+context;
    }


    //Recherhe
    @RequestMapping("/home")
    String home(Model m){
        setContext();

        List<Departement> L = new ArrayList<>();
      //  if(depServiceImplementation.GetMaxEmp() != null) {

            L.add(depServiceImplementation.GetMaxEmp());
            m.addAttribute("departements", L);

            Float salaire = 0F;
            int nbrT = 0, ageT = 0;
            Float myA = 0F;
            if (!empServiceImplementation.All().isEmpty()) {
                List<Employe> l = empServiceImplementation.All();
                nbrT = l.size();
                for (int i = 0; i < l.size(); i++) {
                    salaire = salaire + l.get(i).getSalaire();
                    ageT += l.get(i).getAge_Emp();
                }
                myA = Float.intBitsToFloat(ageT) / Float.intBitsToFloat(l.size());
            }
            m.addAttribute("masse", salaire);
            m.addAttribute("nbrT", nbrT);
            m.addAttribute("mya", String.format("%.2f", myA));
     //   }
        return "home";
    }
    @GetMapping("/Recherche")
    String Get(Model m,@RequestParam(value = "id") String id){
        if(empServiceImplementation.Read(id) != null ){
            List<Employe> L = new ArrayList<>();
            L.add(empServiceImplementation.Read(id));
            m.addAttribute("employes", L);
            return "Employes/Employes";
        }
        else if(depServiceImplementation.Read(id) != null ){
            List<Departement> L = new ArrayList<>();
            L.add(depServiceImplementation.Read(id));

            List<Float> masse = new ArrayList<>();

            for(int i = 0 ; i< L.size() ; i ++) {
                Float salaire = 0F;
                List<Employe> l = depServiceImplementation.GetEmps(L.get(i).getId_Dept());
                for (int j = 0; j < l.size(); j++) {
                    salaire = +salaire + l.get(j).getSalaire();
                }
                masse.add(salaire);
            }
            m.addAttribute("m",masse);

            m.addAttribute("departements",L);
            return "Departements/Departements";
        }
        else {
            return "redirect:home";
        }

    }
    @RequestMapping("/ShowEmploye/{code}")
    String GetEmp(Model m,@PathVariable String code){
        setContext();
        m.addAttribute("employes",depServiceImplementation.GetEmps(code));
        m.addAttribute("Show",code);
        return "Employes/Employes";
    }


}
