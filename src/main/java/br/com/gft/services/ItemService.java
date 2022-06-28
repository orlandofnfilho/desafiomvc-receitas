package br.com.gft.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gft.entites.Item;
import br.com.gft.repositories.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	public Item insert(Item item) {
		return itemRepository.save(item);
	}

}
