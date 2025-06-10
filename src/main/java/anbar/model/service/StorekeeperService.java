package anbar.model.service;

import anbar.model.entity.Storekeeper;
import anbar.model.repository.StorekeeperRepository;

import java.util.List;

public class StorekeeperService {
    public static void save(Storekeeper storekeeper) throws Exception {
        try (StorekeeperRepository storekeeperRepository = new StorekeeperRepository()) {
            storekeeperRepository.save(storekeeper);
        }
    }

    public static void edit(Storekeeper storekeeper) throws Exception {
        try (StorekeeperRepository storekeeperRepository = new StorekeeperRepository()) {
            if (storekeeperRepository.findById(storekeeper.getId()) != null) {
                storekeeperRepository.edit(storekeeper);
            }
            throw new Exception("Product not found");
        }
    }


    public static void delete(int id) throws Exception {

        try (StorekeeperRepository storekeeperRepository = new StorekeeperRepository()) {
            if (storekeeperRepository.findById(id) != null) {
                storekeeperRepository.delete(id);
            }
            storekeeperRepository.delete(id);
        }
    }

    public static List<Storekeeper> findAll() throws Exception {
        try (StorekeeperRepository storekeeperRepository = new StorekeeperRepository()) {
            return storekeeperRepository.findAll();
        }
    }

    public static Storekeeper findById(int id) throws Exception {
        try (StorekeeperRepository storekeeperRepository = new StorekeeperRepository()) {
            return storekeeperRepository.findById(id);
        }
    }


    public static List<Storekeeper> findByNationalId(String nationalId) throws Exception {
        try (StorekeeperRepository storekeeperRepository = new StorekeeperRepository()) {
            return storekeeperRepository.findByNationalId(nationalId);
        }
    }

    public static List<Storekeeper> findByNameAndFamily(String name, String family) throws Exception {
        try (StorekeeperRepository storekeeperRepository = new StorekeeperRepository()) {
            return storekeeperRepository.findByNameAndFamily(name, family);
        }
    }

    public static List<Storekeeper> findByUserAndPassword(String username, String password) throws Exception {
        try (StorekeeperRepository storekeeperRepository = new StorekeeperRepository()) {
            return storekeeperRepository.findByUsernameAndPassword(username, password);
        }
    }

    public static List<Storekeeper> findByUsername(String username) throws Exception {
        try (StorekeeperRepository storekeeperRepository = new StorekeeperRepository()) {
            return storekeeperRepository.findByUsername(username);
        }
    }

}
