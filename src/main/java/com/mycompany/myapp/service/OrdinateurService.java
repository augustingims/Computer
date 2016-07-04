package com.mycompany.myapp.service;


import net.sf.jmimemagic.*;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.List;

@Service
public class OrdinateurService {

	public String checkMime(byte[] img){
		Magic parser = new Magic() ;
		MagicMatch match;
		try {
			match = parser.getMagicMatch(img);
		} catch (MagicParseException e) {
			e.printStackTrace();
			return "application/jpg";
		} catch (MagicMatchNotFoundException e) {
			e.printStackTrace();
			return "application/jpg";
		} catch (MagicException e) {
			e.printStackTrace();
			return "application/jpg";
		}
		return match.getMimeType();
	}

}
