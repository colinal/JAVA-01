package cn.colinal.homework_05;

public class XmlBeanAutowiredEntity3 {
    private String entityName;
    public XmlBeanAutowiredEntity3(){
        System.out.println("entity 3 is created! ");
    }
    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
}
