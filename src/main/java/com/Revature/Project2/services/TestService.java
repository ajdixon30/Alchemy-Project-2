//package com.Revature.Project2.services;
//
//import com.Revature.Project2.beans.pojos.TestEntity;
//import com.Revature.Project2.repos.TestRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import javax.transaction.Transactional;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@Transactional
//public class TestService {
//    private final TestRepo testRepo;
//
//    @Autowired
//    public TestService(TestRepo testRepo) {
//        this.testRepo = testRepo;
//    }
//
//    public void save(TestEntity testEntity){
//        testRepo.save(testEntity);
//    }
//
//    public TestEntity getById(Integer id){
//        return testRepo.getById(id);
//    }
//
//    public List<TestEntity> getAll(){
//        return testRepo.findAll();
//    }
//
//    public List<TestEntity> getSome(){
//        return testRepo.findAll().stream().filter(p-> p.getId() <= 50).collect(Collectors.toList());
//    }
//}
