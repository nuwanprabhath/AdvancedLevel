package advancedlevel;
// Generated Mar 4, 2014 11:26:40 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * Field generated by hbm2java
 */
public class Field  implements java.io.Serializable {


     private String fieldName;
     private String description;
     private Date startedDate;

    public Field() {
    }

	
    public Field(String fieldName, Date startedDate) {
        this.fieldName = fieldName;
        this.startedDate = startedDate;
    }
    public Field(String fieldName, String description, Date startedDate) {
       this.fieldName = fieldName;
       this.description = description;
       this.startedDate = startedDate;
    }
   
    public String getFieldName() {
        return this.fieldName;
    }
    
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getStartedDate() {
        return this.startedDate;
    }
    
    public void setStartedDate(Date startedDate) {
        this.startedDate = startedDate;
    }




}

