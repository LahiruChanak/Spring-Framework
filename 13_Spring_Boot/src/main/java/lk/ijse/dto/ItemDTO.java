package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO {

    private int itemCode;
    private String description;
    private int qtyOnHand;
    private double unitPrice;

}
