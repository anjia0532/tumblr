package com.anjia.tumblr.types;

/**
 * This class represents an individual statement in a ChatPost
 * @author jc
 */
public class Dialogue {

    private String name;
    private String label;
    private String phrase;

    /**
     * Get the name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the label
     * @return label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Get the phrase
     * @return the phrase
     */
    public String getPhrase() {
        return phrase;
    }

	public void setName(String name) {
		this.name = name;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setPhrase(String phrase) {
		this.phrase = phrase;
	}

}
