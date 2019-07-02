/*
 * The following source code ("Code") may only be used in accordance with the terms
 * and conditions of the license agreement you have with IBM Corporation. The Code 
 * is provided to you on an "AS IS" basis, without warranty of any kind.  
 * SUBJECT TO ANY STATUTORY WARRANTIES WHICH CAN NOT BE EXCLUDED, IBM MAKES NO 
 * WARRANTIES OR CONDITIONS EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED 
 * TO, THE IMPLIED WARRANTIES OR CONDITIONS OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE, AND NON-INFRINGEMENT, REGARDING THE CODE. IN NO EVENT WILL 
 * IBM BE LIABLE TO YOU OR ANY PARTY FOR ANY DIRECT, INDIRECT, SPECIAL OR OTHER 
 * CONSEQUENTIAL DAMAGES FOR ANY USE OF THE CODE, INCLUDING, WITHOUT LIMITATION, 
 * LOSS OF, OR DAMAGE TO, DATA, OR LOST PROFITS, BUSINESS, REVENUE, GOODWILL, OR 
 * ANTICIPATED SAVINGS, EVEN IF IBM HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH 
 * DAMAGES. SOME JURISDICTIONS DO NOT ALLOW THE EXCLUSION OR LIMITATION OF 
 * INCIDENTAL OR CONSEQUENTIAL DAMAGES, SO THE ABOVE LIMITATION OR EXCLUSION MAY 
 * NOT APPLY TO YOU.
 */

/*
 * IBM-MDMWB-1.0-[d5a087032f905e90b83fc05244c82c12]
 */

package com.ibm.idm.bp.compositeTxn;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.dwl.base.DWLControl;
import com.dwl.base.DWLResponse;
import com.dwl.base.IDWLErrorMessage;
import com.dwl.base.constant.DWLControlKeys;
import com.dwl.base.error.DWLError;
import com.dwl.base.error.DWLErrorCode;
import com.dwl.base.error.DWLStatus;
import com.dwl.base.requestHandler.DWLTransactionInquiry;
import com.dwl.base.util.DWLClassFactory;
import com.dwl.tcrm.coreParty.component.TCRMOrganizationBObj;
import com.dwl.tcrm.coreParty.component.TCRMPartyAddressBObj;
import com.dwl.tcrm.coreParty.component.TCRMPartyContactMethodBObj;
import com.dwl.tcrm.coreParty.component.TCRMPartyIdentificationBObj;
import com.dwl.tcrm.coreParty.component.TCRMPartyMacroRoleBObj;
import com.dwl.tcrm.coreParty.component.TCRMPartyRelationshipRoleBObj;
import com.dwl.tcrm.coreParty.component.TCRMPersonBObj;
import com.dwl.tcrm.coreParty.component.TCRMPersonSearchBObj;
import com.dwl.tcrm.coreParty.component.TCRMPersonSearchResultBObj;
import com.dwl.tcrm.coreParty.constant.TCRMCorePropertyKeys;
import com.dwl.tcrm.coreParty.interfaces.IOrganization;
import com.dwl.tcrm.coreParty.interfaces.IPartyBusinessServices;
import com.dwl.tcrm.coreParty.interfaces.IPerson;
import com.dwl.tcrm.utilities.StringUtils;
import com.dwl.tcrm.utilities.TCRMClassFactory;
import com.dwl.unifi.tx.exception.BusinessProxyException;
import com.ibm.idm.addition.component.XBusinessJobFunctionBObj;
import com.ibm.idm.addition.component.XJobTitleBObj;
import com.ibm.idm.addition.constant.IDMDataAdditionPropertyKeys;
import com.ibm.idm.addition.interfaces.XJobTitle;
import com.ibm.idm.bp.IDMPersistenceBaseCompositeBP;
import com.ibm.idm.bp.component.IDMCompositePartyBObj;
import com.ibm.idm.bp.constant.IDMCompositeServicesComponentID;
import com.ibm.idm.bp.constant.IDMCompositeServicesErrorReasonCode;
import com.ibm.idm.common.IDMCompositeTxnConstant;
import com.ibm.idm.mdm.extension.component.XAddressBObjExt;
import com.ibm.idm.mdm.extension.component.XAdminContEquivBObjExt;
import com.ibm.idm.mdm.extension.component.XContactMethodBObjExt;
import com.ibm.idm.mdm.extension.component.XEntityPrivPrefBObjExt;
import com.ibm.idm.mdm.extension.component.XOrganizationBObjExt;
import com.ibm.idm.mdm.extension.component.XOrganizationNameBObjExt;
import com.ibm.idm.mdm.extension.component.XPartyAddressBObjExt;
import com.ibm.idm.mdm.extension.component.XPartyAddressPrivPrefBObjExt;
import com.ibm.idm.mdm.extension.component.XPartyContactMethodPrivPrefBObjExt;
import com.ibm.idm.mdm.extension.component.XPartyRelationshipBObjExt;
import com.ibm.idm.mdm.extension.component.XPersonBObjExt;
import com.ibm.idm.mdm.extension.component.XPersonNameBObjExt;
import com.ibm.idm.mdm.extension.utilities.CommonUtils;
import com.ibm.idm.mdm.extension.utilities.IDMPartyUtils;
import com.ibm.idm.mdm.extension.utilities.IDMUtil;
import com.ibm.mdm.common.codetype.interfaces.CodeTypeComponentHelper;
import com.ibm.mdm.common.codetype.obj.CodeTypeBObj;
import com.ibm.mdm.common.util.PropertyManager;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * 
 * @generated NOT
 */
public class IDMGetPartyCompositeTxnBP  extends IDMPersistenceBaseCompositeBP {

    /**
     * @generated NOT
     **/
    private IDWLErrorMessage errHandler;
	private DWLControl control;
    private TCRMOrganizationBObj responseOrg;
    private TCRMPersonBObj responsePerson;
    protected static List excludedTiers = new Vector();
    //commnet this for task 1663728. as all active contEquiv should be returned
    //protected static List PubAdminContequiv = new Vector();
    protected static List PubIdentifier = new Vector();  
    
