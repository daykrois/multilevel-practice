package com.example.demo01parentchild.repository;

import com.example.demo01parentchild.entity.ParentChild;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<ParentChild,Integer> {

    @Query(value = "select * from parent_child",nativeQuery = true)
    public List<ParentChild> getallPerson();
}
