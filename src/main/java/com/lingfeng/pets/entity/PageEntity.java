/**
 * 
 */
package com.lingfeng.pets.entity;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author 谷春
 *
 */
@Data
@ApiModel
public class PageEntity<T> implements Serializable{
    
    
    private int currPage;//当前页数
    private int pageSize;//每页显示的记录数
    private int totalCount;//总记录数
    private int totalPage;//总页数
    private List<T> lists;//每页的显示的数据

}