    /**
      * <!-- begin-user-doc -->
      * <!-- end-user-doc -->
      * @generated 
      */
     private final static com.dwl.base.logging.IDWLLogger logger = com.dwl.base.logging.DWLLoggerManager.getLogger(IDMGetPartyCompositeTxnBP.class);
    /**
     * @generated NOT
     **/
    public IDMGetPartyCompositeTxnBP() {
        super();
        errHandler = TCRMClassFactory.getErrorHandler();
    }
    /**
     * @generated NOT
     **/
    public Object execute(Object inputObj) throws BusinessProxyException {
        logger.finest("ENTER Object execute(Object inputObj)");
	
        DWLResponse response = null;
        DWLTransactionInquiry inputTxnObj = (DWLTransactionInquiry) inputObj;
        control = inputTxnObj.getTxnControl();
        
        DWLStatus status = null;
        XPersonBObjExt personInquired = null;

	        try {
	        // Extract the request parameters. These will appear in the order supplied.
	        Vector parameters = inputTxnObj.getStringParameters();
	        
	        // Ensure that the correct number of parameters are present.
	        // ONLY EID is accepted currently
	        if (parameters == null || parameters.size() < 2) {
	        	//No parameter provided, return an error
	        	logger.finest("EID not provided.");
				DWLError error = CommonUtils.createDWLError(errHandler, IDMCompositeServicesComponentID.IDMGET_PARTY_BUSINESS_PROXY, 
						DWLErrorCode.READ_RECORD_ERROR,IDMCompositeServicesErrorReasonCode.IDMGETPARTY_IDNUM_AND_IDTYPE_ARE_REQUIRED, control);
				status = new DWLStatus();
				status.addError(error);
				status.setStatus(DWLStatus.FATAL);
				response = new DWLResponse();
				response.setStatus(status);
		        
		        return response;
	        	
	        } else {  //there are two parms, but not an EID
	            if ( parameters.size() == 2 && !StringUtils.compareWithTrim((String)parameters.get(1), IDMCompositeTxnConstant.ID_TYPE_EID)) {
	
	            	//return an error
	            	logger.finest("There is ID type, but not EID type.");
	    			DWLError error = CommonUtils.createDWLError(errHandler, IDMCompositeServicesComponentID.IDMGET_PARTY_BUSINESS_PROXY, 
	    					DWLErrorCode.READ_RECORD_ERROR,IDMCompositeServicesErrorReasonCode.IDMGETPARTY_EID_IDTYPE_IS_REQUIRED, control);
	    			status = new DWLStatus();
	    			status.addError(error);
	    			status.setStatus(DWLStatus.FATAL);
	    			response = new DWLResponse();
	    			response.setStatus(status);
	    	        
	    	        return response;
	            	
	            }
	        }
	        
	        
	        // Correct number of parms were received, search for the party
	        logger.finest(parameters.size() + " Parms received....." + parameters.get(0));
	        
	        //Using the giving EID to search for the person
	        personInquired = searchPersonByIdentification((String)parameters.get(1), (String)parameters.get(0), control );
	        if (personInquired == null) {
	        	logger.finest("EID provided, but can't find an active person party, reject the request");
				DWLError error = CommonUtils.createDWLError(errHandler, IDMCompositeServicesComponentID.IDMGET_PARTY_BUSINESS_PROXY, 
						DWLErrorCode.READ_RECORD_ERROR,IDMCompositeServicesErrorReasonCode.IDMGETPARTY_NOT_ACTIVE_PERSON_CONTACT, control);
				status = new DWLStatus();
				status.addError(error);
				status.setStatus(DWLStatus.FATAL);
				response = new DWLResponse();
				response.setStatus(status);
		        
		        return response;
	      	
	        }

	    	//get the excluded tier values from the properties file 
			synchronized(excludedTiers){
		    	if(excludedTiers.isEmpty()){
		    		initExcluedeTiers();    		
		    	}
	    	}
			
			//get the Admin Contequivs that is allowed to be published from the properties file 
			//comment this for task 1663728 as all contEquiv should be returned
//			synchronized(PubAdminContequiv){
//		    	if(PubAdminContequiv.isEmpty()){
//		    		initPubAdminContequiv();    		
//		    	}
//	    	}
			
    		//get the Identifier type that is allowed to be published from the properties file
    		synchronized(PubIdentifier){
    	    	if(PubIdentifier.isEmpty()){
    	    		initPubIdentifier();    		
    	    	}
        	}
			
	        //Now, Add the logic to retrieve all bobjs for the party according to the IDMGetParty business rules
	        logger.finest("responsePerson=" + responsePerson);
	        processPersonAndOrg(personInquired);
	        
	        if (responsePerson != null){
	       		String notGranFields = PropertyManager.getProperty("NotGranFields");  
	       		String notReturnGranPP = PropertyManager.getProperty("NotReturnGranPP");

	       		Vector removedAddressPrivPrefBObjList = new Vector(); 
	            for (Object obj : responsePerson.getItemsTCRMPartyAddressBObj()) {
	  			  	TCRMPartyAddressBObj addressObj = (TCRMPartyAddressBObj) obj;
	  			  	for (Object privPrefObj : addressObj.getItemsTCRMPartyAddressPrivPrefBObj()) {
	  						XPartyAddressPrivPrefBObjExt privPref = (XPartyAddressPrivPrefBObjExt) privPrefObj;
	  						privPref.setXGranPrefCd(privPref.getXGranPrefCd()); //for set GranPrefValue 
	  						if (IDMUtil.isGranPrivPref(privPref) && "Y".equals(notReturnGranPP)){
	  							removedAddressPrivPrefBObjList.add(privPrefObj);
	  						}else{
		  						if (notGranFields != null && notGranFields.indexOf("IDMGetParty") != -1){
			  						privPref.setXGranPrefCd("IDMGetParty"); //due to XGranPrefCd have default value,but IDMGetParty need return null for sales connect XGranPrefCd-Task 1804818
			  						privPref.setXCmpnCd(null);
			  						privPref.setXSrcSysTransId(null);
		  						}
	  						}	  						
	  			  	}
	  			  addressObj.getItemsTCRMPartyAddressPrivPrefBObj().removeAll(removedAddressPrivPrefBObjList);
	            }
	            	            
	            
	       		Vector removedContactMethodPrivPrefBObjList = new Vector(); 
	            for (Object obj : responsePerson.getItemsTCRMPartyContactMethodBObj()) {
	            	TCRMPartyContactMethodBObj contactObj = (TCRMPartyContactMethodBObj) obj;
	  				for (Object privPrefObj : contactObj.getItemsTCRMPartyContactMethodPrivPrefBObj()) {
	  					XPartyContactMethodPrivPrefBObjExt privPref = (XPartyContactMethodPrivPrefBObjExt) privPrefObj;
	  					privPref.setXGranPrefCd(privPref.getXGranPrefCd()); //for set GranPrefValue 
	  					if (IDMUtil.isGranPrivPref(privPref)  && "Y".equals(notReturnGranPP)){
  	  						removedContactMethodPrivPrefBObjList.add(privPrefObj);
  						}else{
	  						if (notGranFields != null && notGranFields.indexOf("IDMGetParty") != -1){
		  						privPref.setXGranPrefCd("IDMGetParty"); //due to XGranPrefCd have default value,but IDMGetParty need return null for sales connect XGranPrefCd-Task 1804818
		  						privPref.setXCmpnCd(null);
		  						privPref.setXSrcSysTransId(null);
	  						}
  						}
	  				}
	  				contactObj.getItemsTCRMPartyContactMethodPrivPrefBObj().removeAll(removedContactMethodPrivPrefBObjList);
	            }	
	            
	            
	            Vector removedPrivPrefBObjList = new Vector(); 
	            for (Object obj : responsePerson.getItemsTCRMPartyPrivPrefBObj()) {
	            	XEntityPrivPrefBObjExt privPref = (XEntityPrivPrefBObjExt) obj;
	            	privPref.setXGranPrefCd(privPref.getXGranPrefCd()); //for set GranPrefValue 
	            	if (IDMUtil.isGranPrivPref(privPref)  && "Y".equals(notReturnGranPP)){
	            		removedPrivPrefBObjList.add(obj);
  					}else{
  						if (notGranFields != null && notGranFields.indexOf("IDMGetParty") != -1){
	  						privPref.setXGranPrefCd("IDMGetParty"); //due to XGranPrefCd have default value,but IDMGetParty need return null for sales connect XGranPrefCd-Task 1804818
	  						privPref.setXCmpnCd(null);
	  						privPref.setXSrcSysTransId(null);
  						}	
  					}
	            }	
	            responsePerson.getItemsTCRMPartyPrivPrefBObj().removeAll(removedPrivPrefBObjList);
	        }
        
        } catch(Exception e){
            logger.error("Error in IDMGetParty." + e.getMessage());
            DWLError error = errHandler.getErrorMessage(IDMCompositeServicesComponentID.IDMGET_PARTY_BUSINESS_PROXY,
                    "READERR",
                    IDMCompositeServicesErrorReasonCode.IDMGETPARTY_FAILED, control, new String[0]);
            throw new BusinessProxyException(error.getErrorMessage());
       }
        	        
        
        //Build the response BObj.
        IDMCompositePartyBObj responseBObj = new IDMCompositePartyBObj();
        responseBObj.setControl(control);
        if(responsePerson != null){            
        	responseBObj.setTCRMPersonBObj(responsePerson);
        }
        if(responseOrg != null){
        	responseBObj.setTCRMOrganizationBObj(responseOrg);   
        }
        

        // Construct the response object.
        DWLStatus outputStatus = new DWLStatus();
        outputStatus.setStatus(DWLStatus.SUCCESS);
        response = new DWLResponse();
        response.setStatus(outputStatus);
        response.setData(responseBObj);
        logger.finest("RETURN Object execute(Object inputObj)");
        return response;
    }
    
  
    
