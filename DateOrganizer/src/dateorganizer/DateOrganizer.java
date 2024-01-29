package dateorganizer;

/**
 * A testbed for a binary heap implementation of a priority queue using 
 * various comparators to sort Gregorian dates
 * @author Duncan, YOUR NAME
 * @see Date, PQueueAPI, PQueue
 * <pre>
 * Date: 99-99-9999
 * Course: csc 3102
 * File: DateOrganizer.java
 * Instructor: Dr. Duncan
 * </pre>
 */
 
import java.io.IOException;
import java.util.Scanner;
import java.io.FileReader;
import java.util.Comparator; 

public class DateOrganizer
{
    /**
     * Gives the integer value equivalent to the day of the
     * week of the specified date 
     * @param d a date on the Gregorian Calendar
     * @return 0->Sunday, 1->Monday, 2->Tuesday, 3->Wednesday,
     * 4->Thursday, 5->Friday, 6->Saturday; otherwise, -1
     */
    public static int getDayNum(Date d)
    {
        String dayOfWeek = "";
        dayOfWeek = d.getDayOfWeek();
        
        switch(dayOfWeek)
        {
        case "Sunday":
        	return 0;
        case "Monday":
        	return 1;
        case "Tuesday":
        	return 2;
        case "Wednesday":
        	return 3;
        case "Thursday":
        	return 4;
        case "Friday":
        	return 5;
        case "Saturday":
        	return 6;
        default:
        	return -1;
        }
        
    }
	public static void main(String[] args) throws IOException, PQueueException
    {
        String usage = "DateOrganizer <date-file-name> <sort-code>%n";
        usage += "sort-code: -2 -month-day-year%n";
        usage += "           -1 -year-month-day%n";
        usage += "            0 +weekDayNumber+monthName+day+year%n";
        usage += "            1 +year+month+day%n";
        usage += "            2 +month+day+year";
        if (args.length != 2)
        {
            System.out.println("Invalid number of command line arguments");
            System.out.printf(usage+"%n%");
            System.exit(1);
        }
        String fileName = args[0];
        int sortCode = Integer.parseInt(args[1]);
        
        Comparator<Date> comparator = null;
        
        switch(sortCode)
        {
        case -2:
        	comparator = (d1,d2) ->
        	{
        		int monthComparator = Integer.compare(d2.getMonth(), d1.getMonth());
        		if (monthComparator != 0)
        		{
        			return monthComparator;
        		}
        		
        		int dayComparator = Integer.compare(d2.getDay(), d1.getDay());
        		if (dayComparator != 0)
        		{
        			return dayComparator;
        		}
        		
        		return Integer.compare(d2.getYear(), d1.getYear());
        	};
        	break;
        	
        case -1:
        	comparator = (d1,d2) ->
        	{
        		int yearComparator = Integer.compare(d2.getYear(), d1.getYear());//year
        		if (yearComparator != 0)//year
        		{
        			return yearComparator;//year
        		}
        		
        		int monthComparator = Integer.compare(d2.getMonth(), d1.getMonth());//month
        		if (monthComparator != 0)//month
        		{
        			return monthComparator;//month
        		}
        		
        		return Integer.compare(d2.getDay(), d1.getDay());
        	};
        	break;
        	
        case 0:
        	comparator = (d1,d2) ->
        	{
        		int dayOfWeekComparator = Integer.compare(getDayNum(d1), getDayNum(d2));
        		if (dayOfWeekComparator != 0)//dayofweek
        		{
        			return dayOfWeekComparator;
        		}
        		
        		int monthComparator = Integer.compare(d1.getMonth(), d2.getMonth());
        		if (monthComparator != 0)//month
        		{
        			return monthComparator;
        		}
        		
        		int dayComparator = Integer.compare(d2.getDay(), d1.getDay());
        		if (dayComparator != 0)//day
        		{
        			return dayComparator;
        		}
        		
        		return Integer.compare(d1.getYear(), d2.getYear());
        	};
        	break;
        	
        case 1:
        	comparator = (d1,d2) ->
        	{
        		int yearComparator = Integer.compare(d1.getYear(), d2.getYear());
        		if (yearComparator != 0)//year
        		{
        			return yearComparator;
        		}
        		
        		int monthComparator = Integer.compare(d1.getMonth(), d2.getMonth());
        		if (monthComparator != 0)//month
        		{
        			return monthComparator;
        		}
        		
        		return Integer.compare(d1.getDay(), d2.getDay());
        	};
        	break;
        	
        case 2:
        	comparator = (d1,d2) ->
        	{
        		int monthComparator = Integer.compare(d1.getMonth(), d2.getMonth());
        		if (monthComparator != 0)
        		{
        			return monthComparator;
        		}
        		
        		int dayComparator = Integer.compare(d1.getDay(), d2.getDay());
        		if (dayComparator != 0)
        		{
        			return dayComparator;
        		}
        		
        		return Integer.compare(d1.getYear(), d2.getYear());
        	};
        	break;
        	
        	default:
        		System.out.println("Invalid Input");
        		System.exit(1);
        		
        		PQueue<Date> dateQueue = new PQueue<>(comparator);
        		
        		try(Scanner inFile = new Scanner(new FileReader(fileName)))
        		{
        			while (inFile.hasNextLine())
        			{
        				String line = inFile.nextLine();
        				String[] parts = line.split("/");
        				if (parts.length == 3)
        				{
        					int month = Integer.parseInt(parts[0]);
        					int day = Integer.parseInt(parts[1]);
        					int year = Integer.parseInt(parts[2]);
        					
        					Date date = new Date(month, day, year);
        					dateQueue.insert(date);
        				}
        				else
        				{
        					System.err.println("Skip invalid format: " + line);
        				}
        			}
        		}
        		
        		if (sortCode == -2)
        		{
        			System.out.printf("Dates from %s in -month-day-year Order:\n\n", fileName);
        		}
        		if (sortCode == -1)
        		{
        			System.out.printf("Dates from %s in -year-month-day Order:\n\n", fileName);
        		}
        		if (sortCode == 0)
        		{
        			System.out.printf("Dates from %s in +weekDayNumber+monthName+day+year Order:\n\n", fileName);
        		}
        		if (sortCode == 1)
        		{
        			System.out.printf("Dates from %s in +year+month+day Order:\n\n", fileName);
        		}
        		if (sortCode == 2)
        		{
        			System.out.printf("Dates from %s in +month+day+year Order:\n\n", fileName);
        		}
        		
        		while (!dateQueue.isEmpty())
        		{
        			Date date = dateQueue.remove();
        			String dayOfWeek = date.getDayOfWeek();
        			String monthName = date.getMonthName();
        			
        			int day = date.getDay();
        			int year = date.getYear();
        			
        			String dateFormatter = String.format("%s, %s, %d, %d", dayOfWeek, monthName, day, year);
        			System.out.println(dateFormatter);
        		}
        		
        }
    }
}
