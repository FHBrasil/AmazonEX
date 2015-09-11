/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.hering.storefront.forms;

import br.hering.facades.wishlist.data.HeringWishlistEntryData;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.ListUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 *
 * @author flieger
 */
public class UpdateWishlistForm
{

    private List<HeringWishlistEntryData> entriesList;
    private List<String> desireds;
    private String publicName;
    private String name;
    private String description;
    private Date birthday;
    private Integer daysLeft = 0;
    private String viewBirthday;

    public UpdateWishlistForm()
    {
        entriesList = ListUtils.lazyList(new ArrayList<HeringWishlistEntryData>(), FactoryUtils.instantiateFactory(HeringWishlistEntryData.class));
        desireds = ListUtils.lazyList(new ArrayList<String>(), FactoryUtils.instantiateFactory(String.class));
    }

    public List<HeringWishlistEntryData> getEntriesList()
    {
        return entriesList;
    }

    public void setEntriesList(List<HeringWishlistEntryData> entriesList)
    {
        this.entriesList = entriesList;
    }

    public String getPublicName()
    {
        return publicName;
    }

    public void setPublicName(String publicName)
    {
        this.publicName = publicName;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public List<String> getDesireds()
    {
        return desireds;
    }

    public void setDesireds(List<String> desireds)
    {
        this.desireds = desireds;
    }

    public Date getBirthday()
    {
        return stringToDate();
    }

    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
        this.setViewBirthday(dateToString());
    }

    public String getViewBirthday()
    {
        return viewBirthday;
    }

    public void setViewBirthday(String viewBirthday)
    {
        this.viewBirthday = viewBirthday;
    }

    public Integer getDaysLeft()
    {
        return daysLeft();
    }

    public void setDaysLeft(Integer daysLeft)
    {
        this.daysLeft = daysLeft;
    }

    public void entryToDesireds(List<HeringWishlistEntryData> entries)
    {

        for (HeringWishlistEntryData entryData : entries)
        {

            desireds.add(String.valueOf(entryData.getDesired()));

        }

    }

    public void desiredsToEntry()
    {

        for (int i = 0; i < desireds.size(); i++)
        {

            if (getIntegerForString(desireds.get(i)) != null)
            {
                entriesList.get(i).setDesired(Integer.parseInt(desireds.get(i)));
            } else
            {
                entriesList.get(i).setDesired(1);
            }
        }

    }

    protected Integer getIntegerForString(final String value)
    {
        if (value != null && !value.isEmpty())
        {
            try
            {
                return Integer.valueOf(value);
            } catch (final Exception ignore)
            {
                // Ignore
            }
        }

        return null;
    }

    protected Integer daysLeft()
    {

        Date currentDate = new Date(System.currentTimeMillis());

        SimpleDateFormat current = new SimpleDateFormat("dd/MM/yyyy");
        String resultCurrent = current.format(currentDate);
        String[] currentDates = resultCurrent.split("/");

        SimpleDateFormat birth = new SimpleDateFormat("dd/MM/yyyy");
        String resultbirthday = birth.format(this.getBirthday());
        String[] birthDates = resultbirthday.split("/");


        DateTime begin = new DateTime(Integer.parseInt(currentDates[2]), Integer.parseInt(currentDates[1]), Integer.parseInt(currentDates[0]), 0, 0, 0);
        DateTime end = new DateTime(Integer.parseInt(currentDates[2]), Integer.parseInt(birthDates[1]), Integer.parseInt(birthDates[0]), 13, 30, 0, 0);

        if (Days.daysBetween(begin, end).getDays() > 0)
        {

            return Days.daysBetween(begin, end).getDays();

        } else
        {

            begin = new DateTime(Integer.parseInt(currentDates[2]), Integer.parseInt(currentDates[1]), Integer.parseInt(currentDates[0]), 0, 0, 0);
            end = new DateTime(Integer.parseInt(currentDates[2]) + 1, Integer.parseInt(birthDates[1]), Integer.parseInt(birthDates[0]), 13, 30, 0, 0);

            return Days.daysBetween(begin, end).getDays();
        }
    }

    protected String dateToString()
    {

        SimpleDateFormat current = new SimpleDateFormat("dd/MM/yyyy");
        return current.format(this.birthday);

    }

    protected Date stringToDate()
    {

        SimpleDateFormat current = new SimpleDateFormat("dd/MM/yyyy");
        try
        {
            return current.parse(viewBirthday);
        } catch (ParseException ex)
        {
            return birthday;
        }

    }
}
