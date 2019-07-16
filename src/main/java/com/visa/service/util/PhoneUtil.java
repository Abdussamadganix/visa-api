package com.visa.service.util;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.visa.service.exception.BadRequestException;
import org.springframework.util.StringUtils;

/**
 * @author Abdussamad
 */
public class PhoneUtil {

  /**
   * This function takes a phone number and represents it in full international format eliminating
   * leading 0s.
   */
  public static String normalizePhone(String phone) throws BadRequestException {

    PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
    String errorMessage = "Phone number is not valid";
    if (StringUtils.isEmpty(phone)) {
      return null;
    }
    try {
      Phonenumber.PhoneNumber phoneNumber = phoneUtil.parse(phone, "NG");
      String normalizedNumber = String.valueOf(phoneNumber.getCountryCode())
          + String.valueOf(phoneNumber.getNationalNumber());
      if (normalizedNumber.length() != 13) {
        throw new BadRequestException(errorMessage);
      }
      return normalizedNumber;
    } catch (NumberParseException e) {
      throw new BadRequestException(errorMessage);
    }
  }
}
