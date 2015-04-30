package com.microerp.model;

import com.ab.db.orm.annotation.Table;
import com.ab.db.orm.annotation.Column;
import com.ab.db.orm.annotation.Id;
/**
 * Created by zhongcy on 2015/4/25.
 */

@Table(name="scm_praybill")
public class SCMPraybill {
    //@Id����������Ϊ�Զ�����
    @Id
    @Column(name="id")
    private int id;

    //�����ˣ�ֵ���½user�û���ͬ
    @Column(name="creator",length=20)
    private String creator;

    @Column(name="createtime")
    private String createtime;

    //��Ʒ����
    @Column(name="product")
    private String product;

    //��Ʒ����
    @Column(name="num")
    private double num;

    //������
    @Column(name="reqdept")
    private String reqdept;

    //��������
    @Column(name="productory")
    private  String productory;

    //�Ƿ����ɲɹ�����
    @Column(name="isorderbill")
    private  boolean isorderbill;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getNum() {
        return num;
    }

    public void setNum(double num) {
        this.num = num;
    }

    public String getReqdept() {
        return reqdept;
    }

    public void setReqdept(String reqdept) {
        this.reqdept = reqdept;
    }

    public String getProductory() {
        return productory;
    }

    public void setProductory(String productory) {
        this.productory = productory;
    }

    public boolean isorderbill() {
        return isorderbill;
    }

    public void setIsorderbill(boolean isorderbill) {
        this.isorderbill = isorderbill;
    }
}
