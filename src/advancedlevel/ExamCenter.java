package advancedlevel;
// Generated Mar 4, 2014 10:34:11 PM by Hibernate Tools 3.2.1.GA



/**
 * ExamCenter generated by hbm2java
 */
public class ExamCenter  implements java.io.Serializable {


     private String examCenterId;
     private String examCenterName;
     private String examCenterAddress;

    public ExamCenter() {
    }

    public ExamCenter(String examCenterId, String examCenterName, String examCenterAddress) {
       this.examCenterId = examCenterId;
       this.examCenterName = examCenterName;
       this.examCenterAddress = examCenterAddress;
    }
   
    public String getExamCenterId() {
        return this.examCenterId;
    }
    
    public void setExamCenterId(String examCenterId) {
        this.examCenterId = examCenterId;
    }
    public String getExamCenterName() {
        return this.examCenterName;
    }
    
    public void setExamCenterName(String examCenterName) {
        this.examCenterName = examCenterName;
    }
    public String getExamCenterAddress() {
        return this.examCenterAddress;
    }
    
    public void setExamCenterAddress(String examCenterAddress) {
        this.examCenterAddress = examCenterAddress;
    }




}


