package com.xmomen.module.wx.module.cart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;

@Entity
@Table(name = "tb_shopping_cart")
public class TbCartItem extends BaseMybatisModel {

	private String id;
	
	private String userToken;
	
	private Integer itemId;
	
	private Integer itemNumber;
	
	@Column(name = "ID")
    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        if(id == null){
              removeValidField("id");
              return;
        }
        addValidField("id");
    }

    @Column(name = "USER_TOKEN")
    public String getUserToken() {
    	return userToken;
    }
    
    public void setUserToken(String userToken) {
    	this.userToken = userToken;
    	if(userToken == null) {
    		removeValidField("userToken");
    		return;
    	}
    	addValidField("userToken");
    }
    
    @Column(name = "ITEM_ID")
    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
        if(itemId == null){
              removeValidField("itemId");
              return;
        }
        addValidField("itemId");
    }

    @Column(name = "ITEM_NUMBER")
    public Integer getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(Integer itemNumber) {
        this.itemNumber = itemNumber;
        if(itemNumber == null){
              removeValidField("itemNumber");
              return;
        }
        addValidField("itemNumber");
    }

}
