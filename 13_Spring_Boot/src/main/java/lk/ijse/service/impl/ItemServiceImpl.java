package lk.ijse.service.impl;

import lk.ijse.dto.ItemDTO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Item;
import lk.ijse.repository.ItemRepo;
import lk.ijse.service.ItemService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public boolean saveItem(ItemDTO itemDTO) {
//        Item item = new Item(
//                itemDTO.getCode(),
//                itemDTO.getName(),
//                itemDTO.getQtyOnHand(),
//                itemDTO.getUnitPrice());

        // use model mapper to map
        if (itemRepo.existsById(itemDTO.getCode())) {
            return false;
        }
        itemRepo.save(modelMapper.map(itemDTO, Item.class));
        return true;
    }

    @Override
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

    @Override
    public boolean updateItem(ItemDTO itemDTO) {
        if (itemRepo.existsById(itemDTO.getCode())) {
//            Item item = new Item(
//                    itemDTO.getCode(),
//                    itemDTO.getName(),
//                    itemDTO.getQtyOnHand(),
//                    itemDTO.getUnitPrice());

            // use model mapper to map
            itemRepo.save(modelMapper.map(itemDTO, Item.class));
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteItem(int id) {
        if (itemRepo.existsById(id)) {
            itemRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<ItemDTO> getAllItems() {
//        List<Item> items = itemRepo.findAll();
//        List<ItemDTO> itemDTOS = new ArrayList<>();
//
//        for (Item item : items) {
//            itemDTOS.add(new ItemDTO(
//                    item.getCode(),
//                    item.getName(),
//                    item.getQtyOnHand(),
//                    item.getUnitPrice()));
//        }

        // use model mapper to map
        return modelMapper.map(itemRepo.findAll(), new TypeToken<List<ItemDTO>>() {}.getType());
    }
}
