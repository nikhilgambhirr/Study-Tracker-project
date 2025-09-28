import java.util.*;
import java.time.LocalDate;
import java.io.*;

    ///////////////////////////////////////////////////////////////////////
    //
    //  Class Name :       StudyLog
    //  Description :      Holds individual study log record
    //  Author :           Nikhil Kailas Gambhir
    //  Date :             25/09/2025
    //
    ///////////////////////////////////////////////////////////////////////
class StudyLog
{
    public LocalDate Date;
    public String Subject;
    public double Duration;
    public String Descrption;

    //////////////////////////////////////////////////////////////////////
    //
    //  Function Name :     StudyLog (Constructor)
    //  Description :        Initializes study log with provided details
    //  Input :              LocalDate, Subject name, Duration, Description
    //  Output :             Object of StudyLog created
    //
    ///////////////////////////////////////////////////////////////////////


    public StudyLog(LocalDate A, String B, double C, String D)
    {
        this.Date = A;
        this.Subject = B;
        this.Duration = C;
        this.Descrption = D;
    }

    ///////////////////////////////////////////////////////////////////////
    //
    //  Function Name :     toString
    //  Description :        Returns formatted string representation of StudyLog
    //  Input :              None
    //  Output :             String (date | subject | duration | description)
    //
    ///////////////////////////////////////////////////////////////////////

    @Override
    public String toString()
    {
        return Date+" | "+Subject+" | "+Duration+" | "+Descrption;
    }
    ///////////////////////////////////////////////////////////////////////
    //
    //  Function Name :     getDate
    //  Description :        Returns date of study log
    //
    ///////////////////////////////////////////////////////////////////////
    public LocalDate getDate()
    {
        return Date;
    }
    ///////////////////////////////////////////////////////////////////////
    //
    //  Function Name :     getSubject
    //  Description :        Returns subject of study log
    //
    ///////////////////////////////////////////////////////////////////////   

    public String getSubject()
    {
        return Subject;
    }
    ///////////////////////////////////////////////////////////////////////
    //
    //  Function Name :     getDuration
    //  Description :        Returns study duration
    //
    ///////////////////////////////////////////////////////////////////////

    public double getDuration()
    {
        return Duration;
    }
    ///////////////////////////////////////////////////////////////////////
    //
    //  Function Name :     getDescription
    //  Description :        Returns description text of study log
    //
    ///////////////////////////////////////////////////////////////////////

    public String getDescription()
    {
        return Descrption;
    }
}
    ///////////////////////////////////////////////////////////////////////
    //
    //  Class Name :       StudyTracker
    //  Description :      Provides functionalities to manage study logs
    //  Author :           Nikhil Kailas Gambhir
    //  Date :             25/09/2025
    //
    ///////////////////////////////////////////////////////////////////////

class StudyTracker
{
    // Data structure to hold the data about study 
    private ArrayList <StudyLog> Database = new ArrayList <StudyLog> ();
    ///////////////////////////////////////////////////////////////////////
    //
    //  Function Name :     InsertLog
    //  Description :        Takes input from user and inserts a new study log
    //  Input :              Subject, Duration, Description (from user)
    //  Output :             New record inserted into Database
    //
    ///////////////////////////////////////////////////////////////////////

    public void InsertLog()
    {
        Scanner ScanerObj = new Scanner(System.in);

        System.out.println("----------------------------------------------------");
        System.out.println("--- Please enter the valid details of your study ---");
        System.out.println("----------------------------------------------------");

        LocalDate DateObj = LocalDate.now();

        System.out.println("Please provide the name of subject like C/C++/Java/OS/DS");
        String sub = ScanerObj.nextLine();

        System.out.println("Enter the time period if your study in hours");
        double dur = ScanerObj.nextDouble();
        ScanerObj.nextLine();

        System.out.println("Please provide the description about the study for future referecne");
        String desc = ScanerObj.nextLine();
        
        StudyLog StudyObj = new StudyLog(DateObj,sub,dur,desc);

        Database.add(StudyObj);

        System.out.println("Study Log gets stored succesfully");
        System.out.println("----------------------------------------------------");
    }
    ///////////////////////////////////////////////////////////////////////
    //
    //  Function Name :     DisplayLog
    //  Description :        Displays all study logs stored in Database
    //  Input :              None
    //  Output :             Console prints all logs
    //
    ///////////////////////////////////////////////////////////////////////

    public void DisplayLog()
    {
        System.out.println("----------------------------------------------------");
       
        if(Database.isEmpty())
        {
            System.out.println("Nothing to display as database is empty");
            System.out.println("----------------------------------------------------");
            return;
        }

        System.out.println("x----------------------------------------------------x");
        System.out.println("---- Log report from Marvellous Study Tracker ------");
        System.out.println("x----------------------------------------------------x");
        
        for(StudyLog sobj : Database)
        {
            System.out.println(sobj);
        }
        System.out.println("x----------------------------------------------------x");
    }
    ///////////////////////////////////////////////////////////////////////
    //
    //  Function Name :     ExportCSV
    //  Description :        Exports all study logs into CSV file
    //  Input :              None
    //  Output :             Creates CSV file (MarvellousStudy.csv)
    //
    ///////////////////////////////////////////////////////////////////////

