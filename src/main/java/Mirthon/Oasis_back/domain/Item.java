package Mirthon.Oasis_back.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    private String itenName;

    private Long itemPrice;

    private String itemImageUrl;

    private String itemBrand;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "myItemId")
    private MyItem myItem;
}
