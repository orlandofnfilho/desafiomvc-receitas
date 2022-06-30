package br.com.gft.services;

import java.util.List;
import java.util.Optional;

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
	
	
	public Item findById(Long id) throws Exception {
		Optional<Item> item = itemRepository.findById(id);
		if (item.isEmpty()) {
			throw new Exception("item não encontrado.");
		}
		return item.get();

	}

	public List<Item> findAll() {
		return itemRepository.findAll();
	}

	public void delete(Long id) {
		itemRepository.findById(id);
	}

}
