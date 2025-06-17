package anbar.model.service;

import anbar.model.entity.Supplier;
import anbar.model.repository.SupplierRepository;

import java.util.List;

public class SupplierService {
    public static void save(Supplier supplier) throws Exception {
        try (SupplierRepository supplierRepository = new SupplierRepository()) {
            supplierRepository.save(supplier);
        }
    }

    public static void edit(Supplier supplier) throws Exception {
        try (SupplierRepository supplierRepository = new SupplierRepository()) {
            if (supplierRepository.findById(supplier.getId()) != null) {
                supplierRepository.edit(supplier);
               }
        }
    }


    public static void delete(int id) throws Exception {

        try (SupplierRepository supplierRepository = new SupplierRepository()) {
            if (supplierRepository.findById(id) != null) {
                supplierRepository.delete(id);
            }}
    }

    public static Supplier findById(int id) throws Exception {
        try (SupplierRepository supplierRepository = new SupplierRepository()) {
            return supplierRepository.findById(id);
        }
    }

    public static List<Supplier> findAll() throws Exception {
        try (SupplierRepository supplierRepository = new SupplierRepository()) {
            return supplierRepository.findAll();
        }
    }


    public static List<Supplier> findByNationalId(String nationalId) throws Exception {
        try (SupplierRepository supplierRepository = new SupplierRepository()) {
            return supplierRepository.findByNationalId(nationalId);
        }
    }

    public static List<Supplier> findByParty(String party) throws Exception {
        try (SupplierRepository supplierRepository = new SupplierRepository()) {
            return supplierRepository.findByParty(party);
        }
    }

    public static List<Supplier> findByName(String name) throws Exception {
        try (SupplierRepository supplierRepository = new SupplierRepository()) {
            return supplierRepository.findByName(name);
        }
    }

    public static List<Supplier> findByPerson(String person) throws Exception {
        try (SupplierRepository supplierRepository = new SupplierRepository()) {
            return supplierRepository.findByPerson(person);
        }
    }


}
