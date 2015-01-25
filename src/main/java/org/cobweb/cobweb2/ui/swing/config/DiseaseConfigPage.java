package org.cobweb.cobweb2.ui.swing.config;

import org.cobweb.cobweb2.plugins.disease.DiseaseParams;
import org.cobweb.io.ChoiceCatalog;
import org.cobweb.swingutil.ColorLookup;

/**
 * Configuration page for Disease
 */
public class DiseaseConfigPage extends TableConfigPage<DiseaseParams> {

	public DiseaseConfigPage(DiseaseParams[] params, ChoiceCatalog catalog, ColorLookup agentColors) {
		super(params, "Disease Parameters", catalog, agentColors);
	}
}
