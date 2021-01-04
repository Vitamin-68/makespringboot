package com.springboot.repos;

import com.springboot.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessagesRepo extends CrudRepository<Message, Integer> {
//    название метода придумано по правилам - ключ слово find затем by и поле по которому искать
//    правила описангы в руководстве по spring JPA
    List<Message> findByTag(String tag);
}
