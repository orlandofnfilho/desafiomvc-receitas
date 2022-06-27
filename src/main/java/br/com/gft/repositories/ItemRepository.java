package br.com.gft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gft.entites.Item;
import br.com.gft.entites.ItemId;

@Repository
public interface ItemRepository extends JpaRepository<Item, ItemId> {

}
