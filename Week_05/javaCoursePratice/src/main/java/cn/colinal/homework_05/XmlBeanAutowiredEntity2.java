package cn.colinal.homework_05;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class XmlBeanAutowiredEntity2 {
    @Value("entityTwo")
    private String entityName;
    public XmlBeanAutowiredEntity2(){
        System.out.println("entity 2 is created! ");
    }


    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
}
