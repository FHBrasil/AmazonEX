package br.flieger.exacttarget.wsdl.api;

public class SoapProxy implements br.flieger.exacttarget.wsdl.api.Soap {
  private String _endpoint = null;
  private br.flieger.exacttarget.wsdl.api.Soap soap = null;
  
  public SoapProxy() {
    _initSoapProxy();
  }
  
  public SoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initSoapProxy();
  }
  
  private void _initSoapProxy() {
    try {
      soap = (new br.flieger.exacttarget.wsdl.api.PartnerAPILocator()).getSoap();
      if (soap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)soap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)soap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (soap != null)
      ((javax.xml.rpc.Stub)soap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.flieger.exacttarget.wsdl.api.Soap getSoap() {
    if (soap == null)
      _initSoapProxy();
    return soap;
  }
  
  public br.flieger.exacttarget.wsdl.api.CreateResponse create(br.flieger.exacttarget.wsdl.api.CreateRequest parameters) throws java.rmi.RemoteException{
    if (soap == null)
      _initSoapProxy();
    return soap.create(parameters);
  }
  
  public br.flieger.exacttarget.wsdl.api.RetrieveResponseMsg retrieve(br.flieger.exacttarget.wsdl.api.RetrieveRequestMsg parameters) throws java.rmi.RemoteException{
    if (soap == null)
      _initSoapProxy();
    return soap.retrieve(parameters);
  }
  
  public br.flieger.exacttarget.wsdl.api.UpdateResponse update(br.flieger.exacttarget.wsdl.api.UpdateRequest parameters) throws java.rmi.RemoteException{
    if (soap == null)
      _initSoapProxy();
    return soap.update(parameters);
  }
  
  public br.flieger.exacttarget.wsdl.api.DeleteResponse delete(br.flieger.exacttarget.wsdl.api.DeleteRequest parameters) throws java.rmi.RemoteException{
    if (soap == null)
      _initSoapProxy();
    return soap.delete(parameters);
  }
  
  public br.flieger.exacttarget.wsdl.api.QueryResponseMsg query(br.flieger.exacttarget.wsdl.api.QueryRequestMsg parameters) throws java.rmi.RemoteException{
    if (soap == null)
      _initSoapProxy();
    return soap.query(parameters);
  }
  
  public br.flieger.exacttarget.wsdl.api.DefinitionResponseMsg describe(br.flieger.exacttarget.wsdl.api.DefinitionRequestMsg parameters) throws java.rmi.RemoteException{
    if (soap == null)
      _initSoapProxy();
    return soap.describe(parameters);
  }
  
  public br.flieger.exacttarget.wsdl.api.ExecuteResponseMsg execute(br.flieger.exacttarget.wsdl.api.ExecuteRequest[] parameters) throws java.rmi.RemoteException{
    if (soap == null)
      _initSoapProxy();
    return soap.execute(parameters);
  }
  
  public br.flieger.exacttarget.wsdl.api.PerformResponseMsg perform(br.flieger.exacttarget.wsdl.api.PerformRequestMsg parameters) throws java.rmi.RemoteException{
    if (soap == null)
      _initSoapProxy();
    return soap.perform(parameters);
  }
  
  public br.flieger.exacttarget.wsdl.api.ConfigureResponseMsg configure(br.flieger.exacttarget.wsdl.api.ConfigureRequestMsg parameters) throws java.rmi.RemoteException{
    if (soap == null)
      _initSoapProxy();
    return soap.configure(parameters);
  }
  
  public br.flieger.exacttarget.wsdl.api.ScheduleResponseMsg schedule(br.flieger.exacttarget.wsdl.api.ScheduleRequestMsg parameters) throws java.rmi.RemoteException{
    if (soap == null)
      _initSoapProxy();
    return soap.schedule(parameters);
  }
  
  public br.flieger.exacttarget.wsdl.api.VersionInfoResponseMsg versionInfo(br.flieger.exacttarget.wsdl.api.VersionInfoRequestMsg parameters) throws java.rmi.RemoteException{
    if (soap == null)
      _initSoapProxy();
    return soap.versionInfo(parameters);
  }
  
  public br.flieger.exacttarget.wsdl.api.ExtractResponseMsg extract(br.flieger.exacttarget.wsdl.api.ExtractRequest[] parameters) throws java.rmi.RemoteException{
    if (soap == null)
      _initSoapProxy();
    return soap.extract(parameters);
  }
  
  public br.flieger.exacttarget.wsdl.api.SystemStatusResponseMsg getSystemStatus(br.flieger.exacttarget.wsdl.api.SystemStatusRequestMsg parameters) throws java.rmi.RemoteException{
    if (soap == null)
      _initSoapProxy();
    return soap.getSystemStatus(parameters);
  }
  
  
}