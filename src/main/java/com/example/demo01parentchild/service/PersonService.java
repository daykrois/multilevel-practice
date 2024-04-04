package com.example.demo01parentchild.service;

import com.example.demo01parentchild.entity.ParentChild;
import com.example.demo01parentchild.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public List<ParentChild> getAllPerson(){
        return personRepository.findAll();
    }

    public List<ParentChild> getPerson() {
//        List<ParentChild> allPerson = personRepository.findAll();
        List<ParentChild> allPerson = personRepository.getallPerson();
        return buildFamilyTree(allPerson);
    }


    private List<ParentChild> buildFamilyTree(List<ParentChild> allPerson){
        List<ParentChild> personList = new ArrayList<>();
        Map<Integer,List<ParentChild>> map = new HashMap<>();
        for (ParentChild person : allPerson) {
            if(person.getParentId()==null){
                personList.add(person);
            }else{
                if(map.get(person.getParentId()) == null){
                    List<ParentChild> maplist = new ArrayList<>();
                    maplist.add(person);
                    map.put(person.getParentId(),maplist);
                }else {
                    map.get(person.getParentId()).add(person);
                }
            }
        }

        return recursiveBuild(personList,map);
    }

    private List<ParentChild> recursiveBuild(List<ParentChild> needBuildList,Map<Integer,List<ParentChild>> map){
        if (!map.isEmpty()){
            for (ParentChild parentChild : needBuildList) {
                if (map.containsKey(parentChild.getId())){
                    List<ParentChild> maplist = map.get(parentChild.getId());
                    for (ParentChild child : maplist) {
                        if (parentChild.getChildren()!=null){
                            parentChild.getChildren().add(child);
                        }else{
                            List<ParentChild> list = new ArrayList<>();
                            list.add(child);
                            parentChild.setChildren(list);
                        }
                        map.remove(parentChild.getId());
                        recursiveBuild(parentChild.getChildren(),map);
                    }
                }
            }
        }
        return needBuildList;
    }
}
