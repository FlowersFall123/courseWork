package mapper;

import entity.Login;
import entity.Pet;

import java.util.List;

public interface PetMapper {
    Pet findById(Long id);
    List<Pet> getAllPet();
    Pet LoginAccount(Login login);
    void addPet(Pet pet);
}
