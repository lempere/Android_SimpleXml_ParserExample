package org.androidconnect.simplexmlparser;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class LaMevaIp {

	@Element
	public String utc;
	@Element
	public String ip;
	@Element
	public String country;	
	@Element
	public String agent;
	@Element
	public String copy;
	
}