    /**
     * Search Person by Identification
     * @param IdentificationTp
     * @param identificationNumber
     * @return
     * @throws Exception
     */
    private XPersonBObjExt searchPersonByIdentification(String identificationType, String identificationNumber, DWLControl dwlControl ) 
    		throws BusinessProxyException{
 	   logger.finest("ENTER searchPersonByIdentification()...IDType=" + identificationType + " IDNumber=" + identificationNumber);
        DWLResponse dwlResponse = null;
        XPersonBObjExt xPersonBObjExt = null;
        
        TCRMPersonSearchBObj tcrmPersonSearchBObj = new TCRMPersonSearchBObj();
        tcrmPersonSearchBObj.setIdentificationType(identificationType);
        tcrmPersonSearchBObj.setIdentificationNum(identificationNumber);
        tcrmPersonSearchBObj.setPartyFilter("ACTIVE");
        tcrmPersonSearchBObj.setControl(dwlControl);
        
        dwlResponse = processBaseSearchAction(IDMCompositeTxnConstant.TXN_SEARCHPERSON, tcrmPersonSearchBObj, dwlControl);
        if (dwlResponse != null && dwlResponse.getStatus().getStatus() != DWLStatus.FATAL) { 
     	Vector<TCRMPersonSearchResultBObj> VectcrmPersonSearchResultBObj =  (Vector<TCRMPersonSearchResultBObj>) dwlResponse.getData(); 
        	
        	logger.finest("Done searching..."); 
        	if(VectcrmPersonSearchResultBObj != null && VectcrmPersonSearchResultBObj.size() > 0){
        		    TCRMPersonSearchBObj tcrmPersonSearchResultBObj = VectcrmPersonSearchResultBObj.firstElement();
 				Vector params = new Vector();
 				params.add(tcrmPersonSearchResultBObj.getPartyId());
 				params.add(IDMCompositeTxnConstant.INQUIRY_LEVEL_0);
 				 
 				dwlResponse = invokeBaseInquiryMethod(IDMCompositeTxnConstant.TXN_GETPERSON, params, dwlControl);
 				if (dwlResponse != null && dwlResponse.getStatus().getStatus() != DWLStatus.FATAL) {                    
 				    xPersonBObjExt = (XPersonBObjExt) dwlResponse.getData();     
 				    logger.finest("Person found ->..partyID=" + xPersonBObjExt.getPartyId() );
 				} 
        	}             
        }
 	   logger.finest("Exiting searchPersonByIdentification().");   
        return xPersonBObjExt;
    }   
    
    /**
     * initExcluedeTiers
     * @throws Exception
     */
    private void initExcluedeTiers() throws Exception{
    	String tiers = PropertyManager.getProperty("ExcludedTiers");
    	if(tiers != null){			
    		String[] splittedTiers = tiers.split(",");
    		
    		//splittedExcludedTiers.
    		for(int i = 0; i < splittedTiers.length; i++)
    			excludedTiers.add(splittedTiers[i]);
    	}
    	
    }

    /**
     * initPubAdminContequiv
     * @throws Exception
     */
    //comment this for task 1663728 as all contEquiv should be returned
//    private void initPubAdminContequiv() throws Exception{
//    	String adminContequivs = PropertyManager.getProperty("AdminContequivToBePublish");
//    	if(adminContequivs != null){			
//    		String[] splittedACKey = adminContequivs.split(",");
//    		
//    		//splittedExcludedTiers.
//    		for(int i = 0; i < splittedACKey.length; i++)
//    			PubAdminContequiv.add(splittedACKey[i]);
//    	}
//    	
//    }
    
    /**
     * Check if the adminConteqiv is in the list to be published.
     * @param adminConteqiv
     * @return
     */
    //comment this for task 1663728 as all contEquiv should be returned
//    private boolean isAdminContequivPublish(String adminConteqiv){    	
//
//    	for(int i = 0; i < PubAdminContequiv.size(); i++){
//    		String pubAdminConteqiv = (String)PubAdminContequiv.get(i);
//    		if(StringUtils.compareWithTrim(pubAdminConteqiv, adminConteqiv))
//    			return true;    		
//    	}
//
//    	return false;
//    }
    
    /**
     * initPubIdentifier
     * @throws Exception
     */
    private void initPubIdentifier() throws Exception{
    	String idTypes = PropertyManager.getProperty("IdentifierToBePublish");
    	if(idTypes != null){			
    		String[] splittedACKey = idTypes.split(",");
    		
    		for(int i = 0; i < splittedACKey.length; i++)
    			PubIdentifier.add(splittedACKey[i]);
    	}
    	
    }

    /**
     * Check if the identifier is in the list to be published.
     * @param identifierType
     * @return
     */
    private boolean isIdentifierPublish(String identifierType){    	

    	for(int i = 0; i < PubIdentifier.size(); i++){
    		String pubID = (String)PubIdentifier.get(i);
    		if(StringUtils.compareWithTrim(pubID, identifierType))
    			return true;    		
    	}

    	return false;
    }  
    
    
    
    
    
