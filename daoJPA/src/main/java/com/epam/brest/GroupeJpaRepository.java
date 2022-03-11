package com.epam.brest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public interface GroupeJpaRepository extends JpaRepository <Groupe, Integer> {

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
         return "Is Empty";
        }
      }



    default Groupe insertNewGroupe(String nameGroupe){
          Groupe groupe = (Groupe) save(new Groupe(nameGroupe));
          return (Groupe) groupe;
      }

    default Groupe getGroupeByName(String name){
        List<Groupe> groupes = (List<Groupe>) getAllGroupes();
        int  index= (int) groupes.stream()
                                    .filter(gr -> name.equals(gr.getGroupe()))
                                    .collect(Collectors.toList()).stream().count();
          if (index > 0) {
              return groupes.get(index-1);
          } else {
              return new Groupe();
          }

     }


    default Groupe updateGroupeByName(String newName, String oldName){
          Groupe groupe = (Groupe) getGroupeByName(oldName);
          groupe.setGroupe(newName);
          return (Groupe) save(groupe);
      }
}
