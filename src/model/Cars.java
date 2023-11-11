/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author busyDev
 */
public class Cars {

    private Integer id;
    private String model;
    private String type;
    private String year;
    private String fault_description;
    private String owner_name;
    private String owner_email;
    private String status;

    public Cars() {
    }

    public Cars(String model, String type, String year, String fault_description, String owner_name, String owner_email, String status) {
        this.model = model;
        this.type = type;
        this.year = year;
        this.fault_description = fault_description;
        this.owner_name = owner_name;
        this.owner_email = owner_email;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getFault_description() {
        return fault_description;
    }

    public void setFault_description(String fault_description) {
        this.fault_description = fault_description;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getOwner_email() {
        return owner_email;
    }

    public void setOwner_email(String owner_email) {
        this.owner_email = owner_email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
