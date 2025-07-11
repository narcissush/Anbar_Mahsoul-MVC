package anbar.model.service;

import anbar.controller.exceptions.DuplicateUsernameException;
import anbar.controller.exceptions.UserNotFoundException;
import anbar.model.entity.User;
import anbar.model.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {
    public static void save(User user) throws Exception {
        try (UserRepository userRepository = new UserRepository()) {
//            Optional<User> foundUser = Optional.ofNullable( userRepository.findByUsername(user.getUsername()));
            userRepository.save(user);
        }
    }


    public static void edit(User user) throws Exception {
        try (UserRepository userRepository = new UserRepository()) {
            userRepository.edit(user);
        }
    }

    public static void delete(int id) throws Exception {

        try (UserRepository userRepository = new UserRepository()) {
            if (userRepository.findById(id) != null) {
                userRepository.delete(id);
            }
            userRepository.delete(id);
        }
    }

    public static User findById(int id) throws Exception {
        try (UserRepository userRepository = new UserRepository()) {
            return userRepository.findById(id);
        }
    }

    public static List<User> findAll() throws Exception {
        try (UserRepository userRepository = new UserRepository()) {
            return userRepository.findAll();
        }
    }


    public static User findByNationalId(String nationalId) throws Exception {
        try (UserRepository userRepository = new UserRepository()) {
            return userRepository.findByNationalId(nationalId);
        }
    }

    public static List<User> findByNameAndFamily(String name, String family) throws Exception {
        try (UserRepository userRepository = new UserRepository()) {
            return userRepository.findByNameAndFamily(name, family);
        }
    }

    public static User findByUserAndPassword(String username, String password) throws Exception {
        try (UserRepository userRepository = new UserRepository()) {
            Optional<User> loginUser = Optional.ofNullable(userRepository.findByUsernameAndPassword(username, password));
            return loginUser.orElseThrow(UserNotFoundException::new);
        }
    }

    public static User findByUsername(String username) throws Exception {
        try (UserRepository userRepository = new UserRepository()) {
            return userRepository.findByUsername(username);
        }
    }


}
