package com.epam.brest.dao;


import com.epam.brest.daoAPI.DaoGroupeApi;
import com.epam.brest.model.Groupe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ComponentScan("com.epam.brest.*")
@EntityScan("com.epam.brest.model")
@Transactional()
public class GroupeRepositoryTest {

    private final Logger logger = LogManager.getLogger(GroupeRepositoryTest.class);

    @Autowired
    private DaoGroupeApi groupeDao;

    @BeforeEach
    public void setUp() {
        String[] groupes = new String[]{"e1", "e2", "e3", "e4", "e5", "e6"};
        List<Groupe> grup = Arrays.stream(groupes)
                .map(gr -> groupeDao.insertNewGroupe(gr))
                .collect(Collectors.toList());
    }


    @Test
    public void testGetAllGroupes() {
        logger.info("GET ALL GROUPES {}");
        List<Groupe> groupes = (List<Groupe>) groupeDao.getAllGroupes();
        assertThat(groupes.get(0).getGroupe() == "e1");
    }

    @Test
    public void testGetAllGroupesByNames() {
        logger.info("GET ALL GROUPE NAMES{}");
        List<String> groupes = (List<String>) groupeDao.getAllGroupeNames();
        assertThat(groupes.size() == 3);
    }

    @Test
    public void testAddGroupe() {
        logger.info("ADD NEW GROUPE {}");
        groupeDao.insertNewGroupe("a1");
        List<String> groupes = (List<String>) groupeDao.getAllGroupeNames();
        assertThat(groupes.size() == 4);
    }

    @Test
    public void testUpdateGroupeName() {
        logger.info("UPDATE GROUPE NAME {}");
        Groupe updatedGroupe = groupeDao.updateGroupeName("a1", "e1");
        Groupe groupe = groupeDao.getGroupeByName("a1");
        assertThat(groupe.getGroupe().equals("a1") && updatedGroupe.getIdG() == groupe.getIdG());
    }


    @Test
    public void testDeleteGroupeByName() {
        logger.info("DELETE GROUPE BY NAME {}");
        String deletedNameGroupe = groupeDao.deleteGroupeByName("e1");
        assertThat(deletedNameGroupe.equals("e1"));
        deletedNameGroupe = groupeDao.deleteGroupeByName("e1");
        assertThat(deletedNameGroupe.equals("ERROR"));
        Groupe groupe = groupeDao.getGroupeByName("e1");
        assertThat(groupe.getGroupe().equals("") );
    }

}
