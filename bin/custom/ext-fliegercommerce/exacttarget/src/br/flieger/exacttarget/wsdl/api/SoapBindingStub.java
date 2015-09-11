/**
 * SoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.flieger.exacttarget.wsdl.api;

public class SoapBindingStub extends org.apache.axis.client.Stub implements br.flieger.exacttarget.wsdl.api.Soap
{
	private final java.util.Vector cachedSerClasses = new java.util.Vector();
	private final java.util.Vector cachedSerQNames = new java.util.Vector();
	private final java.util.Vector cachedSerFactories = new java.util.Vector();
	private final java.util.Vector cachedDeserFactories = new java.util.Vector();

	static org.apache.axis.description.OperationDesc[] _operations;

	static
	{
		_operations = new org.apache.axis.description.OperationDesc[13];
		_initOperationDesc1();
		_initOperationDesc2();
	}

	private static void _initOperationDesc1()
	{
		org.apache.axis.description.OperationDesc oper;
		org.apache.axis.description.ParameterDesc param;
		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("Create");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
				"http://exacttarget.com/wsdl/partnerAPI", "CreateRequest"), org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">CreateRequest"),
				br.flieger.exacttarget.wsdl.api.CreateRequest.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">CreateResponse"));
		oper.setReturnClass(br.flieger.exacttarget.wsdl.api.CreateResponse.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CreateResponse"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[0] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("Retrieve");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
				"http://exacttarget.com/wsdl/partnerAPI", "RetrieveRequestMsg"), org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">RetrieveRequestMsg"),
				br.flieger.exacttarget.wsdl.api.RetrieveRequestMsg.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">RetrieveResponseMsg"));
		oper.setReturnClass(br.flieger.exacttarget.wsdl.api.RetrieveResponseMsg.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RetrieveResponseMsg"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[1] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("Update");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
				"http://exacttarget.com/wsdl/partnerAPI", "UpdateRequest"), org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">UpdateRequest"),
				br.flieger.exacttarget.wsdl.api.UpdateRequest.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">UpdateResponse"));
		oper.setReturnClass(br.flieger.exacttarget.wsdl.api.UpdateResponse.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UpdateResponse"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[2] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("Delete");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
				"http://exacttarget.com/wsdl/partnerAPI", "DeleteRequest"), org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">DeleteRequest"),
				br.flieger.exacttarget.wsdl.api.DeleteRequest.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">DeleteResponse"));
		oper.setReturnClass(br.flieger.exacttarget.wsdl.api.DeleteResponse.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DeleteResponse"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[3] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("Query");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
				"http://exacttarget.com/wsdl/partnerAPI", "QueryRequestMsg"), org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">QueryRequestMsg"),
				br.flieger.exacttarget.wsdl.api.QueryRequestMsg.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">QueryResponseMsg"));
		oper.setReturnClass(br.flieger.exacttarget.wsdl.api.QueryResponseMsg.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "QueryResponseMsg"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[4] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("Describe");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
				"http://exacttarget.com/wsdl/partnerAPI", "DefinitionRequestMsg"), org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">DefinitionRequestMsg"),
				br.flieger.exacttarget.wsdl.api.DefinitionRequestMsg.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">DefinitionResponseMsg"));
		oper.setReturnClass(br.flieger.exacttarget.wsdl.api.DefinitionResponseMsg.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DefinitionResponseMsg"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[5] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("Execute");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
				"http://exacttarget.com/wsdl/partnerAPI", "ExecuteRequestMsg"), org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ExecuteRequestMsg"),
				br.flieger.exacttarget.wsdl.api.ExecuteRequest[].class, false, false);
		param.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Requests"));
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ExecuteResponseMsg"));
		oper.setReturnClass(br.flieger.exacttarget.wsdl.api.ExecuteResponseMsg.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExecuteResponseMsg"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[6] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("Perform");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
				"http://exacttarget.com/wsdl/partnerAPI", "PerformRequestMsg"), org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">PerformRequestMsg"),
				br.flieger.exacttarget.wsdl.api.PerformRequestMsg.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">PerformResponseMsg"));
		oper.setReturnClass(br.flieger.exacttarget.wsdl.api.PerformResponseMsg.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PerformResponseMsg"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[7] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("Configure");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
				"http://exacttarget.com/wsdl/partnerAPI", "ConfigureRequestMsg"), org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ConfigureRequestMsg"),
				br.flieger.exacttarget.wsdl.api.ConfigureRequestMsg.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ConfigureResponseMsg"));
		oper.setReturnClass(br.flieger.exacttarget.wsdl.api.ConfigureResponseMsg.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ConfigureResponseMsg"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[8] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("Schedule");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
				"http://exacttarget.com/wsdl/partnerAPI", "ScheduleRequestMsg"), org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ScheduleRequestMsg"),
				br.flieger.exacttarget.wsdl.api.ScheduleRequestMsg.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ScheduleResponseMsg"));
		oper.setReturnClass(br.flieger.exacttarget.wsdl.api.ScheduleResponseMsg.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ScheduleResponseMsg"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[9] = oper;

	}

	private static void _initOperationDesc2()
	{
		org.apache.axis.description.OperationDesc oper;
		org.apache.axis.description.ParameterDesc param;
		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("VersionInfo");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
				"http://exacttarget.com/wsdl/partnerAPI", "VersionInfoRequestMsg"), org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">VersionInfoRequestMsg"),
				br.flieger.exacttarget.wsdl.api.VersionInfoRequestMsg.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">VersionInfoResponseMsg"));
		oper.setReturnClass(br.flieger.exacttarget.wsdl.api.VersionInfoResponseMsg.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "VersionInfoResponseMsg"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[10] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("Extract");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
				"http://exacttarget.com/wsdl/partnerAPI", "ExtractRequestMsg"), org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ExtractRequestMsg"),
				br.flieger.exacttarget.wsdl.api.ExtractRequest[].class, false, false);
		param.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Requests"));
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ExtractResponseMsg"));
		oper.setReturnClass(br.flieger.exacttarget.wsdl.api.ExtractResponseMsg.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtractResponseMsg"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[11] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("GetSystemStatus");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
				"http://exacttarget.com/wsdl/partnerAPI", "SystemStatusRequestMsg"), org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">SystemStatusRequestMsg"),
				br.flieger.exacttarget.wsdl.api.SystemStatusRequestMsg.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">SystemStatusResponseMsg"));
		oper.setReturnClass(br.flieger.exacttarget.wsdl.api.SystemStatusResponseMsg.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SystemStatusResponseMsg"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[12] = oper;

	}

	public SoapBindingStub() throws org.apache.axis.AxisFault
	{
		this(null);
	}

	public SoapBindingStub(final java.net.URL endpointURL, final javax.xml.rpc.Service service) throws org.apache.axis.AxisFault
	{
		this(service);
		super.cachedEndpoint = endpointURL;
	}

	public SoapBindingStub(final javax.xml.rpc.Service service) throws org.apache.axis.AxisFault
	{
		if (service == null)
		{
			super.service = new org.apache.axis.client.Service();
		}
		else
		{
			super.service = service;
		}
		((org.apache.axis.client.Service) super.service).setTypeMappingVersion("1.2");

		addBindings0();
		addBindings1();
		addBindings2();
		addBindings3();
	}

	private void addBindings0()
	{
		java.lang.Class cls;
		javax.xml.namespace.QName qName;
		javax.xml.namespace.QName qName2;
		final java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
		final java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
		final java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
		final java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">>ConfigureRequestMsg>Configurations");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.APIObject[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIObject");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Configuration");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">>ConfigureResponseMsg>Results");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ConfigureResult[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ConfigureResult");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Result");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">>PerformRequestMsg>Definitions");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.APIObject[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIObject");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Definition");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">>PerformResponseMsg>Results");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.PerformResult[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PerformResult");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Result");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">>ScheduleRequestMsg>Interactions");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.APIObject[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIObject");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Interaction");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">>ScheduleResponseMsg>Results");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ScheduleResult[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ScheduleResult");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Result");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">>SystemStatusResponseMsg>Results");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SystemStatusResult[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SystemStatusResult");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Result");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">Account>Roles");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.Role[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Role");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Role");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">AccountUser>AssociatedBusinessUnits");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.BusinessUnit[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BusinessUnit");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BusinessUnit");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">AccountUser>Roles");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.Role[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Role");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Role");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">AccountUser>SsoIdentities");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SsoIdentity[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SsoIdentity");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SsoIdentity");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">Automation>AutomationTasks");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.AutomationTask[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationTask");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationTask");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">Automation>Notifications");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.AutomationNotification[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationNotification");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Notification");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">AutomationInstance>TaskInstances");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.AutomationTaskInstance[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationTaskInstance");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationTaskInstance");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI",
				">AutomationInstances>AutomationInstanceCollection");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.AutomationInstance[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationInstance");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationInstance");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">AutomationTask>Activities");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.AutomationActivity[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationActivity");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Activity");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">AutomationTaskInstance>ActivityInstances");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.AutomationActivityInstance[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationActivityInstance");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ActivityInstance");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ComplexFilterPart>AdditionalOperands");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.FilterPart[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FilterPart");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Operand");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ConfigureRequestMsg");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ConfigureRequestMsg.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ConfigureResponseMsg");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ConfigureResponseMsg.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ContentValidation>Subscribers");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.Subscriber[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Subscriber");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Subscriber");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI",
				">ContentValidationTaskResult>ValidationResults");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ValidationResult[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ValidationResult");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ValidationResult");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">CreateRequest");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.CreateRequest.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">CreateResponse");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.CreateResponse.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">DataExtension>Fields");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.DataExtensionField[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionField");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Field");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">DataExtensionCreateResult>KeyErrors");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.DataExtensionError[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionError");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "KeyError");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">DataExtensionCreateResult>ValueErrors");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.DataExtensionError[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionError");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ValueError");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">DataExtensionDeleteResult>KeyErrors");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.DataExtensionError[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionError");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "KeyError");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">DataExtensionObject>Keys");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.APIProperty[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIProperty");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Key");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">DataExtensionUpdateResult>KeyErrors");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.DataExtensionError[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionError");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "KeyError");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">DataExtensionUpdateResult>ValueErrors");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.DataExtensionError[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionError");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ValueError");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">DefinitionRequestMsg");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.DefinitionRequestMsg.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">DefinitionResponseMsg");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.DefinitionResponseMsg.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">DeleteRequest");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.DeleteRequest.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">DeleteResponse");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.DeleteResponse.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">DoubleOptInMOKeyword>ValidPublications");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.List[].class;
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
		cls = br.flieger.exacttarget.wsdl.api.TrackingUser[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TrackingUser");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TrackingUser");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ExecuteRequestMsg");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ExecuteRequest[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExecuteRequest");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Requests");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ExecuteResponseMsg");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ExecuteResponseMsg.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ExtractDefinition>Parameters");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ExtractParameterDescription[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtractParameterDescription");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Parameter");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ExtractDefinition>Values");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.APIProperty[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIProperty");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Value");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ExtractDescription>Parameters");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ExtractParameterDescription[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtractParameterDescription");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Parameter");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ExtractRequest>Parameters");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ExtractParameter[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtractParameter");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Parameter");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ExtractRequestMsg");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ExtractRequest[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtractRequest");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Requests");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ExtractResponseMsg");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ExtractResponseMsg.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ImportDefinition>ControlColumnActions");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ImportDefinitionColumnBasedAction[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ImportDefinitionColumnBasedAction");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ControlColumnAction");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ImportDefinition>FieldMaps");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.FieldMap[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FieldMap");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FieldMap");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ObjectDefinition>ExtendedProperties");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.PropertyDefinition[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PropertyDefinition");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtendedProperty");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ObjectExtension>Properties");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.APIProperty[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIProperty");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Property");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">Options>SaveOptions");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SaveOption[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SaveOption");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SaveOption");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">PerformRequestMsg");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.PerformRequestMsg.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">PerformResponseMsg");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.PerformResponseMsg.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">PermissionSet>Permissions");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.Permission[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Permission");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Permission");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">PermissionSet>PermissionSets");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.PermissionSet[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PermissionSet");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PermissionSet");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">PropertyDefinition>PicklistItems");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.PicklistItem[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PicklistItem");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PicklistItem");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">PropertyDefinition>References");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.APIObject[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIObject");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Reference");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">Publication>Subscribers");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.Subscriber[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Subscriber");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Subscriber");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">QueryRequestMsg");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.QueryRequestMsg.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">QueryResponseMsg");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.QueryResponseMsg.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">RetrieveRequest>Retrieves");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.Request[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Request");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Request");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">RetrieveRequestMsg");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.RetrieveRequestMsg.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">RetrieveResponseMsg");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.RetrieveResponseMsg.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">Role>Permissions");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.Permission[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Permission");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Permission");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">Role>PermissionSets");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.PermissionSet[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PermissionSet");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PermissionSet");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ScheduleRequestMsg");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ScheduleRequestMsg.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ScheduleResponseMsg");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ScheduleResponseMsg.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">Send>Sources");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.APIObject[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIObject");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Source");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">Send>SuppressionLists");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.AudienceItem[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AudienceItem");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SuppressionList");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">SendDefinitionList>Parameters");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.APIProperty[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIProperty");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Parameter");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">Subscriber>Addresses");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SubscriberAddress[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SubscriberAddress");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Address");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">SubscriberAddress>Statuses");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.AddressStatus[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AddressStatus");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Status");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">SuppressionListData>Properties");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.APIProperty[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIProperty");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Property");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">SuppressionListDefinition>Contexts");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SuppressionListContext[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SuppressionListContext");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Context");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">SuppressionListDefinition>Fields");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.DataExtensionField[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionField");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Field");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">SystemStatusRequestMsg");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SystemStatusRequestMsg.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">SystemStatusResponseMsg");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SystemStatusResponseMsg.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">SystemStatusResult>Outages");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SystemOutage[].class;
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
		cls = br.flieger.exacttarget.wsdl.api.UpdateRequest.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">UpdateResponse");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.UpdateResponse.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ValidationAction>ValidationOptions");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.APIProperty[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIProperty");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ValidationOption");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">VersionInfoRequestMsg");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.VersionInfoRequestMsg.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">VersionInfoResponseMsg");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.VersionInfoResponseMsg.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Account");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.Account.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AccountDataItem");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.AccountDataItem.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AccountPrivateLabel");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.AccountPrivateLabel.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AccountTypeEnum");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.AccountTypeEnum.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AccountUser");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.AccountUser.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AddressStatus");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.AddressStatus.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIObject");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.APIObject.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIProperty");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.APIProperty.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ArrayOfObjectDefinitionRequest");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ObjectDefinitionRequest[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ObjectDefinitionRequest");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ObjectDefinitionRequest");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AsyncRequestResult");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.AsyncRequestResult.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AsyncResponse");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.AsyncResponse.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AsyncResponseType");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.AsyncResponseType.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Attribute");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.Attribute.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AttributeMap");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.AttributeMap.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AudienceItem");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.AudienceItem.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Authentication");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.Authentication.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

	}

	private void addBindings1()
	{
		java.lang.Class cls;
		javax.xml.namespace.QName qName;
		final javax.xml.namespace.QName qName2;
		final java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
		final java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
		final java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
		final java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
		final java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
		final java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
		final java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
		final java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
		final java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
		final java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Automation");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.Automation.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationActivity");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.AutomationActivity.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationActivityInstance");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.AutomationActivityInstance.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationInstance");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.AutomationInstance.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationInstances");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.AutomationInstances.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationNotification");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.AutomationNotification.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationSource");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.AutomationSource.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationTask");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.AutomationTask.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationTaskInstance");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.AutomationTaskInstance.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BaseMOKeyword");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.BaseMOKeyword.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BounceEvent");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.BounceEvent.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Brand");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.Brand.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BrandTag");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.BrandTag.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BusinessRule");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.BusinessRule.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BusinessUnit");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.BusinessUnit.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Campaign");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.Campaign.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CampaignPerformOptions");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.CampaignPerformOptions.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Certificate");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.Certificate.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ClickEvent");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ClickEvent.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ClientID");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ClientID.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ComplexFilterPart");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ComplexFilterPart.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CompressionConfiguration");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.CompressionConfiguration.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CompressionEncoding");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.CompressionEncoding.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CompressionType");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.CompressionType.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ConfigureOptions");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ConfigureOptions.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ConfigureResult");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ConfigureResult.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ContainerID");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ContainerID.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ContentArea");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ContentArea.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ContentValidation");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ContentValidation.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ContentValidationResult");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ContentValidationResult.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ContentValidationTaskResult");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ContentValidationTaskResult.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CreateOptions");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.CreateOptions.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CreateResult");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.CreateResult.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DailyRecurrence");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.DailyRecurrence.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DailyRecurrencePatternTypeEnum");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.DailyRecurrencePatternTypeEnum.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtension");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.DataExtension.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionCreateResult");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.DataExtensionCreateResult.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionDeleteResult");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.DataExtensionDeleteResult.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionError");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.DataExtensionError.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionField");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.DataExtensionField.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionFieldType");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.DataExtensionFieldType.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionObject");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.DataExtensionObject.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionTemplate");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.DataExtensionTemplate.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionUpdateResult");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.DataExtensionUpdateResult.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtractActivity");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.DataExtractActivity.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataFolder");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.DataFolder.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataSourceTypeEnum");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.DataSourceTypeEnum.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DateTimeUnitOfMeasure");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.DateTimeUnitOfMeasure.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DayOfWeekEnum");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.DayOfWeekEnum.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DeleteOptions");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.DeleteOptions.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DeleteResult");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.DeleteResult.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DeliveredEvent");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.DeliveredEvent.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DeliveryProfile");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.DeliveryProfile.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DeliveryProfileDomainTypeEnum");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.DeliveryProfileDomainTypeEnum.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DeliveryProfileSourceAddressTypeEnum");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.DeliveryProfileSourceAddressTypeEnum.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DoubleOptInMOKeyword");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.DoubleOptInMOKeyword.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Email");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.Email.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "EmailAddress");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.EmailAddress.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "EmailSendDefinition");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.EmailSendDefinition.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "EmailType");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.EmailType.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "EventType");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.EventType.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExecuteRequest");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ExecuteRequest.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExecuteResponse");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ExecuteResponse.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtractDefinition");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ExtractDefinition.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtractDescription");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ExtractDescription.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtractOptions");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ExtractOptions.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtractParameter");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ExtractParameter.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtractParameterDataType");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ExtractParameterDataType.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtractParameterDescription");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ExtractParameterDescription.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtractRequest");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ExtractRequest.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtractResult");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ExtractResult.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtractTemplate");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ExtractTemplate.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FieldMap");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.FieldMap.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FileTransferActivity");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.FileTransferActivity.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FileTransferLocation");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.FileTransferLocation.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FileTrigger");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.FileTrigger.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FileTriggerTypeLastPull");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.FileTriggerTypeLastPull.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FileType");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.FileType.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FilterDefinition");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.FilterDefinition.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FilterPart");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.FilterPart.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Folder");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.Folder.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ForwardedEmailEvent");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ForwardedEmailEvent.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ForwardedEmailOptInEvent");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ForwardedEmailOptInEvent.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "GlobalUnsubscribeCategory");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.GlobalUnsubscribeCategory.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Group");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.Group.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "GroupDefinition");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.GroupDefinition.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "HelpMOKeyword");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.HelpMOKeyword.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "HourlyRecurrence");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.HourlyRecurrence.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "HourlyRecurrencePatternTypeEnum");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.HourlyRecurrencePatternTypeEnum.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ImportDefinition");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ImportDefinition.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ImportDefinitionAutoGenerateDestination");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ImportDefinitionAutoGenerateDestination.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ImportDefinitionColumnBasedAction");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ImportDefinitionColumnBasedAction.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ImportDefinitionColumnBasedActionType");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ImportDefinitionColumnBasedActionType.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ImportDefinitionFieldMap");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ImportDefinitionFieldMap.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ImportDefinitionFieldMappingType");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ImportDefinitionFieldMappingType.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ImportDefinitionSubscriberImportType");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ImportDefinitionSubscriberImportType.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ImportDefinitionUpdateType");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ImportDefinitionUpdateType.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ImportResultsSummary");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ImportResultsSummary.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IntegrationProfile");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.IntegrationProfile.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IntegrationProfileDefinition");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.IntegrationProfileDefinition.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

	}

	private void addBindings2()
	{
		java.lang.Class cls;
		javax.xml.namespace.QName qName;
		javax.xml.namespace.QName qName2;
		final java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
		final java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
		final java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
		final java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
		final java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
		final java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
		final java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
		final java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
		final java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
		final java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "InteractionBaseObject");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.InteractionBaseObject.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "InteractionDefinition");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.InteractionDefinition.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "LandingPage");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.LandingPage.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Layout");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.Layout.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "LayoutType");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.LayoutType.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Link");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.Link.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "LinkSend");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.LinkSend.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "List");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.List.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ListAttribute");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ListAttribute.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ListAttributeFieldType");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ListAttributeFieldType.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ListAttributeRestrictedValue");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ListAttributeRestrictedValue.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ListClassificationEnum");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ListClassificationEnum.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ListSend");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ListSend.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ListSubscriber");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ListSubscriber.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ListTypeEnum");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ListTypeEnum.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Locale");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.Locale.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "LogicalOperators");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.LogicalOperators.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Message");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.Message.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MessageDeliveryTypeEnum");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.MessageDeliveryTypeEnum.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MessageSendActivity");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.MessageSendActivity.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MessagingConfiguration");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.MessagingConfiguration.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MessagingVendorKind");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.MessagingVendorKind.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MinutelyRecurrence");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.MinutelyRecurrence.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MinutelyRecurrencePatternTypeEnum");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.MinutelyRecurrencePatternTypeEnum.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MobileConnectRefreshListActivity");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.MobileConnectRefreshListActivity.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MobileConnectSendSmsActivity");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.MobileConnectSendSmsActivity.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MobilePushSendMessageActivity");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.MobilePushSendMessageActivity.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MonthlyRecurrence");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.MonthlyRecurrence.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MonthlyRecurrencePatternTypeEnum");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.MonthlyRecurrencePatternTypeEnum.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MonthOfYearEnum");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.MonthOfYearEnum.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "NotSentEvent");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.NotSentEvent.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "NullAPIProperty");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.NullAPIProperty.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ObjectDefinition");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ObjectDefinition.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ObjectDefinitionRequest");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ObjectDefinitionRequest.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ObjectExtension");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ObjectExtension.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "OpenEvent");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.OpenEvent.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Options");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.Options.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "OverrideType");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.OverrideType.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Owner");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.Owner.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ParameterDescription");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ParameterDescription.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Parameters");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.APIProperty[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIProperty");
		qName2 = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Parameter");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PerformOptions");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.PerformOptions.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PerformResult");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.PerformResult.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Permission");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.Permission.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PermissionSet");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.PermissionSet.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PicklistItem");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.PicklistItem.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PlatformApplication");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.PlatformApplication.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PlatformApplicationPackage");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.PlatformApplicationPackage.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Portfolio");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.Portfolio.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Priority");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.Priority.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PrivateDomain");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.PrivateDomain.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PrivateDomainSet");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.PrivateDomainSet.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PrivateIP");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.PrivateIP.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PrivateLabel");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.PrivateLabel.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ProgramManifestTemplate");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ProgramManifestTemplate.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PropertyDefinition");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.PropertyDefinition.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PropertyType");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.PropertyType.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Publication");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.Publication.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PublicationSubscriber");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.PublicationSubscriber.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PublicKeyManagement");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.PublicKeyManagement.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Query");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.Query.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "QueryDefinition");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.QueryDefinition.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "QueryObject");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.QueryObject.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "QueryRequest");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.QueryRequest.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Recurrence");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.Recurrence.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RecurrenceRangeTypeEnum");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.RecurrenceRangeTypeEnum.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RecurrenceTypeEnum");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.RecurrenceTypeEnum.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ReplyMailManagementConfiguration");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ReplyMailManagementConfiguration.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ReportActivity");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ReportActivity.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Request");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.Request.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RequestType");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.RequestType.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ResourceSpecification");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ResourceSpecification.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RespondWhen");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.RespondWhen.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Result");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.Result.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ResultItem");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ResultItem.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ResultMessage");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ResultMessage.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RetrieveOptions");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.RetrieveOptions.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RetrieveRequest");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.RetrieveRequest.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RetrieveSingleOptions");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.RetrieveSingleOptions.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RetrieveSingleRequest");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.RetrieveSingleRequest.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Role");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.Role.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SalutationSourceEnum");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SalutationSourceEnum.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SaveAction");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SaveAction.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SaveOption");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SaveOption.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ScheduleDefinition");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ScheduleDefinition.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ScheduleOptions");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ScheduleOptions.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ScheduleResult");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ScheduleResult.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SecurityObject");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SecurityObject.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Send");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.Send.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendAdditionalAttribute");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SendAdditionalAttribute.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendClassification");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SendClassification.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendClassificationTypeEnum");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SendClassificationTypeEnum.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendDefinition");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SendDefinition.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendDefinitionList");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SendDefinitionList.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendDefinitionListTypeEnum");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SendDefinitionListTypeEnum.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendEmailMOKeyword");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SendEmailMOKeyword.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SenderProfile");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SenderProfile.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendPriorityEnum");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SendPriorityEnum.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendSMSMOKeyword");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SendSMSMOKeyword.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendSummary");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SendSummary.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

	}

	private void addBindings3()
	{
		java.lang.Class cls;
		javax.xml.namespace.QName qName;
		final javax.xml.namespace.QName qName2;
		final java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
		final java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
		final java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
		final java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
		final java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
		final java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
		final java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
		final java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
		final java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
		final java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SentEvent");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SentEvent.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SimpleFilterPart");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SimpleFilterPart.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SimpleOperators");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SimpleOperators.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SMSAddress");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SMSAddress.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SMSMOEvent");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SMSMOEvent.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SMSMTEvent");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SMSMTEvent.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SmsSendActivity");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SmsSendActivity.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SMSSharedKeyword");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SMSSharedKeyword.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SMSTriggeredSend");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SMSTriggeredSend.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SMSTriggeredSendDefinition");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SMSTriggeredSendDefinition.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SoapType");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SoapType.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SpamAssassinValidation");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SpamAssassinValidation.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SsoIdentity");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SsoIdentity.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Subscriber");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.Subscriber.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SubscriberAddress");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SubscriberAddress.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SubscriberAddressStatus");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SubscriberAddressStatus.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SubscriberList");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SubscriberList.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SubscriberResult");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SubscriberResult.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SubscriberSendResult");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SubscriberSendResult.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SubscriberStatus");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SubscriberStatus.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SubscriberTypeDefinition");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SubscriberTypeDefinition.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Subscription");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.Subscription.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SuppressionListContext");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SuppressionListContext.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SuppressionListContextEnum");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SuppressionListContextEnum.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SuppressionListData");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SuppressionListData.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SuppressionListDefinition");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SuppressionListDefinition.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SurveyEvent");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SurveyEvent.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SystemOutage");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SystemOutage.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SystemStatusOptions");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SystemStatusOptions.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SystemStatusResult");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SystemStatusResult.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SystemStatusType");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.SystemStatusType.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TagFilterPart");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.TagFilterPart.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TaskResult");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.TaskResult.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Template");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.Template.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TimeZone");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.TimeZone.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TrackingEvent");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.TrackingEvent.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TrackingUser");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.TrackingUser.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TriggeredSend");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.TriggeredSend.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TriggeredSendCreateResult");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.TriggeredSendCreateResult.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TriggeredSendDefinition");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.TriggeredSendDefinition.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TriggeredSendExclusionList");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.TriggeredSendExclusionList.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TriggeredSendStatusEnum");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.TriggeredSendStatusEnum.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TriggeredSendSummary");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.TriggeredSendSummary.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TriggeredSendTypeEnum");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.TriggeredSendTypeEnum.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UnsubEvent");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.UnsubEvent.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UnsubscribeBehaviorEnum");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.UnsubscribeBehaviorEnum.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UnsubscribeFromSMSPublicationMOKeyword");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.UnsubscribeFromSMSPublicationMOKeyword.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UpdateOptions");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.UpdateOptions.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UpdateResult");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.UpdateResult.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UserAccess");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.UserAccess.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UserMap");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.UserMap.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UsernameAuthentication");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.UsernameAuthentication.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ValidationAction");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ValidationAction.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ValidationResult");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.ValidationResult.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "VersionInfoResponse");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.VersionInfoResponse.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "VoiceTriggeredSend");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.VoiceTriggeredSend.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "VoiceTriggeredSendDefinition");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.VoiceTriggeredSendDefinition.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "WeeklyRecurrence");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.WeeklyRecurrence.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "WeeklyRecurrencePatternTypeEnum");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.WeeklyRecurrencePatternTypeEnum.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "WeekOfMonthEnum");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.WeekOfMonthEnum.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "YearlyRecurrence");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.YearlyRecurrence.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "YearlyRecurrencePatternTypeEnum");
		cachedSerQNames.add(qName);
		cls = br.flieger.exacttarget.wsdl.api.YearlyRecurrencePatternTypeEnum.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

	}

	protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException
	{
		try
		{
			final org.apache.axis.client.Call _call = super._createCall();
			if (super.maintainSessionSet)
			{
				_call.setMaintainSession(super.maintainSession);
			}
			if (super.cachedUsername != null)
			{
				_call.setUsername(super.cachedUsername);
			}
			if (super.cachedPassword != null)
			{
				_call.setPassword(super.cachedPassword);
			}
			if (super.cachedEndpoint != null)
			{
				_call.setTargetEndpointAddress(super.cachedEndpoint);
			}
			if (super.cachedTimeout != null)
			{
				_call.setTimeout(super.cachedTimeout);
			}
			if (super.cachedPortName != null)
			{
				_call.setPortName(super.cachedPortName);
			}
			final java.util.Enumeration keys = super.cachedProperties.keys();
			while (keys.hasMoreElements())
			{
				final java.lang.String key = (java.lang.String) keys.nextElement();
				_call.setProperty(key, super.cachedProperties.get(key));
			}
			// All the type mapping information is registered
			// when the first call is made.
			// The type mapping information is actually registered in
			// the TypeMappingRegistry of the service, which
			// is the reason why registration is only needed for the first call.
			synchronized (this)
			{
				if (firstCall())
				{
					// must set encoding style before registering serializers
					_call.setEncodingStyle(null);
					for (int i = 0; i < cachedSerFactories.size(); ++i)
					{
						final java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
						final javax.xml.namespace.QName qName = (javax.xml.namespace.QName) cachedSerQNames.get(i);
						final java.lang.Object x = cachedSerFactories.get(i);
						if (x instanceof Class)
						{
							final java.lang.Class sf = (java.lang.Class) cachedSerFactories.get(i);
							final java.lang.Class df = (java.lang.Class) cachedDeserFactories.get(i);
							_call.registerTypeMapping(cls, qName, sf, df, false);
						}
						else if (x instanceof javax.xml.rpc.encoding.SerializerFactory)
						{
							final org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory) cachedSerFactories
									.get(i);
							final org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory) cachedDeserFactories
									.get(i);
							_call.registerTypeMapping(cls, qName, sf, df, false);
						}
					}
				}
			}
			return _call;
		}
		catch (final java.lang.Throwable _t)
		{
			throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
		}
	}

	public br.flieger.exacttarget.wsdl.api.CreateResponse create(final br.flieger.exacttarget.wsdl.api.CreateRequest parameters)
			throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		final org.apache.axis.client.Call _call = createCall();
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
		try
		{
			final java.lang.Object _resp = _call.invoke(new java.lang.Object[]
			{ parameters });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (br.flieger.exacttarget.wsdl.api.CreateResponse) _resp;
				}
				catch (final java.lang.Exception _exception)
				{
					return (br.flieger.exacttarget.wsdl.api.CreateResponse) org.apache.axis.utils.JavaUtils.convert(_resp,
							br.flieger.exacttarget.wsdl.api.CreateResponse.class);
				}
			}
		}
		catch (final org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public br.flieger.exacttarget.wsdl.api.RetrieveResponseMsg retrieve(
			final br.flieger.exacttarget.wsdl.api.RetrieveRequestMsg parameters) throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		final org.apache.axis.client.Call _call = createCall();
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
		try
		{
			final java.lang.Object _resp = _call.invoke(new java.lang.Object[]
			{ parameters });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (br.flieger.exacttarget.wsdl.api.RetrieveResponseMsg) _resp;
				}
				catch (final java.lang.Exception _exception)
				{
					return (br.flieger.exacttarget.wsdl.api.RetrieveResponseMsg) org.apache.axis.utils.JavaUtils.convert(_resp,
							br.flieger.exacttarget.wsdl.api.RetrieveResponseMsg.class);
				}
			}
		}
		catch (final org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public br.flieger.exacttarget.wsdl.api.UpdateResponse update(final br.flieger.exacttarget.wsdl.api.UpdateRequest parameters)
			throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		final org.apache.axis.client.Call _call = createCall();
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
		try
		{
			final java.lang.Object _resp = _call.invoke(new java.lang.Object[]
			{ parameters });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (br.flieger.exacttarget.wsdl.api.UpdateResponse) _resp;
				}
				catch (final java.lang.Exception _exception)
				{
					return (br.flieger.exacttarget.wsdl.api.UpdateResponse) org.apache.axis.utils.JavaUtils.convert(_resp,
							br.flieger.exacttarget.wsdl.api.UpdateResponse.class);
				}
			}
		}
		catch (final org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public br.flieger.exacttarget.wsdl.api.DeleteResponse delete(final br.flieger.exacttarget.wsdl.api.DeleteRequest parameters)
			throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		final org.apache.axis.client.Call _call = createCall();
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
		try
		{
			final java.lang.Object _resp = _call.invoke(new java.lang.Object[]
			{ parameters });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (br.flieger.exacttarget.wsdl.api.DeleteResponse) _resp;
				}
				catch (final java.lang.Exception _exception)
				{
					return (br.flieger.exacttarget.wsdl.api.DeleteResponse) org.apache.axis.utils.JavaUtils.convert(_resp,
							br.flieger.exacttarget.wsdl.api.DeleteResponse.class);
				}
			}
		}
		catch (final org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public br.flieger.exacttarget.wsdl.api.QueryResponseMsg query(final br.flieger.exacttarget.wsdl.api.QueryRequestMsg parameters)
			throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		final org.apache.axis.client.Call _call = createCall();
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
		try
		{
			final java.lang.Object _resp = _call.invoke(new java.lang.Object[]
			{ parameters });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (br.flieger.exacttarget.wsdl.api.QueryResponseMsg) _resp;
				}
				catch (final java.lang.Exception _exception)
				{
					return (br.flieger.exacttarget.wsdl.api.QueryResponseMsg) org.apache.axis.utils.JavaUtils.convert(_resp,
							br.flieger.exacttarget.wsdl.api.QueryResponseMsg.class);
				}
			}
		}
		catch (final org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public br.flieger.exacttarget.wsdl.api.DefinitionResponseMsg describe(
			final br.flieger.exacttarget.wsdl.api.DefinitionRequestMsg parameters) throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		final org.apache.axis.client.Call _call = createCall();
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
		try
		{
			final java.lang.Object _resp = _call.invoke(new java.lang.Object[]
			{ parameters });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (br.flieger.exacttarget.wsdl.api.DefinitionResponseMsg) _resp;
				}
				catch (final java.lang.Exception _exception)
				{
					return (br.flieger.exacttarget.wsdl.api.DefinitionResponseMsg) org.apache.axis.utils.JavaUtils.convert(_resp,
							br.flieger.exacttarget.wsdl.api.DefinitionResponseMsg.class);
				}
			}
		}
		catch (final org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public br.flieger.exacttarget.wsdl.api.ExecuteResponseMsg execute(
			final br.flieger.exacttarget.wsdl.api.ExecuteRequest[] parameters) throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		final org.apache.axis.client.Call _call = createCall();
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
		try
		{
			final java.lang.Object _resp = _call.invoke(new java.lang.Object[]
			{ parameters });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (br.flieger.exacttarget.wsdl.api.ExecuteResponseMsg) _resp;
				}
				catch (final java.lang.Exception _exception)
				{
					return (br.flieger.exacttarget.wsdl.api.ExecuteResponseMsg) org.apache.axis.utils.JavaUtils.convert(_resp,
							br.flieger.exacttarget.wsdl.api.ExecuteResponseMsg.class);
				}
			}
		}
		catch (final org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public br.flieger.exacttarget.wsdl.api.PerformResponseMsg perform(
			final br.flieger.exacttarget.wsdl.api.PerformRequestMsg parameters) throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		final org.apache.axis.client.Call _call = createCall();
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
		try
		{
			final java.lang.Object _resp = _call.invoke(new java.lang.Object[]
			{ parameters });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (br.flieger.exacttarget.wsdl.api.PerformResponseMsg) _resp;
				}
				catch (final java.lang.Exception _exception)
				{
					return (br.flieger.exacttarget.wsdl.api.PerformResponseMsg) org.apache.axis.utils.JavaUtils.convert(_resp,
							br.flieger.exacttarget.wsdl.api.PerformResponseMsg.class);
				}
			}
		}
		catch (final org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public br.flieger.exacttarget.wsdl.api.ConfigureResponseMsg configure(
			final br.flieger.exacttarget.wsdl.api.ConfigureRequestMsg parameters) throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		final org.apache.axis.client.Call _call = createCall();
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
		try
		{
			final java.lang.Object _resp = _call.invoke(new java.lang.Object[]
			{ parameters });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (br.flieger.exacttarget.wsdl.api.ConfigureResponseMsg) _resp;
				}
				catch (final java.lang.Exception _exception)
				{
					return (br.flieger.exacttarget.wsdl.api.ConfigureResponseMsg) org.apache.axis.utils.JavaUtils.convert(_resp,
							br.flieger.exacttarget.wsdl.api.ConfigureResponseMsg.class);
				}
			}
		}
		catch (final org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public br.flieger.exacttarget.wsdl.api.ScheduleResponseMsg schedule(
			final br.flieger.exacttarget.wsdl.api.ScheduleRequestMsg parameters) throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		final org.apache.axis.client.Call _call = createCall();
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
		try
		{
			final java.lang.Object _resp = _call.invoke(new java.lang.Object[]
			{ parameters });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (br.flieger.exacttarget.wsdl.api.ScheduleResponseMsg) _resp;
				}
				catch (final java.lang.Exception _exception)
				{
					return (br.flieger.exacttarget.wsdl.api.ScheduleResponseMsg) org.apache.axis.utils.JavaUtils.convert(_resp,
							br.flieger.exacttarget.wsdl.api.ScheduleResponseMsg.class);
				}
			}
		}
		catch (final org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public br.flieger.exacttarget.wsdl.api.VersionInfoResponseMsg versionInfo(
			final br.flieger.exacttarget.wsdl.api.VersionInfoRequestMsg parameters) throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		final org.apache.axis.client.Call _call = createCall();
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
		try
		{
			final java.lang.Object _resp = _call.invoke(new java.lang.Object[]
			{ parameters });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (br.flieger.exacttarget.wsdl.api.VersionInfoResponseMsg) _resp;
				}
				catch (final java.lang.Exception _exception)
				{
					return (br.flieger.exacttarget.wsdl.api.VersionInfoResponseMsg) org.apache.axis.utils.JavaUtils.convert(_resp,
							br.flieger.exacttarget.wsdl.api.VersionInfoResponseMsg.class);
				}
			}
		}
		catch (final org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public br.flieger.exacttarget.wsdl.api.ExtractResponseMsg extract(
			final br.flieger.exacttarget.wsdl.api.ExtractRequest[] parameters) throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		final org.apache.axis.client.Call _call = createCall();
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
		try
		{
			final java.lang.Object _resp = _call.invoke(new java.lang.Object[]
			{ parameters });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (br.flieger.exacttarget.wsdl.api.ExtractResponseMsg) _resp;
				}
				catch (final java.lang.Exception _exception)
				{
					return (br.flieger.exacttarget.wsdl.api.ExtractResponseMsg) org.apache.axis.utils.JavaUtils.convert(_resp,
							br.flieger.exacttarget.wsdl.api.ExtractResponseMsg.class);
				}
			}
		}
		catch (final org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public br.flieger.exacttarget.wsdl.api.SystemStatusResponseMsg getSystemStatus(
			final br.flieger.exacttarget.wsdl.api.SystemStatusRequestMsg parameters) throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		final org.apache.axis.client.Call _call = createCall();
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
		try
		{
			final java.lang.Object _resp = _call.invoke(new java.lang.Object[]
			{ parameters });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (br.flieger.exacttarget.wsdl.api.SystemStatusResponseMsg) _resp;
				}
				catch (final java.lang.Exception _exception)
				{
					return (br.flieger.exacttarget.wsdl.api.SystemStatusResponseMsg) org.apache.axis.utils.JavaUtils.convert(_resp,
							br.flieger.exacttarget.wsdl.api.SystemStatusResponseMsg.class);
				}
			}
		}
		catch (final org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

}
