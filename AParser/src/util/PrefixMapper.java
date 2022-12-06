package util;

import javax.xml.bind.annotation.XmlTransient;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

@XmlTransient
public class PrefixMapper extends NamespacePrefixMapper {
    private HashMap<String, String> mappings = new LinkedHashMap();

    public PrefixMapper() {
        this.setDefaultMappings();
    }

    protected void setDefaultMappings() {
        this.clear();
        this.addMapping("http://www.ftn.uns.ac.rs/patent", "pat");
        this.addMapping("http://www.w3.org/2001/XMLSchema-instance", "xsi");
        this.addMapping("http://java.sun.com/xml/ns/jaxb", "jaxb");
    }

    public void addMapping(String uri, String prefix) {
        this.mappings.put(uri, prefix);
    }

    public String getMapping(String uri) {
        return this.mappings.get(uri);
    }

    public HashMap<String, String> getMappings() {
        return this.mappings;
    }

    public void clear() {
        this.mappings.clear();
    }

    public String getPreferredPrefix(String namespaceURI, String suggestion, boolean requirePrefix) {
        String preferredPrefix = this.getMapping(namespaceURI);
        return preferredPrefix != null ? preferredPrefix : suggestion;
    }
}
