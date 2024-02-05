package com.blog.blogingapp.Execptions;

public class ResourceNotFoundExecption extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private long fieldValue;
    public ResourceNotFoundExecption(String resourceName , String fieldName , long fieldValue){
        super(String.format("%s not found with %s : %s", resourceName,fieldName,fieldValue));
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.resourceName = resourceName;
    }
    public String getResourceName() {
        return resourceName;
    }
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
    public String getFieldName() {
        return fieldName;
    }
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    public long getFieldValue() {
        return fieldValue;
    }
    public void setFieldValue(long fieldValue) {
        this.fieldValue = fieldValue;
    }
    
}
