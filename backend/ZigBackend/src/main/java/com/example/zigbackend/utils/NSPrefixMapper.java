package com.example.zigbackend.utils;
import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

import javax.xml.bind.annotation.XmlTransient;
import java.util.HashMap;
import java.util.LinkedHashMap;

@XmlTransient
public class NSPrefixMapper extends NamespacePrefixMapper {

	private HashMap<String, String> mappings;

	public NSPrefixMapper() { 
		
		mappings = new LinkedHashMap<String, String>(); 
		setDefaultMappings(); 
	}

	protected void setDefaultMappings() { 
		
		clear();

		addMapping("http://www.ftn.uns.ac.rs/zig", "sz");
		addMapping("http://www.w3.org/2001/XMLSchema-instance", "xsi"); 
		addMapping("http://java.sun.com/xml/ns/jaxb", "jaxb"); 
	}

	public void addMapping(String uri, String prefix){
		mappings.put(uri, prefix);
	} 
	
	public String getMapping(String uri){
		return (String)mappings.get(uri);
	} 
	public HashMap<String, String> getMappings(){
		return mappings;
	} 
	public void clear(){
		mappings.clear();
	}

	public String getPreferredPrefix(String namespaceURI, String suggestion, boolean requirePrefix) { 
		String preferredPrefix = getMapping(namespaceURI); 
		if(preferredPrefix != null)
			return preferredPrefix;
		return suggestion; 
	} 

}