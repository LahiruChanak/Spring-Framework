package lk.ijse.service;

import lk.ijse.dto.ItemDTO;

import java.util.List;

public interface ItemService {

    public boolean saveItem(ItemDTO itemDTO);
    public ItemDTO getItemById(int id);
    public boolean updateItem(ItemDTO itemDTO);
    public boolean deleteItem(int id);
    public List<ItemDTO> getAllItems();

}
