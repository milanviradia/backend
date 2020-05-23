package com.bloodbank.backend.service;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import  com.bloodbank.backend.model.User;
import  com.bloodbank.backend.repository.UserRepository;


@Repository
@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    
    private final EntityManager em;
    
    @Autowired
	public UserServiceImpl(EntityManager em) {
		this.em = em;
	}

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return this.userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(String userEmail) {
        return this.userRepository.findByEmail(userEmail);
    }

    @Override
    public User createUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User updateUser(User userDetails) {
        return this.userRepository.save(userDetails);
    }
    
    @Override
	public List<User> getAllNearByUsers(User user){
    	  TypedQuery<User> query = em.createQuery("select u from User u  where u.bloodGroup=?1 and (u.zipcode=?2 or (u.city=?3 and u.state=?4 and u.country=?5))", User.class);
    	  query.setParameter(1, user.getBloodGroup());
    	  query.setParameter(2, user.getZipcode());
    	  query.setParameter(3, user.getCity());
    	  query.setParameter(4, user.getState());
    	  query.setParameter(5, user.getCountry());
    	  return query.getResultList();
    }

    @Override
    public void deleteUser(User user) {
        this.userRepository.delete(user);
    }

    @Override
    public void updateUserPassword(String newPassword, String userEmail) {
       this.userRepository.updatePassword(newPassword, userEmail);
    }
}