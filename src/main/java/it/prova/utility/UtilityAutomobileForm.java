package it.prova.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

//nel nome della classe vi è Libro in quanto è una classe specifica
public class UtilityAutomobileForm {

	public static boolean validateInput(String marcaInputParam, String modelloInputParam,
			String cilindrataInputStringParam, String dataImmStringParam) {
		// prima controlliamo che non siano vuoti
		if (StringUtils.isBlank(marcaInputParam) || StringUtils.isBlank(modelloInputParam)
				|| !NumberUtils.isCreatable(cilindrataInputStringParam) || StringUtils.isBlank(dataImmStringParam)) {
			return false;
		}
		return true;
	}

	public static Date parseDateImmatricolazioneFromString(String dataImmStringParam) {
		if (StringUtils.isBlank(dataImmStringParam))
			return null;

		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dataImmStringParam);
		} catch (ParseException e) {
			return null;
		}
	}

}
