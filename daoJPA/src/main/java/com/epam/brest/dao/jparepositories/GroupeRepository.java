package com.epam.brest.dao.jparepositories;

import com.epam.brest.model.Groupe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface GroupeRepository extends JpaRepository <Groupe, Integer> {

    default List<String> getAllGroupesByName (){
          return (List<String>) findAll().stream()
                                         .flatMap(groupes -> Stream.of(groupes.getGroupe()))
                                         .collect(Collectors.toList());
      }


    default List <Groupe> getAllGroupes(){
          return (List<Groupe>) findAll();
      }


    default String deleteGroupeByName(String nameGroupe){
          Optional<Groupe> groupe = (Optional<Groupe>) findAll().stream()
                                                                .filter(gr -> nameGroupe.equalsIgnoreCase(gr.getGroupe()))
                                                                .collect(Collectors.toList())
                                                                .stream()
                                                                .findFirst();
        if (groupe.isPresent()){
            delete(groupe.get());
            return (String) groupe.get().getGroupe();
        } else {
         return "ERROR!!!";
        }
      }



    default Groupe insertNewGroupe(String nameGroupe){
          Groupe groupe = (Groupe) save(new Groupe(nameGroupe));
          return (Groupe) groupe;
      }

    default Groupe getGroupeByName(String name){
           return (Groupe) findAll().stream()
                                    .filter(gr -> name.equalsIgnoreCase(gr.getGroupe()))
                                    .collect(Collectors.toList())
                                    .stream()
                                    .findFirst().get();
     }


    default Groupe updateGroupeByName(String newName, String oldName){
          Groupe groupe = (Groupe) getGroupeByName(oldName);
          groupe.setGroupe(newName);
          return (Groupe) save(groupe);

      }
}
