package org.example.enums;

public enum SexEnum {
    MALE(0,"男生"),FEMALE(1,"女生");
    private int id;
    private String name;

    SexEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static String getNameById(int id){
        SexEnum[] sexEnums = SexEnum.values();
        for (SexEnum sexEnum : sexEnums) {
            if (id == sexEnum.id){
                return sexEnum.name;
            }
        }
        throw new RuntimeException("没有该种性别");
    }

    public static int getIdByName(String sex){
        if (sex==null||"".equals(sex)){
            throw new RuntimeException("sex不能为空");
        }
        SexEnum[] sexEnums = SexEnum.values();
        for (SexEnum sexEnum : sexEnums) {
            if (sexEnum.name.equals(sex)){
                return sexEnum.id;
            }
        }
        throw new RuntimeException("没有该种性别");
    }
}
