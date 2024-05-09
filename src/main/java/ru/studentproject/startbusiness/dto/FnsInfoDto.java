package ru.studentproject.startbusiness.dto;

import org.json.JSONObject;

public class FnsInfoDto {
    private String code;
    private String name;
    private String address;
    private String phone;
    private String reception;

    public FnsInfoDto() {
    }

    public FnsInfoDto(JSONObject data) {
        this.code = data.getString("code");
        this.name = data.getString("name");
        this.address = makePrettyAddress(data.getString("address"));
        this.phone = data.getString("phone");
        this.reception = makeReceptionFromComment(data.getString("comment"));
    }
    private String makeReceptionFromComment(String comment){
        if( comment.contains("Код")){
            comment = comment.substring(18);
        }
        return comment;
    }
    private String makePrettyAddress(String address){
        if (address.charAt(0) == ','){
            address = address.substring(1);
        }
        while(address.contains(",,")){
            address = address.replace(",,",",");
        }
        if (address.charAt(address.length()-1) == ','){
            address = address.substring(0,address.length()-1);
        }
        return address;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReception() {
        return reception;
    }

    public void setReception(String reception) {
        this.reception = reception;
    }
}
