package com.example.rest.services;

import com.example.rest.converter.DozerConverter;
import com.example.rest.converter.custom.PersonConverter;
import com.example.rest.data.model.Person;
import com.example.rest.data.vo.PersonVO;
import com.example.rest.data.vo.v2.PersonVOV2;
import com.example.rest.exception.ResourceNotFoundException;
import com.example.rest.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServices {


    @Autowired
    PersonRepository repository;

    @Autowired
    PersonConverter converter;

    public PersonVO create(PersonVO person) {
        var entity = DozerConverter.parseObject(person, Person.class);
        var vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
        return vo;
    }

    public PersonVOV2 createV2(PersonVOV2 person) {
        var entity = converter.convertVOToEntity(person);
        var vo = converter.convertEntityToVO(repository.save(entity));
        return vo;
    }

    public List<PersonVO> findAll() {
        return DozerConverter.parseListObjects(repository.findAll(), PersonVO.class);
    }

    public PersonVO findById(Long id) {

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        return DozerConverter.parseObject(entity, PersonVO.class);
    }

    public PersonVO update(PersonVO person) {
        var entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
        return vo;
    }

    public void delete(Long id) {
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);
    }
}
