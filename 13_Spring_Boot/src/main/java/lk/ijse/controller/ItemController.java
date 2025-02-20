package lk.ijse.controller;

import lk.ijse.dto.ItemDTO;
import lk.ijse.service.impl.ItemServiceImpl;
import lk.ijse.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/item")
@CrossOrigin(origins = "*")
public class ItemController {

    @Autowired
    private ItemServiceImpl itemService;

    @PostMapping(path = "save")
    public ResponseUtil saveItem(@RequestBody ItemDTO itemDTO) {

        boolean res = itemService.saveItem(itemDTO);

        if (res) {
            return new ResponseUtil(201, "Item Saved Successfully", null);
        }
        return new ResponseUtil(409, "Item Already Exists.", null);
    }

    @GetMapping("search/{id}")
    public ResponseUtil getItemById(@PathVariable int id) {

        return new ResponseUtil(200, "Success", itemService.getItemById(id));
    }

    @PutMapping("update")
    public ResponseUtil updateItem(@RequestBody ItemDTO itemDTO) {
        boolean res = itemService.updateItem(itemDTO);

        if (res) {
            return new ResponseUtil(200, "Item Updated Successfully", null);
        } else {
            return new ResponseUtil(400, "Item Not Updated", null);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseUtil deleteItem(@PathVariable int id) {
        boolean res = itemService.deleteItem(id);

        if (res) {
            return new ResponseUtil(200, "Item Deleted Successfully", null);
        } else {
            return new ResponseUtil(400, "Item Not Deleted", null);
        }
    }

    @GetMapping("getAll")
    public ResponseUtil getAllItems() {

        return new ResponseUtil(200, "Successfully Retrieved", itemService.getAllItems());
    }
}
