package com.msioja.thesisresv.repository;

import com.msioja.thesisresv.model.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ThesisRepository extends JpaRepository<Thesis, Integer> {

    @Query("select t from Thesis t join t.owner u where t.assignedUser is null order by u.lastName, u.firstName, t.id")
    List<Thesis> findByAssignedUserIsNullOrderByOwner();

    @Query("select t from Thesis t join t.owner u where t.assignedUser is not null order by u.lastName, u.firstName, t.id")
    List<Thesis> findByAssignedUserIsNotNullOrderByOwner();

    @Query("select t from Thesis t join t.owner u order by u.lastName, u.firstName, t.id")
    List<Thesis> findAllOrderByOwner();
}