    /**
     * processPersonAndOrg
     * @param partyId
     * @return
     * @throws Exception
     */
    private void processPersonAndOrg(XPersonBObjExt personFound) throws BusinessProxyException{
 	   logger.finest("ENTER processPersonAndOrg() for party=" + personFound.getPartyId());
        DWLResponse dwlResponse = null;
        XPersonBObjExt xPersonBObjExt = personFound;
    	String orgPartyId = null;
    	String partyRelId = null;
    	Vector vecXJobTitle = null;
    	Vector vecXBusinessJobFunction = null;
    	XOrganizationBObjExt xOrganizationBObjExt = null;
        
        try {
	        IPerson partyComp = (IPerson)TCRMClassFactory.getTCRMComponent("party_component");
	        IOrganization orgPartyComp = (IOrganization)TCRMClassFactory.getTCRMComponent("party_component");
	        
           //Check if there is any org relationship - get the winning org relationship
			XPartyRelationshipBObjExt partyRelationship = getPartyRelationship(personFound.getPartyId(),personFound);
			if (partyRelationship != null) {
				orgPartyId = partyRelationship.getRelationshipToPartyId();
	            partyRelId = partyRelationship.getPartyRelationshipIdPK();
			}
			
			//populate organization
			if (orgPartyId != null)
				xOrganizationBObjExt = (XOrganizationBObjExt) orgPartyComp.getParty(orgPartyId, "0", control);	
        
			//------ Process Person party ---------
			
            //Taking care of the country ISO_Code
            if (StringUtils.isNonBlank(xPersonBObjExt.getXPrimaryCountryType()))
            	xPersonBObjExt.setXPrimaryCountryValue(IDMPartyUtils.getCountryISOCode(xPersonBObjExt.getXPrimaryCountryType()));

          	//Populate party macro roles
			xPersonBObjExt.getItemsTCRMPartyMacroRoleBObj().clear();
			IPartyBusinessServices tcrmPartyBusinessServicesComponent = (IPartyBusinessServices) TCRMClassFactory
                    .getTCRMComponent(TCRMCorePropertyKeys.PARTY_BUSINESS_SERVICES_COMPONENT);
            Vector vecPartyMacroRole = tcrmPartyBusinessServicesComponent.getAllPartyMacroRoles(personFound.getPartyId(), "0", "ACTIVE", control);
            if (vecPartyMacroRole != null)
		    	xPersonBObjExt.getItemsTCRMPartyMacroRoleBObj().addAll(vecPartyMacroRole);  
			
			//Populate job title
            xPersonBObjExt.getItemsXJobTitleBObj().clear();
            XJobTitle xJobTitleComponent = (XJobTitle) TCRMClassFactory.getTCRMComponent(IDMDataAdditionPropertyKeys.XJOB_TITLE_COMPONENT);
            DWLResponse jtResponse = xJobTitleComponent.getAllXJobTitleByParty(personFound.getPartyId(), "ACTIVE", control);
            if(jtResponse != null){
                vecXJobTitle = (Vector)jtResponse.getData();
            }
            XJobTitleBObj xJTBobj = getJobTitleBobj(vecXJobTitle, partyRelId);
            if (xJTBobj != null) 
            	xPersonBObjExt.setXJobTitleBObj(xJTBobj);
	        
           
            //Populate job function
            //1185019 - JF/JL is no longer coming from request, it will be derived using JT and cleaning code
            xPersonBObjExt.getItemsXBusinessJobFunctionBObj().clear();
            if (xJTBobj != null) {
               	IDMPartyUtils.processXJTMaster(xPersonBObjExt, xJTBobj, control);
            }

            
            //populate address
		    xPersonBObjExt.getItemsTCRMPartyAddressBObj().clear();
            List resultPartyAddresses = null;
            Vector vecPartyAddressList = partyComp.getAllPartyAddresses(personFound.getPartyId(), "ACTIVE",  control);
		    if (vecPartyAddressList != null)  
              resultPartyAddresses = getPartyAddressList(vecPartyAddressList, orgPartyId);
		    if (resultPartyAddresses != null)
		    	xPersonBObjExt.getItemsTCRMPartyAddressBObj().addAll(resultPartyAddresses);   
 
		    
			//Populate contactMethod
		    //1335422 - All party contactMethod will be returned in response
		    xPersonBObjExt.getItemsTCRMPartyContactMethodBObj().clear();
		    List resultPartyCM = null;
            Vector vecContactMethodList = partyComp.getAllPartyContactMethods(personFound.getPartyId(), "ACTIVE",  control);
            //1600263 - if valueString not "P" or "S", don't return the privpref bobj
		    if (vecContactMethodList != null)  
		    	resultPartyCM = getPartyCMList(vecContactMethodList);
            if (resultPartyCM != null) 
            	xPersonBObjExt.getItemsTCRMPartyContactMethodBObj().addAll(resultPartyCM);

            
            // populate Identifier
		    xPersonBObjExt.getItemsTCRMPartyIdentificationBObj().clear();
	          //For task 1679904 return all person's EID
    		Vector vecIdentifierList = partyComp.getAllPartyIdentifications(personFound.getPartyId(), "ALL",  control);
    		if(vecIdentifierList != null){
                for(int i = 0; i < vecIdentifierList.size(); i++){
                	TCRMPartyIdentificationBObj idBobj= (TCRMPartyIdentificationBObj)vecIdentifierList.get(i);
                    //1282138 - Add the identificationBobj if it is in properties file and active, or it is inactive, but an EID
                	if (isIdentifierPublish(idBobj.getIdentificationType())) {
                	   if (StringUtils.isBlank(idBobj.getEndDate()) || StringUtils.compareWithTrim(idBobj.getIdentificationType(), IDMCompositeTxnConstant.ID_TYPE_EID))
                		xPersonBObjExt.setTCRMPartyIdentificationBObj(idBobj); 
                	}
                }
            }  
    		
  		    // populate relationship
    		xPersonBObjExt.getItemsTCRMPartyRelationshipBObj().clear();
  			if (partyRelationship!= null && orgPartyId != null) {
  				xPersonBObjExt.setTCRMPartyRelationshipBObj(partyRelationship);
  			}
            
  		    // populate AdminContEquiv
    		xPersonBObjExt.getItemsTCRMAdminContEquivBObj().clear();
			Vector vecAdminContEquivList = partyComp.getAllPartyAdminSysKeys(personFound.getPartyId(), control);
			if(vecAdminContEquivList != null){
                for(int i = 0; i < vecAdminContEquivList.size(); i++){
                	XAdminContEquivBObjExt xAdminContEquivBObjExt= (XAdminContEquivBObjExt)vecAdminContEquivList.get(i);
                    //return all ACTIVE adminContequivBobj that is specified in the properties file
          			//for task 1663728. return all active contEquiv
                    //if (StringUtils.isBlank(xAdminContEquivBObjExt.getXEndDate()) && isAdminContequivPublish(xAdminContEquivBObjExt.getAdminSystemType()))
                	if (StringUtils.isBlank(xAdminContEquivBObjExt.getXEndDate()))
                    	xPersonBObjExt.setTCRMAdminContEquivBObj(xAdminContEquivBObjExt);
                }
           }
			
			//populate Personname 
			xPersonBObjExt.getItemsTCRMPersonNameBObj().clear();
			Vector vecPersonName = partyComp.getAllPersonNames(personFound.getPartyId(), "ACTIVE", control);
			if(vecPersonName != null){
				for(int i = 0; i < vecPersonName.size(); i++){
				   XPersonNameBObjExt xPersonName = (XPersonNameBObjExt) vecPersonName.get(i);
             	   //Do not return transliterated person names
             	   if (!StringUtils.compareWithTrim(xPersonName.getNameUsageType(),IDMCompositeTxnConstant.NAME_USAGE_TPYE_TRANSLITERATED)) {
       	               //Taking care of the language locale
       	               if (StringUtils.isNonBlank(xPersonName.getXLanguageType()))
       	            	xPersonName.setXLanguageValue(IDMPartyUtils.getLangLocale(xPersonName.getXLanguageType()));
             		   xPersonBObjExt.setTCRMPersonNameBObj(xPersonName);
             	   }
              }
			} 
	        
	        //Clear the TCRMPartyPrivPrefBObj
            xPersonBObjExt.getItemsTCRMPartyPrivPrefBObj().clear();
            
          
            //Taking care of the language locale
            if (StringUtils.isNonBlank(xPersonBObjExt.getPreferredLanguageType()))
            	xPersonBObjExt.setPreferredLanguageValue(IDMPartyUtils.getLangLocale(xPersonBObjExt.getPreferredLanguageType()));
            
            /*for task 1663728 GDPR
			 *remove displayName
			 *return all active contEquiv
			 */
			xPersonBObjExt.setDisplayName("");

            
			//------ Process Organization party ---------
			if (xOrganizationBObjExt != null) {
				
		        //Clear the TCRMPartyPrivPrefBObj
	            xOrganizationBObjExt.getItemsTCRMPartyPrivPrefBObj().clear();
	            
		        //Clear the partyAddressBobj
	            xOrganizationBObjExt.getItemsTCRMPartyAddressBObj().clear();
	            
		        //Clear the ContactMethodBobj
	            xOrganizationBObjExt.getItemsTCRMPartyContactMethodBObj().clear();
	            
	           //Populate org party macro role
	            xOrganizationBObjExt.getItemsTCRMPartyMacroRoleBObj().clear();
	            Vector vecOrgPartyMacroRole = tcrmPartyBusinessServicesComponent.getAllPartyMacroRoles(orgPartyId, "0", "ACTIVE", control);
	            if(vecOrgPartyMacroRole != null){
	                for(int i = 0; i < vecOrgPartyMacroRole.size(); i++){
	                	TCRMPartyMacroRoleBObj orgPartyMacroRoleBobj =  (TCRMPartyMacroRoleBObj)vecOrgPartyMacroRole.get(i);
	                	logger.finest("partyMacroRole= " + orgPartyMacroRoleBobj.getRoleType() + " Description=" + orgPartyMacroRoleBobj.getRoleDescription());
	                	//Only publish if macroRoleType not equal to '1'
	                	if (!StringUtils.compareWithTrim(orgPartyMacroRoleBobj.getRoleType(),IDMCompositeTxnConstant.MACROROLE_TYPE_CUSTOMER))
	                		xOrganizationBObjExt.setTCRMPartyMacroRoleBObj(orgPartyMacroRoleBobj);
	                }         
	            }


				//populate orgName
	            xOrganizationBObjExt.getItemsTCRMOrganizationNameBObj().clear();
				Vector vecOrganizationName = orgPartyComp.getAllOrganizationNames(orgPartyId, "ACTIVE", control);
				if (vecOrganizationName != null) {
					for(int i = 0; i < vecOrganizationName.size(); i++){
						XOrganizationNameBObjExt xOrgName = (XOrganizationNameBObjExt) vecOrganizationName.get(i);
	             	    //Do not return transliterated org names
						//1519294 - DO not pub/sub Alias org names
	             	    if (!StringUtils.compareWithTrim(xOrgName.getNameUsageType(),IDMCompositeTxnConstant.ORG_NAME_USAGE_TYPE_TRANSLITERATED)
	             	    		&& !StringUtils.compareWithTrim(xOrgName.getNameUsageType(),IDMCompositeTxnConstant.ORG_NAME_USAGE_TYPE_ALIAS)) {
	        	            //Taking care of the language locale
	        	            if (StringUtils.isNonBlank(xOrgName.getXLanguageType()))
	        	            	xOrgName.setXLanguageValue(IDMPartyUtils.getLangLocale(xOrgName.getXLanguageType()));
	             	    	xOrganizationBObjExt.setTCRMOrganizationNameBObj(xOrgName);
	             	    }
		            }
				}
				
				//populate identifier
				xOrganizationBObjExt.getItemsTCRMPartyIdentificationBObj().clear();
		          //For task 1679904, return all EID
				Vector vecOrgIdentifierList = orgPartyComp.getAllPartyIdentifications(orgPartyId, "ACTIVE",  control); //IN1411681, only publish active org EIDs
	  			if(vecOrgIdentifierList != null){
	                  for(int i = 0; i < vecOrgIdentifierList.size(); i++){
	                  	TCRMPartyIdentificationBObj idBobj= (TCRMPartyIdentificationBObj)vecOrgIdentifierList.get(i);
	                    //1282138 - Add the identificationBobj if it is in properties file and active, or it is inactive, but an EID
	                	if (isIdentifierPublish(idBobj.getIdentificationType())) {
	                	   if (StringUtils.isBlank(idBobj.getEndDate()) || StringUtils.compareWithTrim(idBobj.getIdentificationType(), IDMCompositeTxnConstant.ID_TYPE_EID))
	                		   xOrganizationBObjExt.setTCRMPartyIdentificationBObj(idBobj); 
	                	}
	                  }
	             } 
				
	  			// populate AdminContEquiv
	  			xOrganizationBObjExt.getItemsTCRMAdminContEquivBObj().clear();
				Vector vecOrgAdminContEquivList = orgPartyComp.getAllPartyAdminSysKeys(orgPartyId, control);
				XAdminContEquivBObjExt mppAdminContequiv = null;
				if(vecOrgAdminContEquivList != null){
	                for(int i = 0; i < vecOrgAdminContEquivList.size(); i++){
	                	XAdminContEquivBObjExt xAdminContEquivBObjExt= (XAdminContEquivBObjExt)vecOrgAdminContEquivList.get(i);
	                    //return all ACTIVE adminContequivBobj that is specified in the properties file
	                	//for task 1663728. return all active contEquivs
	                	//if (StringUtils.isBlank(xAdminContEquivBObjExt.getXEndDate()) && isAdminContequivPublish(xAdminContEquivBObjExt.getAdminSystemType())) 
	                	if (StringUtils.isBlank(xAdminContEquivBObjExt.getXEndDate()))
	                	{
	                		//1199640 - new rules to handle MPP, only return the MPP with oldest start date.  If it is MPP, save it for later
	                    	if (StringUtils.compareWithTrim(xAdminContEquivBObjExt.getAdminSystemType(), IDMCompositeTxnConstant.ADMIN_SYS_TYPE_MPP)) {
	                    		if (mppAdminContequiv == null) 
	                    			mppAdminContequiv = xAdminContEquivBObjExt;
	                    		else {
	                    			//This is not the first MPP found, compare the start date,  the older start date won
	                    			if (CommonUtils.compareDate(xAdminContEquivBObjExt.getXStartDate(), mppAdminContequiv.getXStartDate()) < 0)
	                    				mppAdminContequiv = xAdminContEquivBObjExt;
	                    		}
	                    	} else
  	                		    xOrganizationBObjExt.setTCRMAdminContEquivBObj(xAdminContEquivBObjExt);
	                	}
	                }
	                
	                //Now, return the winning mpp adminContequiv 
	                if (mppAdminContequiv != null)
	                	xOrganizationBObjExt.setTCRMAdminContEquivBObj(mppAdminContequiv);
	            }
				
	            //Taking care of the country ISO_Code
	            if (StringUtils.isNonBlank(xOrganizationBObjExt.getXPrimaryCountryType()))
	            	xOrganizationBObjExt.setXPrimaryCountryValue(IDMPartyUtils.getCountryISOCode(xOrganizationBObjExt.getXPrimaryCountryType()));
	            
	            //Taking care of the language locale
	            if (StringUtils.isNonBlank(xOrganizationBObjExt.getPreferredLanguageType()))
	            	xOrganizationBObjExt.setPreferredLanguageValue(IDMPartyUtils.getLangLocale(xOrganizationBObjExt.getPreferredLanguageType()));

				/*for task 1663728 GDPR
				 *remove displayName
				 *return all active contEquiv
				 */
				xOrganizationBObjExt.setDisplayName("");
			}
			
			//------ Process response parties ---------	        
	        //set response person
            responsePerson = xPersonBObjExt;
            
            //set response org
            responseOrg = xOrganizationBObjExt;
	       
		} catch (Exception e) {
	           logger.error("Error in IDMGetParty::processPersonAndOrg()." + e.getMessage());
	            
	            DWLError error = errHandler.getErrorMessage(IDMCompositeServicesComponentID.IDMGET_PARTY_BUSINESS_PROXY,
	                    "READERR",
	                    IDMCompositeServicesErrorReasonCode.IDMGETPARTY_FAILED, control, new String[0]);
	            throw new BusinessProxyException(error.getErrorMessage());
		}    
	       
 	   logger.finest("Exiting processPersonAndOrg().");   
    }   
    
   
    
