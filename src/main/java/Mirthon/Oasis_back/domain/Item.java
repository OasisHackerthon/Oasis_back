package Mirthon.Oasis_back.domain;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long item_id;
    private String item_name;
    private String item_brand;
    private Integer item_price;
    private String item_image_url;
    private Long my_item_id;

}
