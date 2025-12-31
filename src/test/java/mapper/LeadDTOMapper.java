package mapper;

import models.LeadDTO;

import java.util.Hashtable;

import static java.lang.Integer.parseInt;

public class LeadDTOMapper {
    public static LeadDTO mapToLeadDTO(Hashtable<String, String> data) {
        LeadDTO lead = new LeadDTO();
        lead.setStatus(data.get("status"));
        lead.setSource(data.get("source"));
        lead.setAssigned(data.get("assigned"));
        lead.setTag(data.get("tag"));
        lead.setLeadName(data.get("leadName"));
        lead.setAddress(data.get("address"));
        lead.setPosition(data.get("position"));
        lead.setCity(data.get("city"));
        lead.setEmailAddress(data.get("emailAddress"));
        lead.setState(data.get("state"));
        lead.setWebsite(data.get("website"));
        lead.setCountry(data.get("country"));
        lead.setPhone(data.get("phone"));
        lead.setZipCode(data.get("zipCode"));
        lead.setLeadValue(data.get("leadValue"));
        lead.setLanguage(data.get("language"));
        lead.setCompany(data.get("company"));
        lead.setDescription(data.get("description"));
        lead.setDateContacted(data.get("dateContacted"));

        lead.setFlag(parseInt(data.get("flag")));
        lead.setFlagEdit(parseInt(data.get("flagEdit")));

        return lead;
    }
}
