/**
 * SoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.flieger.exacttarget.wsdl.api;

public class SoapBindingSkeleton implements br.flieger.exacttarget.wsdl.api.Soap, org.apache.axis.wsdl.Skeleton {
    private br.flieger.exacttarget.wsdl.api.Soap impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CreateRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">CreateRequest"), br.flieger.exacttarget.wsdl.api.CreateRequest.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("create", _params, new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CreateResponse"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">CreateResponse"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "Create"));
        _oper.setSoapAction("Create");
        _myOperationsList.add(_oper);
        if (_myOperations.get("create") == null) {
            _myOperations.put("create", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("create")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RetrieveRequestMsg"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">RetrieveRequestMsg"), br.flieger.exacttarget.wsdl.api.RetrieveRequestMsg.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("retrieve", _params, new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RetrieveResponseMsg"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">RetrieveResponseMsg"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "Retrieve"));
        _oper.setSoapAction("Retrieve");
        _myOperationsList.add(_oper);
        if (_myOperations.get("retrieve") == null) {
            _myOperations.put("retrieve", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("retrieve")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UpdateRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">UpdateRequest"), br.flieger.exacttarget.wsdl.api.UpdateRequest.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("update", _params, new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UpdateResponse"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">UpdateResponse"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "Update"));
        _oper.setSoapAction("Update");
        _myOperationsList.add(_oper);
        if (_myOperations.get("update") == null) {
            _myOperations.put("update", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("update")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DeleteRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">DeleteRequest"), br.flieger.exacttarget.wsdl.api.DeleteRequest.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("delete", _params, new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DeleteResponse"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">DeleteResponse"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "Delete"));
        _oper.setSoapAction("Delete");
        _myOperationsList.add(_oper);
        if (_myOperations.get("delete") == null) {
            _myOperations.put("delete", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("delete")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "QueryRequestMsg"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">QueryRequestMsg"), br.flieger.exacttarget.wsdl.api.QueryRequestMsg.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("query", _params, new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "QueryResponseMsg"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">QueryResponseMsg"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "Query"));
        _oper.setSoapAction("Query");
        _myOperationsList.add(_oper);
        if (_myOperations.get("query") == null) {
            _myOperations.put("query", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("query")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DefinitionRequestMsg"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">DefinitionRequestMsg"), br.flieger.exacttarget.wsdl.api.DefinitionRequestMsg.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("describe", _params, new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DefinitionResponseMsg"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">DefinitionResponseMsg"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "Describe"));
        _oper.setSoapAction("Describe");
        _myOperationsList.add(_oper);
        if (_myOperations.get("describe") == null) {
            _myOperations.put("describe", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("describe")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExecuteRequestMsg"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ExecuteRequestMsg"), br.flieger.exacttarget.wsdl.api.ExecuteRequest[].class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("execute", _params, new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExecuteResponseMsg"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ExecuteResponseMsg"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "Execute"));
        _oper.setSoapAction("Execute");
        _myOperationsList.add(_oper);
        if (_myOperations.get("execute") == null) {
            _myOperations.put("execute", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("execute")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PerformRequestMsg"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">PerformRequestMsg"), br.flieger.exacttarget.wsdl.api.PerformRequestMsg.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("perform", _params, new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PerformResponseMsg"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">PerformResponseMsg"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "Perform"));
        _oper.setSoapAction("Perform");
        _myOperationsList.add(_oper);
        if (_myOperations.get("perform") == null) {
            _myOperations.put("perform", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("perform")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ConfigureRequestMsg"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ConfigureRequestMsg"), br.flieger.exacttarget.wsdl.api.ConfigureRequestMsg.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("configure", _params, new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ConfigureResponseMsg"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ConfigureResponseMsg"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "Configure"));
        _oper.setSoapAction("Configure");
        _myOperationsList.add(_oper);
        if (_myOperations.get("configure") == null) {
            _myOperations.put("configure", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("configure")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ScheduleRequestMsg"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ScheduleRequestMsg"), br.flieger.exacttarget.wsdl.api.ScheduleRequestMsg.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("schedule", _params, new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ScheduleResponseMsg"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ScheduleResponseMsg"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "Schedule"));
        _oper.setSoapAction("Schedule");
        _myOperationsList.add(_oper);
        if (_myOperations.get("schedule") == null) {
            _myOperations.put("schedule", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("schedule")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "VersionInfoRequestMsg"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">VersionInfoRequestMsg"), br.flieger.exacttarget.wsdl.api.VersionInfoRequestMsg.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("versionInfo", _params, new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "VersionInfoResponseMsg"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">VersionInfoResponseMsg"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "VersionInfo"));
        _oper.setSoapAction("VersionInfo");
        _myOperationsList.add(_oper);
        if (_myOperations.get("versionInfo") == null) {
            _myOperations.put("versionInfo", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("versionInfo")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtractRequestMsg"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ExtractRequestMsg"), br.flieger.exacttarget.wsdl.api.ExtractRequest[].class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("extract", _params, new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtractResponseMsg"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ExtractResponseMsg"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "Extract"));
        _oper.setSoapAction("Extract");
        _myOperationsList.add(_oper);
        if (_myOperations.get("extract") == null) {
            _myOperations.put("extract", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("extract")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SystemStatusRequestMsg"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">SystemStatusRequestMsg"), br.flieger.exacttarget.wsdl.api.SystemStatusRequestMsg.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getSystemStatus", _params, new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SystemStatusResponseMsg"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">SystemStatusResponseMsg"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "GetSystemStatus"));
        _oper.setSoapAction("GetSystemStatus");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getSystemStatus") == null) {
            _myOperations.put("getSystemStatus", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getSystemStatus")).add(_oper);
    }

    public SoapBindingSkeleton() {
        this.impl = new br.flieger.exacttarget.wsdl.api.SoapBindingImpl();
    }

    public SoapBindingSkeleton(br.flieger.exacttarget.wsdl.api.Soap impl) {
        this.impl = impl;
    }
    public br.flieger.exacttarget.wsdl.api.CreateResponse create(br.flieger.exacttarget.wsdl.api.CreateRequest parameters) throws java.rmi.RemoteException
    {
        br.flieger.exacttarget.wsdl.api.CreateResponse ret = impl.create(parameters);
        return ret;
    }

    public br.flieger.exacttarget.wsdl.api.RetrieveResponseMsg retrieve(br.flieger.exacttarget.wsdl.api.RetrieveRequestMsg parameters) throws java.rmi.RemoteException
    {
        br.flieger.exacttarget.wsdl.api.RetrieveResponseMsg ret = impl.retrieve(parameters);
        return ret;
    }

    public br.flieger.exacttarget.wsdl.api.UpdateResponse update(br.flieger.exacttarget.wsdl.api.UpdateRequest parameters) throws java.rmi.RemoteException
    {
        br.flieger.exacttarget.wsdl.api.UpdateResponse ret = impl.update(parameters);
        return ret;
    }

    public br.flieger.exacttarget.wsdl.api.DeleteResponse delete(br.flieger.exacttarget.wsdl.api.DeleteRequest parameters) throws java.rmi.RemoteException
    {
        br.flieger.exacttarget.wsdl.api.DeleteResponse ret = impl.delete(parameters);
        return ret;
    }

    public br.flieger.exacttarget.wsdl.api.QueryResponseMsg query(br.flieger.exacttarget.wsdl.api.QueryRequestMsg parameters) throws java.rmi.RemoteException
    {
        br.flieger.exacttarget.wsdl.api.QueryResponseMsg ret = impl.query(parameters);
        return ret;
    }

    public br.flieger.exacttarget.wsdl.api.DefinitionResponseMsg describe(br.flieger.exacttarget.wsdl.api.DefinitionRequestMsg parameters) throws java.rmi.RemoteException
    {
        br.flieger.exacttarget.wsdl.api.DefinitionResponseMsg ret = impl.describe(parameters);
        return ret;
    }

    public br.flieger.exacttarget.wsdl.api.ExecuteResponseMsg execute(br.flieger.exacttarget.wsdl.api.ExecuteRequest[] parameters) throws java.rmi.RemoteException
    {
        br.flieger.exacttarget.wsdl.api.ExecuteResponseMsg ret = impl.execute(parameters);
        return ret;
    }

    public br.flieger.exacttarget.wsdl.api.PerformResponseMsg perform(br.flieger.exacttarget.wsdl.api.PerformRequestMsg parameters) throws java.rmi.RemoteException
    {
        br.flieger.exacttarget.wsdl.api.PerformResponseMsg ret = impl.perform(parameters);
        return ret;
    }

    public br.flieger.exacttarget.wsdl.api.ConfigureResponseMsg configure(br.flieger.exacttarget.wsdl.api.ConfigureRequestMsg parameters) throws java.rmi.RemoteException
    {
        br.flieger.exacttarget.wsdl.api.ConfigureResponseMsg ret = impl.configure(parameters);
        return ret;
    }

    public br.flieger.exacttarget.wsdl.api.ScheduleResponseMsg schedule(br.flieger.exacttarget.wsdl.api.ScheduleRequestMsg parameters) throws java.rmi.RemoteException
    {
        br.flieger.exacttarget.wsdl.api.ScheduleResponseMsg ret = impl.schedule(parameters);
        return ret;
    }

    public br.flieger.exacttarget.wsdl.api.VersionInfoResponseMsg versionInfo(br.flieger.exacttarget.wsdl.api.VersionInfoRequestMsg parameters) throws java.rmi.RemoteException
    {
        br.flieger.exacttarget.wsdl.api.VersionInfoResponseMsg ret = impl.versionInfo(parameters);
        return ret;
    }

    public br.flieger.exacttarget.wsdl.api.ExtractResponseMsg extract(br.flieger.exacttarget.wsdl.api.ExtractRequest[] parameters) throws java.rmi.RemoteException
    {
        br.flieger.exacttarget.wsdl.api.ExtractResponseMsg ret = impl.extract(parameters);
        return ret;
    }

    public br.flieger.exacttarget.wsdl.api.SystemStatusResponseMsg getSystemStatus(br.flieger.exacttarget.wsdl.api.SystemStatusRequestMsg parameters) throws java.rmi.RemoteException
    {
        br.flieger.exacttarget.wsdl.api.SystemStatusResponseMsg ret = impl.getSystemStatus(parameters);
        return ret;
    }

}
