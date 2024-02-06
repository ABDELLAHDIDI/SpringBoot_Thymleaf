package gestion.gestion_springboot.Model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
//@NoArgsConstructor

@Entity
@Table(name = "TDepartement")
public class Departement {
    @Id
    @Column(name = "id_dept")
    private String Id_Dept ;
    @Column(name = "nom_dept")
    private String Nom_Dept ;
    @OneToMany(mappedBy = "Mon_departement")
    private List<Employe> MesEmployes ;

    public List<Employe> getMesEmployes() {
        return MesEmployes;
    }

    public void setMesEmployes(List<Employe> mesEmployes) {
        MesEmployes = mesEmployes;
    }

    public String getId_Dept() {
        return Id_Dept;
    }

    public void setId_Dept(String id_Dept) {
        Id_Dept = id_Dept;
    }

    public String getNom_Dept() {
        return Nom_Dept;
    }

    public void setNom_Dept(String nom_Dept) {
        Nom_Dept = nom_Dept;
    }
    @Override
    public String toString(){
          return  Id_Dept+"   "+Nom_Dept+"    ";
    }
}

