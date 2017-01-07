package com.xmomen.module.receipt.model;

import java.io.Serializable;

import lombok.Data;

public @Data class ReturnItemModel  implements Serializable {

        private String itemCode;
        private Integer itemNumber;
        private String itemName;
}
