#!/bin/awk -f
{
print "<?xml version=\"1.0\" encoding=\"UTF-8\"?><tns0:TCRMService xmlns:tns0=\"http://www.ibm.com/mdm/schema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.ibm.com/mdm/schema MDMDomains.xsd\"><tns0:RequestControl><tns0:requestID>3298413756</tns0:requestID><tns0:DWLControl><tns0:requesterName>IDM-BATCH</tns0:requesterName><tns0:requesterLanguage>100</tns0:requesterLanguage><tns0:userRole>IDM_BATCH</tns0:userRole></tns0:DWLControl></tns0:RequestControl><tns0:TCRMTx><tns0:TCRMTxType>collapsePartiesWithRules</tns0:TCRMTxType><tns0:TCRMTxObject>TCRMPartyListBObj</tns0:TCRMTxObject><tns0:TCRMObject><tns0:TCRMPartyListBObj><tns0:TCRMPartyBObj><tns0:PartyId>"$2"</tns0:PartyId></tns0:TCRMPartyBObj><tns0:TCRMPartyBObj><tns0:PartyId>"$3"</tns0:PartyId></tns0:TCRMPartyBObj></tns0:TCRMPartyListBObj></tns0:TCRMObject></tns0:TCRMTx></tns0:TCRMService>";
}
