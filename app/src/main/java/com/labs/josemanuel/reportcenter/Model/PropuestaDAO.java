package com.labs.josemanuel.reportcenter.Model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Miguel on 21-09-16.
 */

public class PropuestaDAO {
    private String title;
    private Map uid = new HashMap();
//            ,uid,body,field_proposal_aal1,field_proposal_aal2,field_proposal_category,field_proposal_country,field_proposal_formatted_address
//            ,field_proposal_images,field_proposal_locality,field_proposal_location,field_proposal_postal_code,field_proposal_route_name;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Map getUid() {
        return uid;
    }

    public void setUidValue(String key,String value) {
        this.uid.put(key,value);
    }
}
