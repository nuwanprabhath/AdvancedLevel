package advancedlevel;
// Generated Mar 7, 2014 9:11:12 AM by Hibernate Tools 3.2.1.GA



/**
 * Takes generated by hbm2java
 */
public class Takes  implements java.io.Serializable {


     private TakesId id;
     private Double marks;
     private String grade;
     private Boolean presence;
     private Boolean suspended;
     private Double ZScore;

    public Takes() {
    }

	
    public Takes(TakesId id) {
        this.id = id;
    }
    public Takes(TakesId id, Double marks, String grade, Boolean presence, Boolean suspended, Double ZScore) {
       this.id = id;
       this.marks = marks;
       this.grade = grade;
       this.presence = presence;
       this.suspended = suspended;
       this.ZScore = ZScore;
    }
   
    public TakesId getId() {
        return this.id;
    }
    
    public void setId(TakesId id) {
        this.id = id;
    }
    public Double getMarks() {
        return this.marks;
    }
    
    public void setMarks(Double marks) {
        this.marks = marks;
    }
    public String getGrade() {
        return this.grade;
    }
    
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public Boolean getPresence() {
        return this.presence;
    }
    
    public void setPresence(Boolean presence) {
        this.presence = presence;
    }
    public Boolean getSuspended() {
        return this.suspended;
    }
    
    public void setSuspended(Boolean suspended) {
        this.suspended = suspended;
    }
    public Double getZScore() {
        return this.ZScore;
    }
    
    public void setZScore(Double ZScore) {
        this.ZScore = ZScore;
    }




}

