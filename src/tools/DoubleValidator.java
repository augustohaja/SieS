package tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DoubleValidator {
//(?:\d*\.)?\d+
	private Pattern pattern;
	private Matcher matcher;
	private static final String DOUBLE_PATTERN = "[+-]?\\d*\\.?\\d+";


	public DoubleValidator() {
		pattern = Pattern.compile(DOUBLE_PATTERN);
	}

	public boolean validate(final String valor) {		 
	     matcher = pattern.matcher(valor);
	     return matcher.matches();
	}
}
