package me.xxgradzix.gradzixcore.VPLNShop.data.database;

import lombok.*;
import org.bukkit.entity.Player;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VPLNOrderDTO {

    private String orderCreator;
    private String playerName;
    private String orderStatus;
    private double VPLNAmount;

}
