/**
 * RecurringHttpBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adyen.services.recurring;

public class RecurringHttpBindingStub extends org.apache.axis.client.Stub implements com.adyen.services.recurring.RecurringPortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[6];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deactivateRecurring");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "recurringRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://recurring.services.adyen.com", "RecurringRequest"), com.adyen.services.recurring.RecurringRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "RecurringResult"));
        oper.setReturnClass(com.adyen.services.recurring.RecurringResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "recurringResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://recurring.services.adyen.com", "ServiceException"),
                      "com.adyen.services.common.ServiceException",
                      new javax.xml.namespace.QName("http://common.services.adyen.com", "ServiceException"), 
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("disable");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://recurring.services.adyen.com", "DisableRequest"), com.adyen.services.recurring.DisableRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "DisableResult"));
        oper.setReturnClass(com.adyen.services.recurring.DisableResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://recurring.services.adyen.com", "ServiceException"),
                      "com.adyen.services.common.ServiceException",
                      new javax.xml.namespace.QName("http://common.services.adyen.com", "ServiceException"), 
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("initialiseRecurring");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "recurringRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://recurring.services.adyen.com", "RecurringRequest"), com.adyen.services.recurring.RecurringRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "RecurringResult"));
        oper.setReturnClass(com.adyen.services.recurring.RecurringResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "recurringResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://recurring.services.adyen.com", "ServiceException"),
                      "com.adyen.services.common.ServiceException",
                      new javax.xml.namespace.QName("http://common.services.adyen.com", "ServiceException"), 
                      true
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listRecurringDetails");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://recurring.services.adyen.com", "RecurringDetailsRequest"), com.adyen.services.recurring.RecurringDetailsRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "RecurringDetailsResult"));
        oper.setReturnClass(com.adyen.services.recurring.RecurringDetailsResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://recurring.services.adyen.com", "ServiceException"),
                      "com.adyen.services.common.ServiceException",
                      new javax.xml.namespace.QName("http://common.services.adyen.com", "ServiceException"), 
                      true
                     ));
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("storeToken");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://recurring.services.adyen.com", "StoreTokenRequest"), com.adyen.services.recurring.StoreTokenRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "StoreTokenResult"));
        oper.setReturnClass(com.adyen.services.recurring.StoreTokenResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://recurring.services.adyen.com", "ServiceException"),
                      "com.adyen.services.common.ServiceException",
                      new javax.xml.namespace.QName("http://common.services.adyen.com", "ServiceException"), 
                      true
                     ));
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("submitRecurring");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "recurringRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://recurring.services.adyen.com", "RecurringRequest"), com.adyen.services.recurring.RecurringRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "RecurringResult"));
        oper.setReturnClass(com.adyen.services.recurring.RecurringResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "recurringResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://recurring.services.adyen.com", "ServiceException"),
                      "com.adyen.services.common.ServiceException",
                      new javax.xml.namespace.QName("http://common.services.adyen.com", "ServiceException"), 
                      true
                     ));
        _operations[5] = oper;

    }

    public RecurringHttpBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public RecurringHttpBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public RecurringHttpBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://common.services.adyen.com", ">Amount>currency");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://common.services.adyen.com", "Address");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.common.Address.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://common.services.adyen.com", "Amount");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.common.Amount.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://common.services.adyen.com", "Error");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.common.Error.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://common.services.adyen.com", "ServiceException");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.common.ServiceException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://common.services.adyen.com", "Type");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.common.Type.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", ">Card>cvc");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", ">Card>expiryMonth");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", ">Card>expiryYear");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", ">Card>holderName");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", ">Card>issueNumber");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", ">Card>number");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", ">Card>startMonth");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", ">Card>startYear");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", "BankAccount");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.BankAccount.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", "Card");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.Card.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", "ELV");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.ELV.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", "Recurring");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.Recurring.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://recurring.services.adyen.com", "ArrayOfRecurringDetail");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.recurring.RecurringDetail[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://recurring.services.adyen.com", "RecurringDetail");
            qName2 = new javax.xml.namespace.QName("http://recurring.services.adyen.com", "RecurringDetail");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://recurring.services.adyen.com", "DisableRequest");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.recurring.DisableRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://recurring.services.adyen.com", "DisableResult");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.recurring.DisableResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://recurring.services.adyen.com", "RecurringDetail");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.recurring.RecurringDetail.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://recurring.services.adyen.com", "RecurringDetailsRequest");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.recurring.RecurringDetailsRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://recurring.services.adyen.com", "RecurringDetailsResult");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.recurring.RecurringDetailsResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://recurring.services.adyen.com", "RecurringRequest");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.recurring.RecurringRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://recurring.services.adyen.com", "RecurringResult");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.recurring.RecurringResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://recurring.services.adyen.com", "StoreTokenRequest");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.recurring.StoreTokenRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://recurring.services.adyen.com", "StoreTokenResult");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.recurring.StoreTokenResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

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

    public com.adyen.services.recurring.RecurringResult deactivateRecurring(com.adyen.services.recurring.RecurringRequest recurringRequest) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "deactivateRecurring"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {recurringRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.adyen.services.recurring.RecurringResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.adyen.services.recurring.RecurringResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.adyen.services.recurring.RecurringResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.adyen.services.common.ServiceException) {
              throw (com.adyen.services.common.ServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.adyen.services.recurring.DisableResult disable(com.adyen.services.recurring.DisableRequest request) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "disable"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.adyen.services.recurring.DisableResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.adyen.services.recurring.DisableResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.adyen.services.recurring.DisableResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.adyen.services.common.ServiceException) {
              throw (com.adyen.services.common.ServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.adyen.services.recurring.RecurringResult initialiseRecurring(com.adyen.services.recurring.RecurringRequest recurringRequest) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "initialiseRecurring"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {recurringRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.adyen.services.recurring.RecurringResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.adyen.services.recurring.RecurringResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.adyen.services.recurring.RecurringResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.adyen.services.common.ServiceException) {
              throw (com.adyen.services.common.ServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.adyen.services.recurring.RecurringDetailsResult listRecurringDetails(com.adyen.services.recurring.RecurringDetailsRequest request) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "listRecurringDetails"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.adyen.services.recurring.RecurringDetailsResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.adyen.services.recurring.RecurringDetailsResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.adyen.services.recurring.RecurringDetailsResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.adyen.services.common.ServiceException) {
              throw (com.adyen.services.common.ServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.adyen.services.recurring.StoreTokenResult storeToken(com.adyen.services.recurring.StoreTokenRequest request) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "storeToken"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.adyen.services.recurring.StoreTokenResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.adyen.services.recurring.StoreTokenResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.adyen.services.recurring.StoreTokenResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.adyen.services.common.ServiceException) {
              throw (com.adyen.services.common.ServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.adyen.services.recurring.RecurringResult submitRecurring(com.adyen.services.recurring.RecurringRequest recurringRequest) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "submitRecurring"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {recurringRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.adyen.services.recurring.RecurringResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.adyen.services.recurring.RecurringResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.adyen.services.recurring.RecurringResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.adyen.services.common.ServiceException) {
              throw (com.adyen.services.common.ServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

}
