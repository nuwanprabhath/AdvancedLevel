package advancedlevel;
// Generated Mar 4, 2014 6:30:25 PM by Hibernate Tools 3.2.1.GA



/**
 * District generated by hbm2java
 */
public class District  implements java.io.Serializable {


     private String districtId;
     private String districtName;

    public District() {
    }

    public District(String districtId, String districtName) {
       this.districtId = districtId;
       this.districtName = districtName;
    }
   
    public String getDistrictId() {
        return this.districtId;
    }
    
    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }
    public String getDistrictName() {
        return this.districtName;
    }
    
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }




}

