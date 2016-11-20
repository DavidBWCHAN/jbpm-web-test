package test.handler;

import java.util.Collection;
import java.util.Date;

import javax.xml.registry.JAXRException;
import javax.xml.registry.LifeCycleManager;
import javax.xml.registry.infomodel.Association;
import javax.xml.registry.infomodel.Classification;
import javax.xml.registry.infomodel.Concept;
import javax.xml.registry.infomodel.ExternalIdentifier;
import javax.xml.registry.infomodel.ExternalLink;
import javax.xml.registry.infomodel.InternationalString;
import javax.xml.registry.infomodel.Key;
import javax.xml.registry.infomodel.Organization;
import javax.xml.registry.infomodel.ServiceBinding;
import javax.xml.registry.infomodel.Slot;

import org.jbpm.mail.AddressResolver;
import org.jbpm.svc.Service;
import org.jbpm.svc.ServiceFactory;

public class MailAddressResolver implements AddressResolver, ServiceFactory,
		Service, javax.xml.registry.infomodel.Service {

	public Date getExpiration() throws JAXRException {
		// TODO Auto-generated method stub
		return null;
	}

	public int getStability() throws JAXRException {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getStatus() throws JAXRException {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setExpiration(Date arg0) throws JAXRException {
		// TODO Auto-generated method stub

	}

	public void setStability(int arg0) throws JAXRException {
		// TODO Auto-generated method stub

	}

	public void addAssociation(Association arg0) throws JAXRException {
		// TODO Auto-generated method stub

	}

	public void addAssociations(Collection arg0) throws JAXRException {
		// TODO Auto-generated method stub

	}

	public void addClassification(Classification arg0) throws JAXRException {
		// TODO Auto-generated method stub

	}

	public void addClassifications(Collection arg0) throws JAXRException {
		// TODO Auto-generated method stub

	}

	public void addExternalIdentifier(ExternalIdentifier arg0)
			throws JAXRException {
		// TODO Auto-generated method stub

	}

	public void addExternalIdentifiers(Collection arg0) throws JAXRException {
		// TODO Auto-generated method stub

	}

	public void addExternalLink(ExternalLink arg0) throws JAXRException {
		// TODO Auto-generated method stub

	}

	public void addExternalLinks(Collection arg0) throws JAXRException {
		// TODO Auto-generated method stub

	}

	public Collection getAssociatedObjects() throws JAXRException {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection getAssociations() throws JAXRException {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection getAuditTrail() throws JAXRException {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection getClassifications() throws JAXRException {
		// TODO Auto-generated method stub
		return null;
	}

	public InternationalString getDescription() throws JAXRException {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection getExternalIdentifiers() throws JAXRException {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection getExternalLinks() throws JAXRException {
		// TODO Auto-generated method stub
		return null;
	}

	public Key getKey() throws JAXRException {
		// TODO Auto-generated method stub
		return null;
	}

	public LifeCycleManager getLifeCycleManager() throws JAXRException {
		// TODO Auto-generated method stub
		return null;
	}

	public InternationalString getName() throws JAXRException {
		// TODO Auto-generated method stub
		return null;
	}

	public Concept getObjectType() throws JAXRException {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection getRegistryPackages() throws JAXRException {
		// TODO Auto-generated method stub
		return null;
	}

	public Organization getSubmittingOrganization() throws JAXRException {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeAssociation(Association arg0) throws JAXRException {
		// TODO Auto-generated method stub

	}

	public void removeAssociations(Collection arg0) throws JAXRException {
		// TODO Auto-generated method stub

	}

	public void removeClassification(Classification arg0) throws JAXRException {
		// TODO Auto-generated method stub

	}

	public void removeClassifications(Collection arg0) throws JAXRException {
		// TODO Auto-generated method stub

	}

	public void removeExternalIdentifier(ExternalIdentifier arg0)
			throws JAXRException {
		// TODO Auto-generated method stub

	}

	public void removeExternalIdentifiers(Collection arg0) throws JAXRException {
		// TODO Auto-generated method stub

	}

	public void removeExternalLink(ExternalLink arg0) throws JAXRException {
		// TODO Auto-generated method stub

	}

	public void removeExternalLinks(Collection arg0) throws JAXRException {
		// TODO Auto-generated method stub

	}

	public void setAssociations(Collection arg0) throws JAXRException {
		// TODO Auto-generated method stub

	}

	public void setClassifications(Collection arg0) throws JAXRException {
		// TODO Auto-generated method stub

	}

	public void setDescription(InternationalString arg0) throws JAXRException {
		// TODO Auto-generated method stub

	}

	public void setExternalIdentifiers(Collection arg0) throws JAXRException {
		// TODO Auto-generated method stub

	}

	public void setExternalLinks(Collection arg0) throws JAXRException {
		// TODO Auto-generated method stub

	}

	public void setKey(Key arg0) throws JAXRException {
		// TODO Auto-generated method stub

	}

	public void setName(InternationalString arg0) throws JAXRException {
		// TODO Auto-generated method stub

	}

	public String toXML() throws JAXRException {
		// TODO Auto-generated method stub
		return null;
	}

	public void addSlot(Slot arg0) throws JAXRException {
		// TODO Auto-generated method stub

	}

	public void addSlots(Collection arg0) throws JAXRException {
		// TODO Auto-generated method stub

	}

	public Slot getSlot(String arg0) throws JAXRException {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection getSlots() throws JAXRException {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeSlot(String arg0) throws JAXRException {
		// TODO Auto-generated method stub

	}

	public void removeSlots(Collection arg0) throws JAXRException {
		// TODO Auto-generated method stub

	}

	public int getMajorVersion() throws JAXRException {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getMinorVersion() throws JAXRException {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getUserVersion() throws JAXRException {
		// TODO Auto-generated method stub
		return null;
	}

	public void setMajorVersion(int arg0) throws JAXRException {
		// TODO Auto-generated method stub

	}

	public void setMinorVersion(int arg0) throws JAXRException {
		// TODO Auto-generated method stub

	}

	public void setUserVersion(String arg0) throws JAXRException {
		// TODO Auto-generated method stub

	}

	public void addServiceBinding(ServiceBinding arg0) throws JAXRException {
		// TODO Auto-generated method stub

	}

	public void addServiceBindings(Collection arg0) throws JAXRException {
		// TODO Auto-generated method stub

	}

	public Organization getProvidingOrganization() throws JAXRException {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection getServiceBindings() throws JAXRException {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeServiceBinding(ServiceBinding arg0) throws JAXRException {
		// TODO Auto-generated method stub

	}

	public void removeServiceBindings(Collection arg0) throws JAXRException {
		// TODO Auto-generated method stub

	}

	public void setProvidingOrganization(Organization arg0)
			throws JAXRException {
		// TODO Auto-generated method stub

	}

	public void close() {
		// TODO Auto-generated method stub

	}

	public Service openService() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object resolveAddress(String actorId) {
		// TODO Auto-generated method stub
		if(actorId.equals("manager1")){
			return "995626566@qq.com";
		}else{
			return "946014574@qq.com";
		}
	}

}
