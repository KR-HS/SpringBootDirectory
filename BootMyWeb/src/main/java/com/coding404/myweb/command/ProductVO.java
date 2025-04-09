package com.coding404.myweb.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductVO {
    @NotNull
    private Integer prodId;
    private Timestamp prodRegdate;
    private String prodEnddate;
    private String prodCategory;
    private String prodWriter;
    private String prodName;
    @NotNull
    private Integer prodPrice;
    @NotNull
    private Integer prodCount;
    private Integer prodDiscount;
    @Pattern(regexp = "[YN]{1}")
    private String prodPurchaseYn;
    private String prodContent;
    private String prodComment;

    // N:1조인을 위한 컬럼
    private String categoryNav;
}
