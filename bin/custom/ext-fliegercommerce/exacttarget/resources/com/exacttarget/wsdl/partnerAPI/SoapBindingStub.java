/**
 * SoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class SoapBindingStub extends org.apache.axis.client.Stub implements com.exacttarget.wsdl.partnerAPI.Soap {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[13];
        _initOperationDesc1();
        _initOperationDesc2();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Create");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CreateRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">CreateRequest"), com.exacttarget.wsdl.partnerAPI.CreateRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">CreateResponse"));
        oper.setReturnClass(com.exacttarget.wsdl.partnerAPI.CreateResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CreateResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Retrieve");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RetrieveRequestMsg"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">RetrieveRequestMsg"), com.exacttarget.wsdl.partnerAPI.RetrieveRequestMsg.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">RetrieveResponseMsg"));
        oper.setReturnClass(com.exacttarget.wsdl.partnerAPI.RetrieveResponseMsg.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RetrieveResponseMsg"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Update");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UpdateRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">UpdateRequest"), com.exacttarget.wsdl.partnerAPI.UpdateRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">UpdateResponse"));
        oper.setReturnClass(com.exacttarget.wsdl.partnerAPI.UpdateResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UpdateResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Delete");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DeleteRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">DeleteRequest"), com.exacttarget.wsdl.partnerAPI.DeleteRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">DeleteResponse"));
        oper.setReturnClass(com.exacttarget.wsdl.partnerAPI.DeleteResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DeleteResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Query");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "QueryRequestMsg"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">QueryRequestMsg"), com.exacttarget.wsdl.partnerAPI.QueryRequestMsg.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">QueryResponseMsg"));
        oper.setReturnClass(com.exacttarget.wsdl.partnerAPI.QueryResponseMsg.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "QueryResponseMsg"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Describe");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DefinitionRequestMsg"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">DefinitionRequestMsg"), com.exacttarget.wsdl.partnerAPI.DefinitionRequestMsg.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">DefinitionResponseMsg"));
        oper.setReturnClass(com.exacttarget.wsdl.partnerAPI.DefinitionResponseMsg.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DefinitionResponseMsg"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Execute");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExecuteRequestMsg"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ExecuteRequestMsg"), com.exacttarget.wsdl.partnerAPI.ExecuteRequest[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Requests"));
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ExecuteResponseMsg"));
        oper.setReturnClass(com.exacttarget.wsdl.partnerAPI.ExecuteResponseMsg.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExecuteResponseMsg"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Perform");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PerformRequestMsg"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">PerformRequestMsg"), com.exacttarget.wsdl.partnerAPI.PerformRequestMsg.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">PerformResponseMsg"));
        oper.setReturnClass(com.exacttarget.wsdl.partnerAPI.PerformResponseMsg.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PerformResponseMsg"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Configure");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ConfigureRequestMsg"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ConfigureRequestMsg"), com.exacttarget.wsdl.partnerAPI.ConfigureRequestMsg.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ConfigureResponseMsg"));
        oper.setReturnClass(com.exacttarget.wsdl.partnerAPI.ConfigureResponseMsg.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ConfigureResponseMsg"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Schedule");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ScheduleRequestMsg"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ScheduleRequestMsg"), com.exacttarget.wsdl.partnerAPI.ScheduleRequestMsg.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ScheduleResponseMsg"));
        oper.setReturnClass(com.exacttarget.wsdl.partnerAPI.ScheduleResponseMsg.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ScheduleResponseMsg"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("VersionInfo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "VersionInfoRequestMsg"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">VersionInfoRequestMsg"), com.exacttarget.wsdl.partnerAPI.VersionInfoRequestMsg.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">VersionInfoResponseMsg"));
        oper.setReturnClass(com.exacttarget.wsdl.partnerAPI.VersionInfoResponseMsg.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "VersionInfoResponseMsg"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Extract");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtractRequestMsg"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ExtractRequestMsg"), com.exacttarget.wsdl.partnerAPI.ExtractRequest[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Requests"));
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ExtractResponseMsg"));
        oper.setReturnClass(com.exacttarget.wsdl.partnerAPI.ExtractResponseMsg.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtractResponseMsg"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetSystemStatus");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SystemStatusRequestMsg"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">SystemStatusRequestMsg"), com.exacttarget.wsdl.partnerAPI.SystemStatusRequestMsg.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">SystemStatusResponseMsg"));
        oper.setReturnClass(com.exacttarget.wsdl.partnerAPI.SystemStatusResponseMsg.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SystemStatusResponseMsg"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[12] = oper;

    }

    public SoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public SoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public SoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
        addBindings0();
        addBindings1();
        addBindings2();
        addBindings3();
    }

    private void addBindings0() {
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">>ConfigureRequestMsg>Configurations");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.APIObject[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIObject");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Configuration");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">>ConfigureResponseMsg>Results");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ConfigureResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ConfigureResult");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Result");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">>PerformRequestMsg>Definitions");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.APIObject[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIObject");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Definition");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">>PerformResponseMsg>Results");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.PerformResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PerformResult");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Result");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">>ScheduleRequestMsg>Interactions");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.APIObject[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIObject");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Interaction");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">>ScheduleResponseMsg>Results");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ScheduleResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ScheduleResult");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Result");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">>SystemStatusResponseMsg>Results");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SystemStatusResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SystemStatusResult");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Result");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">Account>Roles");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.Role[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Role");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Role");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">AccountUser>AssociatedBusinessUnits");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.BusinessUnit[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BusinessUnit");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BusinessUnit");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">AccountUser>Roles");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.Role[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Role");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Role");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">AccountUser>SsoIdentities");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SsoIdentity[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SsoIdentity");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SsoIdentity");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">Automation>AutomationTasks");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.AutomationTask[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationTask");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationTask");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">Automation>Notifications");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.AutomationNotification[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationNotification");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Notification");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">AutomationInstance>TaskInstances");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.AutomationTaskInstance[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationTaskInstance");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationTaskInstance");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">AutomationInstances>AutomationInstanceCollection");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.AutomationInstance[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationInstance");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationInstance");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">AutomationTask>Activities");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.AutomationActivity[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationActivity");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Activity");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">AutomationTaskInstance>ActivityInstances");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.AutomationActivityInstance[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationActivityInstance");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ActivityInstance");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ComplexFilterPart>AdditionalOperands");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.FilterPart[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FilterPart");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Operand");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ConfigureRequestMsg");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ConfigureRequestMsg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ConfigureResponseMsg");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ConfigureResponseMsg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ContentValidation>Subscribers");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.Subscriber[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Subscriber");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Subscriber");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ContentValidationTaskResult>ValidationResults");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ValidationResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ValidationResult");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ValidationResult");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">CreateRequest");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.CreateRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">CreateResponse");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.CreateResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">DataExtension>Fields");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.DataExtensionField[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionField");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Field");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">DataExtensionCreateResult>KeyErrors");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.DataExtensionError[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionError");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "KeyError");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">DataExtensionCreateResult>ValueErrors");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.DataExtensionError[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionError");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ValueError");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">DataExtensionDeleteResult>KeyErrors");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.DataExtensionError[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionError");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "KeyError");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">DataExtensionObject>Keys");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.APIProperty[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIProperty");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Key");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">DataExtensionUpdateResult>KeyErrors");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.DataExtensionError[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionError");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "KeyError");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">DataExtensionUpdateResult>ValueErrors");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.DataExtensionError[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionError");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ValueError");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">DefinitionRequestMsg");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.DefinitionRequestMsg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">DefinitionResponseMsg");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.DefinitionResponseMsg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">DeleteRequest");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.DeleteRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">DeleteResponse");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.DeleteResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">DoubleOptInMOKeyword>ValidPublications");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.List[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "List");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ValidPublication");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">DoubleOptInMOKeyword>ValidResponses");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ValidResponse");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">EmailSendDefinition>TrackingUsers");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.TrackingUser[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TrackingUser");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TrackingUser");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ExecuteRequestMsg");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ExecuteRequest[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExecuteRequest");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Requests");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ExecuteResponseMsg");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ExecuteResponseMsg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ExtractDefinition>Parameters");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ExtractParameterDescription[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtractParameterDescription");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Parameter");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ExtractDefinition>Values");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.APIProperty[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIProperty");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Value");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ExtractDescription>Parameters");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ExtractParameterDescription[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtractParameterDescription");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Parameter");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ExtractRequest>Parameters");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ExtractParameter[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtractParameter");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Parameter");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ExtractRequestMsg");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ExtractRequest[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtractRequest");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Requests");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ExtractResponseMsg");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ExtractResponseMsg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ImportDefinition>ControlColumnActions");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ImportDefinitionColumnBasedAction[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ImportDefinitionColumnBasedAction");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ControlColumnAction");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ImportDefinition>FieldMaps");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.FieldMap[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FieldMap");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FieldMap");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ObjectDefinition>ExtendedProperties");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.PropertyDefinition[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PropertyDefinition");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtendedProperty");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ObjectExtension>Properties");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.APIProperty[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIProperty");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Property");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">Options>SaveOptions");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SaveOption[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SaveOption");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SaveOption");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">PerformRequestMsg");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.PerformRequestMsg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">PerformResponseMsg");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.PerformResponseMsg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">PermissionSet>Permissions");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.Permission[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Permission");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Permission");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">PermissionSet>PermissionSets");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.PermissionSet[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PermissionSet");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PermissionSet");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">PropertyDefinition>PicklistItems");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.PicklistItem[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PicklistItem");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PicklistItem");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">PropertyDefinition>References");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.APIObject[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIObject");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Reference");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">Publication>Subscribers");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.Subscriber[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Subscriber");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Subscriber");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">QueryRequestMsg");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.QueryRequestMsg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">QueryResponseMsg");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.QueryResponseMsg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">RetrieveRequest>Retrieves");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.Request[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Request");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Request");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">RetrieveRequestMsg");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.RetrieveRequestMsg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">RetrieveResponseMsg");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.RetrieveResponseMsg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">Role>Permissions");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.Permission[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Permission");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Permission");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">Role>PermissionSets");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.PermissionSet[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PermissionSet");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PermissionSet");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ScheduleRequestMsg");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ScheduleRequestMsg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ScheduleResponseMsg");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ScheduleResponseMsg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">Send>Sources");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.APIObject[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIObject");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Source");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">Send>SuppressionLists");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.AudienceItem[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AudienceItem");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SuppressionList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">SendDefinitionList>Parameters");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.APIProperty[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIProperty");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Parameter");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">Subscriber>Addresses");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SubscriberAddress[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SubscriberAddress");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Address");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">SubscriberAddress>Statuses");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.AddressStatus[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AddressStatus");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Status");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">SuppressionListData>Properties");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.APIProperty[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIProperty");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Property");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">SuppressionListDefinition>Contexts");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SuppressionListContext[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SuppressionListContext");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Context");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">SuppressionListDefinition>Fields");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.DataExtensionField[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionField");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Field");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">SystemStatusRequestMsg");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SystemStatusRequestMsg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">SystemStatusResponseMsg");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SystemStatusResponseMsg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">SystemStatusResult>Outages");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SystemOutage[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SystemOutage");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Outage");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">TagFilterPart>Tags");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Tag");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">UpdateRequest");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.UpdateRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">UpdateResponse");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.UpdateResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ValidationAction>ValidationOptions");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.APIProperty[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIProperty");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ValidationOption");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">VersionInfoRequestMsg");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.VersionInfoRequestMsg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">VersionInfoResponseMsg");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.VersionInfoResponseMsg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Account");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.Account.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AccountDataItem");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.AccountDataItem.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AccountPrivateLabel");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.AccountPrivateLabel.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AccountTypeEnum");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.AccountTypeEnum.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AccountUser");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.AccountUser.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AddressStatus");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.AddressStatus.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIObject");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.APIObject.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIProperty");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.APIProperty.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ArrayOfObjectDefinitionRequest");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ObjectDefinitionRequest[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ObjectDefinitionRequest");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ObjectDefinitionRequest");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AsyncRequestResult");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.AsyncRequestResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AsyncResponse");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.AsyncResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AsyncResponseType");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.AsyncResponseType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Attribute");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.Attribute.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AttributeMap");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.AttributeMap.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AudienceItem");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.AudienceItem.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Authentication");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.Authentication.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }
    private void addBindings1() {
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Automation");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.Automation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationActivity");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.AutomationActivity.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationActivityInstance");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.AutomationActivityInstance.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationInstance");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.AutomationInstance.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationInstances");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.AutomationInstances.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationNotification");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.AutomationNotification.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationSource");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.AutomationSource.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationTask");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.AutomationTask.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationTaskInstance");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.AutomationTaskInstance.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BaseMOKeyword");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.BaseMOKeyword.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BounceEvent");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.BounceEvent.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Brand");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.Brand.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BrandTag");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.BrandTag.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BusinessRule");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.BusinessRule.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BusinessUnit");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.BusinessUnit.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Campaign");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.Campaign.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CampaignPerformOptions");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.CampaignPerformOptions.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Certificate");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.Certificate.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ClickEvent");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ClickEvent.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ClientID");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ClientID.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ComplexFilterPart");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ComplexFilterPart.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CompressionConfiguration");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.CompressionConfiguration.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CompressionEncoding");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.CompressionEncoding.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CompressionType");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.CompressionType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ConfigureOptions");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ConfigureOptions.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ConfigureResult");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ConfigureResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ContainerID");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ContainerID.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ContentArea");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ContentArea.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ContentValidation");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ContentValidation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ContentValidationResult");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ContentValidationResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ContentValidationTaskResult");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ContentValidationTaskResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CreateOptions");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.CreateOptions.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CreateResult");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.CreateResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DailyRecurrence");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.DailyRecurrence.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DailyRecurrencePatternTypeEnum");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.DailyRecurrencePatternTypeEnum.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtension");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.DataExtension.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionCreateResult");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.DataExtensionCreateResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionDeleteResult");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.DataExtensionDeleteResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionError");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.DataExtensionError.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionField");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.DataExtensionField.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionFieldType");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.DataExtensionFieldType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionObject");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.DataExtensionObject.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionTemplate");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.DataExtensionTemplate.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionUpdateResult");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.DataExtensionUpdateResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtractActivity");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.DataExtractActivity.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataFolder");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.DataFolder.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataSourceTypeEnum");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.DataSourceTypeEnum.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DateTimeUnitOfMeasure");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.DateTimeUnitOfMeasure.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DayOfWeekEnum");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.DayOfWeekEnum.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DeleteOptions");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.DeleteOptions.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DeleteResult");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.DeleteResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DeliveredEvent");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.DeliveredEvent.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DeliveryProfile");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.DeliveryProfile.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DeliveryProfileDomainTypeEnum");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.DeliveryProfileDomainTypeEnum.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DeliveryProfileSourceAddressTypeEnum");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.DeliveryProfileSourceAddressTypeEnum.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DoubleOptInMOKeyword");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.DoubleOptInMOKeyword.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Email");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.Email.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "EmailAddress");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.EmailAddress.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "EmailSendDefinition");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.EmailSendDefinition.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "EmailType");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.EmailType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "EventType");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.EventType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExecuteRequest");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ExecuteRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExecuteResponse");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ExecuteResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtractDefinition");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ExtractDefinition.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtractDescription");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ExtractDescription.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtractOptions");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ExtractOptions.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtractParameter");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ExtractParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtractParameterDataType");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ExtractParameterDataType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtractParameterDescription");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ExtractParameterDescription.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtractRequest");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ExtractRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtractResult");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ExtractResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtractTemplate");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ExtractTemplate.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FieldMap");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.FieldMap.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FileTransferActivity");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.FileTransferActivity.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FileTransferLocation");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.FileTransferLocation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FileTrigger");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.FileTrigger.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FileTriggerTypeLastPull");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.FileTriggerTypeLastPull.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FileType");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.FileType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FilterDefinition");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.FilterDefinition.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FilterPart");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.FilterPart.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Folder");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.Folder.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ForwardedEmailEvent");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ForwardedEmailEvent.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ForwardedEmailOptInEvent");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ForwardedEmailOptInEvent.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "GlobalUnsubscribeCategory");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.GlobalUnsubscribeCategory.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Group");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.Group.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "GroupDefinition");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.GroupDefinition.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "HelpMOKeyword");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.HelpMOKeyword.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "HourlyRecurrence");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.HourlyRecurrence.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "HourlyRecurrencePatternTypeEnum");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.HourlyRecurrencePatternTypeEnum.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ImportDefinition");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ImportDefinition.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ImportDefinitionAutoGenerateDestination");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ImportDefinitionAutoGenerateDestination.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ImportDefinitionColumnBasedAction");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ImportDefinitionColumnBasedAction.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ImportDefinitionColumnBasedActionType");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ImportDefinitionColumnBasedActionType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ImportDefinitionFieldMap");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ImportDefinitionFieldMap.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ImportDefinitionFieldMappingType");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ImportDefinitionFieldMappingType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ImportDefinitionSubscriberImportType");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ImportDefinitionSubscriberImportType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ImportDefinitionUpdateType");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ImportDefinitionUpdateType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ImportResultsSummary");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ImportResultsSummary.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IntegrationProfile");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.IntegrationProfile.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IntegrationProfileDefinition");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.IntegrationProfileDefinition.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }
    private void addBindings2() {
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "InteractionBaseObject");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.InteractionBaseObject.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "InteractionDefinition");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.InteractionDefinition.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "LandingPage");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.LandingPage.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Layout");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.Layout.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "LayoutType");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.LayoutType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Link");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.Link.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "LinkSend");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.LinkSend.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "List");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.List.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ListAttribute");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ListAttribute.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ListAttributeFieldType");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ListAttributeFieldType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ListAttributeRestrictedValue");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ListAttributeRestrictedValue.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ListClassificationEnum");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ListClassificationEnum.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ListSend");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ListSend.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ListSubscriber");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ListSubscriber.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ListTypeEnum");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ListTypeEnum.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Locale");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.Locale.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "LogicalOperators");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.LogicalOperators.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Message");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.Message.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MessageDeliveryTypeEnum");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.MessageDeliveryTypeEnum.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MessageSendActivity");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.MessageSendActivity.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MessagingConfiguration");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.MessagingConfiguration.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MessagingVendorKind");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.MessagingVendorKind.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MinutelyRecurrence");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.MinutelyRecurrence.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MinutelyRecurrencePatternTypeEnum");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.MinutelyRecurrencePatternTypeEnum.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MobileConnectRefreshListActivity");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.MobileConnectRefreshListActivity.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MobileConnectSendSmsActivity");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.MobileConnectSendSmsActivity.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MobilePushSendMessageActivity");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.MobilePushSendMessageActivity.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MonthlyRecurrence");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.MonthlyRecurrence.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MonthlyRecurrencePatternTypeEnum");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.MonthlyRecurrencePatternTypeEnum.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MonthOfYearEnum");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.MonthOfYearEnum.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "NotSentEvent");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.NotSentEvent.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "NullAPIProperty");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.NullAPIProperty.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ObjectDefinition");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ObjectDefinition.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ObjectDefinitionRequest");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ObjectDefinitionRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ObjectExtension");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ObjectExtension.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "OpenEvent");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.OpenEvent.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Options");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.Options.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "OverrideType");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.OverrideType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Owner");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.Owner.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ParameterDescription");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ParameterDescription.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Parameters");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.APIProperty[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIProperty");
            qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Parameter");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PerformOptions");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.PerformOptions.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PerformResult");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.PerformResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Permission");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.Permission.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PermissionSet");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.PermissionSet.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PicklistItem");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.PicklistItem.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PlatformApplication");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.PlatformApplication.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PlatformApplicationPackage");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.PlatformApplicationPackage.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Portfolio");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.Portfolio.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Priority");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.Priority.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PrivateDomain");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.PrivateDomain.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PrivateDomainSet");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.PrivateDomainSet.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PrivateIP");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.PrivateIP.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PrivateLabel");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.PrivateLabel.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ProgramManifestTemplate");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ProgramManifestTemplate.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PropertyDefinition");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.PropertyDefinition.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PropertyType");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.PropertyType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Publication");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.Publication.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PublicationSubscriber");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.PublicationSubscriber.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PublicKeyManagement");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.PublicKeyManagement.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Query");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.Query.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "QueryDefinition");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.QueryDefinition.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "QueryObject");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.QueryObject.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "QueryRequest");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.QueryRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Recurrence");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.Recurrence.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RecurrenceRangeTypeEnum");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.RecurrenceRangeTypeEnum.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RecurrenceTypeEnum");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.RecurrenceTypeEnum.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ReplyMailManagementConfiguration");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ReplyMailManagementConfiguration.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ReportActivity");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ReportActivity.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Request");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RequestType");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.RequestType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ResourceSpecification");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ResourceSpecification.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RespondWhen");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.RespondWhen.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Result");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.Result.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ResultItem");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ResultItem.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ResultMessage");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ResultMessage.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RetrieveOptions");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.RetrieveOptions.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RetrieveRequest");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.RetrieveRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RetrieveSingleOptions");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.RetrieveSingleOptions.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RetrieveSingleRequest");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.RetrieveSingleRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Role");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.Role.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SalutationSourceEnum");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SalutationSourceEnum.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SaveAction");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SaveAction.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SaveOption");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SaveOption.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ScheduleDefinition");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ScheduleDefinition.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ScheduleOptions");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ScheduleOptions.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ScheduleResult");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ScheduleResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SecurityObject");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SecurityObject.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Send");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.Send.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendAdditionalAttribute");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SendAdditionalAttribute.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendClassification");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SendClassification.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendClassificationTypeEnum");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SendClassificationTypeEnum.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendDefinition");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SendDefinition.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendDefinitionList");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SendDefinitionList.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendDefinitionListTypeEnum");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SendDefinitionListTypeEnum.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendEmailMOKeyword");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SendEmailMOKeyword.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SenderProfile");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SenderProfile.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendPriorityEnum");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SendPriorityEnum.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendSMSMOKeyword");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SendSMSMOKeyword.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendSummary");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SendSummary.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }
    private void addBindings3() {
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SentEvent");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SentEvent.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SimpleFilterPart");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SimpleFilterPart.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SimpleOperators");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SimpleOperators.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SMSAddress");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SMSAddress.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SMSMOEvent");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SMSMOEvent.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SMSMTEvent");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SMSMTEvent.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SmsSendActivity");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SmsSendActivity.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SMSSharedKeyword");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SMSSharedKeyword.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SMSTriggeredSend");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SMSTriggeredSend.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SMSTriggeredSendDefinition");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SMSTriggeredSendDefinition.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SoapType");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SoapType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SpamAssassinValidation");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SpamAssassinValidation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SsoIdentity");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SsoIdentity.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Subscriber");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.Subscriber.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SubscriberAddress");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SubscriberAddress.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SubscriberAddressStatus");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SubscriberAddressStatus.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SubscriberList");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SubscriberList.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SubscriberResult");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SubscriberResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SubscriberSendResult");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SubscriberSendResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SubscriberStatus");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SubscriberStatus.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SubscriberTypeDefinition");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SubscriberTypeDefinition.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Subscription");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.Subscription.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SuppressionListContext");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SuppressionListContext.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SuppressionListContextEnum");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SuppressionListContextEnum.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SuppressionListData");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SuppressionListData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SuppressionListDefinition");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SuppressionListDefinition.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SurveyEvent");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SurveyEvent.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SystemOutage");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SystemOutage.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SystemStatusOptions");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SystemStatusOptions.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SystemStatusResult");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SystemStatusResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SystemStatusType");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.SystemStatusType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TagFilterPart");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.TagFilterPart.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TaskResult");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.TaskResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Template");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.Template.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TimeZone");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.TimeZone.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TrackingEvent");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.TrackingEvent.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TrackingUser");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.TrackingUser.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TriggeredSend");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.TriggeredSend.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TriggeredSendCreateResult");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.TriggeredSendCreateResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TriggeredSendDefinition");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.TriggeredSendDefinition.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TriggeredSendExclusionList");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.TriggeredSendExclusionList.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TriggeredSendStatusEnum");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.TriggeredSendStatusEnum.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TriggeredSendSummary");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.TriggeredSendSummary.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TriggeredSendTypeEnum");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.TriggeredSendTypeEnum.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UnsubEvent");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.UnsubEvent.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UnsubscribeBehaviorEnum");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.UnsubscribeBehaviorEnum.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UnsubscribeFromSMSPublicationMOKeyword");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.UnsubscribeFromSMSPublicationMOKeyword.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UpdateOptions");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.UpdateOptions.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UpdateResult");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.UpdateResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UserAccess");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.UserAccess.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UserMap");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.UserMap.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UsernameAuthentication");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.UsernameAuthentication.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ValidationAction");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ValidationAction.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ValidationResult");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.ValidationResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "VersionInfoResponse");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.VersionInfoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "VoiceTriggeredSend");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.VoiceTriggeredSend.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "VoiceTriggeredSendDefinition");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.VoiceTriggeredSendDefinition.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "WeeklyRecurrence");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.WeeklyRecurrence.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "WeeklyRecurrencePatternTypeEnum");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.WeeklyRecurrencePatternTypeEnum.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "WeekOfMonthEnum");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.WeekOfMonthEnum.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "YearlyRecurrence");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.YearlyRecurrence.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "YearlyRecurrencePatternTypeEnum");
            cachedSerQNames.add(qName);
            cls = com.exacttarget.wsdl.partnerAPI.YearlyRecurrencePatternTypeEnum.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public com.exacttarget.wsdl.partnerAPI.CreateResponse create(com.exacttarget.wsdl.partnerAPI.CreateRequest parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("Create");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Create"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.exacttarget.wsdl.partnerAPI.CreateResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.exacttarget.wsdl.partnerAPI.CreateResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.exacttarget.wsdl.partnerAPI.CreateResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.exacttarget.wsdl.partnerAPI.RetrieveResponseMsg retrieve(com.exacttarget.wsdl.partnerAPI.RetrieveRequestMsg parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("Retrieve");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Retrieve"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.exacttarget.wsdl.partnerAPI.RetrieveResponseMsg) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.exacttarget.wsdl.partnerAPI.RetrieveResponseMsg) org.apache.axis.utils.JavaUtils.convert(_resp, com.exacttarget.wsdl.partnerAPI.RetrieveResponseMsg.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.exacttarget.wsdl.partnerAPI.UpdateResponse update(com.exacttarget.wsdl.partnerAPI.UpdateRequest parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("Update");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Update"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.exacttarget.wsdl.partnerAPI.UpdateResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.exacttarget.wsdl.partnerAPI.UpdateResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.exacttarget.wsdl.partnerAPI.UpdateResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.exacttarget.wsdl.partnerAPI.DeleteResponse delete(com.exacttarget.wsdl.partnerAPI.DeleteRequest parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("Delete");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Delete"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.exacttarget.wsdl.partnerAPI.DeleteResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.exacttarget.wsdl.partnerAPI.DeleteResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.exacttarget.wsdl.partnerAPI.DeleteResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.exacttarget.wsdl.partnerAPI.QueryResponseMsg query(com.exacttarget.wsdl.partnerAPI.QueryRequestMsg parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("Query");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Query"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.exacttarget.wsdl.partnerAPI.QueryResponseMsg) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.exacttarget.wsdl.partnerAPI.QueryResponseMsg) org.apache.axis.utils.JavaUtils.convert(_resp, com.exacttarget.wsdl.partnerAPI.QueryResponseMsg.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.exacttarget.wsdl.partnerAPI.DefinitionResponseMsg describe(com.exacttarget.wsdl.partnerAPI.DefinitionRequestMsg parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("Describe");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Describe"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.exacttarget.wsdl.partnerAPI.DefinitionResponseMsg) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.exacttarget.wsdl.partnerAPI.DefinitionResponseMsg) org.apache.axis.utils.JavaUtils.convert(_resp, com.exacttarget.wsdl.partnerAPI.DefinitionResponseMsg.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.exacttarget.wsdl.partnerAPI.ExecuteResponseMsg execute(com.exacttarget.wsdl.partnerAPI.ExecuteRequest[] parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("Execute");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Execute"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.exacttarget.wsdl.partnerAPI.ExecuteResponseMsg) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.exacttarget.wsdl.partnerAPI.ExecuteResponseMsg) org.apache.axis.utils.JavaUtils.convert(_resp, com.exacttarget.wsdl.partnerAPI.ExecuteResponseMsg.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.exacttarget.wsdl.partnerAPI.PerformResponseMsg perform(com.exacttarget.wsdl.partnerAPI.PerformRequestMsg parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("Perform");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Perform"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.exacttarget.wsdl.partnerAPI.PerformResponseMsg) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.exacttarget.wsdl.partnerAPI.PerformResponseMsg) org.apache.axis.utils.JavaUtils.convert(_resp, com.exacttarget.wsdl.partnerAPI.PerformResponseMsg.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.exacttarget.wsdl.partnerAPI.ConfigureResponseMsg configure(com.exacttarget.wsdl.partnerAPI.ConfigureRequestMsg parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("Configure");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Configure"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.exacttarget.wsdl.partnerAPI.ConfigureResponseMsg) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.exacttarget.wsdl.partnerAPI.ConfigureResponseMsg) org.apache.axis.utils.JavaUtils.convert(_resp, com.exacttarget.wsdl.partnerAPI.ConfigureResponseMsg.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.exacttarget.wsdl.partnerAPI.ScheduleResponseMsg schedule(com.exacttarget.wsdl.partnerAPI.ScheduleRequestMsg parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("Schedule");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Schedule"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.exacttarget.wsdl.partnerAPI.ScheduleResponseMsg) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.exacttarget.wsdl.partnerAPI.ScheduleResponseMsg) org.apache.axis.utils.JavaUtils.convert(_resp, com.exacttarget.wsdl.partnerAPI.ScheduleResponseMsg.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.exacttarget.wsdl.partnerAPI.VersionInfoResponseMsg versionInfo(com.exacttarget.wsdl.partnerAPI.VersionInfoRequestMsg parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("VersionInfo");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "VersionInfo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.exacttarget.wsdl.partnerAPI.VersionInfoResponseMsg) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.exacttarget.wsdl.partnerAPI.VersionInfoResponseMsg) org.apache.axis.utils.JavaUtils.convert(_resp, com.exacttarget.wsdl.partnerAPI.VersionInfoResponseMsg.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.exacttarget.wsdl.partnerAPI.ExtractResponseMsg extract(com.exacttarget.wsdl.partnerAPI.ExtractRequest[] parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("Extract");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Extract"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.exacttarget.wsdl.partnerAPI.ExtractResponseMsg) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.exacttarget.wsdl.partnerAPI.ExtractResponseMsg) org.apache.axis.utils.JavaUtils.convert(_resp, com.exacttarget.wsdl.partnerAPI.ExtractResponseMsg.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.exacttarget.wsdl.partnerAPI.SystemStatusResponseMsg getSystemStatus(com.exacttarget.wsdl.partnerAPI.SystemStatusRequestMsg parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("GetSystemStatus");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "GetSystemStatus"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.exacttarget.wsdl.partnerAPI.SystemStatusResponseMsg) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.exacttarget.wsdl.partnerAPI.SystemStatusResponseMsg) org.apache.axis.utils.JavaUtils.convert(_resp, com.exacttarget.wsdl.partnerAPI.SystemStatusResponseMsg.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
