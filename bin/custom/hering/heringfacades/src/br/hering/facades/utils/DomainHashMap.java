package br.hering.facades.utils;

import de.hybris.platform.site.BaseSiteService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.annotation.Resource;

import org.springframework.web.context.request.RequestContextHolder;


/**
 * This map works similar to standard {@link HashMap}, but it contains multiple HashMaps, and uses one which it's key
 * matches server name from {@link RequestContextHolder}.
 * 
 * @author herbert
 * 
 */
public class DomainHashMap<K, V> implements Map<K, V>
{
	private Map<String, Map<K, V>> maps;

	@Resource
	private BaseSiteService baseSiteService;
	
	/**
	 * 
	 */
	public DomainHashMap()
	{
		maps = new HashMap<String, Map<K, V>>();
	}

	public Map<String, Map<K, V>> getMaps()
	{
		return maps;
	}

	public void setMaps(Map<String, Map<K, V>> maps)
	{
		this.maps = maps;
	}

	private String getDomain()
	{
		String serverName = baseSiteService.getCurrentBaseSite().getUid().toLowerCase();
		if (maps != null)
		{
			for (String domain : maps.keySet())
			{
				if (serverName.contains(domain))
				{
					return domain;
				}
			}
		}
		return serverName.isEmpty() ? null : new StringTokenizer(serverName, ".").nextToken();
	}

	private Map<K, V> getDomainMap()
	{
		return maps != null ? maps.get(getDomain()) : null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#size()
	 */
	@Override
	public int size()
	{
		Map<K, V> domainMap = getDomainMap();
		return domainMap != null ? domainMap.size() : 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#isEmpty()
	 */
	@Override
	public boolean isEmpty()
	{
		Map<K, V> domainMap = getDomainMap();
		return domainMap != null ? domainMap.isEmpty() : true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	@Override
	public boolean containsKey(Object key)
	{
		Map<K, V> domainMap = getDomainMap();
		return domainMap != null && domainMap.containsKey(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#containsValue(java.lang.Object)
	 */
	@Override
	public boolean containsValue(Object value)
	{
		Map<K, V> domainMap = getDomainMap();
		return domainMap != null && domainMap.containsValue(value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@Override
	public V get(Object key)
	{
		Map<K, V> domainMap = getDomainMap();
		return domainMap != null ? domainMap.get(key) : null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public V put(K key, V value)
	{
		Map<K, V> domainMap = getDomainMap();
		return domainMap != null ? domainMap.put(key, value) : null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public V remove(Object key)
	{
		Map<K, V> domainMap = getDomainMap();
		return domainMap != null ? domainMap.remove(key) : null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends K, ? extends V> m)
	{
		Map<K, V> domainMap = getDomainMap();
		if (domainMap != null)
			domainMap.putAll(m);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#clear()
	 */
	@Override
	public void clear()
	{
		Map<K, V> domainMap = getDomainMap();
		if (domainMap != null)
			domainMap.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#keySet()
	 */
	@Override
	public Set<K> keySet()
	{
		Map<K, V> domainMap = getDomainMap();
		return domainMap != null ? domainMap.keySet() : null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#values()
	 */
	@Override
	public Collection<V> values()
	{
		Map<K, V> domainMap = getDomainMap();
		return domainMap != null ? domainMap.values() : null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#entrySet()
	 */
	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet()
	{
		Map<K, V> domainMap = getDomainMap();
		return domainMap != null ? domainMap.entrySet() : null;
	}
}