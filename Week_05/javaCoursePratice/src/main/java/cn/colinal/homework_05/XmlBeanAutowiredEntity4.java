package cn.colinal.homework_05;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class XmlBeanAutowiredEntity4 {
    private String entityName;
    public XmlBeanAutowiredEntity4(){
        System.out.println("entity 4 is created! ");
    }


    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
}
