package ru.ivanovds.tasks.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ivanovds.tasks.demo.dto.PersonDto;

import java.util.List;

@SpringBootTest
@Slf4j
public class PersonServiceTest {

    @Autowired
    PersonService personService;

    @Test
    public void savePersonTest() {
        PersonDto person = new PersonDto(
                null, "Юрий", "Мерзляков",
                "Владимирович","Начальник", "Лукойл", null
        );
        personService.savePerson(person);

        List<PersonDto> people = personService.getAllPerson();
        int size = people.size();

        Assertions.assertNotEquals(size, 0);
    }

    @Test
    public void savePersonWithoutIdTest() {
        PersonDto person = new PersonDto(
                null, "Шеф", "Владимир", "Сергеевич",
                "Семенюк", "Twitch", null
        );

        personService.savePerson(person);

        List<PersonDto> people = personService.getAllPerson();
        int size = people.size();
        log.info(String.valueOf(size));

//        Assertions.assertEquals(size, 2);
    }

    @Test
    public void getPersonByIdTest() throws Exception {
        List<PersonDto> people = personService.getAllPerson();
        int size = people.size();
        Long id = people.get(size - 1).getId();

        PersonDto person = personService.getPersonById(id);

        Assertions.assertEquals(person.getName(), people.get(size - 1).getName());
    }

    @Test
    public void updatePersonByIdTest() {
        PersonDto person = new PersonDto(
                null, "Директор", "Максим", "Иванов",
                "Владимирович", "ОАО", "1"
        );
        List<PersonDto> people = personService.getAllPerson();
        Long id = people.get(people.size() - 1).getId();

        personService.updatePersonById(id, person);

        List<PersonDto> personList = personService.getAllPerson();

        Assertions.assertEquals(person.getName(), personList.get(personList.size() - 1).getName());
    }

    @Test
    public void deletePersonByIdTest() {
        List<PersonDto> people = personService.getAllPerson();
        int size = people.size();
        Long id = people.get(size - 1).getId();

        personService.deletePersonById(id);

        List<PersonDto> personList = personService.getAllPerson();
        int sizeNew = personList.size();

        Assertions.assertNotEquals(size, sizeNew);
    }

    @Test
    public void delAllPersonTest() {
        if (personService.delAllPerson()) {

            List<PersonDto> people = personService.getAllPerson();

            Assertions.assertEquals(people.size(), 0);
        }
    }
}