	 /**
     * getPartyRelationship
     * @param uptPersonBobj
     * @return
     */ 
	private XPartyRelationshipBObjExt getPartyRelationship (String partyID, XPersonBObjExt uptPersonBobj) throws Exception {
		logger.finest("Entering Notification::getPartyRelationship()...");
		
		 IPartyBusinessServices tcrmPartyBusinessServicesComponent = (IPartyBusinessServices) TCRMClassFactory
                .getTCRMComponent(TCRMCorePropertyKeys.PARTY_BUSINESS_SERVICES_COMPONENT);
		 IPerson partyComp = (IPerson)TCRMClassFactory.getTCRMComponent("party_component");
		 IOrganization orgPartyComp = (IOrganization)TCRMClassFactory.getTCRMComponent("party_component");
		 
		 //Check if the party has relationship, max one relationship is expected
		 Vector vecPartyRelationship = partyComp.getAllPartyRelationships(partyID, "ACTIVE",  control);
		 XPartyRelationshipBObjExt winningOrgRelationship = null;

		 
		 //1369620  - The person party should only have one active relationship
		 if(vecPartyRelationship != null && vecPartyRelationship.size() > 0 ){ 
			winningOrgRelationship = (XPartyRelationshipBObjExt)vecPartyRelationship.get(0);
		 
			 //Also Check to see if the organization party is really active
			 if (winningOrgRelationship != null) {
				XOrganizationBObjExt OrgParty = (XOrganizationBObjExt) orgPartyComp.getParty(winningOrgRelationship.getRelationshipToPartyId(), "0", control);	
	         	if (StringUtils.isNonBlank(OrgParty.getInactivatedDate()))
	         		winningOrgRelationship = null;
			 }
			
	        //If there is relationship, populate the relationshipRole 
	        if(winningOrgRelationship != null){    
	        	logger.finest("getPartyRelationship:  relationship found. relationshipID=" + winningOrgRelationship.getPartyRelationshipIdPK());
	        	
	            
	               Vector vecPartyRelationshipRole = tcrmPartyBusinessServicesComponent.getAllPartyRelationshipRoles(
	                		 winningOrgRelationship.getPartyRelationshipIdPK(), uptPersonBobj.getPartyId(), "ACTIVE", control);     
	                if(vecPartyRelationshipRole != null){
	                	//1334850 -Pick only one role to return - either KEY or FCA, but FCA has higher priority
	                	TCRMPartyRelationshipRoleBObj partyRelRoleToPublish = null;
	            		Vector vecFCAType = new Vector();
	            		Vector vecKEYType = new Vector();	
	                	
	            		//Sort out the KEY and FCA roles
	                    for(int j = 0; j < vecPartyRelationshipRole.size(); j++){
	                    	TCRMPartyRelationshipRoleBObj partyRelRolebobj = (TCRMPartyRelationshipRoleBObj) vecPartyRelationshipRole.get(j);
	                    	if (StringUtils.compareWithTrim(partyRelRolebobj.getRoleType(),IDMCompositeTxnConstant.ENTITYROLE_KEY))
	                    		vecKEYType.add(partyRelRolebobj);
	                    	else if (StringUtils.compareWithTrim(partyRelRolebobj.getRoleType(),IDMCompositeTxnConstant.ENTITYROLE_FCA))
	                    		vecFCAType.add(partyRelRolebobj);
	                    }
	                    
	                    if (vecFCAType != null && vecFCAType.size() > 0)   //There are FCA roles to select from
	                    	partyRelRoleToPublish = IDMPartyUtils.getRecentEntityRole(vecFCAType);
	                    else                                               //Only have KEY role types
	                    	partyRelRoleToPublish = IDMPartyUtils.getRecentEntityRole(vecKEYType);
	                    
	                    if (partyRelRoleToPublish != null)
	                    	winningOrgRelationship.setTCRMPartyRelationshipRoleBObj(partyRelRoleToPublish);
	                }
	
	                                       
	            }
		 }

		logger.finest("Exiting Notification::getPartyRelationship()...");
        return winningOrgRelationship;
	}
	
