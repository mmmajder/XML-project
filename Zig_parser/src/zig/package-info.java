@XmlSchema(
        namespace = "http://www.ftn.uns.ac.rs/zig",
        elementFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED,
        xmlns = {@XmlNs(prefix = "sz", namespaceURI="http://www.ftn.uns.ac.rs/zig"),
                @XmlNs(prefix="xs", namespaceURI="http://www.w3.org/2001/XMLSchema")}
)
package zig;

import javax.xml.bind.annotation.XmlSchema;
import javax.xml.bind.annotation.XmlNs;