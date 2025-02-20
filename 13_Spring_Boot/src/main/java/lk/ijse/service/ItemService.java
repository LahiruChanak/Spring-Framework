package lk.ijse.service;

import lk.ijse.dto.ItemDTO;
import lk.ijse.entity.Item;
import lk.ijse.repository.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepo itemRepo;

    public boolean save(ItemDTO itemDTO) {
        Item item = new Item(
                itemDTO.getCode(),
                itemDTO.getName(),
                itemDTO.getQtyOnHand(),
                itemDTO.getUnitPrice());
        itemRepo.save(item);
        return true;
    }

    public ItemDTO getItemById(int id) {
        Optional<Item> itemOptional = itemRepo.findById(id);

        if (itemOptional.isPresent()) {
            Item item = itemOptional.get();
            return new ItemDTO(
                    item.getCode(),
                    item.getName(),
                    item.getQtyOnHand(),
                    item.getUnitPrice());
        }
        return null;
    }

    public boolean updateItem(ItemDTO itemDTO) {
        if (itemRepo.existsById(itemDTO.getCode())) {
            Item item = new Item(
                    itemDTO.getCode(),
                    itemDTO.getName(),
                    itemDTO.getQtyOnHand(),
                    itemDTO.getUnitPrice());
            itemRepo.save(item);
            return true;
        }
        return false;
    }

    public boolean deleteItem(int id) {
        if (itemRepo.existsById(id)) {
            itemRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public List<ItemDTO> getAllItems() {
        List<Item> items = itemRepo.findAll();
        List<ItemDTO> itemDTOS = new ArrayList<>();

        for (Item item : items) {
            itemDTOS.add(new ItemDTO(
                    item.getCode(),
                    item.getName(),
                    item.getQtyOnHand(),
                    item.getUnitPrice()));
        }
        return itemDTOS;
    }
}
