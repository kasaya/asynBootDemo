package com.oycl.demo.orm.model;


import java.io.Serializable;

/**
 * <p>
 * CODE定义
 * </p>
 *
 * @author cango
 * @since 2019-01-15
 */
public class MTask implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分类编号
     */

    private String id;
    /**
     * 分类名称
     */
    private String name;
    /**
     * 选项值
     */
    private String insertTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }
}