    public void ExportCSV()
    {
        if(Database.isEmpty())
        {
            System.out.println("x----------------------------------------------------x");
            System.out.println("Nothing to export as database is empty");
            System.out.println("x----------------------------------------------------x");
            return;
        }

        String FileName = "MarvellousStudy.csv";

        // Create new CSV file
        try(FileWriter fwobj = new FileWriter(FileName))
        {
            // Write CSV header
            fwobj.write("Date,Subject,Duration,Description\n");

            // Travel database
            for(StudyLog sobj : Database)
            {
                // Write each record in CSV
                fwobj.write(sobj.getDate() + ","+
                            sobj.getSubject().replace(","," ") + ","+
                            sobj.getDuration() + ","+
                            sobj.getDescription().replace(","," ") + "\n" 
                );
            }

            System.out.println("Log created Succesfully");
        }
        catch(Exception eobj)
        {
            System.out.println("Exception occured while creating the CSV.");
            System.out.println("Report this issue to Marvellous Infosystems");
        }
    }
    ///////////////////////////////////////////////////////////////////////
    //
    //  Function Name :     SummaryByDate
    //  Description :        Displays summary of total study time grouped by date
    //  Input :              None
    //  Output :             Prints date-wise total hours
    //
    ///////////////////////////////////////////////////////////////////////
    public void SummaryByDate()
    {
        System.out.println("x----------------------------------------------------x");
       
        if(Database.isEmpty())
        {
            System.out.println("Nothing to display as database is empty");
            System.out.println("x----------------------------------------------------x");
            return;
        }

        System.out.println("x----------------------------------------------------x");
        System.out.println("x-- Summary By Date from Marvellous Study Tracker ---x");
        System.out.println("x----------------------------------------------------x");
        
        TreeMap <LocalDate, Double> tobj = new TreeMap <LocalDate, Double> ();

        LocalDate lobj = null;
        double d, old;

        for(StudyLog sobj : Database)
        {
            lobj = sobj.getDate();
            d = sobj.getDuration();

            if(tobj.containsKey(lobj))
            {
                old = tobj.get(lobj);
                tobj.put(lobj,d+old);
            }
            else
            {
                tobj.put(lobj,d);
            }
        }

        // Display the details as per date
        for(LocalDate ldobj : tobj.keySet())
        {
            System.out.println("Date : "+ldobj+" Total Study "+tobj.get(ldobj));
        }

        System.out.println("x----------------------------------------------------x");
    }
     ///////////////////////////////////////////////////////////////////////
    //
    //  Function Name :     SummaryBySubject
    //  Description :        Displays summary of total study time grouped by subject
    //  Input :              None
    //  Output :             Prints subject-wise total hours
    //
    ///////////////////////////////////////////////////////////////////////

    public void SummaryBySubject()
    {
        System.out.println("x----------------------------------------------------x");
       
        if(Database.isEmpty())
        {
            System.out.println("Nothing to display as database is empty");
            System.out.println("x----------------------------------------------------x");
            return;
        }

        System.out.println("x----------------------------------------------------x");
        System.out.println(" Summary By Subject from Marvellous Study Tracker ");
        System.out.println("x----------------------------------------------------x");
        
        TreeMap <String, Double> tobj = new TreeMap <String, Double> ();

        double d, old;
        String s;

        for(StudyLog sobj : Database)
        {
            s = sobj.getSubject();
            d = sobj.getDuration();

            if(tobj.containsKey(s))
            {
                old = tobj.get(s);
                tobj.put(s,d+old);
            }
            else
            {
                tobj.put(s,d);
            }
        }

        // Display the details as per subject
        for(String str : tobj.keySet())
        {
            System.out.println("Subject : "+str+" Total Study "+tobj.get(str));
        }

        System.out.println("x----------------------------------------------------x");
    }
}

///////////////////////////////////////////////////////////////////////
//
//  Class Name :       Study_tracker (Starter Class)
//  Description :      Entry point of Marvellous Study Tracker Application
//  Author :           Nikhil Kailas Gambhir
//  Date :             25/09/2025
//
///////////////////////////////////////////////////////////////////////

class Study_tracker    // StudyTrackerStarter
{

    ///////////////////////////////////////////////////////////////////////
    //
    //  Function Name :     main
    //  Description :        Provides menu-driven program to access features
    //  Input :              Command line arguments
    //  Output :             Runs StudyTracker application
    //
    ///////////////////////////////////////////////////////////////////////

    public static void main(String A[])
    {
        StudyTracker stobj = new StudyTracker();

        Scanner ScanerObj = new Scanner(System.in);

        int iChoice = 0;

        System.out.println("----------------------------------------------------");
        System.out.println("--Welcome to Marvellous Study Tracker Application --");
        System.out.println("----------------------------------------------------");

        do
        {
            System.out.println("Please select the appropriate option");
            System.out.println("1 : Insert new Study Log into Databse");
            System.out.println("2 : View All Study Logs");
            System.out.println("3 : Summary of Study Log by Date");
            System.out.println("4 : Summary of Study Log by Subject");
            System.out.println("5 : Export Study Log to CSV file");
            System.out.println("6 : Exit the application");

            iChoice = ScanerObj.nextInt();

            switch(iChoice)
            {
                case 1:     // Insert new Study Log into Databse
                    stobj.InsertLog();
                    break;

                case 2:     // View All Study Logs
                    stobj.DisplayLog();
                    break;
                
                case 3:     // Summary of Study Log by Date
                    stobj.SummaryByDate();
                    break;
                
                case 4:     // Summary of Study Log by Subject
                    stobj.SummaryBySubject();
                    break;
                
                case 5:     // Export Study Log to CSV file
                    stobj.ExportCSV();
                    break;
                
                case 6:     // Exit the application
                    System.out.println("----------------------------------------------------");
                    System.out.println("Thank you for using Marvellous Study Log application");
                    System.out.println("----------------------------------------------------");
                    break;

                default:
                    System.out.println("Please enter the valid option");
            }
            
        }while(iChoice != 6);

    }

}
