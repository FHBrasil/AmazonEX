/**
 *
 */
package xml.factory;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.persistence.jaxb.metadata.MetadataSourceAdapter;
import org.eclipse.persistence.jaxb.xmlmodel.JavaType;
import org.eclipse.persistence.jaxb.xmlmodel.JavaType.JavaAttributes;
import org.eclipse.persistence.jaxb.xmlmodel.ObjectFactory;
import org.eclipse.persistence.jaxb.xmlmodel.XmlBindings;
import org.eclipse.persistence.jaxb.xmlmodel.XmlBindings.JavaTypes;
import org.eclipse.persistence.jaxb.xmlmodel.XmlElement;
import org.eclipse.persistence.jaxb.xmlmodel.XmlElementWrapper;
import org.eclipse.persistence.jaxb.xmlmodel.XmlJavaTypeAdapter;
import org.eclipse.persistence.jaxb.xmlmodel.XmlJavaTypeAdapters;
import org.eclipse.persistence.jaxb.xmlmodel.XmlRootElement;
import org.springframework.core.annotation.AnnotationUtils;


/**
 * WsDTOGenericMetadataSourceAdapter is responsible for creating JAXB metadata used by Moxy implementation of JAXB. It
 * creates XmlBindings data structure for one class given by constructor parameter.
 */
public class WsDTOGenericMetadataSourceAdapter<T> extends MetadataSourceAdapter
{
	public final String DEFAULT_COLLECTION_ITEM_NAME = "item";

	private final Class<T> clazz;
	private final List<Class> typeAdapters;
	private final Boolean wrapCollections;

	public WsDTOGenericMetadataSourceAdapter(final Class<T> clazz, final List<Class> typeAdapters, final Boolean wrapCollections)
	{
		this.clazz = clazz;
		this.typeAdapters = typeAdapters;
		this.wrapCollections = wrapCollections;
	}

	@Override
	public XmlBindings getXmlBindings(final Map<String, ?> properties, final ClassLoader classLoader)
	{
		final String packageName = clazz.getPackage().getName();
		final ObjectFactory factory = new ObjectFactory();
		final XmlBindings xmlBindings = new XmlBindings();
		xmlBindings.setPackageName(packageName);
		xmlBindings.setJavaTypes(new JavaTypes());
		xmlBindings.setXmlJavaTypeAdapters(new XmlJavaTypeAdapters());

		final JavaType javaType = new JavaType();
		javaType.setName(clazz.getName());
		javaType.setJavaAttributes(new JavaAttributes());

		final XmlRootElement xre = new XmlRootElement();
		xre.setName(createNodeNameFromClass(clazz));
		javaType.setXmlRootElement(xre);

		// check for collections and set XmlElementWrapper and XmlElement for a collection fields in order to have wrappers
		if (wrapCollections != null && Boolean.TRUE.equals(wrapCollections))
		{
			final Field[] fields = clazz.getDeclaredFields();
			for (final Field field : fields)
			{
				final Class fieldClass = field.getType();
				if (Collection.class.isAssignableFrom(fieldClass))
				{
					final XmlElementWrapper xew = new XmlElementWrapper();
					xew.setName(field.getName());

					final XmlElement xe = new XmlElement();
					xe.setJavaAttribute(field.getName());
					xe.setName(createCollectionItemNameFromField(field));
					xe.setXmlElementWrapper(xew);

					javaType.getJavaAttributes().getJavaAttribute().add(factory.createXmlElement(xe));
				}
			}
		}

		xmlBindings.getJavaTypes().getJavaType().add(javaType);

		// set package level type adapters
		for (final Class clazz : this.typeAdapters)
		{
			final XmlJavaTypeAdapter xjta = new XmlJavaTypeAdapter();
			xjta.setValue(clazz.getName());

			final Class<T> type = ((Class<T>) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[1]);
			final String name = type.getName();
			xjta.setType(name);

			xmlBindings.getXmlJavaTypeAdapters().getXmlJavaTypeAdapter().add(xjta);
		}

		return xmlBindings;
	}

	protected String createNodeNameFromClass(final Class clazz)
	{
		final javax.xml.bind.annotation.XmlRootElement xmlRootAnnotation = AnnotationUtils.findAnnotation(clazz,
				javax.xml.bind.annotation.XmlRootElement.class);

		if (xmlRootAnnotation == null)
		{
			return clazz.getSimpleName();
		}

		return xmlRootAnnotation.name();
	}

	protected String createCollectionItemNameFromField(final Field field)
	{
		final Class fieldClass = field.getType();
		if (Collection.class.isAssignableFrom(fieldClass))
		{
			final ParameterizedType pt = (ParameterizedType) field.getGenericType();
			final Type[] typesInside = pt.getActualTypeArguments();
			if (typesInside.length == 1)
			{
				return createNodeNameFromClass((Class) typesInside[0]);
			}
			else
			{
				return DEFAULT_COLLECTION_ITEM_NAME;
			}
		}
		else
		{
			return DEFAULT_COLLECTION_ITEM_NAME;
		}
	}
}