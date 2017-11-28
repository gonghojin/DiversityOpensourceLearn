package com.websystique.springbatch;


import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.joda.time.LocalDate;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

	@Override
	public String marshal(LocalDate v) throws Exception {
		return v.toString();
	}

	@Override
	public LocalDate unmarshal(String v) throws Exception {
		return new LocalDate(v);
	}

}
