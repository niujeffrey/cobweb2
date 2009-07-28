package disease;

import ga.GeneticParams.Phenotype;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import cobweb.params.AbstractReflectionParams;
import cobweb.params.ConfDisplayName;
import cobweb.params.ConfXMLTag;
import cwcore.complexParams.AgentFoodCountable;

public class DiseaseParams extends AbstractReflectionParams {

	private static final long serialVersionUID = 6866958975246266955L;

	@ConfXMLTag("Index")
	public int type;

	@ConfXMLTag("initialInfection")
	@ConfDisplayName("Initially infected fraction")
	public float initialInfection;

	@ConfDisplayName("Transmit to")
	public boolean[] transmitTo;

	/**
	 * Contact transmission rate
	 */
	@ConfXMLTag("contactTransmitRate")
	@ConfDisplayName("Contact transmission rate")
	public float contactTransmitRate;

	@ConfXMLTag("childTransmitRate")
	@ConfDisplayName("Child transmission rate")
	public float childTransmitRate;
	
	@ConfXMLTag("parameter")
	@ConfDisplayName("Parameter")
	public Phenotype param;

	@ConfXMLTag("factor")
	@ConfDisplayName("Factor")
	public float factor = 2;

	private final AgentFoodCountable env;

	private static final Pattern agentTransmit = Pattern.compile("^agent(\\d+)$");

	public DiseaseParams(AgentFoodCountable env) {
		this.env = env;
		transmitTo = new boolean[env.getAgentTypes()];
		initialInfection = 0.0f;
		contactTransmitRate = 0.5f;
		childTransmitRate = 1;
		this.param = new Phenotype();
	}

	@Override
	public void loadConfig(Node root) throws IllegalArgumentException {
		super.loadConfig(root);

		transmitTo = new boolean[env.getAgentTypes()];

		NodeList nl = root.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Node n = nl.item(i);
			if (!n.getNodeName().equals("transmitTo"))
				continue;
			NodeList nl2 = n.getChildNodes();
			for (int j = 0; j < nl2.getLength(); j++) {
				Node tt = nl2.item(j);
				String name = tt.getNodeName();

				Matcher m = agentTransmit.matcher(name);
				if (!m.matches())
					continue;

				int id = Integer.parseInt(m.group(1)) - 1;
				boolean transmit = Boolean.parseBoolean(tt.getTextContent());
				transmitTo[id] = transmit;
			}
		}
	}

	@Override
	public void saveConfig(Node root, Document document) {
		super.saveConfig(root, document);

		Node trans = document.createElement("transmitTo");
		for (int i = 0; i < transmitTo.length; i++) {
			Node n = document.createElement("agent" + (i + 1));
			n.setTextContent(Boolean.toString(transmitTo[i]));
			trans.appendChild(n);
		}
		root.appendChild(trans);

	}

}
