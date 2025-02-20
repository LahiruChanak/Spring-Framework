package lk.ijse.controller;

import lk.ijse.dto.ItemDTO;
import lk.ijse.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/item")
@CrossOrigin(origins = "*")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping(path = "save")
    public Boolean saveItem(@RequestBody ItemDTO itemDTO) {
        boolean res = itemService.save(itemDTO);
        return res;
    }

    @GetMapping("search/{id}")
    public ItemDTO getItemById(@PathVariable int id) {
        ItemDTO itemDTO = itemService.getItemById(id);

        if (itemDTO != null) {
            return itemDTO;
        } else {
            return null;
        }
    }

    @PutMapping("update")
    public Boolean updateItem(@RequestBody ItemDTO itemDTO) {
        boolean res = itemService.updateItem(itemDTO);

        if (res) {
            return true;
        } else {
            return false;
        }
    }

    @DeleteMapping("delete/{id}")
    public Boolean deleteItem(@PathVariable int id) {
        boolean res = itemService.deleteItem(id);

        if (res) {
            return true;
        } else {
            return false;
        }
    }

    @GetMapping("getAll")
    public List<ItemDTO> getAllItems() {
        List<ItemDTO> allItems = itemService.getAllItems();
        return allItems;
    }
}