    /**
     * getJobTitleBobj
     * @param vecXJobTitle
     * @param relId
     * @return
     */ 
	private XJobTitleBObj getJobTitleBobj(Vector vecXJobTitle, String relId) throws Exception {
		logger.finest("Entering IDMGetParty::getJobTitleBobj()...");
		XJobTitleBObj winnerJT = null;
		
		//1369620 - There will be only one JT for each contact party
		if(vecXJobTitle != null)
           	winnerJT = (XJobTitleBObj) vecXJobTitle.get(0);

		
		if (winnerJT != null){
            //Taking care of the language locale
            if (StringUtils.isNonBlank(winnerJT.getJobTitleAlternateLanguageType()))
            	winnerJT.setJobTitleAlternateLanguageValue(IDMPartyUtils.getLangLocale(winnerJT.getJobTitleAlternateLanguageType()));
            if (StringUtils.isNonBlank(winnerJT.getJobTitleLanguageType()))
            	winnerJT.setJobTitleLanguageValue(IDMPartyUtils.getLangLocale(winnerJT.getJobTitleLanguageType()));
        }
		logger.finest("Exiting IDMGetParty::getJobTitleBobj()...");
		return winnerJT;
	}
	
    /**
     * getJobFunction
     * @param vecXBusinessJobFunction
     * @param relId
     * @return
     */ 
	private XBusinessJobFunctionBObj getJobFunction(Vector vecXBusinessJobFunction, String relId) throws Exception {
		logger.finest("Entering IDMGetParty::getJobFunction()...");
		
		XBusinessJobFunctionBObj consumerJF = null;
		XBusinessJobFunctionBObj orgRelatedJF = null;	
		XBusinessJobFunctionBObj winnerJF = null;
		//Decide which JF to return
		if(vecXBusinessJobFunction != null){
            for(int i = 0; i < vecXBusinessJobFunction.size(); i++){
            	XBusinessJobFunctionBObj jobFunctionBobj = (XBusinessJobFunctionBObj) vecXBusinessJobFunction.get(i);
            	//Only take the JF with primary indicator
            	if (StringUtils.isBlank(jobFunctionBobj.getPartyRelationshipIdPK()) && StringUtils.compareIgnoreCaseWithTrim(jobFunctionBobj.getPrimaryIndicator(), "Y"))
            		consumerJF = jobFunctionBobj;
            	else if ( relId != null && StringUtils.compareWithTrim(jobFunctionBobj.getPartyRelationshipIdPK(), relId) && StringUtils.compareIgnoreCaseWithTrim(jobFunctionBobj.getPrimaryIndicator(), "Y"))
            		orgRelatedJF = jobFunctionBobj;
            	logger.finest("JobFunctionTypeCode= " + jobFunctionBobj.getJobFunctionCodeType() + " Cont_rel_id=" + jobFunctionBobj.getPartyRelationshipIdPK());
            }
            
            if (orgRelatedJF == null && consumerJF == null)
            	return null;
            
            if (orgRelatedJF != null && consumerJF != null) {
            	boolean isConsumerwon = checkDates(consumerJF.getStartDate(), consumerJF.getLastUsedDate(), orgRelatedJF.getStartDate(), orgRelatedJF.getLastUsedDate());
            	if (isConsumerwon) 
            		winnerJF = consumerJF;
            	else 
            		winnerJF = orgRelatedJF;
             } else if (orgRelatedJF != null && consumerJF == null) {
            	 winnerJF = orgRelatedJF;
             } else if (orgRelatedJF == null && consumerJF != null) {
            	 winnerJF = consumerJF;
            }
        }
		

		logger.finest("Exiting IDMGetParty::getJobFunction()...");
		return winnerJF;
	}
	
	
	
	/**
     * checkDates
     * @param date1
     * @param date2
     * @return 
     */
    public boolean checkDates(String startDt1, String LUD1, String startDt2, String LUD2) throws Exception {
        
        boolean isConsumerWon = false;
        String tempVal1 = null;
        String tempVal2 = null;
        
       	tempVal1 = CommonUtils.getRecentDate(startDt1, LUD1);
       	tempVal2 = CommonUtils.getRecentDate(startDt2, LUD2);
       	logger.finest("Notification::checkDates. tempVal1= " + tempVal1 + " tempVal2=" + tempVal2 );
        
 
       	//Check to see who won
        if (StringUtils.compareWithTrim(tempVal1,CommonUtils.getRecentDate(tempVal1, tempVal2)))
        	isConsumerWon = true;
      
        logger.finest("Existing Notification::checkDates. isConsumerWon= " + isConsumerWon);
        return isConsumerWon;
    }
    
