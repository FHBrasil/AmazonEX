/**
 * PaymentHttpBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adyen.services.payment;

public class PaymentHttpBindingStub extends org.apache.axis.client.Stub implements com.adyen.services.payment.PaymentPortType {
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
        oper.setName("checkFraud");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://payment.services.adyen.com", "paymentRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://payment.services.adyen.com", "PaymentRequest"), com.adyen.services.payment.PaymentRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://payment.services.adyen.com", "PaymentResult"));
        oper.setReturnClass(com.adyen.services.payment.PaymentResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "paymentResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://payment.services.adyen.com", "ServiceException"),
                      "com.adyen.services.common.ServiceException",
                      new javax.xml.namespace.QName("http://common.services.adyen.com", "ServiceException"), 
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("refundWithData");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://payment.services.adyen.com", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://payment.services.adyen.com", "PaymentRequest"), com.adyen.services.payment.PaymentRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://payment.services.adyen.com", "PaymentResult"));
        oper.setReturnClass(com.adyen.services.payment.PaymentResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://payment.services.adyen.com", "ServiceException"),
                      "com.adyen.services.common.ServiceException",
                      new javax.xml.namespace.QName("http://common.services.adyen.com", "ServiceException"), 
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("authorise");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://payment.services.adyen.com", "paymentRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://payment.services.adyen.com", "PaymentRequest"), com.adyen.services.payment.PaymentRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://payment.services.adyen.com", "PaymentResult"));
        oper.setReturnClass(com.adyen.services.payment.PaymentResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "paymentResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://payment.services.adyen.com", "ServiceException"),
                      "com.adyen.services.common.ServiceException",
                      new javax.xml.namespace.QName("http://common.services.adyen.com", "ServiceException"), 
                      true
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("authoriseReferral");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://payment.services.adyen.com", "modificationRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://payment.services.adyen.com", "ModificationRequest"), com.adyen.services.payment.ModificationRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://payment.services.adyen.com", "ModificationResult"));
        oper.setReturnClass(com.adyen.services.payment.ModificationResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "authoriseReferralResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://payment.services.adyen.com", "ServiceException"),
                      "com.adyen.services.common.ServiceException",
                      new javax.xml.namespace.QName("http://common.services.adyen.com", "ServiceException"), 
                      true
                     ));
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("directdebit");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://payment.services.adyen.com", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://payment.services.adyen.com", "DirectDebitRequest"), com.adyen.services.payment.DirectDebitRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://payment.services.adyen.com", "DirectDebitResponse"));
        oper.setReturnClass(com.adyen.services.payment.DirectDebitResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "response"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://payment.services.adyen.com", "ServiceException"),
                      "com.adyen.services.common.ServiceException",
                      new javax.xml.namespace.QName("http://common.services.adyen.com", "ServiceException"), 
                      true
                     ));
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cancel");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://payment.services.adyen.com", "modificationRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://payment.services.adyen.com", "ModificationRequest"), com.adyen.services.payment.ModificationRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://payment.services.adyen.com", "ModificationResult"));
        oper.setReturnClass(com.adyen.services.payment.ModificationResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "cancelResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://payment.services.adyen.com", "ServiceException"),
                      "com.adyen.services.common.ServiceException",
                      new javax.xml.namespace.QName("http://common.services.adyen.com", "ServiceException"), 
                      true
                     ));
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("balanceCheck");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://payment.services.adyen.com", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://payment.services.adyen.com", "BalanceCheckRequest"), com.adyen.services.payment.BalanceCheckRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://payment.services.adyen.com", "BalanceCheckResult"));
        oper.setReturnClass(com.adyen.services.payment.BalanceCheckResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "response"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://payment.services.adyen.com", "ServiceException"),
                      "com.adyen.services.common.ServiceException",
                      new javax.xml.namespace.QName("http://common.services.adyen.com", "ServiceException"), 
                      true
                     ));
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("addressCheck");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://payment.services.adyen.com", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://payment.services.adyen.com", "AddressCheckRequest"), com.adyen.services.payment.AddressCheckRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://payment.services.adyen.com", "AddressCheckResult"));
        oper.setReturnClass(com.adyen.services.payment.AddressCheckResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "response"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://payment.services.adyen.com", "ServiceException"),
                      "com.adyen.services.common.ServiceException",
                      new javax.xml.namespace.QName("http://common.services.adyen.com", "ServiceException"), 
                      true
                     ));
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("authorise3d");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://payment.services.adyen.com", "paymentRequest3d"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://payment.services.adyen.com", "PaymentRequest3d"), com.adyen.services.payment.PaymentRequest3D.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://payment.services.adyen.com", "PaymentResult"));
        oper.setReturnClass(com.adyen.services.payment.PaymentResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "paymentResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://payment.services.adyen.com", "ServiceException"),
                      "com.adyen.services.common.ServiceException",
                      new javax.xml.namespace.QName("http://common.services.adyen.com", "ServiceException"), 
                      true
                     ));
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("refund");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://payment.services.adyen.com", "modificationRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://payment.services.adyen.com", "ModificationRequest"), com.adyen.services.payment.ModificationRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://payment.services.adyen.com", "ModificationResult"));
        oper.setReturnClass(com.adyen.services.payment.ModificationResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "refundResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://payment.services.adyen.com", "ServiceException"),
                      "com.adyen.services.common.ServiceException",
                      new javax.xml.namespace.QName("http://common.services.adyen.com", "ServiceException"), 
                      true
                     ));
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("capture");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://payment.services.adyen.com", "modificationRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://payment.services.adyen.com", "ModificationRequest"), com.adyen.services.payment.ModificationRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://payment.services.adyen.com", "ModificationResult"));
        oper.setReturnClass(com.adyen.services.payment.ModificationResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "captureResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://payment.services.adyen.com", "ServiceException"),
                      "com.adyen.services.common.ServiceException",
                      new javax.xml.namespace.QName("http://common.services.adyen.com", "ServiceException"), 
                      true
                     ));
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cancelOrRefund");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://payment.services.adyen.com", "modificationRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://payment.services.adyen.com", "ModificationRequest"), com.adyen.services.payment.ModificationRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://payment.services.adyen.com", "ModificationResult"));
        oper.setReturnClass(com.adyen.services.payment.ModificationResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "cancelOrRefundResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://payment.services.adyen.com", "ServiceException"),
                      "com.adyen.services.common.ServiceException",
                      new javax.xml.namespace.QName("http://common.services.adyen.com", "ServiceException"), 
                      true
                     ));
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("fundTransfer");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://payment.services.adyen.com", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://payment.services.adyen.com", "FundTransferRequest"), com.adyen.services.payment.FundTransferRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://payment.services.adyen.com", "FundTransferResult"));
        oper.setReturnClass(com.adyen.services.payment.FundTransferResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://payment.services.adyen.com", "ServiceException"),
                      "com.adyen.services.common.ServiceException",
                      new javax.xml.namespace.QName("http://common.services.adyen.com", "ServiceException"), 
                      true
                     ));
        _operations[12] = oper;

    }

    public PaymentHttpBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public PaymentHttpBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public PaymentHttpBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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

            qName = new javax.xml.namespace.QName("http://common.services.adyen.com", "BrowserInfo");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.common.BrowserInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://common.services.adyen.com", "Error");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.common.Error.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://common.services.adyen.com", "Gender");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.common.Gender.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://common.services.adyen.com", "Installments");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.common.Installments.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://common.services.adyen.com", "Name");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.common.Name.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

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

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", ">addressCheck");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.AddressCheck.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", ">addressCheckResponse");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.AddressCheckResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", ">anyType2anyTypeMap>entry");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.AnyType2AnyTypeMapEntry.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", ">authorise");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.Authorise.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", ">authorise3d");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.Authorise3D.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", ">authorise3dResponse");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.Authorise3DResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", ">authoriseReferral");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.AuthoriseReferral.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", ">authoriseReferralResponse");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.AuthoriseReferralResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", ">authoriseResponse");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.AuthoriseResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", ">balanceCheck");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.BalanceCheck.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", ">balanceCheckResponse");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.BalanceCheckResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", ">cancel");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.Cancel.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", ">cancelOrRefund");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.CancelOrRefund.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", ">cancelOrRefundResponse");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.CancelOrRefundResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", ">cancelResponse");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.CancelResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", ">capture");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.Capture.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", ">captureResponse");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.CaptureResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", ">checkFraud");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.CheckFraud.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", ">checkFraudResponse");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.CheckFraudResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", ">directdebit");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.Directdebit.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", ">directdebitResponse");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.DirectDebitResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", ">fundTransfer");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.FundTransfer.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", ">fundTransferResponse");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.FundTransferResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", ">refund");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.Refund.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", ">refundResponse");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.RefundResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", ">refundWithData");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.RefundWithData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", ">refundWithDataResponse");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.RefundWithDataResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", "AddressCheckRequest");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.AddressCheckRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", "AddressCheckResult");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.AddressCheckResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", "anyType2anyTypeMap");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.AnyType2AnyTypeMapEntry[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", ">anyType2anyTypeMap>entry");
            qName2 = new javax.xml.namespace.QName("http://payment.services.adyen.com", "entry");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", "ArrayOfFraudCheckResult");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.FraudCheckResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", "FraudCheckResult");
            qName2 = new javax.xml.namespace.QName("http://payment.services.adyen.com", "FraudCheckResult");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", "BalanceCheckRequest");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.BalanceCheckRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", "BalanceCheckResponseCode");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.BalanceCheckResponseCode.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", "BalanceCheckResult");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.BalanceCheckResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

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

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", "DirectDebitRequest");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.DirectDebitRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", "DirectDebitResponse");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.DirectDebitResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", "ELV");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.ELV.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", "ForexQuote");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.ForexQuote.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", "FraudCheckResult");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.FraudCheckResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", "FraudResult");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.FraudResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", "FundTransferRequest");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.FundTransferRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", "FundTransferResult");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.FundTransferResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", "ModificationRequest");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.ModificationRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", "ModificationResult");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.ModificationResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", "PaymentRequest");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.PaymentRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", "PaymentRequest3d");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.PaymentRequest3D.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", "PaymentResult");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.PaymentResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", "Recurring");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.Recurring.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://payment.services.adyen.com", "ThreeDSecureData");
            cachedSerQNames.add(qName);
            cls = com.adyen.services.payment.ThreeDSecureData.class;
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

    public com.adyen.services.payment.PaymentResult checkFraud(com.adyen.services.payment.PaymentRequest paymentRequest) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "checkFraud"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {paymentRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.adyen.services.payment.PaymentResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.adyen.services.payment.PaymentResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.adyen.services.payment.PaymentResult.class);
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

    public com.adyen.services.payment.PaymentResult refundWithData(com.adyen.services.payment.PaymentRequest request) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "refundWithData"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.adyen.services.payment.PaymentResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.adyen.services.payment.PaymentResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.adyen.services.payment.PaymentResult.class);
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

    public com.adyen.services.payment.PaymentResult authorise(com.adyen.services.payment.PaymentRequest paymentRequest) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "authorise"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {paymentRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.adyen.services.payment.PaymentResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.adyen.services.payment.PaymentResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.adyen.services.payment.PaymentResult.class);
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

    public com.adyen.services.payment.ModificationResult authoriseReferral(com.adyen.services.payment.ModificationRequest modificationRequest) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "authoriseReferral"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {modificationRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.adyen.services.payment.ModificationResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.adyen.services.payment.ModificationResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.adyen.services.payment.ModificationResult.class);
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

    public com.adyen.services.payment.DirectDebitResult directdebit(com.adyen.services.payment.DirectDebitRequest request) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "directdebit"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.adyen.services.payment.DirectDebitResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.adyen.services.payment.DirectDebitResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.adyen.services.payment.DirectDebitResult.class);
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

    public com.adyen.services.payment.ModificationResult cancel(com.adyen.services.payment.ModificationRequest modificationRequest) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "cancel"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {modificationRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.adyen.services.payment.ModificationResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.adyen.services.payment.ModificationResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.adyen.services.payment.ModificationResult.class);
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

    public com.adyen.services.payment.BalanceCheckResult balanceCheck(com.adyen.services.payment.BalanceCheckRequest request) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "balanceCheck"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.adyen.services.payment.BalanceCheckResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.adyen.services.payment.BalanceCheckResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.adyen.services.payment.BalanceCheckResult.class);
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

    public com.adyen.services.payment.AddressCheckResult addressCheck(com.adyen.services.payment.AddressCheckRequest request) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "addressCheck"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.adyen.services.payment.AddressCheckResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.adyen.services.payment.AddressCheckResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.adyen.services.payment.AddressCheckResult.class);
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

    public com.adyen.services.payment.PaymentResult authorise3D(com.adyen.services.payment.PaymentRequest3D paymentRequest3D) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "authorise3d"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {paymentRequest3D});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.adyen.services.payment.PaymentResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.adyen.services.payment.PaymentResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.adyen.services.payment.PaymentResult.class);
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

    public com.adyen.services.payment.ModificationResult refund(com.adyen.services.payment.ModificationRequest modificationRequest) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "refund"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {modificationRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.adyen.services.payment.ModificationResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.adyen.services.payment.ModificationResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.adyen.services.payment.ModificationResult.class);
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

    public com.adyen.services.payment.ModificationResult capture(com.adyen.services.payment.ModificationRequest modificationRequest) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "capture"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {modificationRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.adyen.services.payment.ModificationResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.adyen.services.payment.ModificationResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.adyen.services.payment.ModificationResult.class);
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

    public com.adyen.services.payment.ModificationResult cancelOrRefund(com.adyen.services.payment.ModificationRequest modificationRequest) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "cancelOrRefund"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {modificationRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.adyen.services.payment.ModificationResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.adyen.services.payment.ModificationResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.adyen.services.payment.ModificationResult.class);
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

    public com.adyen.services.payment.FundTransferResult fundTransfer(com.adyen.services.payment.FundTransferRequest request) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "fundTransfer"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.adyen.services.payment.FundTransferResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.adyen.services.payment.FundTransferResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.adyen.services.payment.FundTransferResult.class);
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
