package advancedlevel;
// Generated Mar 6, 2014 4:19:40 PM by Hibernate Tools 3.2.1.GA



/**
 * TakesId generated by hbm2java
 */
public class TakesId  implements java.io.Serializable {


     private String subjectId;
     private String index;

    public TakesId() {
    }

    public TakesId(String subjectId, String index) {
       this.subjectId = subjectId;
       this.index = index;
    }
   
    public String getSubjectId() {
        return this.subjectId;
    }
    
    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }
    public String getIndex() {
        return this.index;
    }
    
    public void setIndex(String index) {
        this.index = index;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof TakesId) ) return false;
		 TakesId castOther = ( TakesId ) other; 
         
		 return ( (this.getSubjectId()==castOther.getSubjectId()) || ( this.getSubjectId()!=null && castOther.getSubjectId()!=null && this.getSubjectId().equals(castOther.getSubjectId()) ) )
 && ( (this.getIndex()==castOther.getIndex()) || ( this.getIndex()!=null && castOther.getIndex()!=null && this.getIndex().equals(castOther.getIndex()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getSubjectId() == null ? 0 : this.getSubjectId().hashCode() );
         result = 37 * result + ( getIndex() == null ? 0 : this.getIndex().hashCode() );
         return result;
   }   


}


