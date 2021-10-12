import java.util.*;
/**
 * The Student class represents a student in a student administration system.
 * It holds the student details relevant in our context.
 * 
 * @author Michael Kölling and David Barnes
 * Modified by Liam Smith
 * @version 1.0 06/10/2021
 */
public class Student
{
    // the student ID
    public int id;
    // the student's full name
    public String name;
    // The course the student is enrolled on
    public Course course;
    // The marks awarded for the modules on the course
    private ArrayList<ModuleMark> marks;
    
    public String moduleCode;
    public String moduleTitle;
    public int value;
    public Module module;
    
    public ModuleMark moduleMark;
    
    /**
     * This constructor creates a new student with a
     * fixed name and id. 
     */
    public Student()
    {
        this("Liam", 22011764);
    }
    
    /**
     * Create a new student with a given name and ID number.
     */
    public Student(String name, int id)
    {
        this.name = name;
        this.id = id;
        
        marks = new ArrayList<ModuleMark>();
        
    }

    public void addMark(ModuleMark mark)
    {
        this.marks.add(mark);
        
    }
    
    /**
     * Find the module by the moduleCode and
     * set its mark to the value
     */
    public void awardMark(String moduleCode, int value)
    {
        for(int i=0; i < course.modules.size(); i++){
            if(moduleCode.equals(course.modules.get(i).code)){
                
                this.module = course.modules.get(i);
                
                this.moduleTitle = course.modules.get(i).title;
                
                //Find the module in course.models array
                
                for(int k=0; k < marks.size(); k++){
                    if(marks.get(k).module.title.equals(this.module.title)){

                        this.moduleMark = marks.get(k);
                        
                        marks.remove(marks.get(k));
                        
                        moduleMark.setMark(value);
                        
                        addMark(this.moduleMark);
                        
                        //Find module in marks added array. remove old mark. add new mark.  
                    }
                }
            }
        }
    }
    
    /**
     * Set the student's course
     */
    public void enrol(Course course)
    {
        this.course = course;
        //awardTestMarks();
    }
    
    /**
     * Award a different pass mark for each of the
     * modules on the enrolled course
     */
    public void awardTestMarks(ModuleMark module, int value)
    {
        for(int i=0; i < course.modules.size(); i++){
            ModuleMark moduleMark = new ModuleMark(course.modules.get(i));
            moduleMark.setMark(value);
            }
        //if(module1 == course.modules.get(0)){
            //ModuleMark moduleMark = new ModuleMark(course.modules.get(i));
            //moduleMark.setMark(value);
            //} 
    }
      
    /**
     * Return the full name of this student.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Return the student ID of this student.
     */
    public int getID()
    {
        return id;
    }

        
    /**
     * Print the student's name and ID number to the 
     * output terminal.
     */
    public void print()
    {
        System.out.println(" Student ID: " + id + ", " + name);
    }
    
    public void printCourse()
    {
        this.print();
        course.print();
    }
    
    public void printModules()
    {
        int j=1;
        for(int i=0; i < course.modules.size(); i++){
        System.out.println(" Module " + j + ": " + course.modules.get(i).code
        + ": " + course.modules.get(i).title);
        j++;
    }
    }
    
    public void printTranscript()
    {
        System.out.println(" ------------------------------------");
        System.out.println(" App21-02: Exam Board Transcript 2021");
        System.out.println("        by student name");
        System.out.println(" ------------------------------------");
        
        printCourse();
        
        System.out.println();
        System.out.println();
        System.out.println(" ---- \t -------------------- \t ------\t ---- \t -----");
        System.out.println(" Code \t Module \t\tCredit\t Mark \t Grade");
        System.out.println(" ---- \t -------------------- \t ------\t ---- \t -----");
        System.out.println(course.modules.get(0).code);
        System.out.println(course.modules.get(1).code);
        System.out.println(course.modules.get(2).code);
        System.out.println(course.modules.get(3).code);
        
       
        Grades finalGrade = course.calculateGrade(marks);
        
        System.out.println();
        System.out.println();
        
        if(finalGrade == Grades.NS)
        {
            System.out.println(" No Final Course Grade Yet!");
        }
        else
        {
            System.out.println(" Final Course Grade = " + finalGrade);
        }
    }
}