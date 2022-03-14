package com.epam.brest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public interface GroupJpaRepository extends JpaRepository <Group, Integer> {

    default List<String> getAllGroupsNames(){
        return (List<String>) findAll().stream()
                .flatMap(groupes -> Stream.of(groupes.getGroupName()))
                .collect(Collectors.toList());
    }

    default List <Group> getAllGroups(){
          return (List<Group>) findAll();
      }


    default String deleteGroupByGroupName(String nameGroupe){
          Optional<Group> groupe = (Optional<Group>) findAll().stream()
                    .filter(gr -> nameGroupe.equalsIgnoreCase(gr.getGroupName()))
                    .collect(Collectors.toList())
                    .stream()
                    .findFirst();
        if (groupe.isPresent()){
            delete(groupe.get());
            return (String) groupe.get().getGroupName();
        } else {
         return "Is Empty";
        }
      }



    default Group insertNewGroup(String nameGroupe){
          Group groupe = (Group) save(new Group(nameGroupe));
          return (Group) groupe;
      }

    default Group getGroupeByGroupName(String name){
        List<Group> groupes = (List<Group>) getAllGroups();
        int  index= (int) groupes.stream()
                                    .filter(gr -> name.equals(gr.getGroupName()))
                                    .collect(Collectors.toList()).stream().count();
          if (index > 0) {
              return groupes.get(index-1);
          } else {
              return new Group();
          }

     }


    default Group updateGroupByGroupName(String newName, String oldName){
          Group groupe = (Group) getGroupeByGroupName(oldName);
          groupe.setGroupName(newName);
          return (Group) save(groupe);
      }
}
