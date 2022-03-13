package com.epam.brest;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface GroupJpaRepository extends JpaRepository <Group, Integer> {

    default List<String> getAllGroupsByNameGroup () {
        return (List<String>) findAll().stream()
                .flatMap(groups -> Stream.of(groups.getGroupName()))
                .collect(Collectors.toList());
    }

    default List <Group> getAllGroups() {
        return (List<Group>) findAll();
    }

    default Group getGroupByNameGroup(String nameGroup) {
        List<Group> groups = (List<Group>) findAll();
        int  index= (int) groups.stream()
                .filter(gr -> nameGroup.equals(gr.getGroupName()))
                .collect(Collectors.toList()).stream().count();
        if (index > 0) {
            return groups.get(index-1);
        } else {
            return new Group();
        }
    }

    default String deleteGroupByNameGroup(String nameGroup) {
        Optional<Group> group = (Optional<Group>) findAll().stream()
                .filter(gr -> nameGroup.equalsIgnoreCase(gr.getGroupName()))
                .collect(Collectors.toList())
                .stream()
                .findFirst();
        if (group.isPresent()){
                delete(group.get());
                return (String) group.get().getGroupName();
        } else {
                return "Is Empty";
        }
    }

    default Group saveNewGroupe(String newNameGroup) {
        return (Group) save(new Group(newNameGroup));
    }

    default Group updateGroupByNameGroup(String newNameGroup, String oldNameGroup){
        Group group = (Group) getGroupByNameGroup(oldNameGroup);
        group.setGroupName(newNameGroup);
        return (Group) save(group);
    }

}
