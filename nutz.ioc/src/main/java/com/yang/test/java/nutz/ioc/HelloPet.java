package com.yang.test.java.nutz.ioc;

import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;

public class HelloPet {

    public static void main(String[] args) {
        Ioc ioc = new NutIoc(new JsonLoader("com/yang/test/java/nutz/ioc/pets.js"));
        Pet pet = ioc.get(Pet.class, "xb");
        System.out.println(pet.getOneField());
       
        ioc.depose(); // 关闭ioc容器.
    }

}