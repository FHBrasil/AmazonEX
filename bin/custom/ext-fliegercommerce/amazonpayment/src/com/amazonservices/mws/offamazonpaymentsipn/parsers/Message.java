package com.amazonservices.mws.offamazonpaymentsipn.parsers;

import com.amazonservices.mws.offamazonpaymentsipn.NotificationsException;
import com.amazonservices.mws.offamazonpaymentsipn.notificationmetadata.INotificationMetadata;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * This class was modified/hacked to work with gson version 2.2.4 instead of 1.4
 * @author herbert
 *
 */
public class Message
{
  private Map<String, String> parsedMessage;
  private INotificationMetadata metadata;
  private static final String InvalidJsonErrMsg = "Error with message - content is not in json format";
  private static final String MissingMandatoryFieldErrMsg = "Error with message - mandatory field %s cannot be found";
  private static final String FieldNotDateErrMsgFormatString = "Error with message - expected field %s to be Date object";
  private static final Gson gson = new Gson();
  private static final Type type = new TypeToken<Map<String, String>>() {}.getType();
  private static final SimpleDateFormat UtcDateTimeWithMillisecondsFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
  private static final SimpleDateFormat UtcDateTimeWithoutMillisecondsFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
  
  public Message(String msg)
    throws NotificationsException
  {
    try
    {
      parsedMessage = gson.fromJson(msg, type);
    }
    catch (Exception localException)
    {
      throw new NotificationsException(InvalidJsonErrMsg, localException);
    }
  }
  
  public INotificationMetadata getMetadata()
  {
    return metadata;
  }
  
  public void setMetadata(INotificationMetadata paramINotificationMetadata)
  {
    metadata = paramINotificationMetadata;
  }
  
  public String getMandatoryField(String name)
    throws NotificationsException
  {
    String str = getField(name);
    if (str == null) {
      throw new NotificationsException(String.format(MissingMandatoryFieldErrMsg, new Object[] { name }));
    }
    return str;
  }
  
  public Date getMandatoryFieldAsDate(String name)
    throws NotificationsException
  {
    String str = getField(name);
    if (str == null) {
      throw new NotificationsException(String.format(MissingMandatoryFieldErrMsg, new Object[] { name }));
    }
    try
    {
      return UtcDateTimeWithMillisecondsFormatter.parse(str);
    }
    catch (ParseException e1)
    {
      try
      {
        return UtcDateTimeWithoutMillisecondsFormatter.parse(str);
      }
      catch (ParseException e2)
      {
        throw new NotificationsException(String.format(FieldNotDateErrMsgFormatString, new Object[] { name }), e1);
      }
    }
  }
  
  public String getField(String name)
    throws NotificationsException
  {
    return parsedMessage.get(name);
  }
}