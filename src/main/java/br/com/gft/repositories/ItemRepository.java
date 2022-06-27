package br.com.gft.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gft.entites.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