    /**
     * Compare Tier.
     * @param tier1
     * @param tier2
     * @return
     */
    private int compareTier(String tier1, String tier2) throws Exception{
    	
    	int result = 0;
    	CodeTypeComponentHelper codeTypeCompHelper = DWLClassFactory.getCodeTypeComponentHelper();
    	
    	//Convert to Tier value to compare.
    	if (!StringUtils.isBlank(tier1)) {
        	CodeTypeBObj srcCTBObj = codeTypeCompHelper.getCodeTypeByCode("xcdconfgradetp", (String) control.get(DWLControlKeys.LANG_ID), tier1,
        			control);
        	tier1 = srcCTBObj.getvalue();
    	}

       	if (!StringUtils.isBlank(tier2)) {
        	CodeTypeBObj tgtCTBObj = codeTypeCompHelper.getCodeTypeByCode("xcdconfgradetp", (String) control.get(DWLControlKeys.LANG_ID), tier2,
        			control);
        	tier2 = tgtCTBObj.getvalue();  
       	}
    	
       	//If tier1 is blank, tier2 is not blank
    	if(StringUtils.isBlank(tier1)){
    		if(StringUtils.isBlank(tier2))
    			result = 0;
    		else {
    			tier2 = tier2.trim();
    			if(isExcludedTier(tier2))
    				result = 0;
    			else
    				result = -1;
    		}
    	} else { //tier1 is not blank, tier2 is blank
    		if(StringUtils.isBlank(tier2)){
    			tier1 = tier1.trim();
    			//If the tier is in excluded list, consider there is no tier 
    			if(isExcludedTier(tier1))
    				result = 0;
    			else
    				result = 1;
    		} else { // both tiers are not blank
    			tier1 = tier1.trim();
    			tier2 = tier2.trim();
    			
    			if(tier1.compareToIgnoreCase(tier2) > 0)
    				result = -1;
    			else if(tier1.compareToIgnoreCase(tier2) < 0)
    				result = 1;
    			else 
    				result = 0;
    		}
    	}
    	logger.finest("Exiting compareTier()...Result=" + result);
    	return result;
    }

    /**
     * Check if a tier is in the exclude list.
     * @param tier
     * @return
     */
    private boolean isExcludedTier(String tier){    	

    	for(int i = 0; i < excludedTiers.size(); i++){
    		String excludedTier = (String)excludedTiers.get(i);
    		if(StringUtils.compareWithTrim(excludedTier, tier))
    			return true;    		
    	}

    	return false;
    }

    /**
     * Check if a tier is in the exclude list after convert.
     * @param tier
     * @return
     */
    private boolean isExcludedTierUsingValue (String tier) throws Exception{   
    	
    	CodeTypeComponentHelper codeTypeCompHelper = DWLClassFactory.getCodeTypeComponentHelper();
    	
    	//Convert to Tier value to compare.
    	if (!StringUtils.isBlank(tier)) {
        	CodeTypeBObj srcCTBObj = codeTypeCompHelper.getCodeTypeByCode("xcdconfgradetp", (String) control.get(DWLControlKeys.LANG_ID), tier,
        			control);
        	tier = srcCTBObj.getvalue();
    	} else 
    		return false;

    	return isExcludedTier(tier);
    }

    /**
     * getPartyAddressList
     * @param vecPartyAddressList
     * @param orgPartyId
     * @return
     */ 
	private List getPartyAddressList(Vector vecPartyAddressList, String orgPartyId) throws Exception {
		logger.finest("Entering IDMGetParty::getPartyAddressList()...");
		List resultList = new Vector();
		
	
		//Decide which address to publish
		//1301166 - Address only have 3 types and no org related bobjs
		//For address, only publish/sub  Primary and Secondary
		if(vecPartyAddressList != null){
			
            for(int i = 0; i < vecPartyAddressList.size(); i++){
            	XPartyAddressBObjExt partyAddrBobjExt = (XPartyAddressBObjExt) vecPartyAddressList.get(i);
            	if (StringUtils.compareWithTrim(partyAddrBobjExt.getAddressUsageType(), IDMCompositeTxnConstant.ADDRESS_USAGE_TPYE_PRIMARY) ||
                	StringUtils.compareWithTrim(partyAddrBobjExt.getAddressUsageType(), IDMCompositeTxnConstant.ADDRESS_USAGE_TPYE_SECONDARY)) {
                		
    	            logger.finest("getPartyAddressList: partyAddrTP= " + partyAddrBobjExt.getAddressUsageType());
  
	                //Taking care of the country ISO_Code
	            	XAddressBObjExt srcAddress = (XAddressBObjExt) partyAddrBobjExt.getTCRMAddressBObj();
	                if (StringUtils.isNonBlank(srcAddress.getCountryType()))
	                	srcAddress.setCountryValue(IDMPartyUtils.getCountryISOCode(srcAddress.getCountryType()));
	                
	  	            //Taking care of the language locale
    	            if (StringUtils.isNonBlank(srcAddress.getXLanguageType()))
    	            	srcAddress.setXLanguageValue(IDMPartyUtils.getLangLocale(srcAddress.getXLanguageType()));
    	            
    	            //1328807** Temporally to convert Primary to 'Contact Primary', Secondary to 'Contact Secondary', 
    	            //once SC is ready, remove this code
    	            //for task 1648500. Use new address usage type code for sales connect
//    	            if (StringUtils.compareWithTrim(partyAddrBobjExt.getAddressUsageType(), IDMCompositeTxnConstant.ADDRESS_USAGE_TPYE_PRIMARY)) {
//    	            	partyAddrBobjExt.setAddressUsageType("100001");
//    	            	partyAddrBobjExt.setAddressUsageValue("Contact Primary");
//    	            } else {
//    	            	partyAddrBobjExt.setAddressUsageType("100002");
//    	            	partyAddrBobjExt.setAddressUsageValue("Contact Secondary");
//    	            }
    	            
    	            //1600263 if valueString not "P" or "S", don't return the privpref bobj
    	            Vector vecPartyAddressPPList = partyAddrBobjExt.getItemsTCRMPartyAddressPrivPrefBObj();
    	            Vector removePartyAddressesPref = new Vector();
    	            
    	            for(int j = vecPartyAddressPPList.size() - 1; j >= 0; j--){
    	                XPartyAddressPrivPrefBObjExt partyAddressPrivPref = (XPartyAddressPrivPrefBObjExt) vecPartyAddressPPList.get(j);
    	                logger.finest("getPartyAddressList(), PPTypeCd= " + partyAddressPrivPref.getPrivPrefType() + " ValueString=" + partyAddressPrivPref.getValueString());
    	                logger.finest("getPartyAddressList(), PPReasonTPCd= " + partyAddressPrivPref.getPrivPrefReasonType());
    	     		
    	                //1600263 if valueString not "P" and "S", don't publish the bobj
    	                if (!StringUtils.compareIgnoreCaseWithTrim(partyAddressPrivPref.getValueString(), "P") 
    	                		&& !StringUtils.compareIgnoreCaseWithTrim(partyAddressPrivPref.getValueString(), "S")) {
    	                 	//IDMA-437 changes
    	                	//partyAddrBobjExt.getItemsTCRMPartyAddressPrivPrefBObj().clear();
    	                	removePartyAddressesPref.add(partyAddressPrivPref);
    	                }
    	            }
    	        
    	            //IDMA-437 changes
    	            if(removePartyAddressesPref.size() > 0){
    	            	partyAddrBobjExt.getItemsTCRMPartyAddressPrivPrefBObj().removeAll(removePartyAddressesPref);
    	            }
    	            
	            	resultList.add(partyAddrBobjExt);
	            }
            }
        }

		logger.finest("Exiting IDMGetParty::getPartyAddressList()...");
		return resultList;
	}

