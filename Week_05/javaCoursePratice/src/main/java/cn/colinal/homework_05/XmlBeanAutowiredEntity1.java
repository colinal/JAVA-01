package cn.colinal.homework_05;

public class XmlBeanAutowiredEntity1 {
    private String entityName;
    public XmlBeanAutowiredEntity1(){
        System.out.println("entity 1 is created! ");
    }
    public String getEntityName() {
        return entityName;
    }
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
}
