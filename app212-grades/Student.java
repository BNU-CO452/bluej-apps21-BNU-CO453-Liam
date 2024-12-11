import java.util.*;
/**
 * The Student class represents a student in a student administration system.
 * It holds the student details relevant in our context.
 * 
 * @author Michael Kölling and David Barnes
 * Modified by Liam Smith
 * @version 1.1 16/10/2021
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
    protected ArrayList<ModuleMark> marks;
    // the code of a module
    public String moduleCode;
    // the title of a module
    public String moduleTitle;
    // value used to set a mark for a module
    public int value;
    // used for searching modules of this type
    public Module module;
    // used for ammending module marks of this type
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
        
        // creates a marks array
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
        //Find the module in course.models array
        // get the code of the module
        // get the title of the module
        for(int i=0; i < course.modules.size(); i++){
            if(moduleCode.equals(course.modules.get(i).code)){
                
                this.module = course.modules.get(i);
                
                this.moduleTitle = course.modules.get(i).title;
                
                // find the title of the module associated with the module mark
                // remove the original mark
                // set the value of the module mark
                for(int k=0; k < marks.size(); k++){
                    if(marks.get(k).module.title.equals(this.module.title)){

                        this.moduleMark = marks.get(k);
                        
                        marks.remove(marks.get(k));
                        
                        moduleMark.setMark(value);
                        
                        addMark(this.moduleMark);
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
        this.moduleMark = null;
        
        if(marks.size() == 0){
                System.out.println();
                System.out.println(" No marks have been added for this course");
        }
            
        for(int i = 0; i < marks.size(); i++){
                
            if(marks.get(i).equals(module)){  
                    
            this.moduleMark = marks.get(i);

            moduleMark.setMark(value);
                
            }
        }   
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
        if(course == null){
            System.out.println(" The Student is not enrolled on a course");
        }
        
        else {
            this.print();
            course.print();
        }
    }
    
    /**
     * Prints a list of modules for the course that the student has been enrolled on
     */
    public void printModules()
    {
        // checks that the student has been enrolled on a course
        if(course == null){
            System.out.println(" The Student is not enrolled on a course");
        }
        
        // checks if a student has been enrolled on a course but no modules have been added
        if(course != null && course.modules.size() == 0){
            System.out.println(" There are no modules added for this course");
        }
        
        else {
            int j=1;
            for(int i=0; i < course.modules.size(); i++){
            System.out.println(" Module " + j + ": " + course.modules.get(i).code
            + ": " + course.modules.get(i).title);
            j++;
            }
        }
    }
    
    /**
     * Prints a transcript of the students information: course, modules, marks etc
     */
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
        System.out.println(" Code \t Module \t\t Credit  Mark \t Grade");
        System.out.println(" ---- \t -------------------- \t ------\t ---- \t -----");
        
        // checks if the student is enrolled on a course or if the course has modules added
        if(course == null || course.modules.size() == 0){
            System.out.println(" There are no modules to display");
        }
        
        // checks if the marks array is not empty
        if(marks.size() == 0){
            System.out.println(" There are no marks to display");
        }
         
        else {
            for(int i=0; i < course.modules.size(); i++){
                System.out.println(" " + course.modules.get(i).code + " \t " + course.modules.get(i).title + 
                "\t " + marks.get(i).getCredit() + " \t " + marks.get(i).mark + " \t " +
                course.convertToGrade(marks.get(i).mark));  
            }
            
            Grades finalGrade = course.calculateGrade(marks);
        
            System.out.println();
            System.out.println();
        
            if(finalGrade == Grades.NS){
                System.out.println(" No Final Course Grade Yet!");
            }
            else{
                System.out.println(" Final Course Grade = " + finalGrade);
            }
        }      
    }
}