package gestion.gestion_springboot.Model;


import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor

@Entity
@Table(name = "TEmploye")
public class Employe {
    @Id
    private String Id_Emp ;
    private String Nom_Emp ;
    private int Age_Emp;
    @Getter
    private float Salaire ;
    @ManyToOne
    @JoinColumn(name = "RefDept")
    private Departement Mon_departement ;

    public String getIdEmp() {
        return Id_Emp;
    }

    public void setIdEmp(String idEmp) {
        Id_Emp = idEmp;
    }

    public String getNomEmp() {
        return Nom_Emp;
    }

    public void setNomEmp(String nomEmp) {
        Nom_Emp = nomEmp;
    }

    public void setSalaire(float salaire) {
        Salaire = salaire;
    }

    public Departement getMondepartement() {
        return Mon_departement;
    }

    public void setMondepartement(Departement mondepartement) {
        Mon_departement = mondepartement;
    }
    @Override
    public String toString(){
        return Id_Emp+" "+Nom_Emp+" ";
    }
}
