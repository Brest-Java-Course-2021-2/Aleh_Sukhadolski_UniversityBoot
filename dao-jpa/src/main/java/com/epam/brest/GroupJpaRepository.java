package com.epam.brest;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface GroupJpaRepository extends JpaRepository <Group, Integer> {

    default List<String> getAllGroupsNames() {
        return (List<String>) findAll().stream()
                                       .flatMap(groups -> Stream.of(groups.getGroupName()))
                                       .collect(Collectors.toList());
    }

    default List <Group> getAllGroups(){
          return (List<Group>) findAll();
      }


    default String deleteGroupByGroupName(String nameGroupe) {
          Optional<Group> group = (Optional<Group>) findAll().stream()
                                                    .filter(gr -> nameGroupe.equalsIgnoreCase(gr.getGroupName()))
                                                    .collect(Collectors.toList())
                                                    .stream()
                                                    .findFirst();
        if (group.isPresent()) {
            delete(group.get());
            return (String) group.get().getGroupName();
        } else {
         return "Is Empty";
        }
    }



    default Group insertNewGroup(String nameGroupe) {
          Group group = (Group) save(new Group(nameGroupe));
          return (Group) group;
    }

    default Group getGroupeByGroupName(String name) {
        List<Group> groups = (List<Group>) getAllGroups();
        int  index= (int) groups.stream()
                                .filter(gr -> name.equals(gr.getGroupName()))
                                .collect(Collectors.toList()).stream().count();
          if (index > 0) {
              return groups.get(index-1);
          } else {
              return new Group("IsEmpty");
          }
    }

    default Group updateGroupByGroupName(String newName, String oldName) {
          Group group = (Group) getGroupeByGroupName(oldName);
          group.setGroupName(newName);
          return (Group) save(group);
    }
}
