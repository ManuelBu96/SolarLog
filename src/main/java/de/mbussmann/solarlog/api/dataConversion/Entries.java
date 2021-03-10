/*
Copyright 2020 - 2021 Christoph Kohnen

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package de.mbussmann.solarlog.api.dataConversion;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * This class includes the static functions to list all timestamps/days/months in one day/month/year
 * @author Christoph Kohnen
 * @since 0.0.1
 */
public class Entries {
    /**
     * Get all timestamps in which SolarLog saves data. This is every five minutes (0,5,10,15,20,25,30,35,40,45,50,5)
     * @param day The day of which you want to get all timestamps of
     * @return All timestamps in which SolarLog saves data of one day
     */
    public static List<Date> getEntriesPerDay(Date day) {
        List<Date> entries = new ArrayList<>();
        try {
            int min = 0;
            int hour = 0;

            String date = new SimpleDateFormat("yyyyMMdd").format(GetStartOf.day(day));

            for(int i = 0; i <288; i++){
                min = min + 5;
                if(min == 60) {
                    hour++;
                    min = 0;
                }
                @SuppressWarnings("SpellCheckingInspection") DateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                StringBuilder timestamp = new StringBuilder();
                timestamp.append(date);
                if(String.valueOf(hour).length() == 1) {
                    timestamp.append("0").append(hour);
                } else {
                    timestamp.append(hour);
                }
                if(String.valueOf(min).length() == 1) {
                    timestamp.append("0").append(min);
                } else {
                    timestamp.append(min);
                }
                timestamp.append("00");
                Date d = formatter.parse(timestamp.toString());
                entries.add(d);
            }
        } catch (ParseException e) {
            //This should never happen unless you are somehow before 1970 or after 2038 but THEN there are other more urgent problems, duh
            e.printStackTrace();
        }
        return entries;
  }

    /**
     * Get all timestamps in which SolarLog saves data
     * @return All timestamps in which SolarLog saves data
     */
  public static List<String> timestamps() {
        List<String> entries = new ArrayList<>();

        int min = 0;
        int hour = 0;

        for(int i = 0; i <288; i++) {
            min = min + 5;
            if (min == 60) {
                hour++;
                min = 0;
            }
            StringBuilder timestamp = new StringBuilder();
            if (String.valueOf(hour).length() == 1) {
                timestamp.append("0").append(hour).append(":");
            } else {
                timestamp.append(hour).append(":");
            }
            if (String.valueOf(min).length() == 1) {
                timestamp.append("0").append(min);
            } else {
                timestamp.append(min);
            }
            entries.add(timestamp.toString());
        }
        return entries;
      }

    /**
     * Get all days in one specific month
     * @param month The month of which you want to obtain all days from
     * @return All days in the specified month
     */
      public static List<Date> getDaysPerMonth(YearMonth month) {
          List<Date> dateList = new ArrayList<>();

          Calendar cal = Calendar.getInstance();
          cal.set(Calendar.MONTH, month.getMonthValue());
          cal.set(Calendar.DAY_OF_MONTH, 1);
          cal.set(Calendar.YEAR, month.getYear());
          int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
          for (int i = 1; i < maxDay; i++)
          {
              cal.set(month.getYear(),(month.getMonthValue() - 1) ,i,0,0,0);
              dateList.add(cal.getTime());
          }
          return dateList;
      }

    /**
     * Get all months in one specific year
     * @param year The year of which you want to obtain all months from
     * @return All months in the specified year
     */
      public static List<YearMonth> getMonthsPerYear(Year year) {
        List<YearMonth> yearMonths = new ArrayList<>();
        for(int i = 1; i < 13; i++) {
            yearMonths.add(year.atMonth(i));
        }
        return yearMonths;
      }
  }
