package anbar.service;

import anbar.model.entity.Storekeeper;
import anbar.model.repository.StorekeeperRepository;

import java.util.List;

public class StorekeeperService {
    public static void save(Storekeeper storekeeper) throws Exception {
        try(StorekeeperRepository storekeeperRepository = new StorekeeperRepository()){
            storekeeperRepository.save(storekeeper);
        }
    }

    public static void edit(Storekeeper storekeeper,int id) throws Exception {
        try(StorekeeperRepository storekeeperRepository = new StorekeeperRepository()){
            storekeeperRepository.edit(storekeeper,id);
        }
    }

    public static void delete(int id) throws Exception {
        try(StorekeeperRepository storekeeperRepository = new StorekeeperRepository()){
            storekeeperRepository.delete (id);
        }
    }

    public static List<Storekeeper> findAll() throws Exception {
        try(StorekeeperRepository storekeeperRepository = new StorekeeperRepository()){
            return storekeeperRepository.findAll();
        }
    }

    public static List<Storekeeper> findByNationalId(String  nationalId) throws Exception {
        try(StorekeeperRepository storekeeperRepository = new StorekeeperRepository()){
            return storekeeperRepository.findByNationalId(nationalId);
        }
    }

    public static List<Storekeeper> findByNameAndFamily(String  name,String family) throws Exception {
        try(StorekeeperRepository storekeeperRepository = new StorekeeperRepository()){
            return storekeeperRepository.findByNameAndFamily(name,family);
        }
    }


}