   /** 1600263 
  * getPartyCMList
  * @param vecContactMethodList
  * @return resultList
  */ 
	private List getPartyCMList(Vector vecContactMethodList) throws Exception {
		logger.finest("Entering IDMGetParty::getPartyCMList()...");
		List resultList = new Vector();
	
		//Going thru the CM list and remove privpref bobjs that has valueString=N 
		if(vecContactMethodList != null){

			for(int i = 0; i < vecContactMethodList.size(); i++){
         	    XContactMethodBObjExt contactMethodBobjExt = (XContactMethodBObjExt) vecContactMethodList.get(i);

                //1600263 if valueString not "P" or "S", don't return the bobj
	            Vector vecContactMethodPPList = contactMethodBobjExt.getItemsTCRMPartyContactMethodPrivPrefBObj();
	            Vector removePartyCMPref = new Vector();
 	           
	            for(int j = vecContactMethodPPList.size() - 1; j >= 0; j--){
	            	XPartyContactMethodPrivPrefBObjExt partyCMPrivPref = (XPartyContactMethodPrivPrefBObjExt) vecContactMethodPPList.get(j);
	                logger.finest("getContactMethodList(), PPTypeCd= " + partyCMPrivPref.getPrivPrefType() + " ValueString=" + partyCMPrivPref.getValueString());
	                logger.finest("getContactMethodList(), PPReasonTPCd= " + partyCMPrivPref.getPrivPrefReasonType());
	
	                if (!StringUtils.compareIgnoreCaseWithTrim(partyCMPrivPref.getValueString(), "P") 
	                		&& !StringUtils.compareIgnoreCaseWithTrim(partyCMPrivPref.getValueString(), "S")) {
	                	logger.finest("to clear... ");
	                	//IDMA-437 changes
	                	//contactMethodBobjExt.getItemsTCRMPartyContactMethodPrivPrefBObj().clear();
	                	removePartyCMPref.add(partyCMPrivPref);
	                }
	             }
	     
	         	//IDMA-437 changes
	            if(removePartyCMPref.size() > 0){
	            	contactMethodBobjExt.getItemsTCRMPartyContactMethodPrivPrefBObj().removeAll(removePartyCMPref);
	            }
	            
	    		//generate xml for the contactMethod and associated privpref
	            resultList.add(contactMethodBobjExt);

           }
        }
		logger.finest("Exiting IDMGetParty::getPartyCMList()...");
		return resultList;
	}
	
	   /** This method is not being used
     * getContactMethodList
     * @param vecContactMethodList
     * @param orgPartyId
     * @return
     */ 
	private List getContactMethodList(Vector vecContactMethodList, String orgPartyId) throws Exception {
		logger.finest("Entering IDMGetParty::getContactMethodList()...");
		List resultList = new Vector();
		
		Vector vecConsumerCM = new Vector();
		Vector vecOrgRelatedCM = new Vector();	
		Vector vecSecondaryEmail = new Vector();
		ArrayList contactMethodTypes = new ArrayList();
		
	
		//Decide which CM to return
		//For secondary email address, return them all
		if(vecContactMethodList != null){
			//Separate the consumer and org_related bobjs
			for(int i = 0; i < vecContactMethodList.size(); i++){
            	XContactMethodBObjExt contactMethodBobjExt = (XContactMethodBObjExt) vecContactMethodList.get(i);
            	boolean cmAdded = false;
             	
            	//put all secondary emails to one vector if they are for consumer and the active org 
            	if (StringUtils.compareWithTrim(contactMethodBobjExt.getContactMethodUsageType(),IDMCompositeTxnConstant.CONTACT_METHOD_USAGE_TYPE_SECONDARY_BUSINESS_EMAIL)) {
            		if (StringUtils.isBlank(contactMethodBobjExt.getXOrgPartyId()) ||
            			(orgPartyId != null && StringUtils.compareWithTrim(contactMethodBobjExt.getXOrgPartyId(), orgPartyId))	)
            		vecSecondaryEmail.add(contactMethodBobjExt);
            	} else {
            		if (StringUtils.isBlank(contactMethodBobjExt.getXOrgPartyId())) {
            		   vecConsumerCM.add(contactMethodBobjExt);
            		   cmAdded = true;
            		}
            	     else if (orgPartyId != null && StringUtils.compareWithTrim(contactMethodBobjExt.getXOrgPartyId(), orgPartyId)) {
            		   vecOrgRelatedCM.add(contactMethodBobjExt);
               		   cmAdded = true;
            	     }
            		
                   	//Save the types except secondary email
                	if(cmAdded && !contactMethodTypes.contains((String)contactMethodBobjExt.getContactMethodUsageType()))
                		contactMethodTypes.add(contactMethodBobjExt.getContactMethodUsageType());
            		
            	}
            	logger.finest("getContactMethodList(): CM_usageType= " + contactMethodBobjExt.getContactMethodUsageType() +
            			" CMType=" + contactMethodBobjExt.getTCRMContactMethodBObj().getContactMethodType());
			}
			
			//return all secondary email
			for(int j = 0; j < vecSecondaryEmail.size(); j++){
				XContactMethodBObjExt secondaryEmailBobjExt = (XContactMethodBObjExt) vecSecondaryEmail.get(j);
				resultList.add(secondaryEmailBobjExt);
        	}
            	
			//Find the matched contactMethod type to determine the winner
            for(int i = 0; i < contactMethodTypes.size(); i++){
	        	logger.finest("getContactMethodList(): Processing CM_usageType= " + contactMethodTypes.get(i));

	        	XContactMethodBObjExt winnerContactMethod = null;
	        	XContactMethodBObjExt consumerContactMethod = null;
	        	XContactMethodBObjExt orgRelatedContactMethod = null;
            	//get the contactMethod with the same type for consumer
            	for(int j = 0; j < vecConsumerCM.size(); j++){
            		XContactMethodBObjExt contactMethodBobjExt = (XContactMethodBObjExt) vecConsumerCM.get(j);
           		    if (StringUtils.compareWithTrim(contactMethodBobjExt.getContactMethodUsageType(),(String)contactMethodTypes.get(i)))	
            			consumerContactMethod = contactMethodBobjExt;
            	}
            	//get the contactMethod with the same type for orgRelated bobj
            	for(int k = 0; k < vecOrgRelatedCM.size(); k++){
            		XContactMethodBObjExt contactMethodBobjExt = (XContactMethodBObjExt) vecOrgRelatedCM.get(k);
                	if (StringUtils.compareWithTrim(contactMethodBobjExt.getContactMethodUsageType(),(String)contactMethodTypes.get(i)))
            			orgRelatedContactMethod = contactMethodBobjExt;
            	}
            
            
	            if (orgRelatedContactMethod != null && consumerContactMethod != null) {
	            	boolean isConsumerwon = checkDates(consumerContactMethod.getStartDate(), consumerContactMethod.getLastUsedDate(), orgRelatedContactMethod.getStartDate(), orgRelatedContactMethod.getLastUsedDate());
	            	if (isConsumerwon) 
	            		winnerContactMethod = consumerContactMethod;
	            	else 
	            		winnerContactMethod = orgRelatedContactMethod;
	             } else if (orgRelatedContactMethod != null && consumerContactMethod == null) {
	            	 winnerContactMethod = orgRelatedContactMethod;
	             } else if (orgRelatedContactMethod == null && consumerContactMethod != null) {
	            	 winnerContactMethod = consumerContactMethod;
	            }

	    		//generate xml for the contactMethod and associated privpref
	            resultList.add(winnerContactMethod);

            }
        }
		logger.finest("Exiting IDMGetParty::getContactMethodList()...");
		return resultList;
	}
	
    
    

}

